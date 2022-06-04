package com.techelevator.dao;

import com.techelevator.model.NewsAndEvents;

import java.util.List;

public interface NewsAndEventsDao {

    List<NewsAndEvents> getAll();

    boolean create(NewsAndEvents event);

    boolean delete(Long eventId);
}
