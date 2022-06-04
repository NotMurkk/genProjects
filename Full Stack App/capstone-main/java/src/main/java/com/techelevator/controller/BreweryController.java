package com.techelevator.controller;

import com.techelevator.dao.JdbcBreweryDao;
import com.techelevator.model.Brewery;
import com.techelevator.model.FavoriteAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/brewery")
public class BreweryController {

    private final JdbcBreweryDao breweryDao;

    public BreweryController(JdbcBreweryDao breweryDao) {
        this.breweryDao = breweryDao;
    }

    @RequestMapping(path = "/all" , method = RequestMethod.GET)
    public List<Brewery> getAll() {
        return breweryDao.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public boolean create(@RequestBody @Valid Brewery newBrewery) {
        return breweryDao.create(newBrewery);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/delete/{breweryId}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable Long breweryId) {
        return breweryDao.deleteBrewery(breweryId);
    }

    @RequestMapping(path = "/favorites/{username}/all", method = RequestMethod.GET)
    public List<Brewery> getFavorites(@PathVariable String username) {
        return breweryDao.getFavorites(username);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/favorites/{username}/add/{breweryName}", method = RequestMethod.POST)
    public boolean addFavorite(@PathVariable String username, @PathVariable String breweryName) {
        if (!breweryDao.favoriteExists(username, breweryName)) {
            return breweryDao.addFavorite(breweryName, username);
        }
        throw new FavoriteAlreadyExistsException();
    }

    @RequestMapping(path = "/favorites/{username}/remove/{breweryName}", method = RequestMethod.DELETE)
    public boolean removeFavorite(@PathVariable String username, @PathVariable String breweryName) {
        return breweryDao.removeFavorite(username, breweryName);
    }
}
