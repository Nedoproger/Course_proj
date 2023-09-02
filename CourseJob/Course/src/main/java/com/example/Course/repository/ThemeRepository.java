package com.example.Course.repository;

import com.example.Course.model.Theme;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    @Query("select t from Theme t")
    public List<Theme> getList();

    @Query("select p.themes from Presentation p where p.id = :presentationId")
    public List<Theme> getListForPresentation(@Param("presentationId") Integer presentationId);

}
