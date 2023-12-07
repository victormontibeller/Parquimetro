package com.fiap.parquimetro.cliente.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.parquimetro.cliente.entity.enumerations.StatusTiqueteEnum;
import com.fiap.parquimetro.cliente.entity.enumerations.TipoTiqueteEnum;

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
    private Preco preco;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTiqueteEnum status;

}
