package br.unitins.ecommerce.dto.telefone;

import java.util.Map;


public record TelefonesResponseDTO(
    Map<String, Object> telefonePrincipal,
    Map<String, Object> telefoneOpcional
) {
    
}