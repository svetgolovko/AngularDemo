package com.example.demo;

import com.example.demo.controller.ClientController;
import com.example.demo.model.Client;
import com.example.demo.repo.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
public class ClientControllerTest {

    @InjectMocks
    ClientController controller;

    @Mock
    private ClientRepository repository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddClient()
    {
        Client client = new Client(Long.valueOf(1), "Lokesh", "Gupta");
        Client savedClient = controller.addClient(client);
        verify(repository, times(1)).save(client);
    }

    @Test
    public void testDeleteClient()
    {
        Client client = new Client(Long.valueOf(1));
        ResponseEntity rep = controller.deleteClient(client.getId());
        verify(repository, times(1)).deleteById(client.getId());
    }

    @Test
    public void testDeleteAllClient()
    {
        ResponseEntity rep = controller.deleteAllClients();
        verify(repository, times(1)).deleteAll();
    }

}
