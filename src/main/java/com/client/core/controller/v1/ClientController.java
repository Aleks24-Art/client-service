package com.client.core.controller.v1;

import com.client.core.config.SwaggerConfig;
import com.client.core.model.dto.ClientDto;
import com.client.core.model.dto.CreateClientDto;
import com.client.core.model.entity.Client;
import com.client.core.service.ClientService;
import com.client.core.util.mapper.ClientMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Slf4j
@RestController
@Validated
@Api(tags = SwaggerConfig.CLIENT)
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    @ApiOperation("Получение клиента по логину")
    @GetMapping("/v1/client/{login}")
    public ClientDto findClientByLogin(
            @ApiParam(value = "Логин клиента", example = "ivan.ivanov")
            @PathVariable @NotBlank(message = "Логин клиента не может быть пустым") String login) {
        return clientMapper.toDto(clientService.findByLogin(login));
    }

    @ApiOperation("Сохранение клиента")
    @PostMapping("/v1/client")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto saveClient(
            @ApiParam("Модель для создания клиента")
            @RequestBody @Valid CreateClientDto createClientDto) {
        clientService.checkLoginUniqueness(createClientDto.getLogin());
        Client clientToSave = clientMapper.toClient(createClientDto);
        Client savedClient = clientService.save(clientToSave);
        return clientMapper.toDto(savedClient);
    }

    @ApiOperation("Обновление данных клиента")
    @PutMapping("/v1/client/{id}")
    public ClientDto updateClient(
            @ApiParam("ID клиента")
            @PathVariable @NotNull @Positive(message = "ID должно быть больше 0") Long id,
            @ApiParam("Модель для обновления данных клиента")
            @RequestBody @Valid CreateClientDto updateClientDto) {
        clientService.findById(id);
        Client clientToUpdate = clientMapper.toClient(updateClientDto);
        clientToUpdate.setId(id);
        Client updatedClient = clientService.update(clientToUpdate);
        return clientMapper.toDto(updatedClient);
    }

    @ApiOperation("Удаление клиента")
    @DeleteMapping("/v1/client/{login}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(
            @ApiParam("Логин клиента")
            @PathVariable @NotBlank(message = "Логин клиента не может быть пустым") String login) {
        clientService.findByLogin(login);
        clientService.deleteByLogin(login);
    }
}
