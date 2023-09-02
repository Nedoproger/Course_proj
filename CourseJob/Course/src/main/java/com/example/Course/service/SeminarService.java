package com.example.Course.service;

import com.example.Course.model.Seminar;
import com.example.Course.repository.SeminarRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeminarService {

    @Autowired
    private SeminarRepository repository;

    public List<Seminar> findAll() {
        return repository.getList();
    }

    public void add(Seminar seminar) {
        repository.saveAndFlush(seminar);
    }

}
