package com.fiap.parquimetro.pagamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.parquimetro.pagamento.DTO.AtualizarCartaoDTO;
import com.fiap.parquimetro.pagamento.entity.DadosCartao;
import com.fiap.parquimetro.pagamento.repository.DadosCartaoRepository;

@Service
public class DadosCartaoService {

    @Autowired
    private DadosCartaoRepository cartaoRepository;

    //creat
    public DadosCartao inserirNovoCartao(DadosCartao cartao) {
        return cartaoRepository.save(cartao);
    }

    //read
    public DadosCartao encontrarCartao(long cartaoId) {
        return cartaoRepository.getReferenceById(cartaoId);
    } 

    //update
    public DadosCartao alterarCartao(AtualizarCartaoDTO atualizaCartao, long cartaoId) {
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
}
