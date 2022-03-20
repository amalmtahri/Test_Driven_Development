package com.tdd.app.service;

import com.tdd.app.dto.models.ClientDto;
import com.tdd.app.dto.service.IMapClassWithDto;
import com.tdd.app.entity.Client;
import com.tdd.app.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @Slf4j
@AllArgsConstructor
public class ClientService implements IClientService {

    @Autowired
    IMapClassWithDto<Client, ClientDto> clientMapping;

    @Autowired
    private ClientRepository repository;


    @Override
    public List<ClientDto> getClients() {
        List<Client> clients = repository.findAll();
        return clientMapping.convertListToListDto(clients, ClientDto.class);
    }

    @Override
    public ClientDto addClient(ClientDto clientDto){
        // convert DTO to entity
        Client clientRequest = clientMapping.convertToEntity(clientDto,Client.class);
        Client client = repository.save(clientRequest);
        // convert entity to DTO
        ClientDto clientResponse = clientMapping.convertToDto(client, ClientDto.class);
        return clientResponse;
    }

    @Override
    public ClientDto getClientById(long id){
        Client client= repository.findById(id).orElse(null);
        return clientMapping.convertToDto(client, ClientDto.class);
    }

    public String deleteClient(Long id){
        repository.deleteById(id);
        return "Client removed !!";
    }


    @Override
    public  ClientDto updateClient(ClientDto clientDto) {
        Client client = clientMapping.convertToEntity(clientDto,Client.class);
        Client client1=  repository.save(client);
        return clientMapping.convertToDto(client1, ClientDto.class);
    }

    public ClientDto getClientByEmail(String email){
        Client client = repository.getClientByEmail(email);
        log.info(String.valueOf(client));
        return clientMapping.convertToDto(client, ClientDto.class);
    }

    public List<ClientDto> findBySex(String sex) {
        List<Client> clients = repository.findBySex(sex);
        return clientMapping.convertListToListDto(clients, ClientDto.class);
    }

}
