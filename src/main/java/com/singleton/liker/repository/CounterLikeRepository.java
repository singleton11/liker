package com.singleton.liker.repository;

import com.singleton.liker.domain.Counter;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CounterLikeRepository {

    private final MongoTemplate mongoTemplate;

    public void like(String playerId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(playerId));
        Update update = new Update().inc("likes", 1);
        mongoTemplate.findAndModify(query, update, Counter.class);
    }
}
