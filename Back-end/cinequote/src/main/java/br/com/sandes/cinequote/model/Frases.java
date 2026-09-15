package br.com.sandes.cinequote.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Frases {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String frase;
    private String contexto;
    private String personagem;

    @ManyToOne
    private Obra obra;

    public Frases() {

    }

    public Frases(String frase, String contexto, String personagem, Obra obra) {
        this.frase = frase;
        this.contexto = contexto;
        this.personagem = personagem;
        this.setObra(obra);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public String getPersonagem() {
        return personagem;
    }

    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }
}
