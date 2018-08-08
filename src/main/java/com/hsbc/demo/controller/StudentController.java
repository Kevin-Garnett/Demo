package com.hsbc.demo.controller;

import com.hsbc.demo.bean.StudentBean;
import com.hsbc.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/demo")
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value="/greeting", method=RequestMethod.GET)
    public List<StudentBean> greeting(@RequestParam(required = true) String id, @RequestParam(required = false, defaultValue = "")String name){

        StudentBean studentBean = new StudentBean(id);
        studentBean.setName(name);
        studentBean.setAge(33);
        studentService.saveStudentBean(studentBean);

        List<StudentBean> list = studentService.getStudentList();
        return list;
    }

}
