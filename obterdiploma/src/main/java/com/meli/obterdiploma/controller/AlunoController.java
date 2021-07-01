package com.meli.obterdiploma.controller;

import com.meli.obterdiploma.dao.AlunoDAO;
import com.meli.obterdiploma.dto.AlunoDTO;
import com.meli.obterdiploma.entity.Aluno;
import com.meli.obterdiploma.entity.Disciplina;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoDAO dao = new AlunoDAO();

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listaAlunos() {
        return new ResponseEntity<>(AlunoDTO.converte(dao.getList()), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping(("/{id}"))
    public ResponseEntity<AlunoDTO> obterAluno(@PathVariable long id) {
        return new ResponseEntity<>(AlunoDTO.converte(dao.get(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> cadastra(@RequestBody AlunoDTO alunoDTO, UriComponentsBuilder uriBuilder) {
        Aluno aluno = AlunoDTO.converte(alunoDTO, dao);
        dao.add(aluno);
        URI uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    @RequestMapping("/media/{id}")
    public ResponseEntity<String> calculaMedia(@PathVariable long id) {
        AlunoDTO alunoDTO = AlunoDTO.converte(dao.get(id));
        double soma = 0;
        for(Disciplina disciplina : alunoDTO.getDisciplinas()) {
            soma += disciplina.getNota();
        }
        double media = soma / alunoDTO.getDisciplinas().size();
        if(media >= 9) {
            String resposta = "Parabéns " + alunoDTO.getNome() + "!!! Aqui está seu diploma com média " + media;
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        }
        String resposta = alunoDTO.getNome() + ", aqui está seu diploma com média " + media;
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

}
