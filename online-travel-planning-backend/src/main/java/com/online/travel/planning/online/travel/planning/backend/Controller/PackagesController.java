package com.online.travel.planning.online.travel.planning.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.travel.planning.online.travel.planning.backend.Model.Packages;
import com.online.travel.planning.online.travel.planning.backend.Service.PackagesService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/packages")
public class PackagesController {
     @Autowired
    private PackagesService packagesService;

    @PostMapping("/createPackage")
    public Packages createPackage(@RequestBody Packages packages) {
        return packagesService.createPackage(packages);
    }

    @PutMapping("/updatePackage/{id}")
    public Packages updatePackage(@PathVariable String id, @RequestBody Packages packages) {
        return packagesService.updatePackage(id, packages);
    }




}
