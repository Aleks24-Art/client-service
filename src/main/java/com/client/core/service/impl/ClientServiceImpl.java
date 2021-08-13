package com.client.core.service.impl;

import com.client.core.exception.ClientLoginAlreadyRegisteredException;
import com.client.core.exception.ClientNotFoundException;
import com.client.core.model.entity.Client;
import com.client.core.repository.ClientRepository;
import com.client.core.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client findById(Long id) {
        return clientRepository
                .findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    @Override
    public Client findByLogin(String login) {
        return clientRepository
                .findByLogin(login)
                .orElseThrow(() -> new ClientNotFoundException(login));
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client newClient) {
        return clientRepository.save(newClient);
    }

    @Override
    @Transactional
    public void deleteByLogin(String login) {
        clientRepository.deleteByLogin(login);
    }

    @Override
    @Transactional
    public void checkLoginUniqueness(String login) {
        clientRepository
                .findByLogin(login)
                .ifPresent(client -> {
                    throw new ClientLoginAlreadyRegisteredException(client.getLogin());
                });
    }
}
