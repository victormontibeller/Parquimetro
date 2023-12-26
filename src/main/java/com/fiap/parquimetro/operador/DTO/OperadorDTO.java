package com.fiap.parquimetro.operador.DTO;

import com.fiap.parquimetro.endereco.DTO.EnderecoDTO;
import com.fiap.parquimetro.condutor.DTO.UsuarioDTO;
import com.fiap.parquimetro.condutor.entity.enumerations.SexoEnum;

import java.time.LocalDate;

public record OperadorDTO(
        long id,
        long matricula,
        LocalDate dataNascimento,
        String email,
        SexoEnum sexo,
        long CPF,
        long telefone,
        EnderecoDTO endereco,
        UsuarioDTO usuario
) {
   
}
