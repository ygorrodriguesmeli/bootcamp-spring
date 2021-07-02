package com.meli.restauranterefactor.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.restauranterefactor.entity.Prato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PratoRepository {
    private static final File FILE = new File("pratos.json");

    @Autowired
    private ObjectMapper mapper;

    public List<Prato> getList() {
        List<Prato> pratos = new ArrayList<>();
        try {
            FileInputStream is = new FileInputStream(FILE);
            TypeReference<List<Prato>> typeReference = new TypeReference<List<Prato>>() {};
            pratos = mapper.readValue(is, typeReference);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pratos;
    }
}
