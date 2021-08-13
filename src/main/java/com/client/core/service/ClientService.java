package com.client.core.service;

import com.client.core.model.entity.Client;

public interface ClientService {

    Client findById(Long id);

    Client findByLogin(String login);

    Client save(Client mark);

    Client update(Client newClient);

    void deleteByLogin(String login);

    void checkLoginUniqueness(String login);
}
