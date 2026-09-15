package br.com.sandes.cinequote.repository;

import br.com.sandes.cinequote.model.Frases;
import br.com.sandes.cinequote.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface FrasesRepository extends JpaRepository<Frases, UUID> {

    @Query(value = "SELECT * FROM frases ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Frases sortearUmaObra();

    @Query("SELECT f FROM Frases f WHERE f.obra.genero = :g ORDER BY function('RANDOM')")
    List<Frases> sortearPorGenero(Genero g, Pageable pageable);
}
