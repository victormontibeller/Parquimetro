package com.fiap.parquimetro.tiquete.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.parquimetro.condutor.entity.Condutor;
import com.fiap.parquimetro.preco.entity.Preco;
import com.fiap.parquimetro.tiquete.entity.enumerations.StatusTiqueteEnum;
import com.fiap.parquimetro.tiquete.entity.enumerations.TipoTiqueteEnum;
import com.fiap.parquimetro.veiculo.entity.Veiculo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "tiquete")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Tiquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "condutor_id")
    private Condutor condutor;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @Column(nullable = false)
    private LocalDateTime entrada;

    private LocalDateTime saida;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTiqueteEnum tipo;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TipoTiqueteEnum periodo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTiqueteEnum status;

    @ManyToOne
    @JoinColumn(name = "preco_id")
    private Preco preco;
}
