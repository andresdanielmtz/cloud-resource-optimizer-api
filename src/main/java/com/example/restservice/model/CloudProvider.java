package com.example.restservice.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract class representing a generic cloud provider.
 */
public abstract class CloudProvider {
    /**
     * Common properties and methods for all cloud providers can be defined here, such as API keys, authentication methods, etc.
     */
    @Getter
    @Setter
    String apiKey = "";

    /**
     * Constructor for CloudProvider, which takes an API key as a parameter and initializes the apiKey property.
     * @param apiKey The API key for authenticating with the cloud provider's services.
     */
    public CloudProvider(String apiKey) {
        this.apiKey = apiKey;
    }
}
