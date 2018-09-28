package com.hsbc.demo.students;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Data
@Setter
@Getter
@Document(collection="student")
public class Student {

    @Field("name")
    private String name;

    @Id
    private String studentId;

    @Field("age")
    private Integer age;

    public Student(String studentId){
        this.studentId = studentId;
    }

}
