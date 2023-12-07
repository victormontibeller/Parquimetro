package com.fiap.parquimetro.cliente.entity.enumerations;

import lombok.Getter;

@Getter

public enum NacionalidadeVeiculoEnum {
    ALEMANHA("AL","Alemanha"),
    BRASIL("BR","Brasil"),
    CHINA("CH","China"),
    ESTADOS_UNIDOS("US","Estados Unidos"),
    FRANCA("FR","França"),
    JAPAO("JP","Japão"),
    COREIA_DO_SUL("CO","Coreia do Sul"),
    INDIA("IN","Índia"),
    MEXICO("MX","México"),
    OUTROS("OU","Outros");

    
    private final String sigla;
    private final String nome;

    NacionalidadeVeiculoEnum(String sigla, String nome) {
        this.sigla = sigla;
        this.nome = nome;
    }
}

