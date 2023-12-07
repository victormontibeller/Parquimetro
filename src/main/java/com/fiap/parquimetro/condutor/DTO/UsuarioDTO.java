package com.fiap.parquimetro.condutor.DTO;

import com.fiap.parquimetro.condutor.DTO.UsuarioDTO;
import com.fiap.parquimetro.condutor.entity.enumerations.TipoUsuarioEnum;

public record UsuarioDTO(
    Long id,
    String login,
    String senha,
    String nome,
    TipoUsuarioEnum tipoUsuario
) {
}
