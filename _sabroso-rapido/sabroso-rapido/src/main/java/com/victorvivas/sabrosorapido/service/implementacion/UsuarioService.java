package com.victorvivas.sabrosorapido.service.implementacion;

import com.victorvivas.sabrosorapido.dto.entrada.UsuarioDTOEntrada;
import com.victorvivas.sabrosorapido.dto.modificacion.UsuarioDTOEntradaModificar;
import com.victorvivas.sabrosorapido.dto.salida.UsuarioDTOSalida;
import com.victorvivas.sabrosorapido.entity.Usuario;
import com.victorvivas.sabrosorapido.exceptiones.BadRequestException;
import com.victorvivas.sabrosorapido.exceptiones.ResourceNotFoundException;
import com.victorvivas.sabrosorapido.repository.UsuarioRepository;
import com.victorvivas.sabrosorapido.service.InterfaceUsuarioService;
import com.victorvivas.sabrosorapido.utils.JsonPrinter;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService implements InterfaceUsuarioService {
    private final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    //OK<<----
    public UsuarioDTOSalida crearUsuario(UsuarioDTOEntrada usuario) {
        //convertimos mediante el mapper de dtoEntrada a entidad
        LOGGER.info("UsuarioDTOEntrada: " + JsonPrinter.toString(usuario));
        Usuario usuarioEntidad = modelMapper.map(usuario, Usuario.class);

        //mandamos a persistir a la capa dao y obtenemos una entidad
        Usuario usuarioAPersistir = usuarioRepository.save(usuarioEntidad);
        //transformamos la entidad obtenida en salidaDto
        UsuarioDTOSalida usuarioDTOSalida = modelMapper.map(usuarioAPersistir, UsuarioDTOSalida.class);
        LOGGER.info("UsuarioDTOSalida: " + JsonPrinter.toString(usuarioDTOSalida));
        return usuarioDTOSalida;
    }

    //OK<<----
    @Override
    public List<UsuarioDTOSalida> obtenerTodosLosUsuarios() {
        List<UsuarioDTOSalida> usuariosDTOSalidas = usuarioRepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTOSalida.class)).toList();

        LOGGER.info("Listado de todos los 'usuarios': {}", JsonPrinter.toString(usuariosDTOSalidas));

        return usuariosDTOSalidas;
    }

    //OK<<----
    @Override
    public UsuarioDTOSalida obtenerUsuarioPorId(Long id) {
        Usuario usuarioBuscado = usuarioRepository.findById(id).orElse(null);

        if (usuarioBuscado != null) {
            return modelMapper.map(usuarioBuscado, UsuarioDTOSalida.class);
        } else {
            LOGGER.error("El id {} no se encuentra registrado en la base de datos", id);
            throw new BadRequestException("No se encontró el 'Usuario' con el ID: " + id);
        }
    }

    //OK<<----
    @Override
    public UsuarioDTOSalida actualizarUsuario(UsuarioDTOEntradaModificar usuario) {
        Usuario usuarioRecibida = modelMapper.map(usuario, Usuario.class);
        Usuario usuarioAActualizar = usuarioRepository.findById(usuario.getId()).orElse(null);
        UsuarioDTOSalida usuarioDTOSalida = null;

        if (usuarioAActualizar != null) {

            usuarioAActualizar = usuarioRecibida;
            usuarioRepository.save(usuarioAActualizar);

            usuarioDTOSalida = modelMapper.map(usuarioAActualizar, UsuarioDTOSalida.class);

            LOGGER.warn("Usuario actualizado: {}", JsonPrinter.toString(usuarioDTOSalida));

        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el 'Usuario' no se encuentra registrada");
            throw new BadRequestException("No fue posible actualizar los datos ya que el 'Usuario' no se encuentra registrada");
        }
        return usuarioDTOSalida;
    }

    //OK<<---
    @Override
    public void eliminarUsuario(Long id) {
        if (obtenerUsuarioPorId(id) != null) {
            usuarioRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el 'Usuario' con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el 'Usuario' con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el 'Usuario' con id " + id);
        }

    }

    private void configureMapping() {
        modelMapper.typeMap(UsuarioDTOEntrada.class, Usuario.class)
                .addMappings(mapper -> {
                    mapper.map(UsuarioDTOEntrada::getDirecciones, Usuario::setDirecciones);
                    mapper.map(UsuarioDTOEntrada::getTelefonos, Usuario::setTelefonos);
                });

        modelMapper.typeMap(Usuario.class, UsuarioDTOSalida.class)
                .addMappings(modelMapper -> {
                    modelMapper.map(Usuario::getDirecciones, UsuarioDTOSalida::setDirecciones);
                    modelMapper.map(Usuario::getTelefonos, UsuarioDTOSalida::setTelefonos);
                });

        modelMapper.typeMap(UsuarioDTOEntradaModificar.class, Usuario.class)
                .addMappings(mapper -> {
                    mapper.map(UsuarioDTOEntradaModificar::getDireccionDTOEntradaModificar, Usuario::setDirecciones);
                    mapper.map(UsuarioDTOEntradaModificar::getTelefonoDTOEntradaModificar, Usuario::setTelefonos);
                });

    }
}
