package br.edu.ifsc.fln.model;
import br.edu.ifsc.fln.util.DateFormatter;
import br.edu.ifsc.fln.util.CpfFormatter;
import br.edu.ifsc.fln.util.TelefoneFormatter;

import java.time.LocalDate;


public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private String telefone;
    private String cpf;
    private String endereco;

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getDataNascimento() {

        DateFormatter formatador = new DateFormatter();
        return formatador.exibirData(this.dataNascimento);
    }

    public String getTelefone() {

        TelefoneFormatter formatador = new TelefoneFormatter();
        return formatador.formatarTelefone(this.telefone);
    }

    public String getCpf() {

//        CpfFormatter formatador = new CpfFormatter();
//        return formatador.formatarCpf(this.cpf);
        return this.cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDataNascimento(String dataNascimento) {

        DateFormatter formatador = new DateFormatter();
        LocalDate dataFormatada = formatador.formatarData(dataNascimento);
        this.dataNascimento = dataFormatada;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCpf(String cpf) {

        this.cpf = cpf;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return  "\nNome: " + nome +
                "\nEmail: " + email +
                "\nData de nascimento: " + getDataNascimento() +
                "\nTelefone: " + getTelefone() +
                "\nCPF: " + getCpf() +
                "\nEndereço: " + endereco;
    }
}