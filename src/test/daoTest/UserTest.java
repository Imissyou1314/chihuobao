package daoTest;

import com.miss.foodie.dao.UserDao;
import com.miss.foodie.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author:MissYou
 * @CreateTime:2016/11/14:16:41
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class UserTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void testQueryById() {
        User user = userDao.queryByPhone(1);
        System.out.println(user.toString());
        System.out.println("<============================>");
    }

}
