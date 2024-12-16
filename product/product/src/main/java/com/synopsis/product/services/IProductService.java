package com.synopsis.product.services;

import com.synopsis.product.entities.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public List<ProductEntity> list();
    public Optional<ProductEntity> get(Long id);
    public ProductEntity put(Long id,ProductEntity input);
    public ProductEntity post(ProductEntity input);
    public boolean delete(Long id);


    public ProductEntity findProductByCode1(String code);
    public ProductEntity findProductByCode2(String code);
}
