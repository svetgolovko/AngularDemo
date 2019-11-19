package com.example.demo;

import com.example.demo.controller.ClientController;
import com.example.demo.model.Client;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ClientControllerIntegrationTest {

    @Autowired
    ClientController controller;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Autowired private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @Test
    public void whenGetRequestToUsers_thenCorrectResponse() throws Exception {
        ResponseEntity<Iterable<Client>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/clients", HttpMethod.GET,
                null, new ParameterizedTypeReference<Iterable<Client>>() {
        });
        Iterable<Client> products = responseEntity.getBody();
        Assertions
                .assertThat(products)
                .hasSize(4);

      //  assertThat(products, hasItem(hasProperty("firstName", is("Tom"))));
}
}
