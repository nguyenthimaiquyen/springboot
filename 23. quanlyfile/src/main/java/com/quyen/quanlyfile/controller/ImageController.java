package com.quyen.quanlyfile.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quyen.quanlyfile.model.request.ImageRequest;
import com.quyen.quanlyfile.model.request.SearchImageRequest;
import com.quyen.quanlyfile.model.response.ImageResponse;
import com.quyen.quanlyfile.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class ImageController {
    private final ImageService imageService;

    //1. render dữ liệu file
    @GetMapping("/users/{id}/files")
    public String getFilesPage(Model model, @PathVariable Integer id, SearchImageRequest request) {
        ImageResponse imageResponse = imageService.searchImage(request, id);
        model.addAttribute("images", imageResponse.getImages());
        model.addAttribute("requestSearch", request);
        model.addAttribute("currentPage", imageResponse.getCurrentPage());
        model.addAttribute("totalPage", imageResponse.getTotalPage());
        model.addAttribute("totalElement", imageResponse.getTotalElement());
        model.addAttribute("pageSize", imageResponse.getPageSize());
        model.addAttribute("userId", id);
        return "users/image";
    }

    // 2. Xem file
    @GetMapping("/api/v1/files/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        // Code logic
        return ResponseEntity.ok(null);
    }

    // 3. Xoa file
    @DeleteMapping("/api/v1/files/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Integer id) {
        imageService.delete(id);
        return ResponseEntity.ok(null);
    }

    //4. upload file
    @PostMapping("/api/v1/files/{userId}")
    public ResponseEntity<?> uploadFile(@RequestPart("fileRequest") String imageRequest,
                                        @RequestPart("image") MultipartFile image, @PathVariable Integer userId) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        ImageRequest request = gson.fromJson(imageRequest, ImageRequest.class);
        imageService.create(request, image, userId);
        return ResponseEntity.ok(null);
    }
}
