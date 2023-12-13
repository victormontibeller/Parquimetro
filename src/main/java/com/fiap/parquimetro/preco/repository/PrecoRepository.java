package com.fiap.parquimetro.preco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.parquimetro.preco.entity.Preco;

@Repository
public interface PrecoRepository extends JpaRepository<Preco, Long> { 

}