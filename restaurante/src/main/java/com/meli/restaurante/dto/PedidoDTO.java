package com.meli.restaurante.dto;

import com.meli.restaurante.dao.PedidoDAO;
import com.meli.restaurante.entity.Pedido;
import com.meli.restaurante.entity.Prato;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoDTO {
    private List<Prato> pratos;
    private double valorTotal;

    public PedidoDTO() {
    }

    public PedidoDTO(List<Prato> pratos) {
        this.pratos = pratos;
    }

    public PedidoDTO(Pedido pedido) {
        this.pratos = pedido.getPratos();
        this.valorTotal = pedido.getValorTotal();
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public static PedidoDTO converte(Pedido pedido) {
        return new PedidoDTO(pedido);
    }

    public static Pedido converte(PedidoDTO pedidoDTO, PedidoDAO dao) {
        return new Pedido(dao.getPedidos().size()+1, pedidoDTO.getPratos());
    }

    public static List<PedidoDTO> converte(List<Pedido> pedidos) {
        return pedidos.stream().map(PedidoDTO::new).collect(Collectors.toList());
    }
}
