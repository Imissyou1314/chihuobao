package com.miss.foodie.cache;

import com.miss.foodie.utils.ProtoStuffSerializerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Set;

/**
 * @author:MissYou
 * @CreateTime:2016/11/15:10:05
 * @description:Redis缓存
 */
@Component
public class RedisCache {
    //缓存名
    public final static String CACHENAME = "cache";

    //默认没带Key的缓存
    private final static String NO_KEY_CACHE = "noCache";
    //默认缓存时间
    public final static int DEFAULT_CACHETIME = 60;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private byte[] getKeyByte(String key) {
        return key != null ? key.getBytes() : NO_KEY_CACHE.getBytes();
    }

    private <T> byte[] getValueByte(T obj) {
        return obj != null ? ProtoStuffSerializerUtil.serialize(obj) : null;
    }

    public <T> boolean putCache(final String key, final T obj) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.setNX(getKeyByte(key), getValueByte(obj));
            }
        });
        return result;
    }

    public <T> Boolean putCacheWithExpireTime(final String key, final T obj , final Long expiretime) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.setEx(getKeyByte(key), expiretime, getValueByte(obj));
                return true;
            }
        });
    }

    public <T> Boolean putListCache(final String key, final List<T> objs) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.setNX(getKeyByte(key), getValueByte(objs));
            }
        });
    }

    public <T> Boolean putListCacheWithExpiretime(final String key, final List<T> objs, final int expiretime) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.setEx(getKeyByte(key), expiretime, getValueByte(objs));
                return true;
            }
        });
    }

    public <T> T getCache(final String key, Class<T> targetClass) {
        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.get(getKeyByte(key));
            }
        });
        return result != null ? ProtoStuffSerializerUtil.deserialize(result, targetClass): null;
    }

    public <T> List<T> getListCache(final String key, Class<T> targetClass) {
        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.get(getKeyByte(key));
            }
        });
        return result != null ? ProtoStuffSerializerUtil.deserializeList(result, targetClass) : null;
    }

    /**
     *精准的删除Key
     * @param key
     */
    public void deleteCache(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 模糊的删除Key
     * @param pattern
     */
    public void deleteCacheWithPatter(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }

    /**
     * 清空所有缓存
     */
    public void clearCache() {
        deleteCacheWithPatter(RedisCache.CACHENAME + "|*");
    }
}
