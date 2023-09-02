package com.example.Course.repository;

import com.example.Course.model.Speaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

    @Query("select t from Speaker t")
    public List<Speaker> getList();

    @Query("select p.speakers from Presentation p where p.id = :presentationId")
    public List<Speaker> getListForPresentation(@Param("presentationId") Integer presentationId);

}
