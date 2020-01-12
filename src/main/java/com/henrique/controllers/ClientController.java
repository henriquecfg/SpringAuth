package com.henrique.controllers;

import com.henrique.models.Client;
import com.henrique.modelsDTO.ClientDTO;
import com.henrique.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value="/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Client create(@RequestBody ClientDTO clientDTO) {
        Client client = clientService.fromDTO(clientDTO);
        return clientService.save(client);
    }

    @PutMapping
    @ResponseStatus(OK)
    public Client update(@RequestBody ClientDTO clientDTO) {
        Client client = clientService.fromDTO(clientDTO);
        return clientService.update(client);
    }

    @GetMapping("/{client.id}")
    @ResponseStatus(OK)
    public ResponseEntity<Client> get(@PathVariable("client.id") Long clientId) {
        return ResponseEntity.status(OK).body(clientService.findOne(clientId));
    }

    @GetMapping("/")
    @ResponseStatus(OK)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<ClientDTO>> getAll() {
        return ResponseEntity.status(OK).body(clientService.findAll());
    }

    @DeleteMapping("/{client.id}")
    @ResponseStatus(NO_CONTENT)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void delete(@PathVariable("client.id") Long clientId) {
        clientService.delete(clientId);
    }
}
