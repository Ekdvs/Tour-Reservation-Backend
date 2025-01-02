package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.travel.planning.online.travel.planning.backend.Model.Packages;
import com.online.travel.planning.online.travel.planning.backend.Repository.PackagesRepository;
import com.online.travel.planning.online.travel.planning.backend.Service.PackagesService;

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
        existingPackage.setName(packages.getName());
        existingPackage.setDescription(packages.getDescription());
        existingPackage.setOnePersonPrice(packages.getOnePersonPrice());
        existingPackage.setDuration(packages.getDuration());
        existingPackage.setLocation(packages.getLocation());
        existingPackage.setImageUrl(packages.getImageUrl());
        return packagesRepository.save(existingPackage);
    }

    @Override
    public void deletePackage(String id) {
        packagesRepository.deleteById(id);
    }

    @Override
        public List<Packages> getAllPackages() {
            return packagesRepository.findAll();
        }

    @Override
    public Packages getPackageById(String id) {
        return packagesRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
    }




}
