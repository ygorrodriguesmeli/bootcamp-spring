package com.meli.restaurante.dto;

import com.meli.restaurante.dao.PratoDAO;
import com.meli.restaurante.entity.Prato;

import java.util.List;
import java.util.stream.Collectors;

public class PratoDTO {
    private double preco;
    private String descricao;
    private int quantidade;

    public PratoDTO(double preco, String descricao, int quantidade) {
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public PratoDTO(Prato prato) {
        this.preco = prato.getPreco();
        this.descricao = prato.getDescricao();
        this.quantidade = prato.getQuantidade();
    }

    public double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public static PratoDTO converte(Prato prato) {
        return new PratoDTO(prato.getPreco(), prato.getDescricao(), prato.getQuantidade());
    }

    public static Prato converte(PratoDTO pratoDTO, PratoDAO dao) {
        return new Prato(dao.getPratos().size() + 1, pratoDTO.getPreco(),
                pratoDTO.getDescricao(), pratoDTO.getQuantidade());
    }

    public static List<PratoDTO> converte(List<Prato> pratos) {
        return pratos.stream().map(PratoDTO::new).collect(Collectors.toList());
    }
}
