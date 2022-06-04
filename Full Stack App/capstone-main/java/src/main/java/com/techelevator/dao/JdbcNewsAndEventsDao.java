package com.techelevator.dao;

import com.techelevator.model.NewsAndEvents;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcNewsAndEventsDao implements NewsAndEventsDao{

    JdbcTemplate jdbcTemplate;
    JdbcBreweryDao breweryDao;

    public JdbcNewsAndEventsDao(JdbcTemplate jdbcTemplate, JdbcBreweryDao breweryDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.breweryDao = breweryDao;
    }

    @Override
    public List<NewsAndEvents> getAll() {
        List<NewsAndEvents> events = new ArrayList<>();
        String sql = "SELECT * FROM news_events;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            events.add(mapRowToNewsAndEvents(results));
        }
        return events;
    }

    @Override
    public boolean create(NewsAndEvents event) {
        String sql = "INSERT INTO news_events (brewery_id, event_name, event_date, event_description) " +
                "VALUES(?, ?, ?, ?);";
        int rowsUpdated = jdbcTemplate.update(sql, event.getBreweryId(), event.getEventName(), event.getEventDate(), event.getEventDescription());
        return rowsUpdated > 0;
    }

    @Override
    public boolean delete(Long eventId) {
        String sql = "DELETE FROM news_events WHERE news_events_id = ?;";
        int rowsDeleted = jdbcTemplate.update(sql, eventId);
        return rowsDeleted == 1;
    }

    private NewsAndEvents mapRowToNewsAndEvents (SqlRowSet rowSet) {
        NewsAndEvents event = new NewsAndEvents();
        event.setNewsEventsId(rowSet.getLong("news_events_id"));
        event.setBreweryId(rowSet.getLong("brewery_id"));
        event.setBreweryName(breweryDao.getNameById(event.getBreweryId()));
        event.setEventName(rowSet.getString("event_name"));
        event.setEventDate(rowSet.getDate("event_date").toLocalDate());
        event.setEventDescription(rowSet.getString("event_description"));
        return event;
    }
}
