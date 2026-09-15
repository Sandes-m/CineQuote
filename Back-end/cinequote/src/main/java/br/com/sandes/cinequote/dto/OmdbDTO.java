package br.com.sandes.cinequote.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OmdbDTO(@JsonAlias("Title") String nome,
                      @JsonAlias("Poster") String poster,
                      @JsonAlias("Genre") String genero,
                      @JsonAlias("Type") String tipo) {


}


