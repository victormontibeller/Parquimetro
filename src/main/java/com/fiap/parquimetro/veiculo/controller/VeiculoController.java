package com.fiap.parquimetro.veiculo.controller;

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
import com.fiap.parquimetro.veiculo.DTO.VeiculoDTO;
import com.fiap.parquimetro.veiculo.entity.Veiculo;
import com.fiap.parquimetro.veiculo.service.VeiculoService;

@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoController {
    private final VeiculoService veiculoService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CondutorController.class);

    @Autowired
    public VeiculoController(VeiculoService veiculoservice){
        this.veiculoService = veiculoservice;
    }

    // read all
    @GetMapping
    public ResponseEntity<List<Veiculo>> buscarVeiculos() {
        return ResponseEntity.ok().body(veiculoService.buscarVeiculos());
    }

    // read
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veiculo>> buscarVeiculo(@PathVariable long id) {
        return ResponseEntity.ok().body(veiculoService.buscarVeiculo(id));
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> inserirVeiculo(@RequestBody VeiculoDTO veiculo) {
        VeiculoDTO veiculoSalva = veiculoService.inserirVeiculo(veiculo);

        return new ResponseEntity<>(veiculoSalva, HttpStatus.CREATED);
    }    

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> atualizarCondutor(@PathVariable long id, @RequestBody VeiculoDTO veiculoDTO) {
        return ResponseEntity.ok().body(veiculoService.alterarVeiculo(veiculoDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarVeiculo(@PathVariable long id) {
        try {
            veiculoService.deletarVeiculo(id);
            LOGGER.info("Veiculo {} eliminado com sucesso!", id);
        } catch (Exception e) {
            LOGGER.error("Não foi possível eliminar o veiculo {}!", id);
            return new ResponseEntity<>("Não foi possível eliminar o veiculo!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Veiculo eliminado com sucesso!", HttpStatus.OK);
    }

}
