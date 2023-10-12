package com.springmongo.mongospringboot.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Employee")
@Data
public class Employee {
    @Id
    private String id;
    private String name;
    private String city;
}
