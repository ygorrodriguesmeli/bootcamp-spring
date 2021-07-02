package com.example.gestaoloja.dao;

import com.example.gestaoloja.entity.Produto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProdutoDAO {
    private static List<Produto> produtos = new ArrayList<>(
            Arrays.asList(
                    new Produto(1, "Tenis Nike", "Azul", 2, 199.0),
                    new Produto(2, "Bone Nike", "Rosa", 69, 24.0),
                    new Produto(3, "Bicicleta", "Verde", 1, 1999.0),
                    new Produto(4, "Ursinho de pelúcia", "Marrom", 5, 299.0)
            )
    );

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void add(Produto produto) {
        produtos.add(produto);
    }

    public Produto get(long id) {
        Optional<Produto> produtoOptional = produtos.stream().filter(p -> p.getId() == id).findFirst();
        return produtoOptional.orElse(null);
    }
}
