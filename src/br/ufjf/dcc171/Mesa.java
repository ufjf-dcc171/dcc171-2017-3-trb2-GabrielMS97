package br.ufjf.dcc171;

import java.util.List;

public class Mesa {
    private Integer numero;
    private String nome;
    private List<Pedido> pedidos;

    public Mesa()
    {
        this(null,null);
    }
    
    public Mesa(Integer numero, String nome) {
        this.numero = numero;
        this.nome = nome;
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
        return nome ;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    
}
