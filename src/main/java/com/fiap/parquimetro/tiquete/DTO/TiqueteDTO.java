package com.fiap.parquimetro.tiquete.DTO;

import java.time.LocalTime;

import com.fiap.parquimetro.pagamento.DTO.PagamentoDTO;

public record TiqueteDTO(
        PagamentoDTO pagamentoDTO,
        LocalTime entrada,
        LocalTime saida,
        String periodo,
        String descricaoTarifa,
        String preco
) {
    
}
