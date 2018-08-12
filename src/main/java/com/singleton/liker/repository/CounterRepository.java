package com.singleton.liker.repository;

import com.singleton.liker.domain.Counter;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
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
public class CounterRepository {

    private final MongoTemplate mongoTemplate;

    /**
     * Increment likes. The reason why this method implemented through criteria API is avoidance
     * to rely to optimistic locking mechanism of spring data JPA, we rely to mongoDB atomicity
     * isolation instead to avoid concurrency issues.
     *
     * Also we have to avoid phantom read through mongodb upsert mechanism.
     *
     * @param playerId Id of player to like
     */
    public void like(String playerId) {
        Update update = new Update().inc("likes", 1);
        mongoTemplate.upsert(getQuery(playerId), update, Counter.class);
    }

    /**
     * Get or create record. This is used instead just using standard CRUD JPA repository to avoid
     * phantom read
     *
     * @param playerId Id of player to get
     * @return Counter structure with two fields: player id and like count
     */
    public Counter get(String playerId) {
        Update update = new Update().setOnInsert("likes", 0);
        return mongoTemplate.findAndModify(getQuery(playerId),
                                           update,
                                           FindAndModifyOptions.options()
                                                               .upsert(true)
                                                               .returnNew(true),
                                           Counter.class);
    }

    private Query getQuery(String playerId) {
        return new Query().addCriteria(Criteria.where("_id").is(playerId));
    }
}
