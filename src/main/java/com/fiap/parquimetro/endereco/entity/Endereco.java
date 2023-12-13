package com.fiap.parquimetro.endereco.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Embeddable
public class Endereco {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

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
