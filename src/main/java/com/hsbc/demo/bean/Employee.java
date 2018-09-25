package com.hsbc.demo.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "employees")
public class Employee {
    @Id
    String id;

    String firstName;

    String lastName;
}
