package com.futurebank.authservice.client;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.futurebank.authservice.model.User;

@Service
public class AccountClient {

    private final RestTemplate restTemplate;
    private final String accountServiceUrl = "http://localhost:8083/api/accounts/post"; // Adjust based on actual URL

    @Autowired
    public AccountClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createAccountForUser(User user, String accountType) {
        // Assuming User has all necessary info
        HashMap<String, Object> request = new HashMap<>();
        request.put("userId", user.getUserId());
        request.put("accountType", accountType);
        System.out.println("Before Postr");
       Object ob= restTemplate.postForObject(accountServiceUrl, request, String.class);
        System.out.println("After Postr675"+ob.toString());
    }
}
