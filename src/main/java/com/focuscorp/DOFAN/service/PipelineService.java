package com.focuscorp.DOFAN.service;

import com.focuscorp.DOFAN.model.Credential;
import com.focuscorp.DOFAN.model.Pipeline;
import com.focuscorp.DOFAN.repository.PipelineRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    ////////////////////// findAllCredentials Pipeline //////////////////////////////////////////////////
    public Iterable<Pipeline> findAllCredentials() {
        //logger.info("PipelineService.findAllCredentials() execution");
        return pipelineRepository.findAll();
    }


    ////////////////////// findById Pipeline //////////////////////////////////////////////////
    public  Pipeline findById( String id) {
        //logger.info("PipelineService.findById() execution");
        return pipelineRepository.findById(id).get();
    }


    ////////////////////// findByIdProject Pipeline //////////////////////////////////////////////////
    public  Pipeline findByIdProject( String idProject) {
        //logger.info("PipelineService.findByIdProject() execution");
        return pipelineRepository.findById(idProject).get();
    }


    ////////////////////// Delete Pipeline //////////////////////////////////////////////////
    public void deleteById(String id)
    {
        //logger.info("PipelineService.deleteById() execution");
        pipelineRepository.deleteById(id);
    }
}
