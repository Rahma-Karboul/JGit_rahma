package com.focuscorp.DOFAN.repository;

import com.focuscorp.DOFAN.model.EOffer;
import com.focuscorp.DOFAN.model.Pack;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PackRepository extends MongoRepository<Pack, String> {

    Pack findByOffer(EOffer offer);
}
