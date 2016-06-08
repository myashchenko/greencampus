package ua.greencampus.web.rest;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arsenii on 18.04.2016.
 */

@RestController
@RequestMapping(value = "/api/file")
public class FileEndPoint {

    @Autowired
    private Cloudinary cloudinary;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity FileUpload(@RequestParam("file") MultipartFile file) {
        String urlToFile = null;
        try {
            File newFile = new File(file.getOriginalFilename());
            file.transferTo(newFile);
            Map result = cloudinary.uploader().upload(newFile, new HashMap<>());
            urlToFile = (String) result.get("url");
            newFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(urlToFile);
    }


    @RequestMapping(value = "/theme/{id}", method = RequestMethod.POST)
    public ResponseEntity ThemeFileUpload(@PathVariable("id") Long themeId, @RequestParam("file") MultipartFile file) {
        String urlToFile = file.getOriginalFilename();

        try {
            File newFile = new File(file.getOriginalFilename());
            file.transferTo(newFile);
            Map result = cloudinary.uploader().upload(newFile, new HashMap<>());
            urlToFile = (String) result.get("url");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(urlToFile);
    }

}
