package com.fiap.parquimetro.tiquete.controller;

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
import com.fiap.parquimetro.tiquete.DTO.TiqueteDTO;
import com.fiap.parquimetro.tiquete.entity.Tiquete;
import com.fiap.parquimetro.tiquete.service.TiqueteService;

@RestController
@RequestMapping(value = "/tiquetes")
public class TiqueteController {
    private final TiqueteService tiqueteService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CondutorController.class);

    @Autowired
    public TiqueteController(TiqueteService tiqueteservice){
        this.tiqueteService = tiqueteservice;
    }

    @GetMapping
    public ResponseEntity<List<Tiquete>> buscarTiquetes() {
        return ResponseEntity.ok().body(tiqueteService.buscarTiquetes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Tiquete>> buscarTiquete(@PathVariable long id) {
        return ResponseEntity.ok().body(tiqueteService.buscarTiquete(id));
    }

    @PostMapping
    public ResponseEntity<TiqueteDTO> inserirTiquete(@RequestBody TiqueteDTO tiquete) {
        TiqueteDTO tiqueteSalva = tiqueteService.inserirTiquete(tiquete);

        return new ResponseEntity<>(tiqueteSalva, HttpStatus.CREATED);
    }    

    @PutMapping("/{id}")
    public ResponseEntity<TiqueteDTO> atualizarCondutor(@PathVariable long id, @RequestBody TiqueteDTO tiqueteDTO) {
        return ResponseEntity.ok().body(tiqueteService.alterarTiquete(tiqueteDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarTiquete(@PathVariable long id) {
        try {
            tiqueteService.deletarTiquete(id);
            LOGGER.info("Tiquete {} eliminada com sucesso!", id);
        } catch (Exception e) {
            LOGGER.error("Não foi possível eliminar a tiquete {}!", id);
            return new ResponseEntity<>("Não foi possível eliminar a tiquete!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Tiquete eliminada com sucesso!", HttpStatus.OK);
    }

}
