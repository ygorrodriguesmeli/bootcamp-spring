package com.meli.gestaolojarefactor.dto;

import com.meli.gestaolojarefactor.entity.Cliente;
import com.meli.gestaolojarefactor.entity.Pedido;

import java.util.List;
import java.util.stream.Collectors;

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

    public static Cliente converte(ClienteDTO clienteDTO, long id) {
        return new Cliente(id, clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getEmail(),
                clienteDTO.getTelefone(), clienteDTO.getPedidos());
    }

    public static List<ClienteDTO> converte(List<Cliente> clientes) {
        return clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }
}
