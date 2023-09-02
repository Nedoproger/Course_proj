package com.example.Course.repository;

import com.example.Course.model.Seminar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeminarRepository extends JpaRepository<Seminar, Long> {

    @Query("select s from Seminar s")
    public List<Seminar> getList();
    
}
