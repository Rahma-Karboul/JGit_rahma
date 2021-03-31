package com.focuscorp.DOFAN.service;

import com.focuscorp.DOFAN.model.EType;
import com.focuscorp.DOFAN.model.Machine;
import com.focuscorp.DOFAN.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {

    @Autowired
    private final MachineRepository machineRepository;

    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public void addMachine(Machine machine)
    {
        machineRepository.save(machine);
        System.out.println("machine added successfully");
    }

    public Machine findMachineByType(EType type)
    {
        return machineRepository.findByType(type);
    }

    public List<Machine> findAllMachines() {
        System.out.println("machines retrieved!!");
        return machineRepository.findAll();
    }
}
