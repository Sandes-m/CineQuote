package br.com.sandes.cinequote;


import br.com.sandes.cinequote.model.Obra;
import br.com.sandes.cinequote.repository.ObraRepository;
import br.com.sandes.cinequote.utils.DataSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class CinequoteApplication implements CommandLineRunner {

	@Autowired
	private DataSeeder dataSeeder;
	@Autowired
	private ObraRepository obraRepository;

	public static void main(String[] args) {
		SpringApplication.run(CinequoteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataSeeder.seedDataBase();
	}
}

