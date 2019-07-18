package com.bigdata.railway.controller;

import com.bigdata.railway.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    UploadService uploadService;

    @PostMapping(value="/{type}", consumes = "multipart/form-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> readFile(@RequestParam("file") MultipartFile file,
                                        @PathVariable("type") int type){
        Map<String, String> statusMap = new HashMap<>();
        if(uploadService.readFile(file, type))
            statusMap.put("status", "success");
        else
            statusMap.put("status", "error");
        return statusMap;
    }
}
