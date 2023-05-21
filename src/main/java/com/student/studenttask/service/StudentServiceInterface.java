package com.student.studenttask.service;


import com.student.studenttask.dto.StudentDto;

import java.util.List;

public interface StudentServiceInterface {

    public List<StudentDto> getAllStudents();

    public StudentDto registerStudents(StudentDto studentDto);

    public void calculateRank();
    public void updateRank(int rank, int marks);
}

