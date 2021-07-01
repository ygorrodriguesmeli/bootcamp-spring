package com.meli.restaurante.controller;

import com.meli.restaurante.dao.MesaDAO;
import com.meli.restaurante.dao.PedidoDAO;
import com.meli.restaurante.dto.MesaDTO;
import com.meli.restaurante.dto.PedidoDTO;
import com.meli.restaurante.entity.Mesa;
import com.meli.restaurante.entity.Pedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mesas")
public class MesaController {

    private final MesaDAO mesaDAO = new MesaDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();

    @PostMapping
    public ResponseEntity<MesaDTO> create(@RequestBody MesaDTO mesaDTO, UriComponentsBuilder uriBuilder) {
        List<Pedido> pedidos = new ArrayList<>();
        for(Pedido p : mesaDTO.getPedidos()) {
            Pedido pedido = pedidoDAO.getPedidos().get((int) p.getId() - 1);
            pedidos.add(pedido);
        }
        mesaDTO.getPedidos().clear();
        mesaDTO.getPedidos().addAll(pedidos);
        Mesa mesa = MesaDTO.converte(mesaDTO, mesaDAO);
        mesaDAO.add(mesa);

        URI uri = uriBuilder.path("/mesas/{id}").buildAndExpand(mesa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<MesaDTO> obterMesa(@PathVariable long id){
        return new ResponseEntity<>(MesaDTO.converte(mesaDAO.get(id)), HttpStatus.OK);
    }

}
