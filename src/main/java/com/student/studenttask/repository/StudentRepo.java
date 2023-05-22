package com.student.studenttask.repository;

import com.student.studenttask.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Students, Integer> {

    public List<Students> findByTotalMarks(int marks);

    @Query(value = "SELECT * FROM students ORDER BY total_marks DESC ",
    nativeQuery = true)
    public List<Students> findAllByMarks();
}
