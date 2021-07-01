package com.meli.obterdiploma.entity;

public class Disciplina {
    private final String nome;
    private final double nota;

    public Disciplina(long id, String nome, double nota) {
        this.nome = nome;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public double getNota() {
        return nota;
    }
}
