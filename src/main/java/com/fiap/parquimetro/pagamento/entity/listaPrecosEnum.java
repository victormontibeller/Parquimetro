package com.fiap.parquimetro.pagamento.entity;

import lombok.Getter;

@Getter
public enum listaPrecosEnum {
    FIXO(10f),
    AVULSO(15f);

    private final float preco;

    listaPrecosEnum(float preco){
        this.preco = preco;
    }

}
