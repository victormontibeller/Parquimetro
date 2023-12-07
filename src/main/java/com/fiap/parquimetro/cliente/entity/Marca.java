package com.fiap.parquimetro.cliente.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.parquimetro.cliente.entity.enumerations.NacionalidadeVeiculoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "marca")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    NacionalidadeVeiculoEnum pais;
}