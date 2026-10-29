package main.java.com.model;

public class UnidadeClinica {

    private int id_unidade_clinica;
    private String nome;
    private Estado estado;
    private Cidade cidade;
    private String rua;
    private int numero_endereco;
    private String complemento;

    public UnidadeClinica() {

    }

    public UnidadeClinica(int id_unidade_clinica, String nome, Estado estado, Cidade cidade, String rua, int numero_endereco, String complemento) {

        this.id_unidade_clinica = id_unidade_clinica;
        this.nome = nome;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numero_endereco = numero_endereco;
        this.complemento = complemento;
    }

    public String getNome() {

        return nome;
    }
}