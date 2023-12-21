package com.fiap.parquimetro.marca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.parquimetro.marca.DTO.MarcaDTO;
import com.fiap.parquimetro.marca.entity.Marca;
import com.fiap.parquimetro.marca.service.MarcaService;

@RestController
@RequestMapping(value = "/marcas")
public class MarcaController {
    private final MarcaService marcaService;

    @Autowired
    public MarcaController(MarcaService marcaservice){
        this.marcaService = marcaservice;
    }

    @GetMapping
    public ResponseEntity<List<Marca>> buscarMarcas() {
        return ResponseEntity.ok().body(marcaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Marca>> buscarMarca(@PathVariable long id) {
        return ResponseEntity.ok().body(marcaService.encontrarMarca(id));
    }

    @PostMapping
    public ResponseEntity<MarcaDTO> inserirMarca(@RequestBody MarcaDTO marca) {
        MarcaDTO marcaSalva = marcaService.inserirMarca(marca);

        return new ResponseEntity<>(marcaSalva, HttpStatus.CREATED);
    }    
}
