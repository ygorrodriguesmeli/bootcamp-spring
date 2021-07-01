package com.meli.restaurante.controller;

import com.meli.restaurante.dao.MesaDAO;
import com.meli.restaurante.dao.PedidoDAO;
import com.meli.restaurante.dto.PedidoDTO;
import com.meli.restaurante.entity.Mesa;
import com.meli.restaurante.entity.Pedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/caixa")
public class CaixaController {

    private final MesaDAO mesaDAO = new MesaDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private static Double valorEmCaixa = 0.0;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> fechaPedido(@PathVariable long id) {
        Pedido pedido = pedidoDAO.getPedidos().get((int) id - 1);
        valorEmCaixa += pedido.getValorTotal();
        pedidoDAO.getPedidos().remove(id - 1);
        for(Mesa mesa: mesaDAO.getMesas()) {
            mesa.getPedidos().removeIf(p -> p.getId() == id);
        }
        return new ResponseEntity<>("Pedido fechado", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Double> retornaCaixa() {
        return new ResponseEntity<>(valorEmCaixa, HttpStatus.OK);
    }

}
