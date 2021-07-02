package com.meli.starwars.controller;

import com.meli.starwars.entity.Personagem;
import com.meli.starwars.service.StarwarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/starwars")
public class StarwarsController {
    @Autowired
    private StarwarsService service;

    @GetMapping("/{nome}")
    public ResponseEntity<List<Personagem>> listaPersonagensPorNome(@PathVariable String nome) {
        return new ResponseEntity<>(service.getListByName(nome), HttpStatus.OK);
    }
}
