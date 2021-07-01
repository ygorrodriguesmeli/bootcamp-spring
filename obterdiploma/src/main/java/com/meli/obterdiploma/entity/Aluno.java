package com.meli.obterdiploma.entity;

import java.util.List;

public class Aluno {
    private long id;
    private String nome;
    private List<Disciplina> disciplinas;

    public Aluno(int id, String nome, List<Disciplina> disciplinas) {
        this.id = id;
        this.nome = nome;
        this.disciplinas = disciplinas;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
}
