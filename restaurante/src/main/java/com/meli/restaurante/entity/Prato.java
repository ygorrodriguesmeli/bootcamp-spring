package com.meli.restaurante.entity;

public class Prato {
    private long id;
    private double preco;
    private String descricao;
    private int quantidade;

    public Prato() {
    }

    public Prato(long id, double preco, String descricao, int quantidade) {
        this.id = id;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Prato{" +
                "id=" + id +
                ", preco=" + preco +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}
