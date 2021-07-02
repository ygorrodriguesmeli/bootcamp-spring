package com.meli.gestaolojarefactor.service;

import com.meli.gestaolojarefactor.entity.Cliente;
import com.meli.gestaolojarefactor.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public void cadastra(Cliente cliente) {
        repository.add(cliente);
    }

    public List<Cliente> getList() {
        return repository.getList();
    }

    public Cliente getCliente(long id) {
        return repository.getList().get((int) id - 1);
    }
}
