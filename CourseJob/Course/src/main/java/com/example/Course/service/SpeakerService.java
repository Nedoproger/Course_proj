package com.example.Course.service;

import com.example.Course.model.Speaker;
import com.example.Course.repository.SpeakerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeakerService {

    @Autowired
    private SpeakerRepository repository;

    public List<Speaker> findAll() {
        return repository.getList();
    }
    
        public List<Speaker> findAllForPresentation(Integer presentationId) {
        return repository.getListForPresentation(presentationId);
    }

}
