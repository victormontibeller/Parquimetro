package com.fiap.parquimetro.condutor.DTO;

import com.fiap.parquimetro.cliente.entity.enumerations.SexoEnum;
import com.fiap.parquimetro.endereco.DTO.EnderecoDTO;

import java.time.LocalDate;

public record CondutorDTO (
    Long numeroCnh, 
    LocalDate dataNascimento,
    String email,
    SexoEnum sexo,
    Long CPF,
    String telefone,
    EnderecoDTO endereco,
    UsuarioDTO usuario  
){  
}
