package com.fiap.parquimetro.marca.DTO;

import com.fiap.parquimetro.veiculo.entity.enumerations.NacionalidadeVeiculoEnum;

public record MarcaDTO(
    Long id,
    String nome, 
    NacionalidadeVeiculoEnum pais
){

}