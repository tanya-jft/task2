package com.student.studenttask.service.implementations;


import com.student.studenttask.dto.StudentDto;
import com.student.studenttask.entity.Students;
import com.student.studenttask.repository.StudentRepo;
import com.student.studenttask.service.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    private StudentRepo repo;

    // getting dependencies
    @Autowired
    public void getRepo(StudentRepo sRepo) {
        this.repo = sRepo;
    }


    // getting all students
    @Override
    public List<StudentDto> getAllStudents(){
        List<Students> studentList = repo.findAll();

        if(!studentList.isEmpty()){
            // convert the student entity to student DTO
            List<StudentDto> studentDto = studentList.stream()
                    .map(student -> new StudentDto(
                            student.getId(),
                            student.getFullName() ,
                            student.getTotalMarks(),
                            student.getStudentRank()))
                    .collect(Collectors.toList());

            return studentDto;
        }
        return null;
    }




    // [600, 550, 430, 200 ,200, 120] -> 272
    @Override
    public StudentDto registerStudents(StudentDto sDto) {
        Students students = new Students();

        students.setFullName(sDto.getFullName());
        students.setTotalMarks(sDto.getTotalMarks());
        students.setStudentRank(sDto.getStudentRank());
        repo.save(students);
        // recalculate the rank
        calculateRank();

        sDto.setStudentRank(students.getStudentRank());
        return sDto;
    }

    @Override
    public void calculateRank() {
        List<Students> students = repo.findAll();

        // case -> list is not empty
        if(!students.isEmpty()){
            Map<Integer, Integer> mapping = new TreeMap<>();


            List<Integer> totalMarks = students.stream()
                    .map(Students::getTotalMarks)
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());

            for(int i = 0; i < totalMarks.size(); i++){
                
            }



        }

    }

    @Override
    public void updateRank(int rank, int marks) {
        List<Students> students = repo.findByTotalMarks(marks);

        for(int i = 0; i < students.size(); i++){
            students.get(i).setStudentRank(rank-1);
            repo.save(students.get(i));
        }

    }
}
