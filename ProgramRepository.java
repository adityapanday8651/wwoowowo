package com.wowment.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wowment.mongo.Program;


@Repository
public interface ProgramRepository extends MongoRepository<Program, String> {
	
}
