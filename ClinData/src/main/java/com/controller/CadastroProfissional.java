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

        UnidadesClinicas unidadesClinicas = new UnidadesClinicas();

        System.out.println("Qual sua unidade de atuação?\n" + unidadesClinicas.listarUnidades());
        profissional.setUnidadeClinica(Integer.parseInt(read.nextLine().trim()));

        usuarios.registrarUsuario(profissional);

        System.out.println("\nProfissional cadastrado com sucesso!");

        exibirProfissional(profissional);
    }

    public void exibirProfissional(Profissional profissional) {

        System.out.println("\nDADOS DO PROFISSIONAL");
        System.out.println(profissional.toString());
    }
}