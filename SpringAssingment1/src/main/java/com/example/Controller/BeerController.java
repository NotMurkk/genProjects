package com.example.Controller;

import com.example.dao.JdbcBeerDao;
import com.example.model.Beer;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public boolean create(@RequestBody Beer newBeer) {
        return beerDao.createBeer(newBeer);
    }

    @RequestMapping(path = "/favorites/{username}/all", method = RequestMethod.GET)
    public List<Beer> getFavorites(@PathVariable String username) {
        return beerDao.getFavorites(username);
    }

    @RequestMapping(path = "/favorites/{username}/add/{beerName}", method = RequestMethod.POST)
    public boolean addFavorite(@PathVariable String username, @PathVariable String beerName) {
        return beerDao.addFavorite(username, beerName);
    }
}
