package com.meli.gestaolojarefactor.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.gestaolojarefactor.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {
    private static final File FILE = new File("clientes.json");

    @Autowired
    private ObjectMapper mapper;

    public List<Cliente> getList() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            FileInputStream is = new FileInputStream(FILE);
            TypeReference<List<Cliente>> typeReference = new TypeReference<List<Cliente>>() {};
            clientes = mapper.readValue(is, typeReference);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public void add(Cliente cliente) {
        try {
            List<Cliente> clientes = getList();
            clientes.add(cliente);
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE)));
            mapper.writeValue(out, clientes);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
