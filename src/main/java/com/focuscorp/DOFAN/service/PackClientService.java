package com.focuscorp.DOFAN.service;

import com.focuscorp.DOFAN.model.PackClient;
import com.focuscorp.DOFAN.repository.PackClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackClientService {

    @Autowired
    private final PackClientRepository packClientRepository;

    public PackClientService(PackClientRepository packClientRepository) {
        this.packClientRepository = packClientRepository;
    }

    public void addPackClient(PackClient packClient)
    {
        packClientRepository.save(packClient);
        System.out.println("pack client added successfully");
    }
}
