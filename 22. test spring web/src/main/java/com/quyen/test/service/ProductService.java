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
                        .image(product.getImage())
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
                        .image(product.getImage())
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
            productNeedUpdate.setImage(request.getImage());
            productJpaRepository.save(productNeedUpdate);
            return;
        }
        productJpaRepository.save(product);
    }
}