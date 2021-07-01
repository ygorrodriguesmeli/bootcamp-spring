package com.meli.restaurante.controller;

import com.meli.restaurante.dao.PratoDAO;
import com.meli.restaurante.dto.PratoDTO;
import com.meli.restaurante.entity.Prato;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pratos")
public class PratoController {
    private final PratoDAO dao = new PratoDAO();

    @GetMapping
    public ResponseEntity<List<PratoDTO>> listaPratos() {
        return new ResponseEntity<>(PratoDTO.converte(dao.getPratos()), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<PratoDTO> obterPrato(@PathVariable long id) {
        return new ResponseEntity<>(PratoDTO.converte(dao.get(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PratoDTO> create(@RequestBody PratoDTO pratoDTO, UriComponentsBuilder uriBuilder) {
        Prato prato = PratoDTO.converte(pratoDTO, dao);
        dao.add(prato);
        URI uri = uriBuilder.path("/pratos/{id}").buildAndExpand(prato.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
