package com.fiap.parquimetro.pagamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.parquimetro.pagamento.DTO.CartaoDTO;
import com.fiap.parquimetro.pagamento.entity.DadosCartao;
import com.fiap.parquimetro.pagamento.repository.DadosCartaoRepository;

@Service
public class DadosCartaoService {

    @Autowired
    private DadosCartaoRepository cartaoRepository;

    //creat
    public CartaoDTO inserirCartao(CartaoDTO cartaoDTO) {
        DadosCartao dadosCartao = toEntity(cartaoDTO);
        
        dadosCartao = cartaoRepository.save(dadosCartao);
        return toDTO(dadosCartao);
    }

    //read
    public Optional<DadosCartao> encontrarCartao(long cartaoId) {
        return cartaoRepository.findById(cartaoId);
    } 

    //update
    public DadosCartao alterarCartao(CartaoDTO atualizaCartao, long cartaoId) {
        DadosCartao cartao = cartaoRepository.getReferenceById(cartaoId);
        cartao.setNome(atualizaCartao.nome());
        cartao.setSobrenome(atualizaCartao.sobrenome());
        cartao.setNumeroCartao(atualizaCartao.numeroCartao());
        cartao.setCodSeguranca(atualizaCartao.codSeguranca());
        cartao.setValidade(atualizaCartao.validade());

        return cartaoRepository.save(cartao);
    }

    //delete
    public void deletarCartao(long cartaoId) {
        cartaoRepository.deleteById(cartaoId);
    }

    private CartaoDTO toDTO(DadosCartao cartao) {
        return new CartaoDTO(
                cartao.getNome(),
                cartao.getSobrenome(),
                cartao.getNumeroCartao(),
                cartao.getValidade(),
                cartao.getCodSeguranca(),
                cartao.getBandeira());
    }

    private DadosCartao toEntity(CartaoDTO cartao) {
        DadosCartao dadosCartao = new DadosCartao();
        dadosCartao.setNome(cartao.nome());
        dadosCartao.setSobrenome(cartao.sobrenome());
        dadosCartao.setNumeroCartao(cartao.numeroCartao());
        dadosCartao.setValidade(cartao.validade());
        dadosCartao.setCodSeguranca(cartao.codSeguranca());
        dadosCartao.setBandeira(cartao.bandeira());

        return dadosCartao;
    }

    public List<DadosCartao> findAll() {
        return cartaoRepository.findAll();
    }

}
