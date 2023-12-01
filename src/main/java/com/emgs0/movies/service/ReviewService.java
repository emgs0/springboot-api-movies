package com.emgs0.movies.service;

import com.emgs0.movies.model.domain.Movie;
import com.emgs0.movies.model.domain.Review;
import com.emgs0.movies.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service Class
 * This service is where all de business logics go in the API.
 * Uses the class ReviewRepository, and talks to the database
 */
@Service
public class ReviewService {

    //this it's basic repository

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * this class its other way to talk to the DB instead of doing with a Repository, this could help to
     * more complex operations than Repository can't do, and this works better for custom queries
     */
    @Autowired //instantiate this class
    private MongoTemplate mongoTemplate;


    /**
     * This method will look for a imdbId, and it will create a review, this review will be assigned to the
     * imdbId found.
     * @param reviewBody This parameter will create a review
     * @param imdbId This parameter will be used to look for a movie using it,
     * @return review, this review is connected directly to the imdbId.
     */
    public Review createReview(String reviewBody, String imdbId){
        /**
         * to associate a reviewBody to the object Review and insert the reviewBody in the DB as an intermediary
         * layer between Service class and DB, and assign the datetime to the Model and DB as string
         */

        Review review = reviewRepository.insert(new Review(reviewBody, LocalDateTime.now()));

        /*
        to associate the reviewBody to a movie
        UPDATE:     Each movie on our DB, has an array of reviewsId, we want to update that every time that a user write
                    a review, so we need to update a Movie class, to push te review to the DB.

        MATCHING:   We are updating a movie, based on the imdbId, so we need to match the imdbId that exist on our DB
                    with the imdbId sent by the user and received in our API.

        APPLY:      We need to apply the changes on our DB, its like a COMMIT in python, at this point we create the
                    query, that query its update the reviewId (that is pushed), and their value (sent by the user and
                    received as a reviewBody) with the first (and only) matching imdbId
        */

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();
    return review;
    }
}
