package com.meli.restaurante.controller;

import com.meli.restaurante.dao.PedidoDAO;
import com.meli.restaurante.dao.PratoDAO;
import com.meli.restaurante.dto.PedidoDTO;
import com.meli.restaurante.entity.Pedido;
import com.meli.restaurante.entity.Prato;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoDAO dao = new PedidoDAO();
    private final PratoDAO pratoDAO = new PratoDAO();

    @PostMapping
    public ResponseEntity<PedidoDTO> create(@RequestBody PedidoDTO pedidoDTO, UriComponentsBuilder uriBuilder) {
        List<Prato> pratos = new ArrayList<>();
        for(Prato p : pedidoDTO.getPratos()) {
            Prato prato = pratoDAO.getPratos().get((int) (p.getId() - 1));
            pratos.add(prato);
        }
        pedidoDTO.getPratos().clear();
        pedidoDTO.getPratos().addAll(pratos);
        Pedido pedido = PedidoDTO.converte(pedidoDTO, dao);
        dao.add(pedido);
        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<PedidoDTO> obterPedido(@PathVariable long id){
        return new ResponseEntity<>(PedidoDTO.converte(dao.get(id)), HttpStatus.OK);
    }

}
