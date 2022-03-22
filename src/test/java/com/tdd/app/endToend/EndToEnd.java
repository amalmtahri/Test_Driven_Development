package com.tdd.app.endToend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.app.controller.ClientController;
import com.tdd.app.dto.models.ClientDto;
import com.tdd.app.dto.service.IMapClassWithDto;
import com.tdd.app.entity.Client;
import com.tdd.app.enumeration.Sex;
import com.tdd.app.repository.ClientRepository;
import com.tdd.app.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.Silent.class)
@WebMvcTest(ClientController.class)
public class EndToEnd {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @InjectMocks
    private ClientService service;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    IMapClassWithDto<Client, ClientDto> clientMapping;

    @BeforeEach
    public void setup() {
        clientService = new ClientService(clientMapping, clientRepository);
    }

    @Test
    void getClientsService() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12, Sex.Homme,true);
        ClientDto clientDto2 = new ClientDto(2L, "test2@gmail.com","+212612370807","test2",13,Sex.Homme,true);
        List<ClientDto> clientDtos = new ArrayList<>();
        clientDtos.add(clientDto1);
        clientDtos.add(clientDto2);
        Mockito.when(service.getClients()).thenReturn(clientDtos);
        assertThat(service.getClients()).isNotNull();
    }

    @Test
    void addClientService() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(service.addClient(clientDto1))
                .thenReturn(clientDto1);
        assertThat(service.addClient(clientDto1)).isEqualTo(clientDto1);
    }

    @Test
    void getClientByIdService() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(service.getClientById(1L)).thenReturn(clientDto1);
        assertNotNull(service.getClientById(1L));
    }

    @Test
    void deleteClientService() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        assertThat(service.deleteClient(clientDto1.getId())).isEqualTo("Client removed !!");

    }

    @Test
    void updateClientService() {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(service.updateClient(clientDto1))
                .thenReturn(clientDto1);
        assertThat(service.updateClient(clientDto1).getName()).isEqualTo("test1");
    }

    @Test
    void getClientByEmailTestService(){
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        Mockito.when(service.getClientByEmail("test1@gmail.com")).thenReturn(clientDto1);
        assertThat(service.getClientByEmail("test1@gmail.com").getEmail()).isEqualTo("test1@gmail.com");
    }

    @Test
    void findBySexTestService(){
        ClientDto client1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        ClientDto client2 = new ClientDto(2L, "test2@gmail.com","+212653476087","test2",13,Sex.Homme,true);
        List<ClientDto> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        Mockito.when(service.findBySex("homme")).thenReturn(clients);
        assertThat(service.findBySex("homme")).isNotNull();
    }



    @Test
    void getAllClientsController() throws Exception {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        ClientDto clientDto2 = new ClientDto(2L, "test2@gmail.com","+212659987087","test2",12,Sex.Homme,true);
        List<ClientDto> clientDtos = new ArrayList<>();
        clientDtos.add(clientDto1);
        clientDtos.add(clientDto2);
        when(clientService.getClients()).thenReturn(clientDtos);
        mockMvc.perform(get("/api/client/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void addClientController() throws Exception {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        when(clientService.addClient(clientDto1))
                .thenReturn(clientDto1);

        mockMvc.perform(post("/api/client/addClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clientDto1)))
                .andExpect(status().isOk());

    }

    @Test
    void findByIdController() throws Exception {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        when(clientService.getClientById(clientDto1.getId())).thenReturn(clientDto1);
        mockMvc.perform(get("/api/client/findBy/" + clientDto1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void updateClientController() throws Exception {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);

        when(clientService.addClient(clientDto1))
                .thenReturn(clientDto1);
        mockMvc.perform(put("/api/client/updateClient/"+ clientDto1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clientDto1)))
                .andExpect(status().isOk());


    }

    @Test
    void findByEmailTestController() throws Exception {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        when(clientService.getClientByEmail(clientDto1.getEmail())).thenReturn(clientDto1);
        mockMvc.perform(get("/api/client/findByEmail/" + clientDto1.getEmail())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findBySexTestController() throws Exception {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        ClientDto clientDto2 = new ClientDto(2L, "test2@gmail.com","+212698762287","test2",12,Sex.Homme,true);
        List<ClientDto> clientDtos = new ArrayList<>();
        clientDtos.add(clientDto1);
        clientDtos.add(clientDto2);
        when(clientService.findBySex("Homme")).thenReturn(clientDtos);
        mockMvc.perform(get("/api/client/findBySex/" + clientDto1.getSex())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
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
