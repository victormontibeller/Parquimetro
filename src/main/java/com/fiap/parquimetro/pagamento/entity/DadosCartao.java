package com.fiap.parquimetro.pagamento.entity;

import java.time.LocalDate;
import java.util.List;

import com.fiap.parquimetro.cliente.entity.Cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "info_cartao")
public class DadosCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartaoId;

    private String nome;

    private String sobrenome;

    private String numeroCartao;

    private short codSeguranca;

    private String bandeira; 

    private LocalDate validade;

    @ManyToMany(mappedBy = "cartao")
    private List<Cliente> clientes;


}
