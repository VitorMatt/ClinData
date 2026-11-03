package main.java.com.controller;

import java.time.LocalDate;
import java.util.Scanner;

import main.java.com.model.Profissional;

public class CadastroProfissional {

    private Usuarios usuarios;
    private Scanner read;

    public CadastroProfissional(Usuarios usuarios, Scanner read) {
        this.usuarios = usuarios;
        this.read = read;
    }

    public void cadastrarProfissional() {

        Profissional profissional = new Profissional();

        System.out.print("Nome: ");
        profissional.setNome(read.nextLine().trim());

        System.out.print("E-mail: ");
        profissional.setEmail(read.nextLine().trim());

        System.out.print("Senha: ");
        profissional.setSenha(read.nextLine().trim());

        System.out.print("CPF: ");
        profissional.setCpf(read.nextLine().trim());

        System.out.print("Telefone: ");
        profissional.setTelefone(read.nextLine().trim());

        System.out.print("Data de nascimento (AAAA-MM-DD): ");
        profissional.setDataNascimento(
                LocalDate.parse(read.nextLine().trim())
        );

        System.out.print("Endereço: ");
        profissional.setEndereco(read.nextLine().trim());

        System.out.print("Especialidade: ");
        profissional.setEspecialidade(read.nextLine().trim());

        usuarios.registrarUsuario(profissional);

        System.out.println("\nProfissional cadastrado com sucesso!");

        exibirProfissional(profissional);
    }

    public void exibirProfissional(Profissional profissional) {

        System.out.println("\n--- DADOS DO PROFISSIONAL ---");

        System.out.println("ID: " + profissional.getIdProfissional());
        System.out.println("Nome: " + profissional.getNome());
        System.out.println("E-mail: " + profissional.getEmail());
        System.out.println("CPF: " + profissional.getCpf());
        System.out.println("Telefone: " + profissional.getTelefone());
        System.out.println("Data de nascimento: " +
                profissional.getDataNascimento());
        System.out.println("Endereço: " + profissional.getEndereco());
        System.out.println("Especialidade: " +
                profissional.getEspecialidade());
    }
}