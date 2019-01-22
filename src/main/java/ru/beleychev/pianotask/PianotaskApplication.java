package ru.beleychev.pianotask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PianotaskApplication {

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PianotaskApplication.class, args);
    }

    @PostConstruct
    public void setup() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> result = restTemplate.exchange("http://api.stackexchange.com/2.2/questions?order=desc&sort=activity"
                + "&site=stackoverflow", HttpMethod.GET, entity, String.class);
        System.out.println(result);
    }

}

