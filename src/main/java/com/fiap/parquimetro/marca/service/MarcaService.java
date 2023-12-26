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
    public List<Marca> buscarMarcas() {
        return marcaRepository.findAll();
    }

    // read
    public Optional<Marca> buscarMarca(Long id) {
        return marcaRepository.findById(id);
    }

    // add
    public MarcaDTO inserirMarca(MarcaDTO marcaDTO) {
        Marca marca = toEntity(marcaDTO);

        // Salva a nova Marca no repositório
        marca = marcaRepository.save(marca);

        // Retorna a nova marca
        return toDTO(marca);
    }

    // update
    public MarcaDTO alterarMarca(MarcaDTO marcaDTO, long id) {
        Marca marca = marcaRepository.getReferenceById(id);

        marca = marcaRepository.save(toEntity(marcaDTO));

        return toDTO(marca);
    }

    // delete
    public void deletarMarca(long id) {
        marcaRepository.deleteById(id);
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
