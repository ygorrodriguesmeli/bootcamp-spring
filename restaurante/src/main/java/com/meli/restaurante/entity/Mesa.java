package com.meli.restaurante.entity;

import java.util.List;

public class Mesa {
    private long id;
    private List<Pedido> pedidos;
    private double valorTotalConsumido;

    public Mesa() {
    }

    public Mesa(long id, List<Pedido> pedidos) {
        this.id = id;
        this.pedidos = pedidos;
        this.valorTotalConsumido = calculaValorConsumido(pedidos);
    }

    private double calculaValorConsumido(List<Pedido> pedidos) {
        double valor = 0;
        for(Pedido p : pedidos) {
            valor += p.getValorTotal();
        }
        return valor;
    }

    public long getId() {
        return id;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public double getValorTotalConsumido() {
        return valorTotalConsumido;
    }
}
