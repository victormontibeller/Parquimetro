package com.fiap.parquimetro.condutor.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fiap.parquimetro.condutor.DTO.AtualizarUsuarioDTO;
import com.fiap.parquimetro.condutor.entity.Usuario;
import com.fiap.parquimetro.condutor.service.CadastroUsuarioService;


@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    CadastroUsuarioService usuarioService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping
    public ResponseEntity<Usuario> inserirUsuario(@RequestBody Usuario Usuario, UriComponentsBuilder builder) {
        usuarioService.inserirUsuario(Usuario);

        URI location = builder.path("usuarios/{id}").buildAndExpand(Usuario.getLogin()).toUri();
        return ResponseEntity.created(location).body(Usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable long id) {
        return ResponseEntity.ok().body(usuarioService.encontrarUsuario(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable long id, @RequestBody AtualizarUsuarioDTO dto) {
        return ResponseEntity.ok().body(usuarioService.alterarUsuario(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable long id) {
        try {
            usuarioService.deletarUsuario(id);
            LOGGER.info("Usuário {} eliminado com sucesso!", id);
        } catch (Exception e) {
            LOGGER.error("Não foi possível eliminar o usuário {}!", id);
            return new ResponseEntity<>("Não foi possível eliminar o usuário!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Usuário eliminado com sucesso!", HttpStatus.OK);
    }
}