package com.vr.Software.services;

import com.vr.Software.dtos.ClientDTO;
import com.vr.Software.entities.ClientEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface ClientService {
    public List<ClientEntity> listAllClients();
    public void deleteClient(Long codigo);
    public ClientEntity persistClient(ClientEntity client);

}
