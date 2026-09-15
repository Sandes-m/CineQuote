package br.com.sandes.cinequote.model;

import br.com.sandes.cinequote.dto.OmdbDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Obra {

    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Frases> frase = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    private String poster;
    private String tipo;

    @Enumerated(EnumType.STRING)
    private Genero genero;


    public Obra() {

    }

    public Obra(OmdbDTO dadosDaObra) {
        this.nome = dadosDaObra.nome();
        this.poster = dadosDaObra.poster();
        this.genero = Genero.fromOmdb(dadosDaObra.genero().split(",")[0].trim());
        this.tipo = dadosDaObra.tipo();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Frases> getFrase() {
        return frase;
    }

    public void setFrase(List<Frases> frase) {
        this.frase = frase;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

}
