package com.fiap.parquimetro.cliente.DTO;

import com.fiap.parquimetro.cliente.entity.Endereco;

public record AtualizarClienteDTO(String nome, Endereco endereco) {
    
}
