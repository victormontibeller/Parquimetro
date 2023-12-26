package com.fiap.parquimetro.modelo.DTO;

import com.fiap.parquimetro.marca.entity.Marca;

public record ModeloDTO(
        Long id,
        String nome,
        Marca marca) {

    // Construtor adicional com somente o ID
    public ModeloDTO(Long id) {
        this(id, null, null);
    }
}
