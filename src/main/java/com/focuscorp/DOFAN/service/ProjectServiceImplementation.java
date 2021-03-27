package com.focuscorp.DOFAN.service;

import com.focuscorp.DOFAN.model.Project;
import com.focuscorp.DOFAN.repository.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImplementation {

    @Autowired
    private IProjectRepository iProjectRepository;

    public Project addProject(Project project){
        return iProjectRepository.save(project);
    }

    public List<Project> findAllProjects(){
        return iProjectRepository.findAll();
    }

    public void deleteProjectById(String projectId){
        iProjectRepository.deleteById(projectId);
    }

    public Optional<Project> findProjectById(String projectId){
        Optional<Project> project = iProjectRepository.findById(projectId);
        return project;
    }



    /*public long countPieplinePerProject(Project project) {
        List<Pipeline> listPipelinePerProject = project.getPipelines();
        System.out.println("list of pipleine per project " + listPipelinePerProject.stream().count());
        return listPipelinePerProject.stream().count();
    }*/
}
