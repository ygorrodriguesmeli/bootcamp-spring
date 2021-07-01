package com.meli.restaurante.dao;

import com.meli.restaurante.entity.Prato;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PratoDAO {
    private static List<Prato> pratos = new ArrayList<>(
            Arrays.asList(
                    new Prato(1, 15.99, "Filé de frango com arroz", 1),
                    new Prato(2, 19.99, "Filé de frango a parmegiana, acompanhado de arroz", 1),
                    new Prato(3, 18.99, "Feijoada da casa", 1),
                    new Prato(4, 12.99, "Picadinho", 1),
                    new Prato(5, 17.99, "Virada a paulista", 1)
            )
    );

    public List<Prato> getPratos() {
        return pratos;
    }

    public void add(Prato prato) {
        pratos.add(prato);
    }

    public Prato get(long id) {
        Optional<Prato> pratoOptional = pratos.stream().filter(prato -> prato.getId() == id).findFirst();
        return pratoOptional.orElse(null);
    }

}
