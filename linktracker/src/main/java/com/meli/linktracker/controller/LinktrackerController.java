package com.meli.linktracker.controller;

import com.meli.linktracker.dto.LinkDTO;
import com.meli.linktracker.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class LinktrackerController {

    @Autowired
    private LinkService service;

    @PostMapping("/link")
    public ResponseEntity<Long> createLink(@RequestParam("senha") Optional<String> senha) {
        int idLink;
        if(senha.isPresent()) {
            idLink = service.create(senha.get());
        } else {
            idLink = service.create();
        }
        LinkDTO link = new LinkDTO(idLink);
        return new ResponseEntity<>(link.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/link/{linkId}")
    public ModelAndView redirectLink(@PathVariable long linkId, @RequestParam("senha") Optional<String> senhaParam) {
        if(senhaParam.isPresent()) {
            service.redirect(linkId, senhaParam.get());
        } else {
            service.redirect(linkId);
        }
        return new ModelAndView("redirect:/mascarado");
    }

    @GetMapping("/mascarado")
    public ResponseEntity<String> mascaraLink() {
        return new ResponseEntity<>("Redirecionado para uma URL máscara", HttpStatus.PERMANENT_REDIRECT);
    }

    @GetMapping("/metrics/{linkId}")
    public ResponseEntity<LinkDTO> metricasLink(@PathVariable long linkId) {
        LinkDTO link = service.getLink(linkId);
        return new ResponseEntity<>(link, HttpStatus.OK);
    }

    @DeleteMapping("/invalidate/{linkId}")
    public ResponseEntity<Void> invalidateLink(@PathVariable long linkId) {
        service.invalidate(linkId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
