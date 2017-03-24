package org.ikeda.vault.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class VaultService {

    private Logger log = LoggerFactory.getLogger(VaultService.class);

    public String testConnection(String token, String addr) {
        return query(token, addr, "/v1/sys/health");
    }


    public String query(String token, String addr, String path) {
        String url = addr.concat("/v1").concat(path);
        log.info("URI to call is {}", url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Vault-Token", token);
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }

    private String query(String token, String addr, String path, MultiValueMap<String, String> params) {
        String url = addr.concat(path);

        if (params != null) {
            url = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build().toString();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Vault-Token", token);
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }

    public String listRoles(String token, String addr) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        List<String> vals = new ArrayList<>();
        vals.add("true");
        params.put("list", vals);
        return query(token, addr, "/v1/auth/approle/role", params);
    }
}
