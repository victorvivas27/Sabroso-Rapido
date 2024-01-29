package com.victorvivas.sabrosorapido.controller;

import com.victorvivas.sabrosorapido.dto.entrada.DireccionDTOEntrada;
import com.victorvivas.sabrosorapido.dto.entrada.DireccionDTOEntradaNueva;
import com.victorvivas.sabrosorapido.dto.modificacion.DireccionDTOEntradaModificar;
import com.victorvivas.sabrosorapido.dto.salida.DireccionDTOSalida;
import com.victorvivas.sabrosorapido.service.InterfaceDireccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/direccion")
@CrossOrigin
@AllArgsConstructor
public class DireccionControler {
    private static final Logger LOGGER = LoggerFactory.getLogger(DireccionControler.class);
    private final InterfaceDireccionService interfaceDireccionService;
//=================================================================================//
    @Operation(summary = "Registrar una nueva dirección")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dirección registrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DireccionDTOSalida.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<DireccionDTOSalida> registrarDireccion(@Valid @RequestBody DireccionDTOEntrada direccion) {
        return new ResponseEntity<>(interfaceDireccionService.crearDireccion(direccion), HttpStatus.CREATED);
    }
    //===============================================================================//

    @Operation(summary = "Registrar una nueva dirección para un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dirección registrada correctamente para el usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DireccionDTOSalida.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/direccionNueva")
    public ResponseEntity<DireccionDTOSalida> direccionNuevaUsuario(@Valid @RequestBody DireccionDTOEntradaNueva direccion) {
        return new ResponseEntity<>(interfaceDireccionService.crearDireccionParaUsuario(direccion), HttpStatus.CREATED);
    }

    //========================================================================================//
    @Operation(summary = "Obtener la lista de todas las direcciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de direcciones obtenida correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DireccionDTOSalida.class))}),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/listar")
    public ResponseEntity<List<DireccionDTOSalida>> listarDirecciones() {
        return new ResponseEntity<>(interfaceDireccionService.obtenerTodasLasDirecciones(), HttpStatus.OK);
    }

    //========================================================================================//
    @Operation(summary = "Obtener una dirección por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dirección obtenida correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DireccionDTOSalida.class))}),
            @ApiResponse(responseCode = "404", description = "Dirección no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<DireccionDTOSalida> obtenerDireccionPorId(@Parameter(description = "ID de la dirección a obtener", required = true)
                                                                    @PathVariable Long id) {
        return new ResponseEntity<>(interfaceDireccionService.obtenerDireccionPorId(id), HttpStatus.OK);
    }

    //==============================================================================================//
    @Operation(summary = "Actualizar una dirección")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dirección actualizada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DireccionDTOSalida.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Dirección no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PutMapping("/modificar")
    public ResponseEntity<DireccionDTOSalida> actualizarDireccion(@Valid @RequestBody DireccionDTOEntradaModificar direccion) {
        return new ResponseEntity<>(interfaceDireccionService.actualizarDireccion(direccion), HttpStatus.OK);
    }

    //=================================================================================================//
    @Operation(summary = "Eliminar una dirección por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dirección eliminada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "Dirección no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarDireccion(@Parameter(description = "ID de la dirección a eliminar", required = true)
                                               @PathVariable Long id) {
        interfaceDireccionService.eliminarDireccion(id);
        return new ResponseEntity<>("Dirección eliminada correctamente", HttpStatus.OK);
    }
}
