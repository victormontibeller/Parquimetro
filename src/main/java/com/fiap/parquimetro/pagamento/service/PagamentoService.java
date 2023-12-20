package com.fiap.parquimetro.pagamento.service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.parquimetro.pagamento.entity.Pagamento;
import com.fiap.parquimetro.pagamento.entity.TipoPagamentoEnum;
import com.fiap.parquimetro.pagamento.entity.listaPrecosEnum;
import com.fiap.parquimetro.pagamento.repository.PagamentoRepository;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoService.class);

    //create
    public Pagamento inserirPagamento(Pagamento pagamento) throws Exception{
        /* ***melhorar*** - Antes de usar o sistema, o condutor deve registrar sua forma de pagamento preferida, que pode incluir
        cartão de crédito, débito ou PIX.
        - A opção PIX só está disponível para períodos de estacionamento fixos.*/

        if(!pagamento.isPeriodoFixo()){ 
            if(TipoPagamentoEnum.PIX == pagamento.getTipoPagamento()){
                throw new Exception("pagamento só poderá ser feito via crédito ou débito");       
            }
            pagamento.setValor(calcularValor(pagamento.getHoraEntrada(), 
                                             pagamento.getHoraSaida(), 
                                             listaPrecosEnum.AVULSO.getPreco()));
        } else {
            pagamento.setValor(calcularValor(pagamento.getHoraEntrada(), 
                                             pagamento.getHoraSaida(), 
                                             listaPrecosEnum.FIXO.getPreco()));
        }
        return pagamentoRepository.save(pagamento);
    }

    //read
    public Pagamento encontrarPagamento(Long id) {
        return pagamentoRepository.getReferenceById(id);
    } 

    /*update
    possibilidade de que caso o periodo seja fixo, o 
    pagamento deverá ser realizado até o limite do periodo + uma tolerância. 
    */

    /*delete
    não vejo sentido em ter um delete de pagamento, 
    dado que, se foi pago isso não poderá ser alterado no sistema
    */

    public float calcularValor(LocalTime horaEntrada, LocalTime horaSaida, float precoHora) {
        float tempo = calcularPeriodo(horaEntrada, horaSaida);
        return tempo * precoHora;
    }

    public float calcularPeriodo(LocalTime horaEntrada, LocalTime horaSaida) {
        float segundos = ChronoUnit.SECONDS.between(horaEntrada, horaSaida);
        float horas = segundos / 3600;
        float minutos = (segundos % 3600) / 60;
        float segundosFinais = segundos % 60;

        LOGGER.info("tempo de estacionamento total: [{}h{}m{}s]", horas, minutos, segundosFinais);

        return segundos / 3600;
    }


}