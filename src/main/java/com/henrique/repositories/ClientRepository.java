package com.henrique.repositories;

import com.henrique.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    @Transactional(readOnly=true)
    Client findByEmail(String email);
}
