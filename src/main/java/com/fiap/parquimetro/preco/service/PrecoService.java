package com.fiap.parquimetro.preco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.parquimetro.preco.DTO.PrecoDTO;
import com.fiap.parquimetro.preco.entity.Preco;
import com.fiap.parquimetro.preco.repository.PrecoRepository;

@Service
public class PrecoService {

    @Autowired
    private PrecoRepository precoRepository;

    // read all
    public List<Preco> buscarPrecos() {
        return precoRepository.findAll();
    }

    // read
    public Optional<Preco> buscarPreco(Long id) {
        return precoRepository.findById(id);
    }

    // add
    public PrecoDTO inserirPreco(PrecoDTO precoDTO) {
        Preco preco = toEntity(precoDTO);

        // Salva a nova Preco no repositório
        preco = precoRepository.save(preco);

        // Retorna a nova preco
        return toDTO(preco);
    }

    // update
    public PrecoDTO alterarPreco(PrecoDTO precoDTO, long id) {
        Preco preco = precoRepository.getReferenceById(id);

        preco = precoRepository.save(toEntity(precoDTO));

        return toDTO(preco);
    }

    // delete
    public void deletarPreco(long id) {
        precoRepository.deleteById(id);
    }


    private PrecoDTO toDTO(Preco preco) {
        return new PrecoDTO(
                preco.getId(),
                preco.getPrecoPeriodo(),
                preco.getPrecoHoraExcedente(),
                preco.getPeriodo());
    }

    private Preco toEntity(PrecoDTO precoDTO) {
        Preco preco = new Preco();
        preco.setId(precoDTO.id());
        preco.setPrecoPeriodo(precoDTO.precoPeriodo());
        preco.setPrecoHoraExcedente(precoDTO.precoHoraExcedente());
        preco.setPeriodo(precoDTO.periodo());

        return preco;
    }

}
