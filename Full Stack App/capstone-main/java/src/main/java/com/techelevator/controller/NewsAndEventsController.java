package com.techelevator.controller;

import com.techelevator.dao.JdbcNewsAndEventsDao;
import com.techelevator.model.NewsAndEvents;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/newsAndEvents")
public class NewsAndEventsController {

    JdbcNewsAndEventsDao newsAndEventsDao;

    public NewsAndEventsController(JdbcNewsAndEventsDao newsAndEventsDao) {
        this.newsAndEventsDao = newsAndEventsDao;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<NewsAndEvents> getAll () {
        return newsAndEventsDao.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public boolean create(@RequestBody @Valid NewsAndEvents event) {
        return newsAndEventsDao.create(event);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/delete/{eventId}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable Long eventId) {
        return newsAndEventsDao.delete(eventId);
    }
}
