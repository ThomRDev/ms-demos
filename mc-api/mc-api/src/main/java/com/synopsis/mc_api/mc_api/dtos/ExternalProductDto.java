package com.synopsis.mc_api.mc_api.dtos;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

public class ExternalProductDto {
    @NotNull(message = "El campo 'title' no debe ser nulo")
    @NotEmpty(message = "El campo 'title' no debe estar vacío")
    @Length(min = 5, message = "El título debe tener al menos 5 caracteres")
    private String title;

    @NotNull(message = "El campo 'price' no debe ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @DecimalMax(value = "10000.0", message = "El precio no puede exceder los 10000")
    private Double price;

    @NotNull(message = "El campo 'description' no debe ser nulo")
    @NotEmpty(message = "El campo 'description' no debe estar vacío")
    @Length(min = 5, message = "el campo description debe tener al menos 5 caracteres")
    private String description;

    @NotNull(message = "El campo 'images' no debe ser nulo")
    @NotEmpty(message = "Debe incluir al menos una imagen")
    @Size(min = 1, max = 5, message = "Debe incluir entre 1 y 5 imágenes")
    private List<String> images;

    // Constructor sin argumentos
    public ExternalProductDto() {
    }

    // Constructor con todos los argumentos
    public ExternalProductDto(String title, Double price, String description, List<String> images) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.images = images;
    }

    // Getters y setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
