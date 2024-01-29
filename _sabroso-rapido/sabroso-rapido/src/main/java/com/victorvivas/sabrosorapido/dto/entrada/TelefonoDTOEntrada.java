package com.victorvivas.sabrosorapido.dto.entrada;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefonoDTOEntrada {
    @NotNull(message = "El campo 'telefono' no debe estar en blanco")
    @Digits(integer = 15, fraction = 0, message = "El campo 'telefono' debe tener como máximo 15 dígitos.")
    private Long telefono;
}
