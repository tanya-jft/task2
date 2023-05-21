package com.student.studenttask.controller;


import com.student.studenttask.dto.StudentDto;
import com.student.studenttask.service.implementations.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentServiceImpl studentServiceImpl;

    // Autowiring Dependency

    @Autowired
    public void getService(StudentServiceImpl studentServiceImpl){
        this.studentServiceImpl = studentServiceImpl;
    }


    // Mapping to get all the students

    @GetMapping("/")
    public String homePageMapping(){
        return "Home Page View";
    }

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudentsMapping(){
        List<StudentDto> studentDtoList = studentServiceImpl.getAllStudents();

        if(studentDtoList != null){
            return ResponseEntity.accepted().body(studentDtoList);
        }

        // if list is empty
        return ResponseEntity.accepted().body("Student List is Empty");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerStudents(@Valid @RequestBody StudentDto studentDto){
        if(studentDto != null){
            StudentDto sDto = studentServiceImpl.registerStudents(studentDto);
            return ResponseEntity.accepted().body(sDto);
        }

        return ResponseEntity.accepted().body("Failed to register student");

    }


}
