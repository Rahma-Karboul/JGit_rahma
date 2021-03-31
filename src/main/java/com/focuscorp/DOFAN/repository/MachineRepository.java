package com.focuscorp.DOFAN.repository;

import com.focuscorp.DOFAN.model.EType;
import com.focuscorp.DOFAN.model.Machine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MachineRepository extends MongoRepository<Machine, String> {

    Machine findByType(EType type);
}
