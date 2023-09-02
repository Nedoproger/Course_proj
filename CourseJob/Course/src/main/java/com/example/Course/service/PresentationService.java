package com.example.Course.service;

import com.example.Course.FileUploadUtil;
import com.example.Course.model.Presentation;
import com.example.Course.repository.PresentationRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PresentationService {

    @Autowired
    private PresentationRepository repository;
    
    @Autowired
    private FileUploadUtil fileUploadUtil;

    public Presentation findPresentationById(Integer presentationId) {
        return repository.getPresentationById(presentationId);
    }

    public List<Presentation> findAll() {
        return repository.getList();
    }

    public List<Presentation> findAllForSeminarSortByDate(Integer seminarId) {
        return repository.getSortByDateListForSeminar(seminarId);
    }

    public String saveFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        return fileUploadUtil.saveFile(fileName, file);
    }

    public void addPresentation(Presentation presentation, MultipartFile file) throws IOException {
        String fileCode = saveFile(file);
        presentation.setFile(fileCode + "-" + file.getOriginalFilename());
        repository.saveAndFlush(presentation);
    }

    public ResponseEntity<?> downloadFile(String fileName) throws IOException {
        FileUploadUtil downloadUtil = new FileUploadUtil();
         
        Resource resource = null;
        try {
            resource = downloadUtil.getFileAsResource(fileName);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
         
        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
         
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
         
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);  
    }
    
}
