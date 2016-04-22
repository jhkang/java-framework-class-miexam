package kr.ac.jejunu.userdao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class UserDaoTest {
    private UserDao getUserDao() {
        ApplicationContext applicationContext = new GenericXmlApplicationContext("daoFactory.xml");
        return (UserDao) applicationContext.getBean("userDao");
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        UserDao userDao = getUserDao();
        Long id = 1L;
        String name = "허윤호";
        String password = "1234";

        User user = userDao.get(id);
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        String name = "강진혁";
        String password = "1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        UserDao userDao = getUserDao();
        Long id = userDao.add(user);

        user = userDao.get(id);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        String name = "강진혁";
        String password = "1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        UserDao userDao = getUserDao();
        Long id = userDao.add(user);

        name = "Harry";
        password = "4567";

        user.setId(id);
        user.setName(name);
        user.setPassword(password);

        userDao.update(user);

        user = userDao.get(id);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }
}
