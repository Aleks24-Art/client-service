package com.client.core.util.mapper;

import com.client.core.model.dto.ClientDto;
import com.client.core.model.dto.CreateClientDto;
import com.client.core.model.entity.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface ClientMapper {

    List<ClientDto> toDtos(List<Client> clients);

    ClientDto toDto(Client client);

    Client toClient(CreateClientDto createClientDto);
}
