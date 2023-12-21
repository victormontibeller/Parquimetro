package com.fiap.parquimetro.preco.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.parquimetro.tiquete.entity.enumerations.HorarioTiqueteEnum;

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
@Table(name = "preco")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Preco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal precoPeriodo;

    @Column(nullable = false)
    private BigDecimal precoHoraExcedente;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private HorarioTiqueteEnum periodo;

}