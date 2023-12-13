package com.fiap.parquimetro.pagamento.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.parquimetro.preco.entity.Preco;
import com.fiap.parquimetro.tiquete.entity.enumerations.TipoPeriodoEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "pagamento")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private TipoPeriodoEnum tipoPeriodo;

    @ManyToOne
    private Preco preco;

    // Outros campos, construtores, getters e setters

    public BigDecimal calcularCustoEstacionamento() {
        return null;
        /*BigDecimal custo = preco.getPrecoPorHora();

        if (tipoPeriodo != tipoPeriodo.UMA_HORA) {
            BigDecimal valorHorasExcedentes = preco.getPrecoHoraExcedente()
                    .multiply(BigDecimal.valueOf(tipoPeriodo.getHoras() - 1));
            custo = custo.add(valorHorasExcedentes);
        }

        return custo;*/
    }
}