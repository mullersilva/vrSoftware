package com.vr.Software.controllers;


import com.vr.Software.entities.ClientEntity;
import com.vr.Software.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vr-api/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = "/get-all-clients", produces = "application/json")
    public List<ClientEntity> listAllClients () {
        return clientService.listAllClients();
    }

    @DeleteMapping(path = "/delete-client/{codigo}", produces = "application/json")
    public void deleteClient (@PathVariable Long codigo) {
        clientService.deleteClient(codigo);
    }

    @PostMapping(path = "/persist-client", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClientEntity> persistClient(@Valid @RequestBody ClientEntity client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.persistClient(client));
    }
}
