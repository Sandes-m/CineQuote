package br.com.sandes.cinequote.controller;

import br.com.sandes.cinequote.dto.FrasesDTO;
import br.com.sandes.cinequote.dto.ObraDTO;
import br.com.sandes.cinequote.model.Genero;
import br.com.sandes.cinequote.service.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/obra")
public class ObraController {

    @Autowired
    private ObraService obraService;

    @GetMapping()
    public ObraDTO exibirObra() {
        return obraService.buscarObra();
    }

    @GetMapping("/frase")
    public ObraDTO exibirFrase() {
        return obraService.buscarFrase();
    }

    @GetMapping("/genero/{genero}")
    public ObraDTO exibirPorGenero(@PathVariable String genero) {
        return  obraService.buscarPorGenero(genero);
    }

}
