package com.fiap.parquimetro.endereco.DTO;

public record EnderecoDTO(
        String rua,
        int numero,
        String bairro,
        String cidade,
        String estado,
        String pais,
        int cep
) {
}


   


