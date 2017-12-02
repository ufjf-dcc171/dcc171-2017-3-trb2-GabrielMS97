package br.ufjf.dcc171;

import java.util.ArrayList;
import java.util.List;

public class Mesa {

    private Integer numero;
    private String nome;
    private List<Pedido> pedidos;
    private double conta = 0;

    public Mesa() {
        this(null, null);
    }

    public Mesa(Integer numero, String nome) {
        this.numero = numero;
        this.nome = nome;
        this.pedidos = new ArrayList<>();
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public double getConta() {
        return conta;
    }

    public void setConta(double conta) {
        this.conta += conta;
    }
}
