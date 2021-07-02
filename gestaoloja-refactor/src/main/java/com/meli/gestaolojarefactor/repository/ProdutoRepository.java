package com.meli.gestaolojarefactor.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.gestaolojarefactor.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoRepository {
    private static final File FILE = new File("produtos.json");

    @Autowired
    private ObjectMapper mapper;

    public List<Produto> getList() {
        List<Produto> produtos = new ArrayList<>();
        try {
            FileInputStream is = new FileInputStream(FILE);
            TypeReference<List<Produto>> typeReference = new TypeReference<List<Produto>>() {};
            produtos = mapper.readValue(is, typeReference);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}
