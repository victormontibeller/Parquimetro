package com.fiap.parquimetro.tiquete.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.parquimetro.pagamento.DTO.PagamentoDTO;
import com.fiap.parquimetro.pagamento.service.PagamentoService;
import com.fiap.parquimetro.tiquete.DTO.TiqueteDTO;
import com.fiap.parquimetro.tiquete.entity.Tiquete;
import com.fiap.parquimetro.tiquete.repository.TiqueteRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class TiqueteService {

    //aqui ficam as regras de preeenchimento do Tiquete service
    @Autowired
    TiqueteRepository tiqueteRepository;

    @Autowired
    private PagamentoService pagamentoService;

    // read all
    public List<Tiquete> findAll() {
        return tiqueteRepository.findAll();
    }

    // read
    public Optional<Tiquete> encontrarTiquete(Long id) {
        return tiqueteRepository.findById(id);
    }

    // add
    public TiqueteDTO inserirTiquete(PagamentoDTO pagamentoDTO) {
        Tiquete tiquete = toEntity(pagamentoDTO);
        
        tiquete = tiqueteRepository.save(tiquete);

        return toDTO(tiquete);
    }

    public TiqueteDTO getTiquete(Long tiqueteId) {
        Tiquete tiquete = tiqueteRepository.findById(tiqueteId)
                .orElseThrow(() -> new EntityNotFoundException("Tiquete not found with id: " + tiqueteId));

        return toDTO(tiquete);
    }

    public TiqueteDTO toDTO(Tiquete tiquete) {
        PagamentoDTO pagamentoDTO = null;

        if(tiquete.getPagamentos() != null){
            pagamentoDTO = pagamentoService.toDTO(tiquete.getPagamentos());
        }

        return new TiqueteDTO(
                pagamentoDTO,
                tiquete.getEntrada(),
                tiquete.getSaida(),
                tiquete.getPeriodo(),
                tiquete.getDescricaoTarifa(),
                tiquete.getPreco());
    }

    private Tiquete toEntity(PagamentoDTO pagamentoDTO) {
        Tiquete tiquete = new Tiquete();
        tiquete.setEntrada(pagamentoDTO.horaEntrada());
        tiquete.setSaida(pagamentoDTO.horaSaida());
        //tiquete.setTipoPagamento(TipoPagamentoEnum.valueOf(pagamentoDTO.tipoPagamento()));
        tiquete.setPreco(/*Float.toString(pagamentoDTO.valor())*/"10.00");
        tiquete.setPeriodo(/*pagamentoDTO.quantidadeHoras()*/"12h");
        tiquete.setDescricaoTarifa(pagamentoDTO.tarifaDescricao());

        return tiquete;
    }


}