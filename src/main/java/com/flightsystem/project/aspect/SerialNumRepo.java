package com.flightsystem.project.aspect;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SerialNumRepo extends MongoRepository<SerialNumber, String>{
}
