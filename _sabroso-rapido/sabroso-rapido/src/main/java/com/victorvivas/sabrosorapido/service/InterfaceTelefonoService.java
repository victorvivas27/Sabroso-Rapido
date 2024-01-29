package com.victorvivas.sabrosorapido.service;

import com.victorvivas.sabrosorapido.dto.entrada.TelefonoDTOEntrada;
import com.victorvivas.sabrosorapido.dto.entrada.TelefonoDTOEntradaNuevo;
import com.victorvivas.sabrosorapido.dto.modificacion.TelefonoDTOEntradaModificar;
import com.victorvivas.sabrosorapido.dto.salida.TelefonoDTOSalida;

import java.util.List;

public interface InterfaceTelefonoService {
    List<TelefonoDTOSalida> obtenerTodosLosTelefonos(); //OK<<----


    TelefonoDTOSalida obtenerTelefonoPorId(Long id);//OK<<----

    TelefonoDTOSalida crearTelefono(TelefonoDTOEntrada telefonoDTOEntrada);//OK<<----

    TelefonoDTOSalida actualizarTelefono(TelefonoDTOEntradaModificar telefonoDtoEntradaModificar);//OK<<----

    void eliminarTelefono(Long id);//OK<<----

    TelefonoDTOSalida crearTelefonoParaUsuario(TelefonoDTOEntradaNuevo telefonoDTOEntradaNuevo);
}
