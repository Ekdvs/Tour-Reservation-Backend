package com.online.travel.planning.online.travel.planning.backend.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.online.travel.planning.online.travel.planning.backend.Model.Packages;
import com.online.travel.planning.online.travel.planning.backend.Service.PackagesService;

import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/packages")
public class PackagesController {

     @Autowired
    private PackagesService packagesService;

    @GetMapping("/getAllPackages")
    public List<Packages> getAllPackages() {
        return packagesService.getAllPackages();
    }

    @GetMapping("/searchPackage")
    public ResponseEntity<List<Packages>> searchPackage(@RequestParam String name) {
        List<Packages> packages=packagesService.searchPackagesByName(name);
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/getPackageById/{id}")
    public Optional<Packages> getPackageById(@PathVariable("id") String packageId) {
        return packagesService.getPackageById(packageId);
    }

    @GetMapping("/getPackageByType/{packageType}")
    public List<Packages> getPackageByType(@PathVariable("packageType") String packageType) {
        return packagesService.getPackagesByPackageType(packageType);
    }
    @PostMapping("/addPackage")
    public ResponseEntity<?> createPackage(@RequestPart("package")String packageJson, @RequestPart("imageFile") MultipartFile imagefile) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Packages packages = objectMapper.readValue(packageJson, Packages.class);
            Packages packagesExist = packagesService.createPackage(packages,imagefile);
            return new ResponseEntity<>(packagesExist, HttpStatus.CREATED);


        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatePackage/{id}")
    public ResponseEntity<?> updatePackage(
            @PathVariable("id") String packageeId,
            @RequestPart("event") String PackageJson,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile
    ) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Packages updatepackage = objectMapper.readValue(PackageJson, Packages.class);
            Packages packages1 = packagesService.updatePackage(packageeId,updatepackage,imageFile);
            return new ResponseEntity<>(packages1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletePackage/{id}")
    public String deletePackage(@PathVariable("id") String id) {
        packagesService.deletePackage(id);
        return "Package Deleted with ID: " + id;
    }








}
