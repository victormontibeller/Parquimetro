package com.fiap.parquimetro.operador.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.parquimetro.condutor.entity.Endereco;
import com.fiap.parquimetro.condutor.entity.Usuario;
import com.fiap.parquimetro.condutor.entity.enumerations.SexoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "operador")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Operador {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(unique = true)
   private long matricula;

   @Column(nullable = false)
   private LocalDate dataNascimento;

   @Column(nullable = false)
   private String email;

   @Enumerated(EnumType.STRING)
   private SexoEnum sexo;

   @Column(nullable = false, unique = true)
   private long CPF;

   @Column(nullable = false)
   private long telefone;

   @Embedded
   private Endereco endereco;

   @OneToOne
   @JoinColumn(name = "usuario_id", referencedColumnName = "id")
   private Usuario usuario;
}
