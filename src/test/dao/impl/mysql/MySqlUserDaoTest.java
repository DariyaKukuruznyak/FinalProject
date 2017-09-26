package dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.UserDao;
import com.kukuruznyak.bettingcompany.dao.impl.mysql.MySqlUserDao;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.entity.user.builder.UserBuilder;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class MySqlUserDaoTest {
    private UserDao userDao;

    @Before
    public void init() throws SQLException {
        userDao = MySqlUserDao.getInstance();
    }

    @Test
    public void getInstance() throws Exception {
    }

    @Test
    public void getById() throws Exception {
        User user = userDao.getById(20L);
        System.out.println(user);
    }

    @Test
    public void getAll() throws Exception {
       userDao.getAll();
    }

    @Test
    public void add() throws Exception {
        User user = new UserBuilder()
                .buildFirstName("Ivan")
                .buildLastName("Domodedov")
                .buildEmail("vanya@gmail.com")
                .buildLogin("11111")
                .buildPassword("1111")
                .buildSecurityNumber("1111")
                .buildUserRole(UserRole.CLIENT)
                .build();
        userDao.add(user);
    }

    @Test
    public void update() throws Exception {
        User user = userDao.getById(20L);
        System.out.println(user);
        user.setUserRole(UserRole.ADMINISTRATOR);
        userDao.update(user);
        user = userDao.getById(20L);
        System.out.println(user);
    }

    @Test
    public void delete() throws Exception {
        User user = userDao.getById(20L);
        userDao.delete(user.getId());
    }

    @Test
    public void findByLogin() throws Exception {
        User user = userDao.getByLogin("11111");
        System.out.println(user);
    }

}