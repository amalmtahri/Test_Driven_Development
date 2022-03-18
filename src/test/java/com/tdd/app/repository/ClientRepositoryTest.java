package com.tdd.app.repository;

import com.tdd.app.entity.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
class ClientRepositoryTest {

    @Mock private ClientRepository clientRepository;

    @Test
    void getAllClients() {
        Client client1 = new Client(1L, "test1@gmail.com","1222222","test1",12,"homme",true);
        Client client2 = new Client(2L, "test2@gmail.com","09876","test2",13,"homme",true);
        List<Client> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        Mockito.when(clientRepository.findAll()).thenReturn(clients);
        assertThat(clientRepository.findAll()).isNotNull();

    }

    @Test
    void addClient() {
        Client client1 = new Client(1L, "test1@gmail.com","1222222","test1",12,"homme",true);
        Mockito.when(clientRepository.save(client1))
                .thenReturn(client1);
        assertThat(clientRepository.save(client1)).isEqualTo(client1);
    }

    @Test
    void deleteClient() {
        Client client1 = new Client(1L, "test1@gmail.com","1222222","test1",12,"homme",true);
        clientRepository.deleteById(client1.getId());
        assertThat(clientRepository.getById(client1.getId())).isNull();
    }

    @Test
    void findBySexTest(){
        Client client1 = new Client(1L, "test1@gmail.com","1222222","test1",12,"homme",true);
        Client client2 = new Client(2L, "test2@gmail.com","09876","test2",13,"homme",true);
        List<Client> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        Mockito.when(clientRepository.findBySex("homme")).thenReturn(clients);
        assertThat(clientRepository.findBySex("homme")).isNotNull();
    }

    @Test
    void findByEmailTest(){
        Client client1 = new Client(1L, "test1@gmail.com","1222222","test1",12,"homme",true);
        Mockito.when(clientRepository.getClientByEmail("test1@gmail.com")).thenReturn(client1);
        assertThat(clientRepository.getClientByEmail("test1@gmail.com").getEmail()).isEqualTo("test1@gmail.com");

    }

}