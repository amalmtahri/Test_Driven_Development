package com.tdd.app.controller;


import com.tdd.app.dto.models.ClientDto;
import com.tdd.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {


    @Autowired
    private ClientService clientService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ClientDto>> getAllClients(){
        List<ClientDto> clientDtos = clientService.getClients();
        return  ResponseEntity.ok(clientDtos);
    }

    @PostMapping("addClient")
    public ClientDto addClient(@RequestBody ClientDto clientDto){
        return clientService.addClient(clientDto);
    }

    @GetMapping("/findBy/{id}")
    public ClientDto findById(@PathVariable long id){
        return clientService.getClientById(id);
    }

    @DeleteMapping("/deleteClient/{id}")
    public String deleteProfile(@PathVariable long id){
        return clientService.deleteClient(id);
    }

    @PutMapping("/updateClient/{id}")
    public ClientDto updateClient(@RequestBody ClientDto clientDto){
        return clientService.updateClient(clientDto);
    }

}
