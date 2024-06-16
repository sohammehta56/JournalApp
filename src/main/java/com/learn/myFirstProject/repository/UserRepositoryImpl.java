package com.learn.myFirstProject.repository;

import com.learn.myFirstProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * This method should return all the users who have email and sentimentAnalysis set to true
     *
     * @return List<User> - List of users who have email and sentimentAnalysis set to true
     */
    public List<User> getUsersForSentimentAnalysis() {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").exists(true)
                                  .and("sentimentAnalysis").is(true));

        return mongoTemplate.find(query, User.class);
    }
}
