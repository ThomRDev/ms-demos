package com.synopsis.product.controllers;

import com.synopsis.product.entities.ProductEntity;
import com.synopsis.product.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public List<ProductEntity> list(){
        return productService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> get(@PathVariable(name = "id") Long id){
        return productService.get(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") long id, @RequestBody ProductEntity input) {
        return ResponseEntity.ok(productService.put(id, input));
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ProductEntity input) {
        return ResponseEntity.ok(productService.post(input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        var response = productService.delete(id);

        if(response) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/code1/{code}")
    public ProductEntity findProductByCode1(@PathVariable(name = "code") String code) {
        return productService.findProductByCode1(code);
    }

    @GetMapping("/code2/{code}")
    public ProductEntity findProductByCode2(@PathVariable(name = "code") String code) {
        return productService.findProductByCode2(code);
    }
}
