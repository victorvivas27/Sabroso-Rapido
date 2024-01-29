package com.victorvivas.sabrosorapido.service.implementacion;

import com.victorvivas.sabrosorapido.dto.entrada.TelefonoDTOEntrada;
import com.victorvivas.sabrosorapido.dto.entrada.TelefonoDTOEntradaNuevo;
import com.victorvivas.sabrosorapido.dto.modificacion.TelefonoDTOEntradaModificar;
import com.victorvivas.sabrosorapido.dto.salida.TelefonoDTOSalida;
import com.victorvivas.sabrosorapido.entity.Telefono;
import com.victorvivas.sabrosorapido.entity.Usuario;
import com.victorvivas.sabrosorapido.exceptiones.BadRequestException;
import com.victorvivas.sabrosorapido.exceptiones.ResourceNotFoundException;
import com.victorvivas.sabrosorapido.repository.TelefonoRepository;
import com.victorvivas.sabrosorapido.repository.UsuarioRepository;
import com.victorvivas.sabrosorapido.service.InterfaceTelefonoService;
import com.victorvivas.sabrosorapido.utils.JsonPrinter;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor

public class TelefonoService implements InterfaceTelefonoService {
    private final Logger LOGGER = LoggerFactory.getLogger(TelefonoService.class);
    private final TelefonoRepository telefonoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    //OK<<----
    @Override
    public List<TelefonoDTOSalida> obtenerTodosLosTelefonos() {
        List<TelefonoDTOSalida> telefonosDTOSalidas = telefonoRepository.findAll().stream()
                .map(telefono -> modelMapper.map(telefono, TelefonoDTOSalida.class)).toList();
        LOGGER.info("Listado de todos los telefonos: {}", JsonPrinter.toString(telefonosDTOSalidas));
        return telefonosDTOSalidas;
    }

    //OK<<----
    @Override
    public TelefonoDTOSalida obtenerTelefonoPorId(Long id) {
        Telefono telefonoBuscado = telefonoRepository.findById(id).orElse(null);

        if (telefonoBuscado != null) {
            return modelMapper.map(telefonoBuscado, TelefonoDTOSalida.class);
        } else {
            LOGGER.error("El id {} no se encuentra registrado en la base de datos", id);
            throw new BadRequestException("No se encontró el teléfono con el ID: " + id);
        }
    }

    //OK<<----
    @Override
    public TelefonoDTOSalida crearTelefono(TelefonoDTOEntrada telefonoDTOEntrada) {
        Telefono telefonoGuardado = telefonoRepository.save(dtoEntradaAEntidad((telefonoDTOEntrada)));
        TelefonoDTOSalida telefonoDTOSalida = modelMapper.map(telefonoGuardado, TelefonoDTOSalida.class);
        LOGGER.info("Telefono guardado: {}", JsonPrinter.toString(telefonoDTOSalida));
        return telefonoDTOSalida;
    }

    @Transactional
    @Override
    public TelefonoDTOSalida crearTelefonoParaUsuario(TelefonoDTOEntradaNuevo telefonoDTOEntradaNuevo) {
        Telefono telefonoGuardado = telefonoRepository.save(dtoEntradaAEntidad(telefonoDTOEntradaNuevo));
        Usuario usuarioActualizar = usuarioRepository.findById(telefonoDTOEntradaNuevo.getUsuario_id()).orElse(null);

        if (usuarioActualizar != null) {
            usuarioActualizar.getTelefonos().add(telefonoGuardado);
            usuarioRepository.save(usuarioActualizar);
            TelefonoDTOSalida telefonoDTOSalida = modelMapper.map(telefonoGuardado, TelefonoDTOSalida.class);
            LOGGER.info("Telefono nueva guardada: {}", JsonPrinter.toString(telefonoDTOSalida));
            return telefonoDTOSalida;
        } else {

            LOGGER.error("Telefono no encontrado con ID: {}", telefonoDTOEntradaNuevo.getUsuario_id());

            throw new IllegalArgumentException("Usuario no encontrado con ID: " + telefonoDTOEntradaNuevo.getUsuario_id());
        }
    }

    //OK<<----
    @Override
    public TelefonoDTOSalida actualizarTelefono(TelefonoDTOEntradaModificar telefonoDtoEntradaModificar) {
        Telefono telefonoRecibido = modelMapper.map(telefonoDtoEntradaModificar, Telefono.class);
        Telefono telefonoAActualizar = telefonoRepository.findById(telefonoDtoEntradaModificar.getId()).orElse(null);
        TelefonoDTOSalida telefonoDTOSalida = null;

        if (telefonoAActualizar != null) {

            telefonoAActualizar = telefonoRecibido;
            telefonoRepository.save(telefonoAActualizar);

            telefonoDTOSalida = modelMapper.map(telefonoAActualizar, TelefonoDTOSalida.class);

            LOGGER.warn("Telefono actualizado: {}", JsonPrinter.toString(telefonoDTOSalida));

        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el 'telefono' no se encuentra registrado");
            throw new BadRequestException("No fue posible actualizar los datos ya que el 'telefono' no se encuentra registrado");
        }


        return telefonoDTOSalida;
    }

    //OK<<----
    @Override
    public void eliminarTelefono(Long id) {
        if (obtenerTelefonoPorId(id) != null) {
            telefonoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el 'Telefono' con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el 'Telefono' con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el Telefono con id " + id);
        }
    }


    private Telefono dtoEntradaAEntidad(TelefonoDTOEntrada telefonoDTOEntrada) {
        return modelMapper.map(telefonoDTOEntrada, Telefono.class);
    }

    private Telefono dtoEntradaAEntidad(TelefonoDTOEntradaNuevo telefonoDTOEntradaNuevo) {
        return modelMapper.map(telefonoDTOEntradaNuevo, Telefono.class);
    }
}
