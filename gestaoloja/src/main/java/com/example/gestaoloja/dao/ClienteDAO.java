package com.example.gestaoloja.dao;

import com.example.gestaoloja.entity.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO {
    private static List<Cliente> clientes = new ArrayList<>();

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void add(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente get(long id) {
        Optional<Cliente> clienteOptional = clientes.stream().filter(c -> c.getId() == id).findFirst();
        return clienteOptional.orElse(null);
    }
}
