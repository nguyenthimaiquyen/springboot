package com.quyen.quanlyfile.service;

import com.quyen.quanlyfile.entity.Image;
import com.quyen.quanlyfile.model.request.ImageRequest;
import com.quyen.quanlyfile.model.request.SearchImageRequest;
import com.quyen.quanlyfile.model.request.SearchUserRequest;
import com.quyen.quanlyfile.model.response.ImageDetailResponse;
import com.quyen.quanlyfile.model.response.ImageResponse;
import com.quyen.quanlyfile.model.response.UserDetailResponse;
import com.quyen.quanlyfile.model.response.UserResponse;
import com.quyen.quanlyfile.repository.ImageJpaRepository;
import com.quyen.quanlyfile.repository.ImageRepository;
import com.quyen.quanlyfile.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageJpaRepository imageJpaRepository;


    public ImageResponse searchImage(SearchImageRequest request, Integer id) {
        List<ImageDetailResponse> data = imageRepository.searchImage(request, id);
        Long totalElement = 0L;
        if (!CollectionUtils.isEmpty(data)) {
            totalElement = data.get(0).getTotalRecord();
        }
        double totalPageTemp = (double) totalElement / request.getPageSize();
        if (totalPageTemp > totalElement / request.getPageSize()) {
            totalPageTemp++;
        }

        return ImageResponse.builder()
                .images(data)
                .totalElement(totalElement)
                .totalPage(Double.valueOf(totalPageTemp).intValue())
                .currentPage(request.getCurrentPage())
                .pageSize(request.getPageSize())
                .build();
    }


    public void delete(Integer id) {
        imageJpaRepository.deleteById(id);
    }

    public void create(ImageRequest request, MultipartFile image) {
        //lưu ảnh
        String filePath = "user_images" + File.separator + image.getOriginalFilename();
        try {
            Files.copy(image.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image newImage = Image.builder()
                .type(request.getType())
                .createdAt(request.getCreatedAt())
                .build();
        imageJpaRepository.save(newImage);
    }
}
