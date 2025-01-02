package com.online.travel.planning.online.travel.planning.backend.Service;

import java.util.List;

import com.online.travel.planning.online.travel.planning.backend.Model.Packages;

public interface PackagesService {
    Packages createPackage(Packages packages);
    Packages updatePackage(String id, Packages packages);
    void deletePackage(String id);
    List<Packages> getAllPackages();
    Packages getPackageById(String id);
    
    
    
    
    

}
