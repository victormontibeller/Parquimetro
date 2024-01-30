package com.fiap.parquimetro.pagamento.controller;

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

import com.fiap.parquimetro.pagamento.DTO.PagamentoDTO;
import com.fiap.parquimetro.pagamento.entity.Pagamento;
import com.fiap.parquimetro.pagamento.service.PagamentoService;
import com.fiap.parquimetro.tiquete.service.TiqueteService;


@RestController
@RequestMapping(value = "/pagamento")
public class PagamentoControler {

    private final PagamentoService pagamentoService;

    @Autowired
    public PagamentoControler(PagamentoService pagamentoService, TiqueteService tiqueteService){
        this.pagamentoService = pagamentoService;
    }
    
    /**
     * A description of the entire Java function.
     *
     * @return         description of return value
     */
    @GetMapping
    public ResponseEntity<List<Pagamento>> buscarPagamentos() {
        return ResponseEntity.ok().body(pagamentoService.findAll());
    }   

    /**
     * buscarPagamento - Retrieves a payment by its ID.
     *
     * @param  id   the ID of the payment to retrieve
     * @return      the ResponseEntity containing the optional payment
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pagamento>> buscarPagamento(@PathVariable long id) {
        return ResponseEntity.ok().body(pagamentoService.encontrarPagamento(id));
    }

    /**
     * Inserts a payment using the provided payment data.
     *
     * @param  pagamentoDTO   the payment data to be inserted
     * @return                the inserted payment data with HTTP status 201
     */
    @PostMapping
    public ResponseEntity<PagamentoDTO> inserirPagamento(@RequestBody PagamentoDTO pagamentoDTO) throws Exception {
        PagamentoDTO pagamentoSalvo = pagamentoService.inserirPagamento(pagamentoDTO, 0L);

        return new ResponseEntity<>(pagamentoSalvo, HttpStatus.CREATED);
    }
    
}