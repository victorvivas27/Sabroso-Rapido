package com.victorvivas.sabrosorapido.controller;

import com.victorvivas.sabrosorapido.dto.entrada.TelefonoDTOEntrada;
import com.victorvivas.sabrosorapido.dto.entrada.TelefonoDTOEntradaNuevo;
import com.victorvivas.sabrosorapido.dto.modificacion.TelefonoDTOEntradaModificar;
import com.victorvivas.sabrosorapido.dto.salida.TelefonoDTOSalida;
import com.victorvivas.sabrosorapido.service.InterfaceTelefonoService;
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
@RequestMapping("/api/telefono")
@CrossOrigin
@AllArgsConstructor

public class TelefonoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelefonoController.class);
    private final InterfaceTelefonoService interfaceTelefonoService;
//===================================================================//
    @Operation(summary = "Obtener la lista de todos los teléfonos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de teléfonos obtenida correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TelefonoDTOSalida.class))}),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/listar")
    public ResponseEntity<List<TelefonoDTOSalida>> listarTelefonos() {
        return new ResponseEntity<>(interfaceTelefonoService.obtenerTodosLosTelefonos(), HttpStatus.OK);
    }
//======================================================================//
    @Operation(summary = "Obtener un teléfono por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teléfono obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TelefonoDTOSalida.class))}),
            @ApiResponse(responseCode = "404", description = "Teléfono no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TelefonoDTOSalida> obtenerTelefonoPorId(@Parameter(description = "ID del teléfono a obtener", required = true)
                                                                  @PathVariable Long id) {
        return new ResponseEntity<>(interfaceTelefonoService.obtenerTelefonoPorId(id), HttpStatus.OK);
    }
//=============================================================================================//
    @Operation(summary = "Registrar un nuevo teléfono")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Teléfono registrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TelefonoDTOSalida.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<TelefonoDTOSalida> registrarTelefono(@Valid @RequestBody TelefonoDTOEntrada telefono) {
        return new ResponseEntity<>(interfaceTelefonoService.crearTelefono(telefono), HttpStatus.CREATED);
    }
//=================================================================================================//
    @Operation(summary = "Registrar un nuevo teléfono para un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Teléfono registrado correctamente para el usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TelefonoDTOSalida.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/telefonoNuevo")
    public ResponseEntity<TelefonoDTOSalida> telefonoNuevaUsuario(@Valid @RequestBody TelefonoDTOEntradaNuevo telefono) {
        return new ResponseEntity<>(interfaceTelefonoService.crearTelefonoParaUsuario(telefono), HttpStatus.CREATED);
    }
//======================================================================================//
    @Operation(summary = "Actualizar un teléfono")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teléfono actualizado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TelefonoDTOSalida.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Teléfono no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PutMapping("/modificar")
    public ResponseEntity<TelefonoDTOSalida> actualizarTelefono(@Valid @RequestBody TelefonoDTOEntradaModificar telefono) {
        return new ResponseEntity<>(interfaceTelefonoService.actualizarTelefono(telefono), HttpStatus.OK);
    }
//=====================================================================================//
    @Operation(summary = "Eliminar un teléfono por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teléfono eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "Teléfono no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@Parameter(description = "ID del teléfono a eliminar", required = true)
                                             @PathVariable Long id) {
        interfaceTelefonoService.eliminarTelefono(id);
        return new ResponseEntity<>("Teléfono eliminado correctamente", HttpStatus.OK);
    }
}