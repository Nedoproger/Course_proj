package com.example.Course.contoller;

import com.example.Course.model.Seminar;
import com.example.Course.service.SeminarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class SeminarController {

    @Autowired
    private SeminarService service;

    @RequestMapping(method = RequestMethod.GET, path = "/listSeminars")
    public List<Seminar> getAllSeminars() {
        List<Seminar> a = service.findAll();
        return a;
    }
    
    @PostMapping(path = "/addSeminar")
//    @CrossOrigin
    public void addSeminar(@RequestBody Seminar seminar) {
        service.add(seminar);
    }
}
