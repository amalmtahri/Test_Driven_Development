package com.tdd.app.service;

import com.tdd.app.dto.models.ClientDto;

import java.util.List;

public interface IClientService {
    List<ClientDto> getClients();

    ClientDto addClient(ClientDto clientDto);

    ClientDto getClientById(long id);

    ClientDto updateClient(ClientDto clientDto);
}
