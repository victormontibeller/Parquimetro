package com.fiap.parquimetro.modelo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fiap.parquimetro.modelo.DTO.ModeloDTO;
import com.fiap.parquimetro.modelo.entity.Modelo;
import com.fiap.parquimetro.modelo.repository.ModeloRepository;

@Service
public class ModeloService {

    @Autowired
    ModeloRepository modeloRepository;

    // read all
    public List<Modelo> buscarModelos() {
        return modeloRepository.findAll();
    }

    // read
    public Optional<Modelo> buscarModelo(Long id) {
        return modeloRepository.findById(id);
    }

    // add
    public ModeloDTO inserirModelo(ModeloDTO modeloDTO) {
        Modelo modelo = toEntity(modeloDTO);

        // Salva o novo Modelo no repositório
        modelo = modeloRepository.save(modelo);

        // Retorna o novo modelo
        return toDTO(modelo);
    }

    // update
    public ModeloDTO alterarModelo(ModeloDTO modeloDTO, long id) {
        Modelo modelo = modeloRepository.getReferenceById(id);

        modelo = modeloRepository.save(toEntity(modeloDTO));

        return toDTO(modelo);
    }

    // delete
    public void deletarModelo(long id) {
        modeloRepository.deleteById(id);
    }


    private ModeloDTO toDTO(Modelo modelo) {
        //MarcaDTO marcaDoModelo = new MarcaDTO(modelo.getId(),null,null);
        
        return new ModeloDTO(
                modelo.getId(),
                modelo.getNome(),
                modelo.getMarca());
    }

    private Modelo toEntity(ModeloDTO modeloDTO) {
        Modelo modelo = new Modelo();
        modelo.setId(modeloDTO.id());
        modelo.setNome(modeloDTO.nome());
        modelo.setMarca(modeloDTO.marca());

        return modelo;
    }

}
