package com.example.Course.contoller;

import com.example.Course.FileUploadUtil;
import com.example.Course.model.Presentation;
import com.example.Course.model.Seminar;
import com.example.Course.service.PresentationService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/api")
public class PresentationController {

    @Autowired
    private PresentationService service;

    @RequestMapping(method = RequestMethod.GET, path = "/presentation/{presentationId}")
    public Presentation getPresentationById(HttpServletRequest request, @PathVariable Integer presentationId) {
        Presentation a = service.findPresentationById(presentationId);
        return a;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/listPresentations")
    public List<Presentation> getAllPresentations() {
        List<Presentation> a = service.findAll();
        return a;
    }

    @RequestMapping(method = RequestMethod.GET, path = "presentationsForSeminarSortByDate/seminarId={seminarId}")
    public List<Presentation> getSortListForSeminar(HttpServletRequest request, @PathVariable Integer seminarId) {
        List<Presentation> a = service.findAllForSeminarSortByDate(seminarId);
        return a;
    }

    @PostMapping(value = "/addPresentation", consumes = {"multipart/form-data"})
    public void add(
            @RequestParam("name") String namePresentation,
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("mime_type") String mimeType,
            @RequestParam("description") String descriptionPresentation,
            @RequestParam("date") String datePresentation,
            @RequestParam("place") String placePresentation)
            throws IOException {
        System.out.println(multipartFile.getOriginalFilename());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.US);
        LocalDateTime dateTime = LocalDateTime.parse(datePresentation, formatter);

        service.addPresentation(new Presentation(null, namePresentation, "filik", mimeType,
                descriptionPresentation, dateTime, placePresentation, new Seminar(1, "pidor"), null, null, null), multipartFile);
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/downloadFile/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileName") String fileName) throws IOException {
        return service.downloadFile(fileName);     
    }
}
