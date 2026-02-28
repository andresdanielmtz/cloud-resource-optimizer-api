package com.example.restservice.model;

/**
 * Standard JSON response structure for all API endpoints.
 * Provides a consistent response format across the application.
 */
public class StandardResponse {
    private String name;
    private String body;

    public StandardResponse() {
    }

    public StandardResponse(String name, String body) {
        this.name = name;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
