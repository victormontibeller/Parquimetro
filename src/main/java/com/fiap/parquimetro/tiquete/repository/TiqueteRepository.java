package com.fiap.parquimetro.tiquete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.parquimetro.tiquete.entity.Tiquete;

@Repository
public interface TiqueteRepository extends JpaRepository<Tiquete, Long> { 

}