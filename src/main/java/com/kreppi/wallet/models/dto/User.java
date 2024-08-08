package com.kreppi.wallet.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class User {
    @NotBlank(message = "El documento no puede estar vacío")
    private String document;
    @NotBlank(message = "El número de teléfono no puede estar vacío")
    private String nPhone;

}
