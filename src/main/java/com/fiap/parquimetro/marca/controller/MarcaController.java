package com.fiap.parquimetro.marca.controller;

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
import com.fiap.parquimetro.marca.DTO.MarcaDTO;
import com.fiap.parquimetro.marca.entity.Marca;
import com.fiap.parquimetro.marca.service.MarcaService;

@RestController
@RequestMapping(value = "/marcas")
public class MarcaController {
    private final MarcaService marcaService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CondutorController.class);

    @Autowired
    public MarcaController(MarcaService marcaservice){
        this.marcaService = marcaservice;
    }

    @GetMapping
    public ResponseEntity<List<Marca>> buscarMarcas() {
        return ResponseEntity.ok().body(marcaService.buscarMarcas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Marca>> buscarMarca(@PathVariable long id) {
        return ResponseEntity.ok().body(marcaService.buscarMarca(id));
    }

    @PostMapping
    public ResponseEntity<MarcaDTO> inserirMarca(@RequestBody MarcaDTO marca) {
        MarcaDTO marcaSalva = marcaService.inserirMarca(marca);

        return new ResponseEntity<>(marcaSalva, HttpStatus.CREATED);
    }    

    @PutMapping("/{id}")
    public ResponseEntity<MarcaDTO> atualizarCondutor(@PathVariable long id, @RequestBody MarcaDTO marcaDTO) {
        return ResponseEntity.ok().body(marcaService.alterarMarca(marcaDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarMarca(@PathVariable long id) {
        try {
            marcaService.deletarMarca(id);
            LOGGER.info("Marca {} eliminada com sucesso!", id);
        } catch (Exception e) {
            LOGGER.error("Não foi possível eliminar a marca {}!", id);
            return new ResponseEntity<>("Não foi possível eliminar a marca!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Marca eliminada com sucesso!", HttpStatus.OK);
    }

}
