package com.hsbc.demo.students;

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
    public List<Student> greeting(@RequestParam(required = true) String id, @RequestParam(required = false, defaultValue = "")String name){

        Student student = new Student(id);
        student.setName(name);
        student.setAge(33);
        studentService.saveStudentBean(student);

        List<Student> list = studentService.getStudentList();
        return list;
    }

}
