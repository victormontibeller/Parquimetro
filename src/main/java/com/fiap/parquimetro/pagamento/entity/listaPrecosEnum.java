package com.fiap.parquimetro.pagamento.entity;

import lombok.Getter;

@Getter
public enum listaPrecosEnum {
    FIXO(10f, "FIXO"),
    AVULSO(15f,"AVULSO");

    private final float preco;
    private final String descricao;

    listaPrecosEnum(float preco, String descricao){
        this.preco = preco;
        this.descricao = descricao;
    }

}
