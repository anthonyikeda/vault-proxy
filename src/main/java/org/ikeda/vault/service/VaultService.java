package org.ikeda.vault.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VaultService {

    public String testConnection(String token, String addr) {
        return query(token, addr, "/v1/sys/health");
    }

    public String getPath()
    private String query(String token, String addr, String path) {
        String url = addr.concat(path);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Vault-Token", token);
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
}
