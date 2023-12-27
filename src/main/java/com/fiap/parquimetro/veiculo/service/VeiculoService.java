package com.fiap.parquimetro.veiculo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.parquimetro.condutor.DTO.CondutorDTO;
import com.fiap.parquimetro.condutor.entity.Condutor;
import com.fiap.parquimetro.modelo.DTO.ModeloDTO;
import com.fiap.parquimetro.modelo.entity.Modelo;
import com.fiap.parquimetro.veiculo.DTO.VeiculoDTO;
import com.fiap.parquimetro.veiculo.entity.Veiculo;
import com.fiap.parquimetro.veiculo.repository.VeiculoRepository;

@Service
public class VeiculoService {
    @Autowired
    VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    // add
    public VeiculoDTO inserirVeiculo(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = toEntity(veiculoDTO);

        // Salva o novo Veiculo no repositório
        veiculo = veiculoRepository.save(veiculo);

        // Retorna o novo veiculo
        return toDTO(veiculo);
    }

    // read all
    public List<Veiculo> buscarVeiculos() {
        return veiculoRepository.findAll();
    }

    // read
    public Optional<Veiculo> buscarVeiculo(Long id) {
        return veiculoRepository.findById(id);
    }

    // update
    public VeiculoDTO alterarVeiculo(VeiculoDTO veiculoDTO, long id) {
        Veiculo veiculo = veiculoRepository.getReferenceById(id);

        veiculo = veiculoRepository.save(toEntity(veiculoDTO));

        return toDTO(veiculo);
    }

    // delete
    public void deletarVeiculo(long id) {
        veiculoRepository.deleteById(id);
    }

    private VeiculoDTO toDTO(Veiculo veiculo) {
        
        CondutorDTO condutorDTO = new CondutorDTO(veiculo.getCondutor().getNumeroCnh());

        ModeloDTO modeloDTO = new ModeloDTO(veiculo.getModelo().getId());

        return new VeiculoDTO(
                veiculo.getId(),
                veiculo.getPlaca(),
                veiculo.getCor(),
                modeloDTO,
                condutorDTO
        );

    }

    private Veiculo toEntity(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(veiculoDTO.placa());
        veiculo.setCor(veiculoDTO.cor());

        // Convertendo CondutorDTO para Condutor
        Condutor condutor = new Condutor();
        condutor.setNumeroCnh(veiculoDTO.condutor().numeroCnh());
        veiculo.setCondutor(condutor);

        // Convertendo ModeloDTO para Modelo
        Modelo modelo = new Modelo();
        modelo.setId(veiculoDTO.modelo().id());
        veiculo.setModelo(modelo);
 
        return veiculo;
    }    
}
