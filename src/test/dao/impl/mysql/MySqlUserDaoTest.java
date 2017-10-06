package dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.UserDao;
import com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql.MySqlUserDaoImpl;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.entity.user.builder.UserBuilder;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class MySqlUserDaoTest {

    private UserDao userDao;

    @Before
    public void init() throws SQLException {
        userDao = MySqlUserDaoImpl.getInstance();
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
    public void getAllByRole() throws Exception {
        System.out.println(userDao.getUsersByRole(UserRole.ADMINISTRATOR.toString()));
    }

    @Test
    public void add() throws Exception {
        User user = new UserBuilder()
                .buildFirstName("ADMINISTRATOR")
                .buildLastName("ADMINISTRATOR")
                .buildEmail("client@gmail.com")
                .buildLogin("ADMINISTRATOR")
                .buildPassword("1111")
                .buildUserRole(UserRole.ADMINISTRATOR)
                .build();
        userDao.add(user);
    }

    @Test
    public void update() throws Exception {
        User user = userDao.getById(19L);
        System.out.println(user);
        user.setPassword("password");
        userDao.update(user);
    }

    @Test
    public void delete() throws Exception {
        User user = userDao.getById(20L);
        userDao.delete(user.getId());
    }

    @Test
    public void findByLogin() throws Exception {
        userDao.getByLogin("0");
    }
}