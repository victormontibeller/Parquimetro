package com.fiap.parquimetro.pagamento.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fiap.parquimetro.condutor.entity.Condutor;
import com.fiap.parquimetro.pagamento.entity.DadosCartao;
import com.fiap.parquimetro.tiquete.entity.Tiquete;

public record PagamentoDTO(
        Long id,
//        Long tiqueteId,
        Float valor,
        LocalDate dataPagamento,
        LocalTime horaEntrada,
        LocalTime horaSaida,
        String tipoPagamento,
        String quantidadeHoras,
        String tarifaDescricao, 
        Condutor condutor,
        DadosCartao dadosCartao,
        Tiquete tiquete) {
       
}