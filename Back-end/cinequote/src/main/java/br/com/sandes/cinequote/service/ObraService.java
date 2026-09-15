package br.com.sandes.cinequote.service;

import br.com.sandes.cinequote.dto.FrasesDTO;
import br.com.sandes.cinequote.dto.ObraDTO;
import br.com.sandes.cinequote.model.Frases;
import br.com.sandes.cinequote.model.Genero;
import br.com.sandes.cinequote.model.Obra;
import br.com.sandes.cinequote.repository.FrasesRepository;
import br.com.sandes.cinequote.repository.ObraRepository;
import br.com.sandes.cinequote.utils.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObraService {

   @Autowired
   private ObraRepository obraRepository;
   @Autowired
   private FrasesRepository frasesRepository;

    private ObraDTO conversorObraDTO(Frases f) {
        return new ObraDTO(f.getObra().getId(), f.getObra().getNome(), f.getObra().getPoster(),
                f.getObra().getGenero(), f.getFrase(), f.getPersonagem(), f.getContexto());
    }

    public ObraDTO buscarObra() {
        return conversorObraDTO(frasesRepository.sortearUmaObra());
    }

    public ObraDTO buscarFrase() {
        ObraDTO obraDoBanco = buscarObra();
        Obra obra = obraRepository.findById(obraDoBanco.id()).get();

        List<String> frasesExistentes = obra.getFrase().stream()
                .map(Frases::getFrase)
                .toList();

        try {

            String jsonAi = AiClient.buscarInfoObra(obra.getNome(), frasesExistentes);

            DataMapper dataMapper = new DataMapper();
            var f = dataMapper.obterDados(jsonAi, FrasesDTO.class);

            Frases novaFrase = new Frases(f.frase(), f.contexto(), f.personagem(), obra);
            frasesRepository.save(novaFrase);

            return new ObraDTO(obra.getId(), obra.getNome(), obra.getPoster(), obra.getGenero(), f.frase(),
                    f.personagem(), f.contexto());
        } catch (Exception e) {
            System.err.println("Aviso: IA indisponível. Aplicando fallback: " + e.getMessage());
            return obraDoBanco;
        }

    }

    public ObraDTO buscarPorGenero(String genero) {
       Genero g = Genero.fromUsuario(genero);

       List<Frases> fraseDoBanco = frasesRepository.sortearPorGenero(g, PageRequest.of(0,1));

       Frases frase = fraseDoBanco.stream()
               .findFirst()
               .orElseThrow(() -> new RuntimeException("Nenhuma frase encontrada com esse gênero!"));

       return conversorObraDTO(frase);

    }
}
