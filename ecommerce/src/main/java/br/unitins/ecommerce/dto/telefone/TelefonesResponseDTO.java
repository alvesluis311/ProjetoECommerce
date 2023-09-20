package br.unitins.ecommerce.dto.telefone;

import java.util.Map;

import br.unitins.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.unitins.ecommerce.model.usuario.Usuario;

public record TelefonesResponseDTO(
    Map<String, Object> telefonePrincipal,
    Map<String, Object> telefoneOpcional
) {
    
}