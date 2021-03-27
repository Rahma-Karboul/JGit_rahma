package com.focuscorp.DOFAN.service;

import com.focuscorp.DOFAN.model.Credential;
import com.focuscorp.DOFAN.model.Pipeline;
import com.focuscorp.DOFAN.repository.PipelineRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PipelineService {
    //private static final Logger logger = Logger.getLogger(PipelineService.class);

    @Autowired
    private final PipelineRepository pipelineRepository;

    public PipelineService(PipelineRepository pipelineRepository) {
        this.pipelineRepository = pipelineRepository;
    }


    ////////////////////// Create/Save pipeline //////////////////////////////////////////////////
    public void addPipeline(Pipeline pipeline) {

        //logger.info("PipelineService.addUser() execution");
        pipelineRepository.save(pipeline);
    }

    public void addPipelines(List<Pipeline> pipelines)
    {
        pipelineRepository.saveAll(pipelines);
    }

    ////////////////////// findAll Pipelines //////////////////////////////////////////////////
    public Iterable<Pipeline> findAllPipelines() {
        //logger.info("PipelineService.findAllCredentials() execution");
        return pipelineRepository.findAll();
    }


    ////////////////////// findById Pipeline //////////////////////////////////////////////////
    public Optional<Pipeline> findPipelineById(String id) {
        //logger.info("PipelineService.findById() execution");
        Optional<Pipeline> pipeline = pipelineRepository.findById(id);
        return pipeline;
    }


    ////////////////////// findByIdProject Pipeline //////////////////////////////////////////////////
    public  Pipeline findByIdProject( String idProject) {
        //logger.info("PipelineService.findByIdProject() execution");
        return pipelineRepository.findById(idProject).get();
    }


    ////////////////////// Delete Pipeline //////////////////////////////////////////////////
    public void deletePipelineById(String id)
    {
        //logger.info("PipelineService.deleteById() execution");
        pipelineRepository.deleteById(id);
    }
}
