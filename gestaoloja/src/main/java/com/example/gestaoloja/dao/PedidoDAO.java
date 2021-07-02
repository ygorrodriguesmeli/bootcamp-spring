package com.example.gestaoloja.dao;

import com.example.gestaoloja.entity.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoDAO {
    private static List<Pedido> pedidos = new ArrayList<>();

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void add(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Pedido get(long id) {
        Optional<Pedido> pedidoOptional = pedidos.stream().filter(p -> p.getId() == id).findFirst();
        return pedidoOptional.orElse(null);
    }
}
