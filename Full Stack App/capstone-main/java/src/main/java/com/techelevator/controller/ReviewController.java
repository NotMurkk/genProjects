package com.techelevator.controller;

import com.techelevator.dao.JdbcReviewDao;
import com.techelevator.model.Review;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/review")
public class ReviewController {

    JdbcReviewDao reviewDao;

    public ReviewController(JdbcReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Review> getAll() {
        return reviewDao.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public boolean create(@RequestBody @Valid Review newReview) {
        return reviewDao.create(newReview);
    }

    @RequestMapping(path = "/delete/{reviewId}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable Long reviewId) {
        return reviewDao.delete(reviewId);
    }
}
