package com.meli.gestaolojarefactor.service;

import com.meli.gestaolojarefactor.entity.Produto;
import com.meli.gestaolojarefactor.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public List<Produto> getList() {
        return repository.getList();
    }

    public Produto getProduto(long id) {
        return repository.getList().get((int) id - 1);
    }
}
