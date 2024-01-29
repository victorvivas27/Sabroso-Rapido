package com.victorvivas.sabrosorapido.dto.salida;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTOSalida {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String dni;
    private Date fechaNacimiento;
    private String password;
    private Set<DireccionDTOSalida> direcciones = new HashSet<>();
    private Set<TelefonoDTOSalida> telefonos = new HashSet<>();


}
