package com.fiap.parquimetro.tiquete.entity.enumerations;

import lombok.Getter;

@Getter
public enum StatusTiqueteEnum {

    ABERTO("ABERTO"),
    PAGO("PAGO"),
    SUSPENSO("SUSPENSO"),
    CANCELADO("CANCELADO");
    
    private final String descricao;

    StatusTiqueteEnum(String descricao){
        this.descricao = descricao;
    }
}
