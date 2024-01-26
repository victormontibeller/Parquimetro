package com.fiap.parquimetro.pagamento.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fiap.parquimetro.condutor.entity.Condutor;
import com.fiap.parquimetro.tiquete.entity.Tiquete;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    
    //TipoPagamentoEnum
    private String tipoPagamento;
    //listaPrecosEnum
    private String tarifaDescricao;
    
    private String quantidadeHoras;

    private boolean periodoFixo; // periodo poderá ser fixo, ou  poderá ser avulso
     
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Condutor condutor;
    
    @ManyToOne
    @JoinColumn(name = "cartaoId")
    private DadosCartao dadosCartao;

    @OneToOne
    @JoinColumn(name = "tiquete_id")
    private Tiquete tiquete;

    private static final Logger LOGGER = LoggerFactory.getLogger(Pagamento.class);

    /**
     * Returns the quantity of hours and minutes between the given entry and exit times.
     *
     * @param  horaEntrada   the entry time
     * @param  horaSaida     the exit time
     * @return               the quantity of hours and minutes in the format "XhYm"
     */
    public String getQuantidadeHoras(LocalTime horaEntrada, LocalTime horaSaida) {
        float segundos = ChronoUnit.SECONDS.between(horaEntrada, horaSaida);
        int horas = (int) segundos / 3600;
        int minutos = ((int) segundos % 3600) / 60;

        return Integer.toString(horas)+"h"+Integer.toString(minutos)+"m";
    }

    /**
     * Os recibos fornecem informações detalhadas, 
     * incluindo o tempo estacionado, 
     * a tarifa aplicada e o valor total pago
     *
     * @param  pagamento   description of parameter
     * @return             description of return value
     */
    public String criarRecibo(Pagamento pagamento) {
        String tempo = getQuantidadeHoras(pagamento.getHoraEntrada(), pagamento.getHoraSaida());
        String tarifaString = pagamento.isPeriodoFixo() ? listaPrecosEnum.FIXO.getDescricao() : listaPrecosEnum.AVULSO.getDescricao();
        float  tarifa= pagamento.isPeriodoFixo() ? listaPrecosEnum.FIXO.getPreco() : listaPrecosEnum.AVULSO.getPreco();
        float valor = calcularValor(pagamento.getHoraEntrada(), pagamento.getHoraSaida(), tarifa);
        String quebraLinha = "\n"; 
        String recibo = "recibo: \n"+"tempo estacionado: "+tempo+quebraLinha+"tarifa: "+tarifaString+quebraLinha+"total à ser pago: "+valor+quebraLinha; 
        return recibo;
    }

    /**
     * Calculate the value based on the entry and exit time and the price per hour.
     *
     * @param  horaEntrada    the entry time
     * @param  horaSaida      the exit time
     * @param  precoHora      the price per hour
     * @return               the calculated value
     */
    public float calcularValor(LocalTime horaEntrada, LocalTime horaSaida, float precoHora) {
        float tempo = calcularPeriodo(horaEntrada, horaSaida);
        return tempo * precoHora;
    }

    /**
     * Calculates the period between the given entrance and exit times in LocalTime.
     *
     * @param  horaEntrada   the entrance time
     * @param  horaSaida     the exit time
     * @return               the period in hours
     */
    public float calcularPeriodo(LocalTime horaEntrada, LocalTime horaSaida) {
        float segundos = ChronoUnit.SECONDS.between(horaEntrada, horaSaida);
        float horas = segundos / 3600;
        float minutos = (segundos % 3600) / 60;
        float segundosFinais = segundos % 60;

        LOGGER.info("tempo de estacionamento total: [{}h{}m{}s]", horas, minutos, segundosFinais);

        return segundos / 3600;
    }

}