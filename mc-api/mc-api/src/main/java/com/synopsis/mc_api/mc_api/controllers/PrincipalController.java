package com.synopsis.mc_api.mc_api.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.synopsis.mc_api.mc_api.dtos.ExternalProductDto;
import com.synopsis.mc_api.mc_api.entites.ExternalProduct;
import com.synopsis.mc_api.mc_api.models.BodyResponse;
import com.synopsis.mc_api.mc_api.models.ExternalProductBean;
import com.synopsis.mc_api.mc_api.repositories.ExternalProductRepository;
import com.synopsis.mc_api.mc_api.services.IWebClient;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.reflect.TypeToken;
@RestController
@RequestMapping("/api/external-product")
@Slf4j
public class PrincipalController {

    @Autowired
    private IWebClient webClientExternal;

    @Autowired
    private ExternalProductRepository externalProductRepository;


    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody ExternalProductDto dto) {

        log.info("Call to external service request ",dto);

        JsonNode response = webClientExternal.createExternalProduct(dto);

        log.info("Call to external service response ",response);

        ExternalProduct externalProduct = new ExternalProduct();
        Gson gson = new Gson();
        externalProduct.setBody(response.toString());

        LocalDateTime localDateTime = LocalDateTime.now();

        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        externalProduct.setDate(date);

        externalProductRepository.save(externalProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public List<BodyResponse> getAllExternalProducts() {
        return externalProductRepository.findAll().stream().map(product->{
            Gson gson = new Gson();
            var body = gson.fromJson(product.getBody(), ExternalProductBean.class);
            return new BodyResponse(product.getId(),body,product.getDate());
        }).collect(Collectors.toList());
    }
}
