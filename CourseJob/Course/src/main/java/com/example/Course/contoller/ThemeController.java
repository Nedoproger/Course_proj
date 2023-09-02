package com.example.Course.contoller;

import com.example.Course.model.Theme;
import com.example.Course.service.ThemeService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class ThemeController {

    @Autowired
    private ThemeService service;

    @RequestMapping(method = RequestMethod.GET, path = "/listThemes")
    public List<Theme> getAllThemes() {
        List<Theme> a = service.findAll();
        return a;
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/themesForPresentation/presentationId={presentationId}")
    public List<Theme> getListForPresentation(HttpServletRequest request, @PathVariable Integer presentationId) {
        List<Theme> a = service.findAllForPresentation(presentationId);
        return a;
    }
}
