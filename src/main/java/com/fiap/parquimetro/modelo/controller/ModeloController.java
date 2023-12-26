package com.fiap.parquimetro.modelo.controller;

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
import com.fiap.parquimetro.modelo.DTO.ModeloDTO;
import com.fiap.parquimetro.modelo.entity.Modelo;
import com.fiap.parquimetro.modelo.service.ModeloService;

@RestController
@RequestMapping(value = "/modelos")
public class ModeloController {
    private final ModeloService modeloService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CondutorController.class);

    @Autowired
    public ModeloController(ModeloService modeloservice){
        this.modeloService = modeloservice;
    }

    @GetMapping
    public ResponseEntity<List<Modelo>> buscarModelos() {
        return ResponseEntity.ok().body(modeloService.buscarModelos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Modelo>> buscarModelo(@PathVariable long id) {
        return ResponseEntity.ok().body(modeloService.buscarModelo(id));
    }

    @PostMapping
    public ResponseEntity<ModeloDTO> inserirModelo(@RequestBody ModeloDTO modelo) {
        ModeloDTO modeloSalva = modeloService.inserirModelo(modelo);

        return new ResponseEntity<>(modeloSalva, HttpStatus.CREATED);
    }    

    @PutMapping("/{id}")
    public ResponseEntity<ModeloDTO> atualizarCondutor(@PathVariable long id, @RequestBody ModeloDTO modeloDTO) {
        return ResponseEntity.ok().body(modeloService.alterarModelo(modeloDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarModelo(@PathVariable long id) {
        try {
            modeloService.deletarModelo(id);
            LOGGER.info("Modelo {} eliminada com sucesso!", id);
        } catch (Exception e) {
            LOGGER.error("Não foi possível eliminar a modelo {}!", id);
            return new ResponseEntity<>("Não foi possível eliminar a modelo!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Modelo eliminada com sucesso!", HttpStatus.OK);
    }

}
