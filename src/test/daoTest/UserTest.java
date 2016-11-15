package daoTest;

import com.miss.foodie.dao.UserDao;
import com.miss.foodie.entity.User;
import com.miss.foodie.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
//
//    @Autowired
//    private UserService userService;

    @Test
    public void testQueryById() {
        User user = userDao.queryByPhone(1);
        System.out.println(user.toString());
        System.out.println("<============================>");
    }

    @Test
    public void testFindById() {
        User user = userDao.findById(1);
        System.out.println(user != null ? user.toString() : null);
        System.out.println("<=============================>");
    }

    @Test
    public void testGetAll() {
        List<User> users = userDao.queryAll(0,1);
        for (User user : users) {
            System.out.println(user != null ? user.toString() : null);
            System.out.println("<======================>");
        }
    }
//
//    @Test
//    public void testGetAll() {
//        List<User> users = userService.getUserList(0,10);
//        for (User user : users) {
//            System.out.println(user != null ? user.toString() : null);
//            System.out.println("<======================>");
//        }
//    }
}
