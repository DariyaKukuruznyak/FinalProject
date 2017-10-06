package dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql.MySqlEventDaoImpl;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.eventbuilder.EventBuilder;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.user.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class MySqlEventDaoTest {

    private EventDao eventDao;

    @Before
    public void init() throws SQLException {
        eventDao = MySqlEventDaoImpl.getInstance();
    }

    @Test
    public void getInstance() throws Exception {
    }

    @Test
    public void getById() throws Exception {
        eventDao.getById(2L);
    }

    @Test
    public void getAll() throws Exception {
        eventDao.getAll();
    }
    @Test
    public void getAllByBookerId() throws Exception {
        System.out.println( eventDao.getAllByBookmakerId(7L));
    }
    @Test
    public void add() throws Exception {
        Tournament tournament = new Tournament();
        tournament.setId(2L);
        User bookmaker = new User();
        bookmaker.setId(7L);
        Event event = new EventBuilder()
                .buildTournament(tournament)
                .buildBookmaker(bookmaker)
                .build();
        eventDao.add(event);
    }

    @Test
    public void update() throws Exception {
        Event event = eventDao.getById(2L);
        System.out.println(event);
        eventDao.update(event);
    }

    @Test
    public void delete() throws Exception {
        eventDao.delete(2L);
    }

}

