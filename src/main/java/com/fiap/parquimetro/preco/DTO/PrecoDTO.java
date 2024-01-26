package com.fiap.parquimetro.preco.DTO;

import com.fiap.parquimetro.tiquete.entity.Tiquete;
import com.fiap.parquimetro.tiquete.entity.enumerations.HorarioTiqueteEnum;

import java.math.BigDecimal;

public record PrecoDTO(
        Long id,
        BigDecimal precoPeriodo,
        BigDecimal precoHoraExcedente,
        String periodo,
        Tiquete tiquete
) {
  
}
