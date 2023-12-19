package com.fiap.parquimetro.marca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.parquimetro.marca.entity.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> { 

}