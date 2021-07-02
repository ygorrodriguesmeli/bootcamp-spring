package com.meli.gestaolojarefactor.entity;

import java.util.Date;
import java.util.List;

public class Pedido {
    private long id;
    private List<Produto> produtos;
    private double valorTotal;
    private Date date;

    public Pedido() {
    }

    public Pedido(long id, List<Produto> produtos) {
        this.id = id;
        this.produtos = produtos;
        this.valorTotal = calculaValorTotal(produtos);
        this.date = new Date();
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

    public Date getDate() {
        return date;
    }
}
