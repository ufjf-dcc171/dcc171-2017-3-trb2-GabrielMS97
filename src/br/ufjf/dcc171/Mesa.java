package br.ufjf.dcc171;

public class Mesa {
    private Integer numero;
    private String nome;

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
    
    
}
