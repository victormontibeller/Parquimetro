package com.fiap.parquimetro.tiquete.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.parquimetro.pagamento.DTO.PagamentoDTO;
import com.fiap.parquimetro.pagamento.entity.Pagamento;
import com.fiap.parquimetro.pagamento.entity.TipoPagamentoEnum;
import com.fiap.parquimetro.pagamento.entity.listaPrecosEnum;
import com.fiap.parquimetro.tiquete.DTO.TiqueteDTO;
import com.fiap.parquimetro.tiquete.entity.Tiquete;
import com.fiap.parquimetro.tiquete.service.TiqueteService;

@RestController
@RequestMapping(value = "/tiquete")
public class TiqueteController {
    
    private final TiqueteService tiqueteService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TiqueteController.class);


    @Autowired
    public TiqueteController(TiqueteService tiqueteService){
        this.tiqueteService = tiqueteService;
    }

    /**
     * Retrieve a list of Tiquetes using a GET request.
     *
     * @return         	ResponseEntity with a list of Tiquetes
     */
    @GetMapping
    public ResponseEntity<List<Tiquete>> buscarTiquetes() {
        return ResponseEntity.ok().body(tiqueteService.findAll());
    }

    /**
     * buscarTiquete - retrieves a Tiquete by its ID.
     *
     * @param  id	the ID of the Tiquete to be retrieved
     * @return      an Optional containing the retrieved Tiquete
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Tiquete>> buscarTiquete(@PathVariable long id) {
        return ResponseEntity.ok().body(tiqueteService.encontrarTiquete(id));
    }

    /**
     * inserirTiquete - Inserts a Tiquete using the given PagamentoDTO
     *
     * @param  pagamentoDTO	the PagamentoDTO to insert the Tiquete
     * @return         		the ResponseEntity containing the inserted TiqueteDTO
     */
    @PostMapping
    public ResponseEntity<TiqueteDTO> inserirTiquete(@RequestBody PagamentoDTO pagamentoDTO) throws Exception {
        TiqueteDTO TiqueteSalvo = tiqueteService.inserirTiquete(pagamentoDTO);

        return new ResponseEntity<>(TiqueteSalvo, HttpStatus.CREATED);
    }
}