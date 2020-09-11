package com.oauth2.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.oauth2")
public class OAuth2ConfigProperties {

    private String clientId;
    private String clientSecret;
    private String[] clientScopes;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String[] getClientScopes() {
        return clientScopes;
    }

    public void setClientScopes(String[] clientScopes) {
        this.clientScopes = clientScopes;
    }
}
