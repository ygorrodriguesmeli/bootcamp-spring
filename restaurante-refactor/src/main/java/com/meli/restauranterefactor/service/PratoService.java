package com.meli.restauranterefactor.service;

import com.meli.restauranterefactor.entity.Prato;
import com.meli.restauranterefactor.repository.PratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PratoService {

    @Autowired
    private PratoRepository repository;

    public Prato getPrato(long id) {
        return repository.getList().get((int) id - 1);
    }

}
