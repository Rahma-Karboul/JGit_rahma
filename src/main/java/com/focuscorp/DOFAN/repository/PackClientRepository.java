package com.focuscorp.DOFAN.repository;

import com.focuscorp.DOFAN.model.PackClient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PackClientRepository extends MongoRepository<PackClient, String> {

}