package com.example.restservice.model;

public class AzureProvider extends CloudProvider {
    /**
     * Constructor for AzureProvider, which takes an API key as a parameter and passes it to the superclass constructor.
     * @param apiKey The API key for authenticating with Azure services.
     */
    public AzureProvider(String apiKey) {
        super(apiKey);
    }

}
