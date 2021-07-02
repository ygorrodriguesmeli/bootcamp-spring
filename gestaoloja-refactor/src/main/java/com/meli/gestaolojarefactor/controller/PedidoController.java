package com.meli.gestaolojarefactor.controller;

import com.meli.gestaolojarefactor.dto.PedidoDTO;
import com.meli.gestaolojarefactor.entity.Pedido;
import com.meli.gestaolojarefactor.entity.Produto;
import com.meli.gestaolojarefactor.service.PedidoService;
import com.meli.gestaolojarefactor.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> cadastra(@RequestBody PedidoDTO dto, UriComponentsBuilder uriBuilder) {
        List<Produto> produtos = new ArrayList<>();
        for(Produto p : dto.getProdutos()) {
            Produto produto = produtoService.getProduto(p.getId());
            produtos.add(produto);
        }
        dto.getProdutos().clear();
        dto.getProdutos().addAll(produtos);

        Pedido pedido = PedidoDTO.converte(dto,service.getList().size() + 1);
        service.cadastra(pedido);

        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listPedidos() {
        List<PedidoDTO> listPedidos = PedidoDTO.converte(service.getList());
        return new ResponseEntity<>(listPedidos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePedido(@PathVariable long id) {
        service.remove(id);
        return new ResponseEntity<>("Removido", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizaPedido(@RequestBody PedidoDTO dto, @PathVariable long id) {
        List<Produto> produtosUp = new ArrayList<>();
        for(Produto p : dto.getProdutos()) {
            Produto produto = produtoService.getProduto(p.getId());
            produtosUp.add(produto);
        }
        dto.getProdutos().clear();
        dto.getProdutos().addAll(produtosUp);

        Pedido pedido = PedidoDTO.converte(dto, id);
        service.atualiza(pedido);

        return new ResponseEntity<>("Pedido atualizado", HttpStatus.OK);
    }

    @GetMapping("/caixa/total")
    public ResponseEntity<String> valorTotalEmCaixa() {
        return new ResponseEntity<>("Valor em caixa: " + service.valorTotalEmCaixa(), HttpStatus.OK);
    }

    @GetMapping("/caixa")
    public ResponseEntity<String> valorEmCaixaHoje() {
        Date date = new Date();
        return new ResponseEntity<>("Valor em caixa de hoje: " + service.valorEmCaixa(date), HttpStatus.OK);
    }

    @GetMapping("/caixa/{data}")
    public ResponseEntity<String> valorEmCaixa(@PathVariable String data) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date parsedDate = formatter.parse(data);
            return new ResponseEntity<>("Valor em caixa do dia: " + service.valorEmCaixa(parsedDate), HttpStatus.OK);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Data incorreta!", HttpStatus.BAD_REQUEST);
        }
    }
}
