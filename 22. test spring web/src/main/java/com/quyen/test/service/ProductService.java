package com.quyen.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quyen.test.entity.Product;
import com.quyen.test.exception.ProductNotFoundException;
import com.quyen.test.model.request.ProductRequest;
import com.quyen.test.model.response.ProductResponse;
import com.quyen.test.repository.ProductJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ObjectMapper objectMapper;
    private final ProductJpaRepository productJpaRepository;


    public List<ProductResponse> getAll() {
        List<Product> products = productJpaRepository.findAll();
        return products.stream().map(
                product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
//                        .images(product.getImages())
                        .build()
        ).collect(Collectors.toList());
    }

    public ProductResponse getProductDetails(Long id) throws ProductNotFoundException {
        return productJpaRepository.findById(id).map(
                product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
//                        .images(product.getImages())
                        .build()
        ).orElseThrow( () -> new ProductNotFoundException("Product with id " + id + " could not be found"));
    }

    public void save(ProductRequest request) {
        Product product = objectMapper.convertValue(request, Product.class);
        if (!ObjectUtils.isEmpty(request.getId())) {
            Optional<Product> studentOptional = productJpaRepository.findById(request.getId());
            Product productNeedUpdate = studentOptional.get();
            productNeedUpdate.setName(request.getName());
            productNeedUpdate.setPrice(request.getPrice());
            productNeedUpdate.setDescription(request.getDescription());
//            productNeedUpdate.setImages(request.getImage());
            productJpaRepository.save(productNeedUpdate);
            return;
        }
        productJpaRepository.save(product);
    }

    public void create(ProductRequest request, List<MultipartFile> images) {
        //lưu ảnh
        List<String> imageUrls = images.stream().map(img -> {
            String filePath = "images" + File.separator + img.getOriginalFilename();
            try {
                Files.copy(img.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return img.getOriginalFilename();
        }).collect(Collectors.toList());

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
//                .images(imageUrls)
                .build();
        productJpaRepository.save(product);
    }


}
