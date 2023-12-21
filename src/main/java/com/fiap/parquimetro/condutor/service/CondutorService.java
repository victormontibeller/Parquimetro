package com.fiap.parquimetro.condutor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.parquimetro.condutor.entity.Endereco;
import com.fiap.parquimetro.condutor.entity.Usuario;
import com.fiap.parquimetro.condutor.DTO.CondutorDTO;
import com.fiap.parquimetro.condutor.DTO.UsuarioDTO;
import com.fiap.parquimetro.condutor.entity.Condutor;
import com.fiap.parquimetro.condutor.repository.CondutorRepository;
import com.fiap.parquimetro.endereco.DTO.EnderecoDTO;

@Service
public class CondutorService {

    @Autowired
    CondutorRepository condutorRepository;

    public CondutorService(CondutorRepository condutorRepository) {
        this.condutorRepository = condutorRepository;
    }
    
    //read all
    public List<Condutor> buscarCondutores() {
        return condutorRepository.findAll();
    } 

    // add
    public CondutorDTO inserirCondutor(CondutorDTO condutorDTO) {
        Condutor condutor = toEntity(condutorDTO);

        // Salva o novo Condutor no repositório
        condutor = condutorRepository.save(condutor);

        // Retorna o novo condutor
        return toDTO(condutor);
    }

    // read
    public CondutorDTO encontrarCondutor(long numeroCnh) {
        return toDTO(condutorRepository.getReferenceById(numeroCnh));
    }

    // update
    public CondutorDTO alterarCondutor(CondutorDTO condutorDTO, long numeroCnh) {
        Condutor condutor = condutorRepository.getReferenceById(numeroCnh);

        condutor = condutorRepository.save(toEntity(condutorDTO));

        return toDTO(condutor);
    }

    // delete
    public void deletarCondutor(long numeroCnh) {
        condutorRepository.deleteById(numeroCnh);
    }

    private CondutorDTO toDTO(Condutor condutor) {
        
        EnderecoDTO enderecoDTO = new EnderecoDTO(
                condutor.getEndereco().getRua(), condutor.getEndereco().getNumero(), condutor.getEndereco().getBairro(),
                condutor.getEndereco().getCidade(), condutor.getEndereco().getEstado(),
                condutor.getEndereco().getPais(), condutor.getEndereco().getCep());
                 
        UsuarioDTO usuarioDTO = new UsuarioDTO(condutor.getUsuario().getId(), null, null, null, null);

        return new CondutorDTO(
                condutor.getNumeroCnh(),
                condutor.getDataNascimento(),
                condutor.getEmail(),
                condutor.getSexo(),
                condutor.getCPF(),
                condutor.getTelefone(),
                enderecoDTO,
                usuarioDTO
        );

    }

    private Condutor toEntity(CondutorDTO condutorDTO) {
        Condutor condutor = new Condutor();
        condutor.setNumeroCnh(condutorDTO.numeroCnh());
        condutor.setDataNascimento(condutorDTO.dataNascimento());
        condutor.setEmail(condutorDTO.email());
        condutor.setSexo(condutorDTO.sexo());
        condutor.setCPF(condutorDTO.CPF());
        condutor.setTelefone(condutorDTO.telefone());

        // Convertendo EnderecoDTO para Endereco
        Endereco endereco = new Endereco();
        
        endereco.setRua(condutorDTO.endereco().rua());
        endereco.setNumero(condutorDTO.endereco().numero());
        endereco.setBairro(condutorDTO.endereco().bairro());
        endereco.setCidade(condutorDTO.endereco().cidade());
        endereco.setEstado(condutorDTO.endereco().estado());
        endereco.setPais(condutorDTO.endereco().pais());
        endereco.setCep(condutorDTO.endereco().cep());
         
        condutor.setEndereco(endereco);

        // Convertendo UsuarioDTO para Usuario
        Usuario usuario = new Usuario();
        usuario.setId(condutorDTO.usuario().id());
        condutor.setUsuario(usuario);

        return condutor;
    }
}