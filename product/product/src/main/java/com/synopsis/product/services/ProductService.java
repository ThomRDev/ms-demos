package com.synopsis.product.services;

import com.synopsis.product.entities.ProductEntity;
import com.synopsis.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> list() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> get(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public ProductEntity put(Long id,ProductEntity input) {
        ProductEntity find = productRepository.findById(id).get();
        if(find != null){
            find.setCode(input.getCode());
            find.setName(input.getName());
        }
        return productRepository.save(find);
    }

    @Override
    public ProductEntity post(ProductEntity input) {
        return productRepository.save(input);
    }

    @Override
    public boolean delete(Long id) {
        Optional<ProductEntity> findById = productRepository.findById(id);
        if(findById.get() != null){
            productRepository.delete(findById.get());
            return true;
        }
        return false;
    }

    @Override
    public ProductEntity findProductByCode1(String code) {
        return productRepository.findProductByCode(code).orElse(null);
    }

    @Override
    public ProductEntity findProductByCode2(String code) {
        //return productRepository.findByCode(code).orElse(null);
        return productRepository.findProductByCodeUsingSP(code).orElse(null);
    }


}
