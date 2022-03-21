package com.tdd.app.controller;

import com.tdd.app.dto.models.ClientDto;
import com.tdd.app.enumeration.Sex;
import com.tdd.app.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @MockBean
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientController clientController;


    @Test
    @Disabled
    void addClient() throws Exception {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);

        when(clientService.addClient(clientDto1)).thenReturn(clientDto1);
        mockMvc.perform(post("/addClient")
                    .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"soumia@gmail.com\",\"phone\": 123456789,\"name\": \"soumiaKab\",\"age\": 24,\"sex\": \"Femme\"}"))
               .andExpect(status().isNotFound());
    }

    @Test
    void findById() throws Exception {
        ClientDto clientDto1 = new ClientDto(1L, "test1@gmail.com","+212659697087","test1",12,Sex.Homme,true);
        when(clientService.addClient(clientDto1)).thenReturn(clientDto1);
        mockMvc.perform(MockMvcRequestBuilders.get("/findBy/{id}",1L))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllClients() throws Exception{
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/getAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }




}