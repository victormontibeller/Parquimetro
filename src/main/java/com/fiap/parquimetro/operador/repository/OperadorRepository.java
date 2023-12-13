package com.fiap.parquimetro.operador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.parquimetro.operador.entity.Operador;

@Repository
public interface OperadorRepository extends JpaRepository<Operador, Long> { 

}