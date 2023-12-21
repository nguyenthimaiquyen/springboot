package com.quyen.quanlyfile.controller;

import com.google.gson.Gson;
import com.quyen.quanlyfile.entity.User;
import com.quyen.quanlyfile.model.request.ImageRequest;
import com.quyen.quanlyfile.model.request.SearchImageRequest;
import com.quyen.quanlyfile.model.response.ImageResponse;
import com.quyen.quanlyfile.repository.ImageJpaRepository;
import com.quyen.quanlyfile.repository.UserJpaRepository;
import com.quyen.quanlyfile.service.ImageService;
import com.quyen.quanlyfile.service.UserService;
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
    private final Gson gson;

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
    @PostMapping("/api/v1/files")
    public ResponseEntity<?> uploadFile(@RequestPart("fileRequest") String imageRequest,
                                        @RequestPart("image") MultipartFile image) {
        System.out.println(imageRequest);
        ImageRequest request = gson.fromJson(imageRequest, ImageRequest.class);
        System.out.println("chưa chuyển đổi thành công đâu nhé");
        imageService.create(request, image);
        return ResponseEntity.ok(null);
    }
}
