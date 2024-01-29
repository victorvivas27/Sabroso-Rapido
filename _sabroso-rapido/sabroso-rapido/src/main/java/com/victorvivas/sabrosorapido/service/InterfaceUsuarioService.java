package com.victorvivas.sabrosorapido.service;

import com.victorvivas.sabrosorapido.dto.entrada.UsuarioDTOEntrada;
import com.victorvivas.sabrosorapido.dto.modificacion.UsuarioDTOEntradaModificar;
import com.victorvivas.sabrosorapido.dto.salida.UsuarioDTOSalida;

import java.util.List;

public interface InterfaceUsuarioService {
    UsuarioDTOSalida crearUsuario(UsuarioDTOEntrada usuarioDTOEntrada);//OK<<----

    List<UsuarioDTOSalida> obtenerTodosLosUsuarios();//OK<<----

    UsuarioDTOSalida obtenerUsuarioPorId(Long id);//OK<<----

    UsuarioDTOSalida actualizarUsuario(UsuarioDTOEntradaModificar usuarioDTOEntradaModificar);//OK<<----

    void eliminarUsuario(Long id);//OK<<----
}
