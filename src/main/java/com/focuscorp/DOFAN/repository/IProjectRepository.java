package com.focuscorp.DOFAN.repository;

import com.focuscorp.DOFAN.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProjectRepository extends MongoRepository<Project,String> {

    Project findProjectByProjectName(String projectName);
    Optional<Project> findProjectByProjectDescriptionContaining(String keyword);
    /*Long countPieplinePerProject(Project project);*/


}
