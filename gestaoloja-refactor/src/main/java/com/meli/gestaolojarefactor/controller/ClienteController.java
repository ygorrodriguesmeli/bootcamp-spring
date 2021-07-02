package com.meli.gestaolojarefactor.controller;

import com.meli.gestaolojarefactor.dto.ClienteDTO;
import com.meli.gestaolojarefactor.dto.PedidoDTO;
import com.meli.gestaolojarefactor.entity.Cliente;
import com.meli.gestaolojarefactor.entity.Pedido;
import com.meli.gestaolojarefactor.service.ClienteService;
import com.meli.gestaolojarefactor.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ClienteService service;

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastra(@RequestBody ClienteDTO dto, UriComponentsBuilder uriBuilder) {
        List<Pedido> pedidos = new ArrayList<>();
        for(Pedido p : dto.getPedidos()) {
            Pedido pedido = pedidoService.getPedido(p.getId());
            System.out.println("alo");
            pedidos.add(pedido);
        }
        dto.getPedidos().clear();
        dto.getPedidos().addAll(pedidos);

        Cliente cliente = ClienteDTO.converte(dto,service.getList().size() + 1);
        service.cadastra(cliente);

        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listClientes() {
        List<ClienteDTO> listClientes = ClienteDTO.converte(service.getList());
        return new ResponseEntity<>(listClientes, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<ClienteDTO> obterCliente(@PathVariable long id){
        return new ResponseEntity<>(ClienteDTO.converte(service.getCliente(id)), HttpStatus.OK);
    }

}
