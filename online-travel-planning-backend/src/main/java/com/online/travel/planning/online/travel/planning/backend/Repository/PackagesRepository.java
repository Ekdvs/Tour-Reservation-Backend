package com.online.travel.planning.online.travel.planning.backend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.online.travel.planning.online.travel.planning.backend.Model.Packages;

public interface PackagesRepository extends MongoRepository<Packages, String> {

}
