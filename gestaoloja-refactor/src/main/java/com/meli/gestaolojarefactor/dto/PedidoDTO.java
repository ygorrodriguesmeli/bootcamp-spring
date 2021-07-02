package com.meli.gestaolojarefactor.dto;

import com.meli.gestaolojarefactor.entity.Pedido;
import com.meli.gestaolojarefactor.entity.Produto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoDTO {
    private double valorTotal;
    private Date date;
    private List<Produto> produtos;

    public PedidoDTO(List<Produto> produtos, double valorTotal) {
        this.produtos = produtos;
        this.valorTotal = valorTotal;
    }

    public PedidoDTO() {
    }

    public PedidoDTO(Pedido pedido) {
        this.produtos = pedido.getProdutos();
        this.valorTotal = pedido.getValorTotal();
        this.date = pedido.getDate();
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

    public static PedidoDTO converte(Pedido pedido) {
        return new PedidoDTO(pedido);
    }

    public static Pedido converte(PedidoDTO pedidoDTO, long id) {
        return new Pedido(id, pedidoDTO.getProdutos());
    }

    public static List<PedidoDTO> converte(List<Pedido> pedidos) {
        return pedidos.stream().map(PedidoDTO::new).collect(Collectors.toList());
    }
}
