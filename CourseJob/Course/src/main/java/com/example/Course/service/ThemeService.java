package com.example.Course.service;

import com.example.Course.model.Theme;
import com.example.Course.repository.ThemeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository repository;

    public List<Theme> findAll() {
        return repository.getList();
    }
    
        public List<Theme> findAllForPresentation(Integer presentationId) {
        return repository.getListForPresentation(presentationId);
    }

}
