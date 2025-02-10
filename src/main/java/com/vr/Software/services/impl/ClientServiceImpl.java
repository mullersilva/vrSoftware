package com.vr.Software.services.impl;

import com.vr.Software.dtos.ClientDTO;
import com.vr.Software.entities.ClientEntity;
import com.vr.Software.repositories.ClientRepository;
import com.vr.Software.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientEntity> listAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteClient (Long codigo) {
        clientRepository.deleteById(codigo);
    }

    @Override
    public ClientEntity persistClient(ClientEntity client) {
        return clientRepository.save(client);
    }
}
