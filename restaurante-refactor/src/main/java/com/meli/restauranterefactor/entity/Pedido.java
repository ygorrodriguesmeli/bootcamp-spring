package com.meli.restauranterefactor.entity;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Pedido {
    private long id;
    private int mesa;
    private String status;
    private Date data;
    private List<Prato> pratos;
    private double valorTotal;

    public Pedido() {
    }

    public Pedido(long id, List<Prato> pratos, int mesa) {
        this.id = id;
        this.pratos = pratos;
        this.valorTotal = calculaValor(pratos);
        this.status = "Aberto";
        this.mesa = mesa;
        this.data = new GregorianCalendar().getTime();
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

    public Date getData() {
        return data;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMesa() {
        return mesa;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", mesa=" + mesa +
                ", status='" + status + '\'' +
                ", data=" + data +
                ", pratos=" + pratos +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
