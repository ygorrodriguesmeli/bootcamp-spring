package com.example.gestaoloja.dto;

import com.example.gestaoloja.dao.PedidoDAO;
import com.example.gestaoloja.entity.Pedido;
import com.example.gestaoloja.entity.Produto;

import java.util.List;

public class PedidoDTO {
    private List<Produto> produtos;
    private double valorTotal;

    public PedidoDTO(List<Produto> produtos, double valorTotal) {
        this.produtos = produtos;
        this.valorTotal = valorTotal;
    }

    public PedidoDTO() {
    }

    public PedidoDTO(Pedido pedido) {
        this.produtos = pedido.getProdutos();
        this.valorTotal = pedido.getValorTotal();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public static PedidoDTO converte(Pedido pedido) {
        return new PedidoDTO(pedido.getProdutos(), pedido.getValorTotal());
    }

    public static Pedido converte(PedidoDTO pedidoDTO, PedidoDAO dao) {
        return new Pedido(dao.getPedidos().size() + 1, pedidoDTO.getProdutos());
    }
}
