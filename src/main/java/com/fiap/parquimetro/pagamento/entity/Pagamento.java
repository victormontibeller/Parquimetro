package com.fiap.parquimetro.pagamento.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fiap.parquimetro.condutor.entity.Condutor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private float valor;

    private LocalDate dataPagamento;

    private LocalTime horaEntrada;

    private LocalTime horaSaida;

    private TipoPagamentoEnum tipoPagamento; 

    private boolean periodoFixo; // periodo poderá ser fixo, ou  poderá ser avulso
     
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Condutor condutor;
    
    @ManyToOne
    @JoinColumn(name = "cartaoId")
    private DadosCartao dadosCartao;

}