package com.henrique.services;

import com.henrique.models.Client;
import com.henrique.models.Role;
import com.henrique.modelsDTO.ClientDTO;
import com.henrique.repositories.ClientRepository;
import com.henrique.security.UserSS;
import com.henrique.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Client findOne(Long id){

        UserSS user = UserService.authenticated();
        if (user==null || !user.hasRole(Role.ADMIN) && !id.equals(user.getId())) {
            throw new ResponseStatusException(FORBIDDEN, "Client with ID "+ id +" not authorized.");
        }



        Optional<Client> client = clientRepository.findById(id);
        return client.orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Client with ID "+ id +" not found."));
    }

    public List<ClientDTO> findAll(){
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    public Client save(Client client){
        client.setId(null);
        client.setCreatedAt(LocalDateTime.now());
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
    }

    public Client update(Client client){
        findOne(client.getId());
        client.setUpdatedAt(LocalDateTime.now());
        return clientRepository.save(client);
    }

    public void delete(Long id){
        findOne(id);
        clientRepository.deleteById(id);
    }

    public Client fromDTO(ClientDTO clientDTO){
        return new Client(clientDTO.getId(),clientDTO.getName(),clientDTO.getEmail(),clientDTO.getPassword(),null,null);
    }

    public Client findByEmail(String email) {

        Client obj = clientRepository.findByEmail(email);
        if (obj == null) {
            throw new ResponseStatusException(NOT_FOUND, "Client with Email "+ email +" not found.");
        }
        return obj;
    }
}
