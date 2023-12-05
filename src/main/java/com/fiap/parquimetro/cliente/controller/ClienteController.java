package com.fiap.parquimetro.cliente.controller;

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

import com.fiap.parquimetro.cliente.DTO.AtualizarClienteDTO;
import com.fiap.parquimetro.cliente.entity.Cliente;
import com.fiap.parquimetro.cliente.service.CadastroClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    CadastroClienteService clienteService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

    @PostMapping
    public ResponseEntity<Cliente> inserirCliente(@RequestBody Cliente cliente, UriComponentsBuilder builder) {
        clienteService.inserirCliente(cliente);

        URI location = builder.path("clientes/{numeroCnh}").buildAndExpand(cliente.getNumeroCnh()).toUri();
        return ResponseEntity.created(location).body(cliente);
    }

    @GetMapping("/{numeroCnh}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable long numeroCnh) {
        return ResponseEntity.ok().body(clienteService.encontrarCliente(numeroCnh));
    }

    @PutMapping("/{numeroCnh}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable long numeroCnh, @RequestBody AtualizarClienteDTO dto) {
        return ResponseEntity.ok().body(clienteService.alterarCliente(dto, numeroCnh));
    }

    @DeleteMapping("/{numeroCnh}")
    public ResponseEntity<String> deletarCliente(@PathVariable long numeroCnh) {
        try {
            clienteService.deletarCliente(numeroCnh);
            LOGGER.info("Cliente {} eliminado com sucesso!", numeroCnh);
        } catch (Exception e) {
            LOGGER.error("Não foi possível eliminar o cliente {}!", numeroCnh);
            return new ResponseEntity<>("Não foi possível eliminar o cliente!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Cliente eliminado com sucesso!", HttpStatus.OK);
    }
}