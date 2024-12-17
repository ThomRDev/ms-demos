package com.synopsis.customer.controllers;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synopsis.customer.formatter.FormatterTransaction;
import com.synopsis.customer.models.Transaction;
import com.synopsis.customer.services.IWebClientCustomer;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import jakarta.persistence.Transient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.synopsis.customer.entities.Customer;
import com.synopsis.customer.entities.CustomerProduct;
import com.synopsis.customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;


    @Autowired
    private IWebClientCustomer webClientCustomer;


    @GetMapping()
    public List<Customer> list() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable(name = "id") long id) {
        return customerRepository.findById(id).get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") long id, @RequestBody Customer input) {
        Customer find = customerRepository.findById(id).get();
        if(find != null){
            find.setCode(input.getCode());
            find.setName(input.getName());
            find.setIban(input.getIban());
            find.setPhone(input.getPhone());
            find.setSurname(input.getSurname());
        }
        Customer save = customerRepository.save(find);
        return ResponseEntity.ok(save);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Customer input) {

        input.getProducts().forEach(x -> x.setCustomer(input));
        Customer save = customerRepository.save(input);
        return ResponseEntity.ok(save);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        Optional<Customer> findById = customerRepository.findById(id);
        if(findById.get() != null){
            customerRepository.delete(findById.get());
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping("/full/{id}")
    public Customer getFull(@PathVariable(name = "id") long id) {

        Customer customer = customerRepository.findById(id).get();

        List<CustomerProduct> products = customer.getProducts();
        customer.setTransactions(
                FormatterTransaction.formatter(
                        webClientCustomer.getListTransaction(customer.getIban())
                )
        );

        products.forEach(
                product -> {
                    String productName = webClientCustomer.getProductName(product.getProductId());
                    product.setProductName(productName);
                }
        );


        return customer;
    }

    @PutMapping("/update-sp/{id}")
    public ResponseEntity<Void> updateCustomer(
            @PathVariable Long id,
            @RequestParam String code,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String iban,
            @RequestParam String surname,
            @RequestParam String address
    ) {
        customerRepository.updateCustomerName(id, code, name, phone, iban, surname, address);

        return ResponseEntity.ok().build();
    }
}
