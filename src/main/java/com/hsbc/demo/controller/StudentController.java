package com.hsbc.demo.controller;

import com.hsbc.demo.bean.StudentBean;
import com.hsbc.demo.service.StudentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/demo")
public class StudentController {

    @Autowired
    StudentService studentService;

    @ApiOperation(value="Get student list", notes="Save student and refresh student list")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="Student ID", required=true, dataType="String"),
            @ApiImplicitParam(name="name", value="Student Name", required=false, dataType="String")
    })
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
