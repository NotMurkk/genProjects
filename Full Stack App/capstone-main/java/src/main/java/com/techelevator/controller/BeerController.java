package com.techelevator.controller;

import com.techelevator.dao.JdbcBeerDao;
import com.techelevator.model.Beer;
import com.techelevator.model.FavoriteAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/beer")
public class BeerController {

    private final JdbcBeerDao beerDao;

    public BeerController(JdbcBeerDao beerDao) {
        this.beerDao = beerDao;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Beer> getAll() {
        return beerDao.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public boolean create(@RequestBody @Valid Beer newBeer) {
        return beerDao.createBeer(newBeer);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/delete/{beerId}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable Long beerId) {
        return beerDao.deleteBeer(beerId);
    }

    @RequestMapping(path = "/favorites/{username}/all", method = RequestMethod.GET)
    public List<Beer> getFavorites(@PathVariable String username) {
        return beerDao.getFavorites(username);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/favorites/{username}/add/{beerName}", method = RequestMethod.POST)
    public boolean addFavorite(@PathVariable String username, @PathVariable String beerName) {
        if (!beerDao.favoriteExists(username, beerName)) {
            return beerDao.addFavorite(username, beerName);
        }
        throw new FavoriteAlreadyExistsException();
    }

    @RequestMapping(path = "/favorites/{username}/remove/{beerName}", method = RequestMethod.DELETE)
    public boolean removeFavorite(@PathVariable String username, @PathVariable String beerName) {
        return beerDao.removeFavorite(username, beerName);
    }
}
