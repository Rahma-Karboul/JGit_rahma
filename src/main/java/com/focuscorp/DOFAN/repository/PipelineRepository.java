package com.focuscorp.DOFAN.repository;


import com.focuscorp.DOFAN.model.Pipeline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PipelineRepository extends CrudRepository<Pipeline, String> {
    //List<Pipeline> findByProjectId(int projectId);
}
