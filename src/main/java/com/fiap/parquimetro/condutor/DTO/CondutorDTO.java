package com.fiap.parquimetro.condutor.DTO;

import com.fiap.parquimetro.veiculo.entity.Veiculo;
import com.fiap.parquimetro.cliente.entity.Endereco;
import com.fiap.parquimetro.cliente.entity.Usuario;
import com.fiap.parquimetro.cliente.entity.enumerations.SexoEnum;

import java.time.LocalDate;
import java.util.Set;

public record CondutorDTO (
    Long numeroCnh, 
    LocalDate dataNascimento,
    String email,
    SexoEnum sexo,
    long CPF,
    long telefone,
    Endereco endereco,
    Usuario usuario,
    Set<Veiculo> veiculos
){

}
