package com.focuscorp.DOFAN.service;

import com.focuscorp.DOFAN.model.EOffer;
import com.focuscorp.DOFAN.model.Pack;
import com.focuscorp.DOFAN.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackService {

    @Autowired
    private final PackRepository packRepository;

    public PackService(PackRepository packRepository) {
        this.packRepository = packRepository;
    }

    public void addPack(Pack pack)
    {
        packRepository.save(pack);
        System.out.println("pack added successfully");
    }

    public Pack findPackByOffer(EOffer offer)
    {
        return packRepository.findByOffer(offer);
    }

    public List<Pack> findAllPacks() {
        System.out.println("packs retrieved!!");
        return packRepository.findAll();
    }
}
