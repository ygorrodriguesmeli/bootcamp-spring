package com.meli.restauranterefactor.service;

import com.meli.restauranterefactor.entity.Pedido;
import com.meli.restauranterefactor.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public void cadastra(Pedido pedido) {
        pedidoRepository.add(pedido);
    }

    public List<Pedido> getList() {
        return pedidoRepository.getList();
    }

    public List<Pedido> getListAberto() {
        return pedidoRepository.getList().stream().filter(p -> p.getStatus().equals("Aberto")).collect(Collectors.toList());
    }

    public double valorTotalEmCaixa() {
        List<Pedido> pedidosFechados = pedidoRepository.getList().stream()
                .filter(p -> p.getStatus().equals("Fechado")).collect(Collectors.toList());
        double valor = 0;
        for(Pedido p: pedidosFechados) {
            valor += p.getValorTotal();
        }
        return valor;
    }

    public double valorEmCaixa(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        List<Pedido> pedidosFechados = pedidoRepository.getList().stream()
                .filter(p -> p.getStatus().equals("Fechado") && fmt.format(p.getData()).equals(fmt.format(date)))
                .collect(Collectors.toList());
        double valor = 0;
        for(Pedido p: pedidosFechados) {
            valor += p.getValorTotal();
        }
        return valor;
    }

    public void remove(long id) {
        pedidoRepository.remove(id);
    }

    public void atualiza(Pedido pedido) {
        pedidoRepository.atualiza(pedido);
    }

    public void fecharPedido(long id) {
        pedidoRepository.fecharPedido(id);
    }
}
