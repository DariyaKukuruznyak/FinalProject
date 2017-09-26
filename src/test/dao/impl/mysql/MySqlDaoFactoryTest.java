package dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.impl.mysql.*;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class MySqlDaoFactoryTest {
    @Test
    public void mySqlDaoFactoryNotNull() {
        assertNotNull(MySqlDaoFactory.getInstance());
    }

    @Test
    public void mySqlDaoFactoryIsSingleton() {
        MySqlDaoFactory instance = MySqlDaoFactory.getInstance();
        assertSame(instance, MySqlDaoFactory.getInstance());
    }

    @Test
    public void mySqlBetDaoNotNull() {
        assertNotNull(MySqlBetDao.getInstance());
    }

    @Test
    public void mySqlBetDaoIsSingleton() {
        MySqlBetDao instance = MySqlBetDao.getInstance();
        assertSame(instance, MySqlBetDao.getInstance());
    }

    @Test
    public void mySqlClientDaoNotNull() {
        assertNotNull(MySqlClientDao.getInstance());
    }

    @Test
    public void mySqlClientDaoIsSingleton() {
        MySqlClientDao instance = MySqlClientDao.getInstance();
        assertSame(instance, MySqlClientDao.getInstance());
    }

    @Test
    public void mySqlEventDaoNotNull() throws Exception {
        assertNotNull(MySqlEventDao.getInstance());
    }

    @Test
    public void mySqlEventDaoIsSingleton() {
        MySqlEventDao instance = MySqlEventDao.getInstance();
        assertSame(instance, MySqlEventDao.getInstance());
    }

    @Test
    public void mySqlParticipantDaoNotNull() throws Exception {
        assertNotNull(MySqlParticipantDao.getInstance());
    }

    @Test
    public void mySqlParticipantDaoIsSingleton() {
        MySqlParticipantDao instance = MySqlParticipantDao.getInstance();
        assertSame(instance, MySqlParticipantDao.getInstance());
    }
}