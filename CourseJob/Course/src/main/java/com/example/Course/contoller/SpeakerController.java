package com.example.Course.contoller;

import com.example.Course.model.Speaker;
import com.example.Course.service.SpeakerService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class SpeakerController {

    @Autowired
    private SpeakerService service;

    @RequestMapping(method = RequestMethod.GET, path = "/listSpeakers")
    public List<Speaker> getAllSpeakers() {
        List<Speaker> a = service.findAll();
        return a;
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/speakersForPresentation/presentationId={presentationId}")
    public List<Speaker> getListForPresentation(HttpServletRequest request, @PathVariable Integer presentationId) {
        List<Speaker> a = service.findAllForPresentation(presentationId);
        return a;
    }
}
