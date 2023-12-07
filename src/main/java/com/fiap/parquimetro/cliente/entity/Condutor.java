package com.fiap.parquimetro.cliente.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.parquimetro.cliente.entity.enumerations.SexoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "condutor")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Condutor {

   @Id
   @Column(unique = true)
   private long numeroCnh;

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

   @ManyToMany(mappedBy = "condutores")
   private Set<Veiculo> veiculos = new HashSet<>();

}
