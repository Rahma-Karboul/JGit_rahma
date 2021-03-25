package com.focuscorp.DOFAN.repository;


import com.focuscorp.DOFAN.model.Pipeline;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PipelineRepository extends CrudRepository<Pipeline, String> {
    //List<Pipeline> findByProjectId(int projectId);
}
