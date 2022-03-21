package com.tdd.app.controller;

import com.tdd.app.dto.models.ClientDto;
import com.tdd.app.enumeration.Sex;
import com.tdd.app.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    public void setup() {
        clientController = new ClientController(clientService);
    }

    @Test
    void getAllClients() {

        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12, Sex.Homme,true);
        ClientDto clientDto2 = new ClientDto(2L, "test2@gmail.com","+212655667087","test2",13,Sex.Homme,true);
        List<ClientDto> clientDtos = new ArrayList<>();
        clientDtos.add(clientDto1);
        clientDtos.add(clientDto2);
        ResponseEntity<List<ClientDto>> clientDtoResponseEntity = clientController.getAllClients();
        assertThat(clientDtoResponseEntity).isNotNull();

    }

    @Test
    void addClient() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(clientController.addClient(clientDto1))
                .thenReturn(clientDto1);
        assertThat(clientController.addClient(clientDto1)).isEqualTo(clientDto1);
    }

    @Test
    void findById() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(clientController.findById(1L)).thenReturn(clientDto1);
        assertNotNull(clientController.findById(1L));
    }


    @Test
    void updateClient() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(clientController.updateClient(clientDto1))
                .thenReturn(clientDto1);
        assertThat(clientController.updateClient(clientDto1).getName()).isEqualTo("test1");

    }

    @Test
    void findByEmailTest(){
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(clientController.findByEmail("test1@gmail.com")).thenReturn(clientDto1);
        assertThat(clientController.findByEmail("test1@gmail.com").getEmail()).isEqualTo("test1@gmail.com");
    }

    @Test
    void findBySexTest(){
        ClientDto client1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        ClientDto client2 = new ClientDto(2L, "test2@gmail.com","+212659987087","test2",13,Sex.Homme,true);
        List<ClientDto> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        ResponseEntity<List<ClientDto>> clientDtoResponseEntity = clientController.findBySex("homme");
        assertThat(clientDtoResponseEntity).isNotNull();
    }
}