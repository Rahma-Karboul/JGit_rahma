package com.focuscorp.DOFAN.service;

import com.focuscorp.DOFAN.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.focuscorp.DOFAN.repository.CredentialRepository;

@Service
public class CredentialService {

    @Autowired
    private final CredentialRepository credentialRepository;

    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public void addCredential(Credential credential)
    {
        credentialRepository.save(credential);
    }

    public Iterable<Credential> findAllCredentials() {
        return credentialRepository.findAll();
    }

    public  Credential findById( String id) {
        return credentialRepository.findById(id).get();
    }
    public void deleteById(String id)
    {
        credentialRepository.deleteById(id);
    }
}
