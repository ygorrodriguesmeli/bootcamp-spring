package com.example.gestaoloja.controller;

import com.example.gestaoloja.dao.PedidoDAO;
import com.example.gestaoloja.dao.ProdutoDAO;
import com.example.gestaoloja.dto.PedidoDTO;
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
@RequestMapping("/pedidos")
public class PedidoController {

    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();

    @PostMapping
    public ResponseEntity<PedidoDTO> cadastra(@RequestBody PedidoDTO pedidoDTO, UriComponentsBuilder uriBuilder) {
        List<Produto> produtos = new ArrayList<>();
        for(Produto p : pedidoDTO.getProdutos()) {
            Produto produto = produtoDAO.getProdutos().get((int) (p.getId() - 1));
            produtos.add(produto);
        }
        pedidoDTO.getProdutos().clear();
        pedidoDTO.getProdutos().addAll(produtos);
        Pedido pedido = PedidoDTO.converte(pedidoDTO, pedidoDAO);
        pedidoDAO.add(pedido);
        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<PedidoDTO> obterPedido(@PathVariable long id){
        return new ResponseEntity<>(PedidoDTO.converte(pedidoDAO.get(id)), HttpStatus.OK);
    }
}
