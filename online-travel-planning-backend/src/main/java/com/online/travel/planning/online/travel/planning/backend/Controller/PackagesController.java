package com.online.travel.planning.online.travel.planning.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.travel.planning.online.travel.planning.backend.Service.PackagesService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/packages")
public class PackagesController {
     @Autowired
    private PackagesService packagesService;


}
