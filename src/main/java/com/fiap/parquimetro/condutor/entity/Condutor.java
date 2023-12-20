package com.fiap.parquimetro.condutor.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.parquimetro.condutor.entity.enumerations.SexoEnum;
import com.fiap.parquimetro.pagamento.entity.DadosCartao;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
   private Long numeroCnh;

   @Column(nullable = false)
   private LocalDate dataNascimento;

   @Column(nullable = false)
   private String email;

   @Enumerated(EnumType.STRING)
   private SexoEnum sexo;

   @Column(nullable = false, unique = true)
   private Long CPF;

   @Column(nullable = false)
   private String telefone;

   @Embedded
   private Endereco endereco;

   @OneToOne
   @JoinColumn(name = "usuario_id", referencedColumnName = "id")
   private Usuario usuario;

   @ManyToMany
   @JoinTable(
      name = "nomeTitular",
      joinColumns = @JoinColumn(name = "cliente_id"),
      inverseJoinColumns = @JoinColumn(name = "cartaoId")
   )
   private List<DadosCartao> cartao;

}
