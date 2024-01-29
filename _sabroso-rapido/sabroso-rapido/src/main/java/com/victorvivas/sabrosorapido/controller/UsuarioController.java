package com.victorvivas.sabrosorapido.controller;

import com.victorvivas.sabrosorapido.dto.entrada.UsuarioDTOEntrada;
import com.victorvivas.sabrosorapido.dto.modificacion.UsuarioDTOEntradaModificar;
import com.victorvivas.sabrosorapido.dto.salida.UsuarioDTOSalida;
import com.victorvivas.sabrosorapido.service.InterfaceUsuarioService;
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
@RequestMapping("/api/usuario")
@CrossOrigin
@AllArgsConstructor
public class UsuarioController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);
    private final InterfaceUsuarioService interfaceUsuarioService;

    //=================================================================//
    @Operation(summary = "Registrar un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTOSalida.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<UsuarioDTOSalida> registrarUsuario(@Valid @RequestBody UsuarioDTOEntrada usuario) {
        return new ResponseEntity<>(interfaceUsuarioService.crearUsuario(usuario), HttpStatus.CREATED);
    }

    //=============================================================================//
    @Operation(summary = "Obtener la lista de todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTOSalida.class))}),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTOSalida>> listarUsuarios() {
        return new ResponseEntity<>(interfaceUsuarioService.obtenerTodosLosUsuarios(), HttpStatus.OK);
    }

    //===========================================================================//
    @Operation(summary = "Obtener un usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTOSalida.class))}),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTOSalida> obtenerUsuarioPorId(@Parameter(description = "ID del usuario a obtener", required = true)
                                                                @PathVariable Long id) {
        return new ResponseEntity<>(interfaceUsuarioService.obtenerUsuarioPorId(id), HttpStatus.OK);
    }

    //==========================================================================================//
    @Operation(summary = "Actualizar un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTOSalida.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PutMapping("/modificar")
    public ResponseEntity<UsuarioDTOSalida> actualizarUsuario(@Valid @RequestBody UsuarioDTOEntradaModificar usuario) {
        return new ResponseEntity<>(interfaceUsuarioService.actualizarUsuario(usuario), HttpStatus.OK);
    }

    //============================================================================================//
    @Operation(summary = "Eliminar un usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@Parameter(description = "ID del usuario a eliminar", required = true)
                                             @PathVariable Long id) {
        interfaceUsuarioService.eliminarUsuario(id);
        return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
    }
}
