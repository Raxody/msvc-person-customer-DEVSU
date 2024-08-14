package com.devsu.msvc_person_customer.client;

import com.devsu.msvc_person_customer.controller.CustomerController;
import com.devsu.msvc_person_customer.dto.AccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncCustomerAccountClient {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private final RestTemplate restTemplate;
    @Value("${account.movement.service}")
    private String accountServiceUrl;

    @Autowired
    public AsyncCustomerAccountClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CompletableFuture<Void> createDefaultAccountAsync(AccountDTO accountDTO) {
        return CompletableFuture.runAsync(() -> {
            HttpEntity<AccountDTO> request = new HttpEntity<>(accountDTO);
            ResponseEntity<Void> response = restTemplate.exchange(accountServiceUrl, HttpMethod.POST, request, Void.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Failed to create default account");
            }
            log.info("Se hizo asyncrono");
        });
    }
}
