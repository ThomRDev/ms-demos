package com.synopsis.mc_api.mc_api.repositories;

import com.synopsis.mc_api.mc_api.entites.ExternalProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalProductRepository extends JpaRepository<ExternalProduct, Long> {
}
