package com.example.demo.repo;
import com.example.demo.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findByLastName(String lastName);

    //Client findById(long id);
}