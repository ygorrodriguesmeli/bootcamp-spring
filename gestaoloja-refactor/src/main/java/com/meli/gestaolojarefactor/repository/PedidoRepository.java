package com.meli.gestaolojarefactor.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.gestaolojarefactor.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PedidoRepository {
    private static final File FILE = new File("pedidos.json");

    @Autowired
    private ObjectMapper mapper;

    public List<Pedido> getList() {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            FileInputStream is = new FileInputStream(FILE);
            TypeReference<List<Pedido>> typeReference = new TypeReference<List<Pedido>>() {};
            pedidos = mapper.readValue(is, typeReference);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public void add(Pedido pedido) {
        try {
            List<Pedido> pedidos = getList();
            pedidos.add(pedido);
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE)));
            mapper.writeValue(out, pedidos);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(long id) {
        try {
            List<Pedido> pedidos = getList();
            pedidos.removeIf(pedido -> pedido.getId() == id);
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE)));
            mapper.writeValue(out, pedidos);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void atualiza(Pedido pedido) {
        try {
            List<Pedido> pedidos = getList();
            Optional<Pedido> pedidoOpt = pedidos.stream().filter(p -> p.getId() == pedido.getId()).findFirst();
            Pedido pedidoAntigo = pedidoOpt.orElse(null);
            pedidos.remove(pedidoAntigo);
            pedidos.add(pedido);
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE)));
            mapper.writeValue(out, pedidos);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
