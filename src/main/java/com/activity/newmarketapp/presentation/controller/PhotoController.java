package com.activity.newmarketapp.presentation.controller;

import com.activity.newmarketapp.domain.service.PhotoService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/photo")
public class PhotoController {

    private PhotoService service;


    @PatchMapping("/{id}")
    public ResponseEntity<String> savePhoto(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id) {
        String message = service.savePhoto(multipartFile, id);
        return ResponseEntity.ok(message);
    }
}
