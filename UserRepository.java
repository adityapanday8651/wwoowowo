package com.wowment.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wowment.mongo.User;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
}
