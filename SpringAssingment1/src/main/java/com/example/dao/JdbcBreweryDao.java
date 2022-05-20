package com.example.dao;

import com.example.model.Brewery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcBreweryDao implements BreweryDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcBreweryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Brewery> getAll() {
        List<Brewery> breweries = new ArrayList<>();
        String sql = "SELECT * FROM brewery;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            breweries.add(mapRowToBrewery(results));
        }
        return breweries;
    }

    @Override
    public boolean create(Brewery newBrewery) {
        String sql = "INSERT INTO brewery (owner_id, name, address, description, image) " +
                "VALUES (?, ?, ?, ?, ?);";
        int rowsUpdated = jdbcTemplate.update(sql, newBrewery.getOwnerId(), newBrewery.getName(), newBrewery.getAddress(), newBrewery.getDescription(), newBrewery.getImage());
        return rowsUpdated > 0;
    }

    @Override
    public List<Brewery> getFavorites(String username) {
        List<Brewery> favorites = new ArrayList<>();
        String sql = "SELECT * FROM brewery " +
                "JOIN favorite_brewery ON brewery.brewery_id = favorite_brewery.brewery_id " +
                "JOIN users ON favorite_brewery.user_id = users.user_id " +
                "WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        while (results.next()) {
            favorites.add(mapRowToBrewery(results));
        }
        return favorites;
    }

    @Override
    public boolean addFavorite(String breweryName, String username) {
        String sql = "INSERT INTO favorite_brewery (user_id, brewery_id) " +
                "VALUES (" +
                "(SELECT user_id FROM users WHERE username = ?), " +
                "(SELECT brewery_id FROM brewery WHERE name = ?)" +
                ");";
        int rowsUpdated = jdbcTemplate.update(sql, username, breweryName);
        return rowsUpdated > 0;
    }

    private Brewery mapRowToBrewery(SqlRowSet rowSet) {
        Brewery brewery = new Brewery();
        brewery.setBreweryId(rowSet.getLong("brewery_id"));
        brewery.setOwnerId(rowSet.getLong("owner_id"));
        brewery.setName(rowSet.getString("name"));
        brewery.setAddress(rowSet.getString("address"));
        brewery.setDescription(rowSet.getString("description"));
        brewery.setImage(rowSet.getString("image"));
        return brewery;
    }
}

