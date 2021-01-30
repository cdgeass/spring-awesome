package io.github.cdgeass.awesome.springoauth2authorizationserver;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author cdgeass
 * @since  2021-01-3-
 */
@Entity
public class Client {

    @Id
    private Long id;

    private String clientId;

    private String secret;

    private String redirectUri;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientId='" + clientId + '\'' +
                ", secret='" + secret + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                '}';
    }
}
