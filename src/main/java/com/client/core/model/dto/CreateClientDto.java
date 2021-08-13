package com.client.core.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ToString
@Setter
@Getter
@NoArgsConstructor
@ApiModel(description = "Создание клиента")
public class CreateClientDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Логин клиента не может быть пустым")
    @ApiModelProperty(value = "Уникальный логин клиента", example = "ivan.ivanov")
    private String login;

    @NotBlank(message = "Имя клиента не может быть пустым")
    @ApiModelProperty(value = "Имя клиента", example = "Иван")
    private String name;

    @Min(value = 18, message = "Возраст клиента должен быть больше 18")
    @ApiModelProperty(value = "Возраст клиента", example = "23")
    private Integer age;
}
