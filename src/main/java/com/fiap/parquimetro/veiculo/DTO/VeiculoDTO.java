package com.fiap.parquimetro.veiculo.DTO;

import com.fiap.parquimetro.condutor.DTO.CondutorDTO;
import com.fiap.parquimetro.modelo.DTO.ModeloDTO;

public record VeiculoDTO(
    String placa, 
    String cor, 
    ModeloDTO modelo, 
    CondutorDTO condutor
) {    
    
}
