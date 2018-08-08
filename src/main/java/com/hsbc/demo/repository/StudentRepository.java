package com.hsbc.demo.repository;

import com.hsbc.demo.bean.StudentBean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<StudentBean, String> {

    StudentBean findByStudentId(String studentId);

}
