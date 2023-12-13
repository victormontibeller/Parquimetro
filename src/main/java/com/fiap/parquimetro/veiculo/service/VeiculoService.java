package com.fiap.parquimetro.veiculo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.fiap.parquimetro.cliente.entity.Modelo;
import com.fiap.parquimetro.condutor.DTO.CondutorDTO;
import com.fiap.parquimetro.condutor.entity.Condutor;
import com.fiap.parquimetro.modelo.DTO.ModeloDTO;
import com.fiap.parquimetro.veiculo.DTO.VeiculoDTO;
import com.fiap.parquimetro.veiculo.entity.Veiculo;
import com.fiap.parquimetro.veiculo.repository.VeiculoRepository;

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

    // read
    public VeiculoDTO encontrarVeiculo(long numeroCnh) {
        return toDTO(veiculoRepository.getReferenceById(numeroCnh));
    }

    // update
    public VeiculoDTO alterarVeiculo(VeiculoDTO veiculoDTO, long numeroCnh) {
        Veiculo veiculo = veiculoRepository.getReferenceById(numeroCnh);

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
