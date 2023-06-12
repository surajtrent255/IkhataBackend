package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.utils.FileUtilService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@Slf4j
public class ImageController {

    @Value("${file.upload.directory}")
    private String path;

    @Autowired
    private FileUtilService fileUtilService;

    @GetMapping(value = "/images/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {
   InputStream resource= fileUtilService.getResource(path,imageName);
   response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
