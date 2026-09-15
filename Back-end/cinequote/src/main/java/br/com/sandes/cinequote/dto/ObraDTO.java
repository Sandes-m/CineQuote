package br.com.sandes.cinequote.dto;

import br.com.sandes.cinequote.model.Genero;

import java.util.UUID;

public record ObraDTO(UUID id,
                      String titulo,
                      String poster,
                      Genero genero,
                      String frase,
                      String personagem,
                      String contexto) {
}
