package com.example.dao;

import com.example.model.Beer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcBeerDao implements BeerDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcBeerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Beer> getAll() {
        List<Beer> beers = new ArrayList<>();
        String sql = "SELECT * FROM beer;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            beers.add(mapRowToBeer(results));
        }
        return beers;
    }

    @Override
    public boolean createBeer(Beer newBeer) {
        String sql = "INSERT INTO beer (brewery_id, name, type, abv, description, image) " +
                "VALUES(?, ?, ?, ?, ?, ?);";
        int rowsUpdated = jdbcTemplate.update(sql, newBeer.getBreweryId(), newBeer.getName(), newBeer.getType(), newBeer.getAbv(), newBeer.getDescription(), newBeer.getImage());
        return rowsUpdated > 0;
    }

    @Override
    public List<Beer> getFavorites(String username) {
        List<Beer> favorites = new ArrayList<>();
        String sql = "SELECT * FROM beer " +
                "JOIN favorite_beer ON beer.beer_id = favorite_beer.beer_id " +
                "JOIN users ON favorite_beer.user_id = users.user_id " +
                "WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        while (results.next()) {
            favorites.add(mapRowToBeer(results));
        }
        return favorites;
    }

    @Override
    public boolean addFavorite(String username, String beerName) {
        String sql = "INSERT INTO favorite_beer (user_id, beer_id) " +
                "VALUES (" +
                "(SELECT user_id FROM users WHERE username = ?), " +
                "(SELECT beer_id FROM beer WHERE name = ?)" +
                ");";
        int rowsUpdated = jdbcTemplate.update(sql, username, beerName);
        return rowsUpdated > 0;
    }

    private Beer mapRowToBeer(SqlRowSet rowSet) {
        Beer beer = new Beer();
        beer.setBeerId(rowSet.getLong("beer_id"));
        beer.setBreweryId(rowSet.getLong("brewery_id"));
        beer.setName(rowSet.getString("name"));
        beer.setType(rowSet.getString("type"));
        beer.setAbv(rowSet.getDouble("abv"));
        beer.setDescription(rowSet.getString("description"));
        beer.setImage(rowSet.getString("image"));
        return beer;
    }
}

