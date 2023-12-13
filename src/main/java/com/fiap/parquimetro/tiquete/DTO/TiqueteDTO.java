package com.fiap.parquimetro.tiquete.DTO;

import com.fiap.parquimetro.condutor.DTO.CondutorDTO;
import com.fiap.parquimetro.preco.DTO.PrecoDTO;
import com.fiap.parquimetro.tiquete.entity.enumerations.StatusTiqueteEnum;
import com.fiap.parquimetro.tiquete.entity.enumerations.TipoPeriodoEnum;
import com.fiap.parquimetro.veiculo.DTO.VeiculoDTO;

import java.time.LocalDateTime;

public record TiqueteDTO(
        Long id,
        CondutorDTO condutor,
        VeiculoDTO veiculo,
        LocalDateTime entrada,
        LocalDateTime saida,
        TipoPeriodoEnum tipo,
        TipoPeriodoEnum periodo,
        PrecoDTO preco,
        StatusTiqueteEnum status
) {
    
}
