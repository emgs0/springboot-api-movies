package com.emgs0.movies.service;

import com.emgs0.movies.model.domain.Movie;
import com.emgs0.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Class
 * This service is where all de business logics go in the API.
 * Uses the class MovieRepository, and talks to the database
 */
@Service
public class MovieService {
    @Autowired //instantiate this class
    private MovieRepository movieRepository;
        /**
         * this method talks with the DB.
         * using the MovieRepository instance, it will get the list all the elements.
         * @return List<Movie> (All elements in DB)
         */
    public List<Movie> allMovies(){
        return movieRepository.findAll();
    }
         /*
          this method talks with the DB
          It's an Optional<Movie> Because it could return a null object, in other ways it could launch an Exception
          @param id: It's a parameter received by the controller, it comes via URL
          @return Optional<Movie> (Movie object/ null object)
          public Optional<Movie> singleMovie(ObjectId id){
               return movieRepository.findById(id);
          }
          it's no longer used because we need to look for the imdbId no the ID from DB
         */

     /**
     * this method talks with the DB
     * It's an Optional<Movie> Because it could return a null object, in other ways it could launch an Exception
     * @param imdbId It's a parameter received by the controller, it comes via URL
     * @return Optional<Movie> (Movie object/ null object)
     */
    public Optional<Movie> singleMovie(String imdbId){
        return movieRepository.findMovieByImdbId(imdbId);
    }
}
