package com.emgs0.movies.controller;

import com.emgs0.movies.model.domain.Review;
import com.emgs0.movies.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller class, in a layer of the API.
 * Only concern itself to have the task about getting and request from the user
 * and returning a response.
 * Uses the serviceClass ReviewService
 */
@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {
    /**
     * Instance of the class
     */
    @Autowired
    private ReviewService reviewService;

    /**
     * this method receives a payload, and we Map it (Map<String,String>) the first string it's a key and the second one
     * it's the value, in this case the whole review created by the user will be the key and the value will be the imdbId
     * because this could be repeated but the review hardly will do, and then we will send it to the API Layer.
     *
     * @param payload Its a Map<String, String>that we request in the POST method
     * @return ResponseEntity<Review>, it'll return the review body, imdbId as an entity Review and status Created (201)
     */
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload){
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"),payload.get("imdbId")), HttpStatus.CREATED);
    }

}
