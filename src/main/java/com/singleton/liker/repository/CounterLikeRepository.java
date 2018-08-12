package com.singleton.liker.repository;

import com.singleton.liker.domain.Counter;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


/**
 * Custom repository to provide separate from crud custom methods to avoid implementation of all
 * crud functionality
 */
@Repository
@AllArgsConstructor
public class CounterLikeRepository {

    private final MongoTemplate mongoTemplate;

    /**
     * Increment likes. The reason why this method implemented through criteria API is avoidance
     * to rely to optimistic locking mechanism of spring data JPA, we rely to mongoDB atomicity
     * isolation instead to avoid concurrency issues.
     *
     * @param playerId Id of player to like
     */
    public void like(String playerId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(playerId));
        Update update = new Update().inc("likes", 1);
        mongoTemplate.findAndModify(query, update, Counter.class);
    }
}
