package com.fiap.parquimetro.condutor.DTO;

import com.fiap.parquimetro.condutor.entity.enumerations.SexoEnum;
import com.fiap.parquimetro.endereco.DTO.EnderecoDTO;

import java.time.LocalDate;

public record CondutorDTO(
        Long numeroCnh,
        LocalDate dataNascimento,
        String email,
        SexoEnum sexo,
        Long CPF,
        String telefone,
        EnderecoDTO endereco,
        UsuarioDTO usuario) {

    // Construtor padrão
    public CondutorDTO {
        if (numeroCnh == null) {
            throw new IllegalArgumentException("numeroCnh cannot be null");
        }
    }

    // Construtor adicional com somente o numeroCnh
    public CondutorDTO(Long numeroCnh) {
        this(numeroCnh, null, null, null, null, null, null, null);
    }
}
