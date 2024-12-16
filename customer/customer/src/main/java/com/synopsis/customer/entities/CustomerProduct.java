package com.synopsis.customer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CustomerProduct {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long productId;

    @Transient
    private String productName;

    @JsonIgnore//Es necesario evitar la repeticion infinita.
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Customer.class)
    @JoinColumn(name = "customerId", nullable = true)
    private Customer customer;

}