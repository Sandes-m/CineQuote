package br.com.sandes.cinequote.utils;

import br.com.sandes.cinequote.dto.FrasesDTO;
import br.com.sandes.cinequote.dto.OmdbDTO;
import br.com.sandes.cinequote.exception.AiRequestFailedException;
import br.com.sandes.cinequote.model.Frases;
import br.com.sandes.cinequote.model.Obra;
import br.com.sandes.cinequote.repository.FrasesRepository;
import br.com.sandes.cinequote.repository.ObraRepository;
import br.com.sandes.cinequote.service.AiClient;
import br.com.sandes.cinequote.service.OmdbClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private FrasesRepository frasesRepository;
    @Autowired
    private ObraRepository obraRepository;


    // Método para semear o banco com novos dados
    public void seedDataBase() {

        Scanner entrada = new Scanner(System.in);

        System.out.println("Cadastrar uma Obra no banco? (y/N");
        String resposta = entrada.nextLine().trim().toLowerCase();

        if (!resposta.equalsIgnoreCase("y")) {
            System.out.println("Rodando aplicação normalmente...");
        }

        while(resposta.equalsIgnoreCase("y")) {

            System.out.println("Digite o nome da Obra a ser salva (ou '0' para sair):");
            String obra = entrada.nextLine();

            if (obra.equalsIgnoreCase("0")) {
                System.out.println("Encerrando cadastro!");
                break;
            }

            OmdbClient omdbClient = new OmdbClient();
            String jsonOmdb = omdbClient.omdbRequest(obra);
            String jsonAi = AiClient.buscarInfoObra(obra);

            DataMapper dataMapper = new DataMapper();
            OmdbDTO omdbDTO = dataMapper.obterDados(jsonOmdb, OmdbDTO.class);
            if (omdbDTO.nome() == null || omdbDTO.genero() == null) {
                System.out.println("Obra não encontrada na OMDb! Dica: tente o título original em inglês.");
                continue;
            }

            System.out.println("Retorno da OMDb: " + jsonOmdb);
            FrasesDTO frasesDTO = dataMapper.obterDados(jsonAi, FrasesDTO.class);
            if (frasesDTO == null || frasesDTO.frase() == null || frasesDTO.frase().isBlank()) {
                throw new AiRequestFailedException("IA Indisponível no momento!");
            }

            Obra obraPronta = new Obra(omdbDTO);
            Frases fraseDaObra = new Frases(frasesDTO.frase(), frasesDTO.contexto(), frasesDTO.personagem(), obraPronta);
            obraPronta.getFrase().add(fraseDaObra);

            obraRepository.save(obraPronta);
            System.out.println("Obra salva com sucesso!");


        }

    }

    // Método para atualizar o banco.
    public void updateDataBase() {

        // Selecione o ID da obra
        UUID id = UUID.fromString("");
        Obra obra = obraRepository.findById(id).orElse(null);

        if (obra != null) {
            // Selecione quais informações da obra serão atualizadas...
            obra.setPoster("");
            obraRepository.save(obra);

            System.out.println("Obra atualizada com sucesso!");
        }
    }

    @Override
    public void run(String... args) throws Exception {
        seedDataBase();
    }
}
