package com.meli.restaurante.dto;

import com.meli.restaurante.dao.MesaDAO;
import com.meli.restaurante.entity.Mesa;
import com.meli.restaurante.entity.Pedido;

import java.util.List;
import java.util.stream.Collectors;

public class MesaDTO {
    private List<Pedido> pedidos;
    private double valorTotalConsumido;

    public MesaDTO() {
    }

    public MesaDTO(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public MesaDTO(Mesa mesa) {
        this.pedidos = mesa.getPedidos();
        this.valorTotalConsumido = mesa.getValorTotalConsumido();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public double getValorTotalConsumido() {
        return valorTotalConsumido;
    }

    public static MesaDTO converte(Mesa mesa) {
        return new MesaDTO(mesa);
    }

    public static Mesa converte(MesaDTO mesaDTO, MesaDAO dao) {
        return new Mesa(dao.getMesas().size() + 1, mesaDTO.getPedidos());
    }

    public static List<MesaDTO> converte(List<Mesa> mesas) {
        return mesas.stream().map(MesaDTO::new).collect(Collectors.toList());
    }
}
