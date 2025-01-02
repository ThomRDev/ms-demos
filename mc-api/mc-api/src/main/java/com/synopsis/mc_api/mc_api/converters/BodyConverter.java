package com.synopsis.mc_api.mc_api.converters;

import com.google.gson.Gson;
import com.synopsis.mc_api.mc_api.models.ExternalProductBean;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class BodyConverter implements AttributeConverter<ExternalProductBean, String> {
    private final static Gson GSON = new Gson();
    @Override
    public String convertToDatabaseColumn(ExternalProductBean externalProduct) {
        return GSON.toJson(externalProduct);
    }
    @Override
    public ExternalProductBean convertToEntityAttribute(String value) {
        return GSON.fromJson(value, ExternalProductBean.class);
    }
}
