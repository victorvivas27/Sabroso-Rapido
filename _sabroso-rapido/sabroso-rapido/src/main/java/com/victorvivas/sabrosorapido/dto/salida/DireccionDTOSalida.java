package com.victorvivas.sabrosorapido.dto.salida;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTOSalida {
    private Long id;
    private String calle;
    private Long numero;
    private String dpto;
    private String pais;
    private String provincia;
    private String localidad;
    private String barrio;


}
