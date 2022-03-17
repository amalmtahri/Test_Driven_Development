package com.tdd.app.service;

import com.tdd.app.dto.models.ClientDto;
import com.tdd.app.dto.service.IMapClassWithDto;
import com.tdd.app.entity.Client;
import com.tdd.app.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository repository;

    @Mock
    IMapClassWithDto<Client, ClientDto> clientMapping;



    @BeforeEach
    public void setup() {
        clientService = new ClientService(clientMapping, repository);
    }

    @Test
    void getClients() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","1222222","test1",12,"homme",true);
        ClientDto clientDto2 = new ClientDto(2L, "test2@gmail.com","09876","test2",13,"homme",true);
        List<ClientDto> clientDtos = new ArrayList<>();
        clientDtos.add(clientDto1);
        clientDtos.add(clientDto2);
        Mockito.when(clientService.getClients()).thenReturn(clientDtos);
        assertThat(clientService.getClients()).isNotNull();
    }

    @Test
    void addClient() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","1222222","test1",12,"homme",true);
        Mockito.when(clientService.addClient(clientDto1))
                .thenReturn(clientDto1);
        assertThat(clientService.addClient(clientDto1)).isEqualTo(clientDto1);

    }

    @Test
    void getClientById() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","1222222","test1",12,"homme",true);
        Mockito.when(clientService.getClientById(1L)).thenReturn(clientDto1);
        assertNotNull(clientService.getClientById(1L));
    }

    @Test
    void deleteClient() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","1222222","test1",12,"homme",true);
        assertThat(clientService.deleteClient(clientDto1.getId())).isEqualTo("Client removed !!");

    }

    @Test
    void updateClient() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","1222222","test1",12,"homme",true);
        Mockito.when(clientService.updateClient(clientDto1))
                .thenReturn(clientDto1);
        assertThat(clientService.updateClient(clientDto1).getName()).isEqualTo("test1");

    }
}