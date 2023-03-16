package test;

import org.example.dao.DaoFactory;
import org.example.dao.UserDao;
import org.example.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDaoTest {

    private UserDao userDao;

    @Before
    public void setUp(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        this.userDao = context.getBean("userDao", UserDao.class);
    }

    @Test
    public void addAndGet() throws ClassNotFoundException, SQLException {

        userDao.deleteAll();
        assertEquals(userDao.getCount(), 0);

        User user = new User();
        user.setId("gyumee");
        user.setName("박성철");
        user.setPassword("springno1");

        userDao.add(user);

        User findUser = userDao.get(user.getId());

        assertEquals(user.getName(), findUser.getName());
        assertEquals(user.getPassword(), findUser.getPassword());

        User user2 = new User("re1125", "김태우", "springnew");
        userDao.add(user2);

        User findUser2 = userDao.get(user2.getId());
        assertEquals(user2.getName(), findUser2.getName());
        assertEquals(user2.getPassword(), findUser2.getPassword());

    }

    @Test(expected = SQLException.class)
    public void getUserFailure() throws ClassNotFoundException, SQLException {
        userDao.deleteAll();
        assertEquals(userDao.getCount(), 0);


        userDao.get("unknown");
    }


    @Test
    public void count() throws ClassNotFoundException, SQLException {
        userDao.deleteAll();
        assertEquals(userDao.getCount(), 0);

        User user = new User();
        user.setId("1");
        user.setName("김");
        user.setPassword("springno1");

        userDao.add(user);
        assertEquals(userDao.getCount(), 1);

        User user2 = new User();
        user2.setId("2");
        user2.setName("김");
        user2.setPassword("springno1");

        userDao.add(user2);
        assertEquals(userDao.getCount(), 2);


        User user3 = new User();
        user3.setId("3");
        user3.setName("김");
        user3.setPassword("springno1");

        userDao.add(user3);
        assertEquals(userDao.getCount(), 3);


    }
}