package com.example.Course.repository;

import com.example.Course.model.Presentation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Long> {

    @Query("select p from Presentation p where p.id = :presentationId")
    public Presentation getPresentationById(@Param("presentationId") Integer presentationId);
    
    @Query("select p from Presentation p")
    public List<Presentation> getList();

    @Query("select p from Presentation p where p.seminar.id = :seminarId ORDER BY p.date DESC")
    public List<Presentation> getSortByDateListForSeminar(@Param("seminarId") Integer seminarId);

}
