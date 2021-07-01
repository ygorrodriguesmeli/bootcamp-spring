package com.meli.obterdiploma.dto;

import com.meli.obterdiploma.dao.AlunoDAO;
import com.meli.obterdiploma.entity.Aluno;
import com.meli.obterdiploma.entity.Disciplina;

import java.util.List;
import java.util.stream.Collectors;

public class AlunoDTO {

    private String nome;
    private List<Disciplina> disciplinas;

    public AlunoDTO(String nome, List<Disciplina> disciplinas) {
        this.nome = nome;
        this.disciplinas = disciplinas;
    }

    public AlunoDTO(Aluno aluno) {
        this.nome = aluno.getNome();
        this.disciplinas = aluno.getDisciplinas();
    }

    public String getNome() {
        return nome;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public static AlunoDTO converte(Aluno aluno) {
        return new AlunoDTO(aluno.getNome(), aluno.getDisciplinas());
    }

    public static Aluno converte(AlunoDTO alunoDTO, AlunoDAO dao) {
        return new Aluno(dao.getList().size()+1, alunoDTO.getNome(), alunoDTO.getDisciplinas());
    }

    public static List<AlunoDTO> converte(List<Aluno> alunos) {
        return alunos.stream().map(AlunoDTO::new).collect(Collectors.toList());
    }

}
