package com.tdd.app.controller;


import com.tdd.app.dto.models.ClientDto;
import com.tdd.app.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientController {


    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<ClientDto>> getAllClients(){
        List<ClientDto> clientDtos = clientService.getClients();
        return  ResponseEntity.ok(clientDtos);
    }

    @PostMapping("/addClient")
    public ClientDto addClient(@RequestBody ClientDto clientDto){
        return clientService.addClient(clientDto);
    }

    @GetMapping("/findBy/{id}")
    public ClientDto findById(@PathVariable long id){
        return clientService.getClientById(id);
    }

    @DeleteMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable long id){
        return clientService.deleteClient(id);
    }

    @PutMapping("/updateClient/{id}")
    public ClientDto updateClient(@RequestBody ClientDto clientDto){
        return clientService.updateClient(clientDto);
    }
    @GetMapping("/findByEmail/{email}")
    public ClientDto findByEmail(@PathVariable("email") String email){
        return clientService.getClientByEmail(email);
    }

    @GetMapping("/findBySex/{sex}")
    public ResponseEntity<List<ClientDto>> findBySex(@PathVariable String sex) {
        List<ClientDto> clientDtos = clientService.findBySex(sex);
        return ResponseEntity.ok(clientDtos);
    }
}
