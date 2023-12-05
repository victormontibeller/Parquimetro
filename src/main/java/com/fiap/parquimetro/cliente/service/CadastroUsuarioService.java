package com.fiap.parquimetro.cliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.parquimetro.cliente.DTO.AtualizarUsuarioDTO;
import com.fiap.parquimetro.cliente.entity.Usuario;
import com.fiap.parquimetro.cliente.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    //creat
    public Usuario inserirUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //read
    public Usuario encontrarUsuario(Long id) {
        return usuarioRepository.getReferenceById(id);
    } 

    //update
    public Usuario alterarUsuario(AtualizarUsuarioDTO atualizaUsuario, long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.setSenha(atualizaUsuario.senha());
        usuario.setLogin(atualizaUsuario.login());

        return usuarioRepository.save(usuario);
    }

    //delete
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}