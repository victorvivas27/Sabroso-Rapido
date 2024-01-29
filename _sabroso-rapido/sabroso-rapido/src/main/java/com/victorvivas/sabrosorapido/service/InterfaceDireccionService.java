package com.victorvivas.sabrosorapido.service;

import com.victorvivas.sabrosorapido.dto.entrada.DireccionDTOEntrada;
import com.victorvivas.sabrosorapido.dto.entrada.DireccionDTOEntradaNueva;
import com.victorvivas.sabrosorapido.dto.modificacion.DireccionDTOEntradaModificar;
import com.victorvivas.sabrosorapido.dto.salida.DireccionDTOSalida;

import java.util.List;

public interface InterfaceDireccionService {
    DireccionDTOSalida crearDireccion(DireccionDTOEntrada direccionDTOEntrada);//OK<<----


    List<DireccionDTOSalida> obtenerTodasLasDirecciones();//OK<<----

    DireccionDTOSalida obtenerDireccionPorId(Long id);//OK<<----


    DireccionDTOSalida actualizarDireccion(DireccionDTOEntradaModificar direccionDTOEntradaModificar);//OK<<----

    void eliminarDireccion(Long id);

    DireccionDTOSalida crearDireccionParaUsuario(DireccionDTOEntradaNueva direccionDTOEntradaNueva);//OK<<----
}
