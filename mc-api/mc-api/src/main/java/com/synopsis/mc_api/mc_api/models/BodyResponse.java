package com.synopsis.mc_api.mc_api.models;

import java.util.Date;

public class BodyResponse {
    private Long id;

    private ExternalProductBean body;

    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExternalProductBean getBody() {
        return body;
    }

    public void setBody(ExternalProductBean body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BodyResponse(Long id, ExternalProductBean body, Date date) {
        this.id = id;
        this.body = body;
        this.date = date;
    }
}
