package com.emgs0.movies.controller;

import com.emgs0.movies.model.domain.Movie;
import com.emgs0.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
/**
 * Controller class, in a layer of the API.
 * Only concern itself to have the task about getting and request from the user
 * and returning a response.
 * Uses the serviceClass MovieService
 */
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
            /*
            to test if the requestMapping works, like a return in php using ajax
            @GetMapping
            public String allMovies(){
                return "All movies";
            }
            @GetMapping
            public ResponseEntity<String> getAllMovies(){
                return new ResponseEntity<String>("all movies!", HttpStatus.OK);
                //same case like the top example, but using entities and status HTTP
            }
            */
    @Autowired
    private MovieService movieService;

    /**
     * This method delegate the task of fetching all the movies from MongoDB and giving back
     * to the API Layer.
     * 1- It call the allMovies Method inside on the Service.
     * 2- Gets the list of the movie.
     * 3- Return them with a status OK
     * @return ResponseEntity<List<Movie>> with all the movies and a HTTP status code.
     */
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<List<Movie>>(movieService.allMovies(),HttpStatus.OK);
    }
    /**
     * this method delegate the task of fetching only one movie from MongoDB and giving back
     * to the API Layer.
     * Due to could return an empty Movie, this ResponseEntity its <Optional<Movie>>
     * @param imdbId This parameter it's a String sent via URL
     * 1- It call the singleMovie Method inside on the Service.
     * 2- Gets the movie.
     * 3- Return it with a status OK
     */
    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String imdbId){
        return new ResponseEntity<Optional<Movie>>(movieService.singleMovie(imdbId),HttpStatus.OK);
    }


}
