package br.com.sandes.cinequote.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FrasesDTO(
        @NotBlank(message = "A frase não pode ser vazia!")
        String frase,

        @NotBlank(message = "O nome do personagem é obrigatório!")
        @Size(max = 100, message = "O nome do personagem é muito longo!")
        String personagem,

        @NotBlank(message = "O contexto é obrigatório!")
        @Size(max = 250, message = "O contexto não pode ultrapassar 250 caracteres!")
        String contexto) {
}
