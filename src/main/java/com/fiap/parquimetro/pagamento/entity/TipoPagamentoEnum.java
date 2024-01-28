package com.fiap.parquimetro.pagamento.entity;

import lombok.Getter;

@Getter
public enum TipoPagamentoEnum {
    DEBITO("DEBITO"),
    CREDITO("CREDITO"),
    PIX("PIX"),
    BITCOIN("BITCOIN"); 

private final String descricao;

    TipoPagamentoEnum(String descricao){
        this.descricao = descricao;
    }
}
