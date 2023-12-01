package com.emgs0.movies.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private ObjectId id;
    private String body;
    private LocalDateTime created;

    /**
     * this Review constructor doesn't exist with lombok due to it does only all or nothing variables
     * @param body this is the string expected, the body of the review
     * @param created this is the string expected, the datetime when its created the review
     */
    public Review(String body, LocalDateTime created) {
        this.body = body;
        this.created = created;
    }
}
