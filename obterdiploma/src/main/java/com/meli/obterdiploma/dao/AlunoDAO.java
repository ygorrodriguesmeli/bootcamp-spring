package com.meli.obterdiploma.dao;

import com.meli.obterdiploma.entity.Aluno;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoDAO {
    private static List<Aluno> alunos = new ArrayList<Aluno>();

    public List<Aluno> getList(){
        return alunos;
    }

    public void add(Aluno aluno) {
        alunos.add(aluno);
    }

    public Aluno get(long id) {
        Optional<Aluno> alunoOptional = alunos.stream().filter(a -> a.getId() == id).findFirst();
        return alunoOptional.orElse(null);
    }
}
