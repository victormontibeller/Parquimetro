package com.fiap.parquimetro.pagamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.parquimetro.pagamento.DTO.PagamentoDTO;
import com.fiap.parquimetro.pagamento.entity.Pagamento;
import com.fiap.parquimetro.pagamento.entity.TipoPagamentoEnum;
import com.fiap.parquimetro.pagamento.entity.listaPrecosEnum;
import com.fiap.parquimetro.pagamento.repository.PagamentoRepository;
import com.fiap.parquimetro.tiquete.entity.Tiquete;
import com.fiap.parquimetro.tiquete.entity.enumerations.StatusTiqueteEnum;
import com.fiap.parquimetro.tiquete.repository.TiqueteRepository;

import lombok.NonNull;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private TiqueteRepository tiqueteRepository;

    //create
    public PagamentoDTO inserirPagamento(PagamentoDTO pagamentoDTO, 
                                            @NonNull Long tiqueteId) throws Exception{
        Pagamento pagamento = toEntity(pagamentoDTO);
        if(pagamento == null){
            throw new Exception("valor do pagamento é nulo");       
        }

        calcularInformacoesPagamento(pagamento);
        Tiquete tiquete = preencherTiquete(pagamento);
        tiqueteRepository.save(tiquete);

        pagamento = pagamentoRepository.save(pagamento);

        return toDTO(pagamento);
    }

    //read
    @NonNull 
    public Optional<Pagamento> encontrarPagamento(Long id) {
        return pagamentoRepository.findById(id);
    } 

    //read all
    public List<Pagamento> findAll(){
        return pagamentoRepository.findAll();
    }

    /**
     * Calcula as informações de pagamento com base no objeto Pagamento fornecido.
     *
     * @param  pagamento  o objeto Pagamento contendo as informações necessárias
     * @throws Exception  se ocorrer um erro durante o cálculo das informações de pagamento
     */
    void calcularInformacoesPagamento(Pagamento pagamento) throws Exception{
        final String quantidadeHoras = pagamento.getQuantidadeHoras(pagamento.getHoraEntrada(), 
                                                                    pagamento.getHoraSaida());
        pagamento.setQuantidadeHoras(quantidadeHoras);

        if(!pagamento.isPeriodoFixo()){ 
            if(TipoPagamentoEnum.PIX.getDescricao() == pagamento.getTipoPagamento()){
                throw new Exception("pagamento só poderá ser feito via crédito ou débito");       
            }
            pagamento.setValor(pagamento.calcularValor(pagamento.getHoraEntrada(), 
                                             pagamento.getHoraSaida(), 
                                             listaPrecosEnum.AVULSO.getPreco()));
        } else {
            pagamento.setValor(pagamento.calcularValor(pagamento.getHoraEntrada(), 
                                             pagamento.getHoraSaida(), 
                                             listaPrecosEnum.FIXO.getPreco()));
        }
    }

    /**
     * Converts Pagamento object to PagamentoDTO object.
     *
     * @param  pagamento  the Pagamento object to be converted
     * @return            the PagamentoDTO object
     */
    public PagamentoDTO toDTO(Pagamento pagamento){
        return new PagamentoDTO(
            pagamento.getId(), 
            pagamento.getValor(), 
            pagamento.getDataPagamento(), 
            pagamento.getHoraEntrada(), 
            pagamento.getHoraSaida(), 
            pagamento.getTipoPagamento(), 
            pagamento.getQuantidadeHoras(), 
            pagamento.getTarifaDescricao(),
            pagamento.getCondutor(),       
            pagamento.getDadosCartao(), 
            pagamento.getTiquete()
            );
    }
    /**
     * Converts PagamentoDTO to Pagamento entity.
     *
     * @param  pagamentoDTO  the PagamentoDTO to be converted
     * @return               the Pagamento entity
     */
    private Pagamento toEntity(PagamentoDTO pagamentoDTO){
        Pagamento pagamento = new Pagamento();
        pagamento.setId(pagamentoDTO.id());
        pagamento.setValor(pagamentoDTO.valor());
        pagamento.setDataPagamento(pagamentoDTO.dataPagamento());
        pagamento.setHoraEntrada(pagamentoDTO.horaEntrada());
        pagamento.setHoraSaida(pagamentoDTO.horaSaida());
        pagamento.setTarifaDescricao(pagamentoDTO.tarifaDescricao());
        pagamento.setQuantidadeHoras(pagamentoDTO.quantidadeHoras());
        pagamento.setTipoPagamento(pagamentoDTO.tipoPagamento());
        pagamento.setCondutor(pagamentoDTO.condutor());
        pagamento.setDadosCartao(pagamentoDTO.dadosCartao());

        return pagamento;
    }
    
    /**
     * preencherTiquete - Fills out a tiquete based on the given payment information.
     *
     * @param  pagamento   the payment information to fill the tiquete with
     * @return             the filled out tiquete
     */
    public Tiquete preencherTiquete(Pagamento pagamento){
        //tiquete.setVeiculo(null);
        Tiquete tiquete = new Tiquete();
        tiquete.setCondutor(pagamento.getCondutor());
        tiquete.setEntrada(pagamento.getHoraEntrada());
        tiquete.setSaida(pagamento.getHoraSaida());
        tiquete.setDescricaoTarifa(pagamento.getTarifaDescricao());
        tiquete.setPeriodo(pagamento.getQuantidadeHoras());
        tiquete.setPreco(Float.toString(pagamento.getValor()));
        tiquete.setTipoPagamento(pagamento.getTipoPagamento());
        tiquete.setStatus(StatusTiqueteEnum.PAGO);
        tiquete.setPagamentos(pagamento);   
    
        return tiquete;
    }
}