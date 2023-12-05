package com.fiap.parquimetro.cliente.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Embeddable
public class Endereco {

   @Column(nullable = false)
   private String rua;

   @Column(nullable = false)
   private int numero;

   @Column(nullable = false)
   private String bairro;

   @Column(nullable = false)
   private String cidade;

   @Column(nullable = false)
   private String estado;

   @Column(nullable = false)
   private String pais;

   @Column(nullable = false)
   private int cep;

}
