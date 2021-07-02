package com.meli.gestaolojarefactor.dto;

import com.meli.gestaolojarefactor.entity.Produto;

public class ProdutoDTO {
    private String descricao;
    private String cor;
    private int quantidade;
    private double preco;

    public ProdutoDTO(String descricao, String cor, int quantidade, double preco) {
        this.descricao = descricao;
        this.cor = cor;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public ProdutoDTO(Produto produto) {
        this.descricao = produto.getDescricao();
        this.cor = produto.getCor();
        this.quantidade = produto.getQuantidade();
        this.preco = produto.getPreco();
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCor() {
        return cor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public static ProdutoDTO converte(Produto produto) {
        return new ProdutoDTO(produto.getDescricao(), produto.getCor(), produto.getQuantidade(), produto.getPreco());
    }

    public static Produto converte(ProdutoDTO produtoDTO) {
        return new Produto(0 , produtoDTO.getDescricao(), produtoDTO.getCor(),
                produtoDTO.getQuantidade(), produtoDTO.getPreco());
    }
}
