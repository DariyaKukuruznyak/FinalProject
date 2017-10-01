package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.event.Event;

public interface EventDao extends AbstractDao<Event> {
   Event getEventByTournamentId(Long id);
}
