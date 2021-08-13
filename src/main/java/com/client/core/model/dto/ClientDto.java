package com.client.core.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ApiModel(description = "Клиент")
public class ClientDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID клиента", example = "10")
    private Long id;

    @ApiModelProperty(value = "Уникальный логин клиента", example = "ivan.ivanov")
    private String login;

    @ApiModelProperty(value = "Имя клиента", example = "Иван")
    private String name;

    @ApiModelProperty(value = "Возраст клиента", example = "23")
    private Integer age;
}