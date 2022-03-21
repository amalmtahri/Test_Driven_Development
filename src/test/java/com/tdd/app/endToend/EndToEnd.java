package com.tdd.app.endToend;

import com.tdd.app.controller.ClientController;
import com.tdd.app.dto.models.ClientDto;
import com.tdd.app.dto.service.IMapClassWithDto;
import com.tdd.app.entity.Client;
import com.tdd.app.enumeration.Sex;
import com.tdd.app.repository.ClientRepository;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class EndToEnd {

    @InjectMocks
    private ClientController clientController;

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    IMapClassWithDto<Client, ClientDto> clientMapping;

    @BeforeEach
    public void setup() {
        clientController = new ClientController(clientService);
        clientService = new ClientService(clientMapping, clientRepository);
    }

    @Test
    void getClientsService() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12, Sex.Homme,true);
        ClientDto clientDto2 = new ClientDto(2L, "test2@gmail.com","+212612370807","test2",13,Sex.Homme,true);
        List<ClientDto> clientDtos = new ArrayList<>();
        clientDtos.add(clientDto1);
        clientDtos.add(clientDto2);
        Mockito.when(clientService.getClients()).thenReturn(clientDtos);
        assertThat(clientService.getClients()).isNotNull();
    }

    @Test
    void addClientService() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(clientService.addClient(clientDto1))
                .thenReturn(clientDto1);
        assertThat(clientService.addClient(clientDto1)).isEqualTo(clientDto1);
    }

    @Test
    void getClientByIdService() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(clientService.getClientById(1L)).thenReturn(clientDto1);
        assertNotNull(clientService.getClientById(1L));
    }

    @Test
    void deleteClientService() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        assertThat(clientService.deleteClient(clientDto1.getId())).isEqualTo("Client removed !!");

    }

    @Test
    void updateClientService() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(clientService.updateClient(clientDto1))
                .thenReturn(clientDto1);
        assertThat(clientService.updateClient(clientDto1).getName()).isEqualTo("test1");
    }

    @Test
    void getClientByEmailTestService(){
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(clientService.getClientByEmail("test1@gmail.com")).thenReturn(clientDto1);
        assertThat(clientService.getClientByEmail("test1@gmail.com").getEmail()).isEqualTo("test1@gmail.com");
    }

    @Test
    void findBySexTestService(){
        ClientDto client1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        ClientDto client2 = new ClientDto(2L, "test2@gmail.com","+212653476087","test2",13,Sex.Homme,true);
        List<ClientDto> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        Mockito.when(clientService.findBySex("homme")).thenReturn(clients);
        assertThat(clientService.findBySex("homme")).isNotNull();
    }



    @Test
    void getAllClientsController() {

        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12, Sex.Homme,true);
        ClientDto clientDto2 = new ClientDto(2L, "test2@gmail.com","+212655667087","test2",13,Sex.Homme,true);
        List<ClientDto> clientDtos = new ArrayList<>();
        clientDtos.add(clientDto1);
        clientDtos.add(clientDto2);
        ResponseEntity<List<ClientDto>> clientDtoResponseEntity = clientController.getAllClients();
        assertThat(clientDtoResponseEntity).isNotNull();

    }

    @Test
    void addClientController() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(clientController.addClient(clientDto1))
                .thenReturn(clientDto1);
        assertThat(clientController.addClient(clientDto1)).isEqualTo(clientDto1);
    }

    @Test
    void findByIdController() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(clientController.findById(1L)).thenReturn(clientDto1);
        assertNotNull(clientController.findById(1L));
    }


    @Test
    void updateClientController() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(clientController.updateClient(clientDto1))
                .thenReturn(clientDto1);
        assertThat(clientController.updateClient(clientDto1).getName()).isEqualTo("test1");

    }

    @Test
    void findByEmailTestController(){
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(clientController.findByEmail("test1@gmail.com")).thenReturn(clientDto1);
        assertThat(clientController.findByEmail("test1@gmail.com").getEmail()).isEqualTo("test1@gmail.com");
    }

    @Test
    void findBySexTestController(){
        ClientDto client1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        ClientDto client2 = new ClientDto(2L, "test2@gmail.com","+212659987087","test2",13,Sex.Homme,true);
        List<ClientDto> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        ResponseEntity<List<ClientDto>> clientDtoResponseEntity = clientController.findBySex("homme");
        assertThat(clientDtoResponseEntity).isNotNull();
    }



    @Test
    void getAllClientsRepo() {
        Client client1 = new Client(1L, "test1@gmail.com","+212659697087","test1",12, Sex.Homme,true);
        Client client2 = new Client(2L, "test2@gmail.com","+212687345087","test2",13,Sex.Homme,true);
        List<Client> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        Mockito.when(clientRepository.findAll()).thenReturn(clients);
        assertThat(clientRepository.findAll()).isNotNull();
    }

    @Test
    void addClientRepo() {
        Client client1 = new Client(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(clientRepository.save(client1))
                .thenReturn(client1);
        assertThat(clientRepository.save(client1)).isEqualTo(client1);
    }

    @Test
    void deleteClientRepo() {
        Client client1 = new Client(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        clientRepository.deleteById(client1.getId());
        assertThat(clientRepository.getById(client1.getId())).isNull();
    }

    @Test
    void findBySexTestRepo(){
        Client client1 = new Client(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Client client2 = new Client(2L, "test2@gmail.com","+212659233447","test2",13,Sex.Homme,true);
        List<Client> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        Mockito.when(clientRepository.findBySex("homme")).thenReturn(clients);
        assertThat(clientRepository.findBySex("homme")).isNotNull();
    }

    @Test
    void findByEmailTestRepo(){
        Client client1 = new Client(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(clientRepository.getClientByEmail("test1@gmail.com")).thenReturn(client1);
        assertThat(clientRepository.getClientByEmail("test1@gmail.com").getEmail()).isEqualTo("test1@gmail.com");
    }

    @Test
    void findByIdRepo(){
        Client client1 = new Client(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(clientRepository.findById(client1.getId())).thenReturn(Optional.of(client1));
        assertThat(clientRepository.findById(client1.getId())).isNotNull();
    }
}
