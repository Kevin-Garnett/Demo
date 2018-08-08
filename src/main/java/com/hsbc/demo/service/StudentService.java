package com.hsbc.demo.service;

import com.hsbc.demo.bean.StudentBean;
import com.hsbc.demo.repository.StudentRepository;
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

    public StudentBean getStudentBean(String studentId){
        return studentRepository.findByStudentId(studentId);
    }

    public void saveStudentBean(StudentBean studentBean) {
        studentRepository.save(studentBean);
    }

    public List<StudentBean> getStudentList(){
        return studentRepository.findAll();
    }

    @PostConstruct
    public void removeStudentList(){
        logger.info("Delete all record in Student");
        studentRepository.deleteAll();
    }
}

