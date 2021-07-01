package com.meli.restaurante.entity;

import java.util.List;

public class Pedido {
    private long id;
    private List<Prato> pratos;
    private double valorTotal;

    public Pedido() {
    }

    public Pedido(long id, List<Prato> pratos) {
        this.id = id;
        this.pratos = pratos;
        this.valorTotal = calculaValor(pratos);
    }

    private double calculaValor(List<Prato> pratos) {
        double valor = 0;
        for(Prato p : pratos) {
            valor += p.getPreco();
        }
        return valor;
    }

    public long getId() {
        return id;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
