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
        sDto.setId(students.getId());
        return sDto;
    }

    @Override
    public void calculateRank() {
        List<Students> students = repo.findAllByMarks();
        System.out.println(students);

        if(!students.isEmpty()){
            int prevMarks = 0;
            int prevRank = -1;
            int i = 0;

            for(Students s : students){
                if(i == 0){ // initial case
                    s.setStudentRank(1);
                    i++;
                } else{
                    if(prevMarks == s.getTotalMarks()){ // check prev marks == current marks
                        // for the first matching pair
                        if(prevRank != -1){
                            s.setStudentRank(prevRank);
                        } else{
                            prevRank = i;
                            s.setStudentRank(i);
                        }
                    } else{
                        i++;
                        prevRank = i;
                        s.setStudentRank(i);
                    }
                }
                prevMarks = s.getTotalMarks();
                repo.save(s);
            }
        }


    }

}
