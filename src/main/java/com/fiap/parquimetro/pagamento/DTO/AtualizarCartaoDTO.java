package com.fiap.parquimetro.pagamento.DTO;

import java.time.LocalDate;

public record AtualizarCartaoDTO(String nome, 
                                 String sobrenome, 
                                 String numeroCartao, 
                                 LocalDate validade, 
                                 short codSeguranca,
                                 String bandeira) {
    
}
