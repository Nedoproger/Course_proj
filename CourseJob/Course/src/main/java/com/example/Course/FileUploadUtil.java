package com.example.Course;
 
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
 
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
 
@Component
public class FileUploadUtil {
    public String saveFile(String fileName, MultipartFile multipartFile)
            throws IOException {
        Path uploadPath = Paths.get("files");
          
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileCode = RandomStringUtils.randomAlphanumeric(8);
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {       
            throw new IOException("Could not save file: " + fileName, ioe);
        }
        return fileCode;
    }
    
    //DOWNLOAD
    private Path foundFile;
     
    public Resource getFileAsResource(String fileName) throws IOException {
        Path dirPath = Paths.get("files");
         
        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileName)) {
                foundFile = file;
                return;
            }
        });
 
        if (foundFile != null) {
            return new UrlResource(foundFile.toUri());
        }
         
        return null;
    }
}