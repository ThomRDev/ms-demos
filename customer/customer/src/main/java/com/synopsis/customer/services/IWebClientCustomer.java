package com.synopsis.customer.services;

import com.fasterxml.jackson.databind.JsonNode;

public interface IWebClientCustomer {
    public String getProductName (long id);
    public JsonNode getListTransaction (String accountNumber);
}
