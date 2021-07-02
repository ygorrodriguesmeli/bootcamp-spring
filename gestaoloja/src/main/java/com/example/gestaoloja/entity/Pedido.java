package com.example.gestaoloja.entity;

import java.util.List;

public class Pedido {
    private long id;
    private List<Produto> produtos;
    private double valorTotal;

    public Pedido() {
    }

    public Pedido(long id, List<Produto> produtos) {
        this.id = id;
        this.produtos = produtos;
        this.valorTotal = calculaValorTotal(produtos);
    }

    private double calculaValorTotal(List<Produto> produtos) {
        double valor = 0;
        for(Produto p : produtos) {
            valor += p.getPreco();
        }
        return valor;
    }

    public long getId() {
        return id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
