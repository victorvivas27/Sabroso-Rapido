package com.victorvivas.sabrosorapido.dto.modificacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTOEntradaModificar {
    @NotNull(message = "El campo 'id' no puede ser nulo o en blanco.")
    private Long id;

    @NotBlank(message = "El campo 'nombre' no puede estar en blanco")
    @NotNull(message = "El campo 'nombre' no puede ser nulo")
    @Size(max = 50, message = "El campo  'nombre' no puede superar los 50 caracteres.")
    private String nombre;

    @NotBlank(message = "El campo 'apellido' no puede estar en blanco")
    @NotNull(message = "El campo 'apellido' no puede ser nulo")
    @Size(max = 50, message = "El campo 'apellido' no puede superar los 50 caracteres.")
    private String apellido;

    @NotBlank(message = "El campo 'email' no puede estar en blanco")
    @NotNull(message = "El campo 'email' no puede ser nulo")
    @Email(message = "Proporciona una dirección de correo electrónico válida.")
    @Size(max = 25, message = "El campo 'email' no puede superar los 25 caracteres.")
    private String email;

    @NotBlank(message = "El campo 'dni' no puede estar en blanco")
    @NotNull(message = "El campo 'dni' no puede ser nulo")
    @Size(max = 15, message = "El campo 'dni' no puede superar los 15 caracteres.")
    private String dni;

    @NotNull(message = "El campo 'fechaNacimiento' no puede estar en blanco")
    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @NotBlank(message = "El campo 'password' no puede estar en blanco")
    @NotBlank(message = "El campo 'pasword' no puede estar en blanco")
    @Size(min = 5, max = 15, message = "La contraseña debe tener: minimo 5 caracteres y maximo 15 .")
    private String password;
    @NotNull(message = "El domicilio del usuario no puede ser nulo")
    @Valid
    private DireccionDTOEntradaModificar direccionDTOEntradaModificar;
    @NotNull(message = "El telefono del usuario no puede ser nulo")
    @Valid
    private TelefonoDTOEntradaModificar telefonoDTOEntradaModificar;
}

