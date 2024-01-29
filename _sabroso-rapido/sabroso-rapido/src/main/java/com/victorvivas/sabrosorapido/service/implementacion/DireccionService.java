package com.victorvivas.sabrosorapido.service.implementacion;

import com.victorvivas.sabrosorapido.dto.entrada.DireccionDTOEntrada;
import com.victorvivas.sabrosorapido.dto.entrada.DireccionDTOEntradaNueva;
import com.victorvivas.sabrosorapido.dto.modificacion.DireccionDTOEntradaModificar;
import com.victorvivas.sabrosorapido.dto.salida.DireccionDTOSalida;
import com.victorvivas.sabrosorapido.entity.Direccion;
import com.victorvivas.sabrosorapido.entity.Usuario;
import com.victorvivas.sabrosorapido.exceptiones.BadRequestException;
import com.victorvivas.sabrosorapido.exceptiones.ResourceNotFoundException;
import com.victorvivas.sabrosorapido.repository.DireccionRepository;
import com.victorvivas.sabrosorapido.repository.UsuarioRepository;
import com.victorvivas.sabrosorapido.service.InterfaceDireccionService;
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
public class DireccionService implements InterfaceDireccionService {
    private final Logger LOGGER = LoggerFactory.getLogger(DireccionService.class);
    private final DireccionRepository direccionRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    //OK<<----
    @Override
    public DireccionDTOSalida crearDireccion(DireccionDTOEntrada direccionDTOEntrada) {
        Direccion direccionGuardado = direccionRepository.save(dtoEntradaAEntidad((direccionDTOEntrada)));
        DireccionDTOSalida direccionDTOSalida = modelMapper.map(direccionGuardado, DireccionDTOSalida.class);
        LOGGER.info("Direccion  guardada: {}", JsonPrinter.toString(direccionDTOSalida));
        return direccionDTOSalida;
    }

    @Transactional
    @Override
    public DireccionDTOSalida crearDireccionParaUsuario(DireccionDTOEntradaNueva direccionDTOEntradaNueva) {
        Direccion direccionGuardado = direccionRepository.save(dtoEntradaAEntidad(direccionDTOEntradaNueva));
        Usuario usuarioActualizar = usuarioRepository.findById(direccionDTOEntradaNueva.getUsuario_id()).orElse(null);

        if (usuarioActualizar != null) {
            usuarioActualizar.getDirecciones().add(direccionGuardado);
            usuarioRepository.save(usuarioActualizar);
            DireccionDTOSalida direccionDTOSalida = modelMapper.map(direccionGuardado, DireccionDTOSalida.class);
            LOGGER.info("Direccion nueva guardada: {}", JsonPrinter.toString(direccionDTOSalida));
            return direccionDTOSalida;
        } else {

            LOGGER.error("Usuario no encontrado con ID: {}", direccionDTOEntradaNueva.getUsuario_id());

            throw new IllegalArgumentException("Usuario no encontrado con ID: " + direccionDTOEntradaNueva.getUsuario_id());
        }
    }


    //OK <<----
    @Override
    public List<DireccionDTOSalida> obtenerTodasLasDirecciones() {
        List<DireccionDTOSalida> direccionesDTOSalidas = direccionRepository.findAll().stream()
                .map(direccion -> modelMapper.map(direccion, DireccionDTOSalida.class)).toList();
        LOGGER.info("Listado de todas las 'direcciones': {}", JsonPrinter.toString(direccionesDTOSalidas));
        return direccionesDTOSalidas;
    }

    //OK<<----
    @Override
    public DireccionDTOSalida obtenerDireccionPorId(Long id) {
        Direccion direccionBuscado = direccionRepository.findById(id).orElse(null);

        if (direccionBuscado != null) {
            return modelMapper.map(direccionBuscado, DireccionDTOSalida.class);
        } else {
            LOGGER.error("El id {} no se encuentra registrado en la base de datos", id);
            throw new BadRequestException("No se encontró la 'direccion' con el ID: " + id);
        }
    }


    //OK<<----
    @Override
    public DireccionDTOSalida actualizarDireccion(DireccionDTOEntradaModificar direccion) {
        Direccion direccionRecibida = modelMapper.map(direccion, Direccion.class);
        Direccion direccionAActualizar = direccionRepository.findById(direccion.getId()).orElse(null);
        DireccionDTOSalida direccionDTOSalida = null;

        if (direccionAActualizar != null) {

            direccionAActualizar = direccionRecibida;
            direccionRepository.save(direccionAActualizar);

            direccionDTOSalida = modelMapper.map(direccionAActualizar, DireccionDTOSalida.class);

            LOGGER.warn("Telefono actualizado: {}", JsonPrinter.toString(direccionDTOSalida));

        } else {
            LOGGER.error("No fue posible actualizar los datos ya que la 'direccion' no se encuentra registrada");
            throw new BadRequestException("No fue posible actualizar los datos ya que la 'direccion' no se encuentra registrada");
        }
        return direccionDTOSalida;
    }

    @Override
    public void eliminarDireccion(Long id) {
        if (obtenerDireccionPorId(id) != null) {
            direccionRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado la 'Direccion' con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado la 'Direccion' con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado la 'Direccion' con id " + id);
        }
    }

    private Direccion dtoEntradaAEntidad(DireccionDTOEntrada direccionDTOEntrada) {
        return modelMapper.map(direccionDTOEntrada, Direccion.class);
    }

    private Direccion dtoEntradaAEntidad(DireccionDTOEntradaNueva direccionDTOEntradaNueva) {
        return modelMapper.map(direccionDTOEntradaNueva, Direccion.class);
    }
}
