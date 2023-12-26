package com.fiap.parquimetro.operador.controller;

import java.util.List;
import java.util.Optional;

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

import com.fiap.parquimetro.condutor.controller.CondutorController;
import com.fiap.parquimetro.operador.DTO.OperadorDTO;
import com.fiap.parquimetro.operador.entity.Operador;
import com.fiap.parquimetro.operador.service.OperadorService;

@RestController
@RequestMapping(value = "/operadores")
public class OperadorController {
    private final OperadorService operadorService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CondutorController.class);

    @Autowired
    public OperadorController(OperadorService operadoreservice){
        this.operadorService = operadoreservice;
    }

    // read all
    @GetMapping
    public ResponseEntity<List<Operador>> buscarOperadores() {
        return ResponseEntity.ok().body(operadorService.buscarOperadores());
    }

    // read
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Operador>> buscarOperador(@PathVariable long id) {
        return ResponseEntity.ok().body(operadorService.buscarOperador(id));
    }

    @PostMapping
    public ResponseEntity<OperadorDTO> inserirOperador(@RequestBody OperadorDTO operador) {
        OperadorDTO operadorSalva = operadorService.inserirOperador(operador);

        return new ResponseEntity<>(operadorSalva, HttpStatus.CREATED);
    }    

    @PutMapping("/{id}")
    public ResponseEntity<OperadorDTO> atualizarCondutor(@PathVariable long id, @RequestBody OperadorDTO operadorDTO) {
        return ResponseEntity.ok().body(operadorService.alterarOperador(operadorDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarOperador(@PathVariable long id) {
        try {
            operadorService.deletarOperador(id);
            LOGGER.info("Operador {} eliminado com sucesso!", id);
        } catch (Exception e) {
            LOGGER.error("Não foi possível eliminar o operador {}!", id);
            return new ResponseEntity<>("Não foi possível eliminar o operador!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Operador eliminado com sucesso!", HttpStatus.OK);
    }

}
