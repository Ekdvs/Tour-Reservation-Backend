package com.online.travel.planning.online.travel.planning.backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "user")

public class User {
    @Id
    private String userId;
    private String firstName;

}
