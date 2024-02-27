package com.demo.controller;

import com.demo.model.ResponseDTO;
import com.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping
    public ResponseDTO readFileContent() {
        return fileService.readFileContent();
    }

    @PostMapping
    public ResponseDTO rewriteFileContent(@RequestBody String content) {
        return fileService.rewriteFileContent(content);
    }

    @PutMapping
    public ResponseDTO appendToFile(@RequestBody String content) {
        return fileService.appendToFile(content);
    }

    @DeleteMapping
    public ResponseDTO deleteContent(@RequestParam String content) {
        return fileService.deleteContent(content);
    }
}
