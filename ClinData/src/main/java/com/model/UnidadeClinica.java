package main.java.com.model;

public class UnidadeClinica {

    private int id_unidade_clinica;
    private String nome;
    private Estado estado;
    private String cidade;
    private String rua;
    private int numero_endereco;
    private String complemento;

    public UnidadeClinica() {

    }

    public int getIdUnidade() {

        return this.id_unidade_clinica;
    }

    public UnidadeClinica(int id_unidade_clinica, String nome, Estado estado, String cidade, String rua, int numero_endereco, String complemento) {

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

    @Override
    public String toString() {
        return "UnidadeClinica\n" +
                "\nId: " + id_unidade_clinica +
                "\nNome: " + nome + '\'' +
                "\nEstado: " + estado +
                "\nCidade: " + cidade + '\'' +
                "\nRua: " + rua + '\'' +
                "\nNumero_endereco: " + numero_endereco +
                "\nComplemento: " + complemento;
    }
}