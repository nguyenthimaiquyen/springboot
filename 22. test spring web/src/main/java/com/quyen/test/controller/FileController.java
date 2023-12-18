package com.quyen.test.controller;

import com.quyen.test.entity.FileEntity;
import com.quyen.test.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1/files")
@AllArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping("/{fileName}")
    public ResponseEntity<?> download(@PathVariable String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return ResponseEntity.badRequest().body("File name is empty");
        }
        FileEntity fileEntity = fileService.findByName(fileName);
        if (ObjectUtils.isEmpty(fileEntity)) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        List<String> customHeaders = new ArrayList<>();
        customHeaders.add(HttpHeaders.CONTENT_DISPOSITION);
        customHeaders.add("Content-Response");
        headers.setAccessControlExposeHeaders(customHeaders);
        headers.set("Content-disposition", "attachment;filename=" + fileEntity.getName() + "." + fileEntity.getExtension());
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        byte[] imageData = fileService.download(fileName);
        if (ObjectUtils.isEmpty(imageData)) {
            return ResponseEntity.noContent().build();
        }
        ByteArrayResource resource = new ByteArrayResource(imageData);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
