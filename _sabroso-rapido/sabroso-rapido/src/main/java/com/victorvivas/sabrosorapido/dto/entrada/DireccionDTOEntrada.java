package com.victorvivas.sabrosorapido.dto.entrada;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DireccionDTOEntrada {
    @NotNull(message = "El campo 'calle' no puede ser nulo")
    @NotBlank(message = "El campo 'calle' no puede estar en blanco")
    @Size(max = 50, message = "El campo 'calle' no puede superar los 50 caracteres.")
    private String calle;

    @NotNull(message = "El campo 'numero' no puede ser nulo")
    @Digits(integer = 5, fraction = 0, message = "El campo 'numero' debe tener como máximo 5 dígitos.")
    private Long numero;

    @Size(max = 5, message = "El campo 'dpto' debe tener como máximo 5 dígitos.")
    @NotBlank(message = "El campo 'dpto' no puede estar en blanco")
    private String dpto;

    @NotNull(message = "El campo 'pais' no puede ser nulo")
    @Size(max = 50, message = "El campo 'pais' no puede superar los 50 caracteres.")
    @NotBlank(message = "El campo 'pais' no puede estar en blanco")
    private String pais;

    @NotNull(message = "El campo 'provincia' no puede ser nulo")
    @Size(max = 50, message = "El campo 'provincia' no puede superar los 50 caracteres.")
    @NotBlank(message = "El campo 'provincia' no puede estar en blanco")
    private String provincia;

    @NotNull(message = "El campo 'localidad' no puede ser nulo")
    @Size(max = 50, message = "El campo 'localidad' no puede superar los 50 caracteres.")
    @NotBlank(message = "El campo 'localidad' no puede estar en blanco")
    private String localidad;

    @NotNull(message = "El campo 'barrio' no puede ser nulo")
    @Size(max = 50, message = "El campo 'barrio' no puede superar los 50 caracteres.")
    @NotBlank(message = "El campo 'barrio' no puede estar en blanco")
    private String barrio;


}
