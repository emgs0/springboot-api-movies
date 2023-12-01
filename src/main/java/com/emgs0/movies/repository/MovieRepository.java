package com.emgs0.movies.repository;

import com.emgs0.movies.model.domain.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface in the Data Access Layer, it's the contract to know what the service will do
 * in this case it will take mongoRepository properties, using a class Movie or an ObjectId
 */
@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
    /**
     * This method is created for look for in DB with the imdbId as string, to ensure the security of data for the IDs
     * because MongoRepository it's capable to do only with Object Movie or ObjectId by default and create dynamic
     * Queries.
     * SpringData MongDB will know what to do only with the name of the Method, so the framework do the query,
     * we only send the parameter custom, and it will return a Optional<Movie>
     * @param imdbId Custom parameter to look for in the DB, in this case its a String sent in the URL
     * @return
     */
    Optional<Movie> findMovieByImdbId(String imdbId);

}
