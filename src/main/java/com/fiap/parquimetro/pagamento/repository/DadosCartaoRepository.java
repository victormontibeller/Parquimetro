package com.fiap.parquimetro.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.parquimetro.pagamento.entity.DadosCartao;

@Repository
public interface DadosCartaoRepository extends JpaRepository<DadosCartao, Long>{
    
}
