package com.hsbc.demo.students;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class StudentService
{
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    StudentRepository studentRepository;

    public Student getStudentBean(String studentId){
        return studentRepository.findByStudentId(studentId);
    }

    public void saveStudentBean(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getStudentList(){
        return studentRepository.findAll();
    }

    @PostConstruct
    public void removeStudentList(){
        logger.info("Delete all record in Student");
        studentRepository.deleteAll();
    }
}

