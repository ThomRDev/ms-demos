package com.synopsis.mc_api.mc_api.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.synopsis.mc_api.mc_api.dtos.ExternalProductDto;

public interface IWebClient {
    public JsonNode createExternalProduct(ExternalProductDto externalProduct);
}
