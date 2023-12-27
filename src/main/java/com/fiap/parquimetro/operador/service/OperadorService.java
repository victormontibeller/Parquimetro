package com.fiap.parquimetro.operador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.parquimetro.condutor.DTO.UsuarioDTO;
import com.fiap.parquimetro.condutor.entity.Endereco;
import com.fiap.parquimetro.condutor.entity.Usuario;
import com.fiap.parquimetro.endereco.DTO.EnderecoDTO;
import com.fiap.parquimetro.operador.DTO.OperadorDTO;
import com.fiap.parquimetro.operador.entity.Operador;
import com.fiap.parquimetro.operador.repository.OperadorRepository;

@Service
public class OperadorService {
    @Autowired
    OperadorRepository operadorRepository;

    // read all
    public List<Operador> buscarOperadores() {
        return operadorRepository.findAll();
    }

    // read
    public Optional<Operador> buscarOperador(Long id) {
        return operadorRepository.findById(id);
    }

    // add
    public OperadorDTO inserirOperador(OperadorDTO operadorDTO) {
        Operador operador = toEntity(operadorDTO);

        // Salva o novo Operador no repositório
        operador = operadorRepository.save(operador);

        // Retorna o novo operador
        return toDTO(operador);
    }

    // update
    public OperadorDTO alterarOperador(OperadorDTO operadorDTO, long id) {
        Operador operador = operadorRepository.getReferenceById(id);

        operador = operadorRepository.save(toEntity(operadorDTO));

        return toDTO(operador);
    }

    // delete
    public void deletarOperador(long id) {
        operadorRepository.deleteById(id);
    }

    private OperadorDTO toDTO(Operador operador) {
        EnderecoDTO enderecoDTO = new EnderecoDTO(
        operador.getEndereco().getRua(), operador.getEndereco().getNumero(), operador.getEndereco().getBairro(),
        operador.getEndereco().getCidade(), operador.getEndereco().getEstado(),
        operador.getEndereco().getPais(), operador.getEndereco().getCep());

        UsuarioDTO usuarioDTO = new UsuarioDTO(operador.getUsuario().getId(), null, null, null, null);

        return new OperadorDTO(
                operador.getId(),
                operador.getMatricula(),
                operador.getDataNascimento(),
                operador.getEmail(),
                operador.getSexo(),
                operador.getCPF(),
                operador.getTelefone(),
                enderecoDTO,
                usuarioDTO
        );

    }

    private Operador toEntity(OperadorDTO operadorDTO) {
        Operador operador = new Operador();

        operador.setCPF(operadorDTO.CPF());
        operador.setDataNascimento(operadorDTO.dataNascimento());
        operador.setEmail(operadorDTO.email());
        operador.setSexo(operadorDTO.sexo());
        operador.setMatricula(operadorDTO.matricula());
        operador.setTelefone(operadorDTO.telefone());

        // Convertendo UsuarioDTO para Usuario
        Usuario usuario = new Usuario();
        usuario.setId(operadorDTO.usuario().id());

        operador.setUsuario(usuario);

        // Convertendo EnderecoDTO para Endereco
        Endereco endereco = new Endereco();
        
        endereco.setRua(operadorDTO.endereco().rua());
        endereco.setNumero(operadorDTO.endereco().numero());
        endereco.setBairro(operadorDTO.endereco().bairro());
        endereco.setCidade(operadorDTO.endereco().cidade());
        endereco.setEstado(operadorDTO.endereco().estado());
        endereco.setPais(operadorDTO.endereco().pais());
        endereco.setCep(operadorDTO.endereco().cep());
         
        operador.setEndereco(endereco);
 
        return operador;
    }    
}
