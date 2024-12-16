package com.synopsis.product.repositories;

import com.synopsis.product.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {


    @Query("SELECT p FROM ProductEntity p WHERE p.code = :code")
    Optional<ProductEntity> findProductByCode(@Param("code") String code);

    Optional<ProductEntity> findByCode(String code);

    @Query(value = "SELECT * FROM public.find_product_by_code(:code)", nativeQuery = true)
    Optional<ProductEntity> findProductByCodeUsingSP(@Param("code") String code);
}
