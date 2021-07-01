package com.meli.restaurante.dao;

import com.meli.restaurante.entity.Mesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MesaDAO {
    private static List<Mesa> mesas = new ArrayList<>();

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void add(Mesa mesa) {
        mesas.add(mesa);
    }

    public Mesa get(long id) {
        Optional<Mesa> mesaOptional = mesas.stream().filter(mesa -> mesa.getId() == id).findFirst();
        return mesaOptional.orElse(null);
    }
}
