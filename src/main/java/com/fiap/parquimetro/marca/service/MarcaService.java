package com.fiap.parquimetro.marca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.parquimetro.marca.DTO.MarcaDTO;
import com.fiap.parquimetro.marca.entity.Marca;
import com.fiap.parquimetro.marca.repository.MarcaRepository;

@Service
public class MarcaService {

    @Autowired
    MarcaRepository marcaRepository;

    // read all
    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    // read
    public Optional<Marca> encontrarMarca(Long id) {
        return marcaRepository.findById(id);
    }

    // add
    public MarcaDTO inserirMarca(MarcaDTO marcaDTO) {
        Marca marca = toEntity(marcaDTO);

        // Salva o novo Condutor no repositório
        marca = marcaRepository.save(marca);

        // Retorna o novo condutor
        return toDTO(marca);
    }

    private MarcaDTO toDTO(Marca marca) {
        return new MarcaDTO(
                marca.getId(),
                marca.getNome(),
                marca.getPais());
    }

    private Marca toEntity(MarcaDTO marcaDTO) {
        Marca marca = new Marca();
        marca.setId(marcaDTO.id());
        marca.setNome(marcaDTO.nome());
        marca.setPais(marcaDTO.pais());

        return marca;
    }

}
