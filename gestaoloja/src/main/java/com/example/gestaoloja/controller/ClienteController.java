package com.example.gestaoloja.controller;

import com.example.gestaoloja.dao.ClienteDAO;
import com.example.gestaoloja.dao.PedidoDAO;
import com.example.gestaoloja.dto.ClienteDTO;
import com.example.gestaoloja.dto.PedidoDTO;
import com.example.gestaoloja.entity.Cliente;
import com.example.gestaoloja.entity.Pedido;
import com.example.gestaoloja.entity.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastra(@RequestBody ClienteDTO clienteDTO, UriComponentsBuilder uriBuilder) {
        List<Pedido> pedidos = new ArrayList<>();
        for(Pedido p : clienteDTO.getPedidos()) {
            Pedido pedido = pedidoDAO.getPedidos().get((int) (p.getId() - 1));
            pedidos.add(pedido);
        }
        clienteDTO.getPedidos().clear();
        clienteDTO.getPedidos().addAll(pedidos);
        Cliente cliente = ClienteDTO.converte(clienteDTO, clienteDAO);
        clienteDAO.add(cliente);
        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<ClienteDTO> obterCliente(@PathVariable long id){
        return new ResponseEntity<>(ClienteDTO.converte(clienteDAO.get(id)), HttpStatus.OK);
    }

}
