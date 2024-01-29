package com.victorvivas.sabrosorapido.dto.modificacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TelefonoDTOEntradaModificar {
    @NotNull(message = "El campo 'telefono' no debe estar en blanco")
    @Digits(integer = 15, fraction = 0, message = "El campo 'telefono' debe tener como máximo 15 dígitos.")
    public Long telefono;
    @NotNull(message = "El campo 'id' no puede ser nulo o en blanco.")
    private Long id;


}
