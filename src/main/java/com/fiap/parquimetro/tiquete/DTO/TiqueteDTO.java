package com.fiap.parquimetro.tiquete.DTO;

import com.fiap.parquimetro.preco.DTO.PrecoDTO;
import com.fiap.parquimetro.tiquete.entity.enumerations.StatusTiqueteEnum;
import com.fiap.parquimetro.tiquete.entity.enumerations.TipoTiqueteEnum;
import com.fiap.parquimetro.veiculo.DTO.VeiculoDTO;

import java.time.LocalDateTime;

public record TiqueteDTO(
        Long id,
        VeiculoDTO veiculo,
        LocalDateTime entrada,
        LocalDateTime saida,
        TipoTiqueteEnum tipo,
        TipoTiqueteEnum periodo,
        StatusTiqueteEnum status,
        PrecoDTO preco
) {
    
}
