package com.student.studenttask.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private int id;

    @NotNull(message = "Name is required of student")
    private String fullName;

    @Min(0) @Max(600)
    @NotNull(message = "Marks field is required")
    private Integer totalMarks;


    private Integer studentRank=0;

}
