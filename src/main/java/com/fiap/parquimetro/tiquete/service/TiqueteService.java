package com.fiap.parquimetro.tiquete.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.parquimetro.preco.DTO.PrecoDTO;
import com.fiap.parquimetro.tiquete.DTO.TiqueteDTO;
import com.fiap.parquimetro.tiquete.entity.Tiquete;
import com.fiap.parquimetro.tiquete.repository.TiqueteRepository;
import com.fiap.parquimetro.veiculo.DTO.VeiculoDTO;
import com.fiap.parquimetro.veiculo.entity.Veiculo;

@Service
public class TiqueteService {

    @Autowired
    TiqueteRepository tiqueteRepository;

    // read all
    public List<Tiquete> buscarTiquetes() {
        return tiqueteRepository.findAll();
    }

    // read
    public Optional<Tiquete> buscarTiquete(Long id) {
        return tiqueteRepository.findById(id);
    }

    // add
    public TiqueteDTO inserirTiquete(TiqueteDTO tiqueteDTO) {
        Tiquete tiquete = toEntity(tiqueteDTO);

        // Salva a nova Tiquete no repositório
        tiquete = tiqueteRepository.save(tiquete);

        // Retorna a nova tiquete
        return toDTO(tiquete);
    }

    // update
    public TiqueteDTO alterarTiquete(TiqueteDTO tiqueteDTO, long id) {
        Tiquete tiquete = tiqueteRepository.getReferenceById(id);

        tiquete = tiqueteRepository.save(toEntity(tiqueteDTO));

        return toDTO(tiquete);
    }

    // delete
    public void deletarTiquete(long id) {
        tiqueteRepository.deleteById(id);
    }


    private TiqueteDTO toDTO(Tiquete tiquete) {
        ///CondutorDTO condutorDoVeiculo = new CondutorDTO(null);
        VeiculoDTO veiculoTiquete = new VeiculoDTO(0, null, null, null, null);
        PrecoDTO precoTiquete = new PrecoDTO(null, null, null, null);

        return new TiqueteDTO(
                tiquete.getId(),
                veiculoTiquete,
                tiquete.getEntrada(),
                tiquete.getSaida(),
                tiquete.getTipo(),
                tiquete.getPeriodo(),
                tiquete.getStatus(),
                precoTiquete);
    }

    private Tiquete toEntity(TiqueteDTO tiqueteDTO) {
        Veiculo veiculoTiquete = new Veiculo();
        veiculoTiquete.setId(tiqueteDTO.veiculo().id());

        Tiquete tiquete = new Tiquete();
        tiquete.setId(tiqueteDTO.id());
        tiquete.setVeiculo(veiculoTiquete);
        tiquete.setEntrada(tiqueteDTO.entrada());
        tiquete.setSaida(tiqueteDTO.saida());
        tiquete.setPeriodo(tiqueteDTO.periodo());
        tiquete.setStatus(tiqueteDTO.status());

        return tiquete;
    }

}
