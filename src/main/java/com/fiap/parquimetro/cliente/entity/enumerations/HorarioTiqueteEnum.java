package com.fiap.parquimetro.cliente.entity.enumerations;

import lombok.Getter;

@Getter

public enum HorarioTiqueteEnum {
    UMA_HORA("1 Hora", 1),
    DUAS_HORAS("2 Horas", 2),
    QUATRO_HORAS("4 Horas", 4),
    OITO_HORAS("8 Horas", 8),
    DIARIA("Diária", 24);

    private final String descricao;
    private final int horas;

    HorarioTiqueteEnum(String descricao, int horas) {
        this.descricao = descricao;
        this.horas = horas;
    }

}