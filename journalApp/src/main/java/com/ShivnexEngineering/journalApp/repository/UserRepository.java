package com.ShivnexEngineering.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ShivnexEngineering.journalApp.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId>{

	User findByUserName(String userName);
	
	void deleteByUserName(String userName);
	
}
