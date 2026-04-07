package com.ShivnexEngineering.journalApp.repository;

import com.ShivnexEngineering.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/*

    Mongo Template :-

        User Repository using Criteria not QueryMethodDSL.

*/

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getSpecificUserWithJournalEntries(){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is("Shiwank Kumar"));
        query.addCriteria(Criteria.where("journalEntries").exists(true).not().size(0));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }

    // getting all users that have email and sentiments as true
    // Here we are making use of criteria, not writing direct query methods as in UserRepository.
    public List<User> getIfEmailExistAndSentimentIsTrue(){
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(
                    Criteria.where("email").regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"),
                    Criteria.where("sentimentAnalysis").is(true)
                );
        query.addCriteria(criteria);
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }

}
