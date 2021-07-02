package com.meli.gestaolojarefactor.service;

import com.meli.gestaolojarefactor.entity.Pedido;
import com.meli.gestaolojarefactor.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    public void cadastra(Pedido pedido) {
        repository.add(pedido);
    }

    public List<Pedido> getList() {
        return repository.getList();
    }

    public Pedido getPedido(long id) {
        return repository.getList().get((int) id - 1);
    }

    public void remove(long id) {
        repository.remove(id);
    }

    public void atualiza(Pedido pedido) {
        repository.atualiza(pedido);
    }

    public double valorTotalEmCaixa() {
        List<Pedido> pedidos = repository.getList();
        double valor = 0;
        for(Pedido p: pedidos) {
            valor += p.getValorTotal();
        }
        return valor;
    }

    public double valorEmCaixa(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        List<Pedido> pedidos = repository.getList().stream()
                .filter(p -> fmt.format(p.getDate()).equals(fmt.format(date)))
                .collect(Collectors.toList());
        double valor = 0;
        for(Pedido p: pedidos) {
            valor += p.getValorTotal();
        }
        return valor;
    }
}
