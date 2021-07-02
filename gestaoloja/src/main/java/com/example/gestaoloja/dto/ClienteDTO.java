package com.example.gestaoloja.dto;

import com.example.gestaoloja.dao.ClienteDAO;
import com.example.gestaoloja.entity.Cliente;
import com.example.gestaoloja.entity.Pedido;

import java.util.List;

public class ClienteDTO {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private List<Pedido> pedidos;

    public ClienteDTO(String nome, String cpf, String email, String telefone, List<Pedido> pedidos) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.pedidos = pedidos;
    }

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
        this.pedidos = cliente.getPedidos();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public static ClienteDTO converte(Cliente cliente) {
        return new ClienteDTO(cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getTelefone(),
                cliente.getPedidos());
    }

    public static Cliente converte(ClienteDTO clienteDTO, ClienteDAO dao) {
        return new Cliente(dao.getClientes().size() + 1, clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getEmail(),
                clienteDTO.getTelefone(), clienteDTO.getPedidos());
    }
}
