package com.fiap.parquimetro.veiculo.DTO;

import com.fiap.parquimetro.condutor.entity.Condutor;
import com.fiap.parquimetro.cliente.entity.Modelo;

public record VeiculoDTO(
    String placa, 
    String cor, 
    Modelo modelo, 
    Condutor condutor
) {    
    
}
