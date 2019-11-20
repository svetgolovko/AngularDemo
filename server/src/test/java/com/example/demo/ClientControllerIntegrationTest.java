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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

@TestPropertySource("classpath:application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerIntegrationTest {

    @Autowired
    ClientController controller;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    @Test
    public void whenGetRequestToClients_thenCorrectResponse() throws Exception {
        ResponseEntity<Iterable<Client>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/clients", HttpMethod.GET,
                null, new ParameterizedTypeReference<Iterable<Client>>() {
        });
        Iterable<Client> clients = responseEntity.getBody();
        Assertions
                .assertThat(clients)
                .hasSize(2);
      //  assertThat(products, hasItem(hasProperty("firstName", is("Tom"))));
    }

    @Test
    public void testAddClient()
    {
         Client client = new Client(Long.valueOf(1), "Lokesh", "Gupta");
         Client savedClient = controller.addClient(client);
         assertThat(savedClient.getFirstName().equals(client.getFirstName()));
         assertThat(savedClient.getLastName().equals(client.getLastName()));
    }

    @Test
    public void testAddClientsAndGetClient() {
        // given
        Client alex = new Client(Long.valueOf(2),"alex", "bond");
        Client savedClient = controller.addClient(alex);
        Client getClient = controller.getClient(savedClient.getId()).get();
        assertThat(getClient.getLastName().equals(savedClient.getLastName()));
    }

    @Test
    public void testAddClientAndDelete() {

        Client alex = new Client(Long.valueOf(2),"alex", "bond");
        Client savedClient = controller.addClient(alex);

        Client getClient = controller.getClient(savedClient.getId()).get();
        assertThat(getClient.getLastName().equals(savedClient.getLastName()));

        ResponseEntity entity = controller.deleteClient(savedClient.getId());
        assertThat(entity.getStatusCode() .equals(HttpStatus.OK ));

        assertFalse(controller.getClient(savedClient.getId())==null);
    }

}
