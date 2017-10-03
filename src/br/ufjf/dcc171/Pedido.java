package br.ufjf.dcc171;

import java.text.DecimalFormat;
import java.util.Date;

public class Pedido {
    private Integer qtdItens;
    private String item;
    private Double preco;
    private Date dataCriado;
    
    public Pedido()
    {
        this(null, null, null, null);
    }

    public Pedido(Integer qtdItens, String item, Double preco, Date criado) {
        this.qtdItens = qtdItens;
        this.item = item;
        this.preco = preco;
        this.dataCriado = criado;
    }

    public Integer getQtdItens() {
        return qtdItens;
    }

    public void setQtdItens(Integer qtdItens) {
        this.qtdItens = qtdItens;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double[] precos) {
        this.preco = preco;
    }

    public Date getCriado() {
        return dataCriado;
    }

    public void setCriado(Date criado) {
        this.dataCriado = criado;
    }

    @Override
    public String toString() {
        DecimalFormat d = new DecimalFormat();
        d.setMinimumFractionDigits(2);
        d.setMaximumFractionDigits(2);
        return item + "| Quantidade: " + qtdItens + "| Valor total: R$" + d.format(qtdItens * preco) + " | " + dataCriado;
    }

    
}
