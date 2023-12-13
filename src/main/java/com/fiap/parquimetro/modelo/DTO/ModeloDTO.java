package com.fiap.parquimetro.modelo.DTO;

public record ModeloDTO(
        Long id,
        String nome,
        String pais) {

    // Construtor adicional com somente o ID
    public ModeloDTO(Long id) {
        this(id, null, null);
    }
}
