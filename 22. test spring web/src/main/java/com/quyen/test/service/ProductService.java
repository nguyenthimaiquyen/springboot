package com.quyen.test.service;

import com.quyen.test.entity.Product;
import com.quyen.test.exception.ProductNotFoundException;
import com.quyen.test.model.request.ProductRequest;
import com.quyen.test.model.request.SearchProductRequest;
import com.quyen.test.model.response.ProductDetailResponse;
import com.quyen.test.model.response.ProductResponse;
import com.quyen.test.repository.ProductJpaRepository;
import com.quyen.test.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
    private final ProductJpaRepository productJpaRepository;
    private final ProductRepository productRepository;


    public List<ProductDetailResponse> getAll() {
        List<Product> products = productJpaRepository.findAll();
        return products.stream().map(p ->
                ProductDetailResponse.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .price(p.getPrice())
                        .description(p.getDescription())
                        .image(p.getImage())
                        .build()
        ).collect(Collectors.toList());
    }

    public ProductDetailResponse getProductDetails(Long id) throws ProductNotFoundException {
        return productJpaRepository.findById(id).map(
                product -> ProductDetailResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .image(product.getImage())
                        .build()
        ).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " could not be found"));
    }

    public void save(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .image(request.getImage())
                .build();
        if (!ObjectUtils.isEmpty(request.getId())) {
            Optional<Product> studentOptional = productJpaRepository.findById(request.getId());
            Product productNeedUpdate = studentOptional.get();
            productNeedUpdate.setName(request.getName());
            productNeedUpdate.setPrice(request.getPrice());
            productNeedUpdate.setDescription(request.getDescription());
            productNeedUpdate.setImage(request.getImage());
            productJpaRepository.save(productNeedUpdate);
            return;
        }
        productJpaRepository.save(product);
    }

    public void create(ProductRequest request, MultipartFile image) {
        //lưu ảnh
        String filePath = "product_images" + File.separator + image.getOriginalFilename();
        try {
            Files.copy(image.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .image(image.getOriginalFilename())
                .build();
        productJpaRepository.save(product);
    }


    public ProductResponse searchProduct(SearchProductRequest request) {
        List<ProductDetailResponse> data = productRepository.searchProduct(request);
        Long totalElement = 0L;
        if (!CollectionUtils.isEmpty(data)) {
            totalElement = data.get(0).getTotalRecord();
        }

        double totalPageTemp = (double) totalElement / request.getPageSize();
        if (totalPageTemp > totalElement / request.getPageSize()) {
            totalPageTemp++;
        }
        return ProductResponse.builder()
                .products(data)
                .totalElement(totalElement)
                .totalPage(Double.valueOf(totalPageTemp).intValue())
                .currentPage(request.getCurrentPage())
                .pageSize(request.getPageSize())
                .build();

    }
}
