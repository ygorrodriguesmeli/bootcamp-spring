package com.meli.restauranterefactor.dto;

import com.meli.restauranterefactor.entity.Pedido;
import com.meli.restauranterefactor.entity.Prato;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoDTO {
    private int mesa;
    private String status;
    private double valorTotal;
    private Date data;
    private List<Prato> pratos;

    public PedidoDTO() {
    }

    public PedidoDTO(int mesa, List<Prato> pratos) {
        this.mesa = mesa;
        this.pratos = pratos;
    }

    public PedidoDTO(Pedido pedido) {
        this.mesa = pedido.getMesa();
        this.status = pedido.getStatus();
        this.valorTotal = pedido.getValorTotal();
        this.data = pedido.getData();
        this.pratos = pedido.getPratos();
    }

    public int getMesa() {
        return mesa;
    }

    public String getStatus() {
        return status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Date getData() {
        return data;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public static PedidoDTO converte(Pedido pedido) {
        return new PedidoDTO(pedido);
    }

    public static Pedido converte(PedidoDTO pedidoDTO, long id) {
        return new Pedido(id, pedidoDTO.getPratos(), pedidoDTO.getMesa());
    }

    public static List<PedidoDTO> converte(List<Pedido> pedidos) {
        return pedidos.stream().map(PedidoDTO::new).collect(Collectors.toList());
    }
}
