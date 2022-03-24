package com.tdd.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.app.dto.models.ClientDto;
import com.tdd.app.enumeration.Sex;
import com.tdd.app.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ClientController.class)
class ClientControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;


    @Test
    void getAllClients() throws Exception {
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
    void addClient() throws Exception {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        when(clientService.addClient(clientDto1))
                .thenReturn(clientDto1);
        mockMvc.perform(post("/api/client/addClient")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(clientDto1)))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        when(clientService.getClientById(clientDto1.getId())).thenReturn(clientDto1);
        mockMvc.perform(get("/api/client/findBy/" + clientDto1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void updateClient() throws Exception {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);

                when(clientService.addClient(clientDto1))
                .thenReturn(clientDto1);
        mockMvc.perform(put("/api/client/updateClient/"+ clientDto1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clientDto1)))
                .andExpect(status().isOk());
    }

    @Test
    void findByEmailTest() throws Exception {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        when(clientService.getClientByEmail(clientDto1.getEmail())).thenReturn(clientDto1);
        mockMvc.perform(get("/api/client/findByEmail/" + clientDto1.getEmail())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findBySexTest() throws Exception {
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
}