package com.synopsis.mc_api.mc_api.entites;

import com.synopsis.mc_api.mc_api.converters.BodyConverter;
import com.synopsis.mc_api.mc_api.models.ExternalProductBean;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class ExternalProduct {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String body;

    @Column(nullable = false, updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date date;


    public void setId(Long id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Date getDate() {
        return date;
    }


}
