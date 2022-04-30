package com.flightsystem.project.aspect;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddRequestsRepo extends MongoRepository<AddRequestDoc,Long>{
}
