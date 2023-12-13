package com.fiap.parquimetro.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.parquimetro.modelo.entity.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> { 

}