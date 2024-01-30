package com.fiap.parquimetro.tiquete.entity;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.parquimetro.condutor.entity.Condutor;
import com.fiap.parquimetro.pagamento.entity.Pagamento;
import com.fiap.parquimetro.pagamento.entity.TipoPagamentoEnum;
import com.fiap.parquimetro.pagamento.entity.listaPrecosEnum;
import com.fiap.parquimetro.tiquete.entity.enumerations.StatusTiqueteEnum;
import com.fiap.parquimetro.veiculo.entity.Veiculo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "tiquete")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Tiquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "condutor_id")
    private Condutor condutor;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

//    @Column(nullable = false)
    private LocalTime entrada;

    private LocalTime saida;

//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
    private String tipoPagamento;

//    @Column(nullable = false, length = 255)
    private String descricaoTarifa;

    @Column(nullable = false, length = 255)
    private String periodo;

//    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTiqueteEnum status;

//    @ManyToOne
//    @JoinColumn(name = "preco_id")
    private String preco;

    @OneToOne(mappedBy = "tiquete")
    private Pagamento pagamentos;

}
