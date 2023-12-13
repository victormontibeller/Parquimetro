package com.fiap.parquimetro.condutor.controller;

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

import com.fiap.parquimetro.condutor.DTO.CondutorDTO;
import com.fiap.parquimetro.condutor.service.CondutorService;

@RestController
@RequestMapping(value = "/condutores")
public class CondutorController {

    @Autowired
    public CondutorService condutorService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CondutorController.class);

    public CondutorController(CondutorService condutorService) {
        this.condutorService = condutorService;
    }

    @PostMapping
    public ResponseEntity<CondutorDTO> inserirCondutor(@RequestBody CondutorDTO condutor) {
        CondutorDTO condutorSalvo = condutorService.inserirCondutor(condutor);

        return new ResponseEntity<>(condutorSalvo, HttpStatus.CREATED);
    }

    @GetMapping("/{numeroCnh}")
    public ResponseEntity<CondutorDTO> buscarCondutor(@PathVariable long numeroCnh) {
        return ResponseEntity.ok().body(condutorService.encontrarCondutor(numeroCnh));
    }

    @PutMapping("/{numeroCnh}")
    public ResponseEntity<CondutorDTO> atualizarCondutor(@PathVariable long numeroCnh, @RequestBody CondutorDTO condutorDTO) {
        return ResponseEntity.ok().body(condutorService.alterarCondutor(condutorDTO, numeroCnh));
    }

    @DeleteMapping("/{numeroCnh}")
    public ResponseEntity<String> deletarCondutor(@PathVariable long numeroCnh) {
        try {
            condutorService.deletarCondutor(numeroCnh);
            LOGGER.info("Condutor {} eliminado com sucesso!", numeroCnh);
        } catch (Exception e) {
            LOGGER.error("Não foi possível eliminar o condutor {}!", numeroCnh);
            return new ResponseEntity<>("Não foi possível eliminar o condutor!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Condutor eliminado com sucesso!", HttpStatus.OK);
    }
}