package com.miss.foodie.cache;

import com.miss.foodie.utils.ProtoStuffSerializerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * @author:MissYou
 * @CreateTime:2016/11/15:10:28
 * @description: 采用JedisCulster的redis缓存
 */
public class RedisClusterCache {

    //缓存名
    public final static String CACHENAME = "cache";

    //默认没带Key的缓存
    private final static String NO_KEY_CACHE = "noCache";
    //默认缓存时间
    public final static int DEFAULT_CACHETIME = 60;

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 插入缓存
     * @param key
     * @param obj
     * @param <T>
     */
    public <T> void putCache(String key, T obj) {
        final byte[] bkeys = getKeyByte(key);
        final byte[] bvalue =getValueByte(obj);
        jedisCluster.set(bkeys, bvalue);
    }

    /**
     * 带缓存时间的插入
     * @param key
     * @param obj
     * @param expireTime
     * @param <T>
     */
    public <T> void putCacheWithExpireTime(String key, T obj, int expireTime) {
        final byte[] bkyes = getKeyByte(key);
        final byte[] bvalue = getKeyByte(key);
        jedisCluster.setex(bkyes, expireTime, bvalue);
    }

    public <T> void putListCache(String key, List<T> objs) {
        final byte[] bkeys = getKeyByte(key);
        final byte[] bvalue = getValueByte(objs);
        jedisCluster.set(bkeys,bvalue);
    }

    public <T> void putListCacheExpireTime(String key, List<T> objs, int expiretime) {
        final byte[] bkeys = getKeyByte(key);
        final byte[] bvalue = getValueByte(objs);
        jedisCluster.setex(bkeys, expiretime, bvalue);
    }

    /**
     * 获取缓存
     * @param key
     * @param targetClass
     * @param <T>
     * @return
     */
    public <T> T getCache(final String key, Class<T> targetClass) {
        byte[] result = jedisCluster.get(getKeyByte(key));
        return result != null ? ProtoStuffSerializerUtil.deserialize(result, targetClass) : null;
    }

    /**
     * 获取List缓存
     * @param key
     * @param targetClass
     * @param <T>
     * @return
     */
    public <T> List<T> getListCache(final String key, Class<T> targetClass) {
        byte[] resultList = jedisCluster.get(getKeyByte(key));
        return resultList != null ? ProtoStuffSerializerUtil.deserializeList(resultList, targetClass) : null;
    }

    private byte[] getKeyByte(String key) {
        return key != null ? key.getBytes() : NO_KEY_CACHE.getBytes();
    }

    private <T> byte[] getValueByte(T obj) {
        return obj != null ? ProtoStuffSerializerUtil.serialize(obj) : null;
    }
}
