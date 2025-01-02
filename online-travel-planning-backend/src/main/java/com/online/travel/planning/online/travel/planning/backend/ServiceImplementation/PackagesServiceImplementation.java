package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.travel.planning.online.travel.planning.backend.Model.Packages;
import com.online.travel.planning.online.travel.planning.backend.Repository.PackagesRepository;

@Service
public class PackagesServiceImplementation implements PackagesService {

    @Autowired
    private PackagesRepository packagesRepository;

    @Override
    public Packages createPackage(Packages packages) {
        return packagesRepository.save(packages);
    }

    @Override
    public Packages updatePackage(String id, Packages packages) {
        Packages existingPackage = packagesRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
        
        return packagesRepository.save(existingPackage);
    }



}
