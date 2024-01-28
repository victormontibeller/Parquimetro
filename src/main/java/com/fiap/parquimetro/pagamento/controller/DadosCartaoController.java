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

import com.fiap.parquimetro.pagamento.DTO.CartaoDTO;
import com.fiap.parquimetro.pagamento.entity.DadosCartao;
import com.fiap.parquimetro.pagamento.service.DadosCartaoService;

@RestController
@RequestMapping(value = "/cartao")
public class DadosCartaoController {

    private final DadosCartaoService cartaoService;

    @Autowired
    public DadosCartaoController(DadosCartaoService cartaoService){
        this.cartaoService = cartaoService;
    }

    @GetMapping
    public ResponseEntity<List<DadosCartao>> buscarcartao() {
        return ResponseEntity.ok().body(cartaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DadosCartao>> buscarCartao(@PathVariable long id) {
        return ResponseEntity.ok().body(cartaoService.encontrarCartao(id));
    }

    @PostMapping
    public ResponseEntity<CartaoDTO> inserirCartao(@RequestBody CartaoDTO cartaoDTO) throws Exception {
        CartaoDTO cartaoSalvo = cartaoService.inserirCartao(cartaoDTO);

        return new ResponseEntity<>(cartaoSalvo, HttpStatus.CREATED);
    }       
}
