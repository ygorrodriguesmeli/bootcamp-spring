package com.meli.apiprodutos.controller;

import com.meli.apiprodutos.model.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProdutoController {

    private static List<Produto> produtos = new ArrayList<Produto>();
    private static int id = 1;

    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> listaProdutos() {
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @PostMapping("/produtos")
    public ResponseEntity<Produto> criaProduto(@RequestBody Produto produto) {
        produto.setId(id);
        id++;
        produtos.add(produto);
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<String> atualizaProduto(@PathVariable Integer id ,@RequestBody Produto produto) {
        int index = id - 1;
        try {
            produtos.get(index).setNome(produto.getNome());
            produtos.get(index).setPreco(produto.getPreco());
            produtos.get(index).setQuantidade(produto.getQuantidade());
            return new ResponseEntity<>("Produto alterado com sucesso.", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Produto não encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<String> deletaProduto(@PathVariable Integer id) {
        int index = id - 1;
        try {
            produtos.remove(index);
            return new ResponseEntity<>("Produto deletado.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Produto não encontrado.", HttpStatus.NOT_FOUND);
        }
    }

}
