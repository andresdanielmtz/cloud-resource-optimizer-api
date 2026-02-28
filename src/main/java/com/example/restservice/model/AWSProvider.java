package com.example.restservice.model;

public class AWSProvider extends CloudProvider {
    /**
     * Constructor for AWSProvider, which takes an API key as a parameter and passes it to the superclass constructor
     * @param apiKey The API key for authenticating with AWS services
     */
    public AWSProvider(String apiKey) {
        super(apiKey);
    }

    // AWS-specific properties and methods can be added here
}
