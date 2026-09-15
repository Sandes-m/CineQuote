package br.com.sandes.cinequote.repository;

import br.com.sandes.cinequote.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ObraRepository extends JpaRepository<Obra, UUID> {
}
