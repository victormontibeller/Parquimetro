package com.fiap.parquimetro.condutor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.parquimetro.condutor.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> { 

}