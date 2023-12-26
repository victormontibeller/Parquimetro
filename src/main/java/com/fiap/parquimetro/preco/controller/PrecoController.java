package com.fiap.parquimetro.preco.controller;

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
import com.fiap.parquimetro.preco.DTO.PrecoDTO;
import com.fiap.parquimetro.preco.entity.Preco;
import com.fiap.parquimetro.preco.service.PrecoService;

@RestController
@RequestMapping(value = "/precos")
public class PrecoController {
    private final PrecoService precoService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CondutorController.class);

    @Autowired
    public PrecoController(PrecoService precoservice){
        this.precoService = precoservice;
    }

    @GetMapping
    public ResponseEntity<List<Preco>> buscarPrecos() {
        return ResponseEntity.ok().body(precoService.buscarPrecos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Preco>> buscarPreco(@PathVariable long id) {
        return ResponseEntity.ok().body(precoService.buscarPreco(id));
    }

    @PostMapping
    public ResponseEntity<PrecoDTO> inserirPreco(@RequestBody PrecoDTO preco) {
        PrecoDTO precoSalva = precoService.inserirPreco(preco);

        return new ResponseEntity<>(precoSalva, HttpStatus.CREATED);
    }    

    @PutMapping("/{id}")
    public ResponseEntity<PrecoDTO> atualizarCondutor(@PathVariable long id, @RequestBody PrecoDTO precoDTO) {
        return ResponseEntity.ok().body(precoService.alterarPreco(precoDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPreco(@PathVariable long id) {
        try {
            precoService.deletarPreco(id);
            LOGGER.info("Preco {} eliminada com sucesso!", id);
        } catch (Exception e) {
            LOGGER.error("Não foi possível eliminar a preco {}!", id);
            return new ResponseEntity<>("Não foi possível eliminar a preco!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Preco eliminada com sucesso!", HttpStatus.OK);
    }

}
