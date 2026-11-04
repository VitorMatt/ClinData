
package main.java.com.controller;

import main.java.com.model.Paciente;

import java.time.LocalDate;
import java.util.Scanner;

public class CadastroPaciente {

    private Usuarios usuarios;
    private Scanner read;

    public CadastroPaciente(Usuarios usuarios, Scanner read) {
        this.usuarios = usuarios;
        this.read = read;
    }

    public void cadastrarPaciente() {

        Paciente paciente = new Paciente();

        System.out.println("\nCADASTRO DE PACIENTE");

        System.out.print("Nome: ");
        paciente.setNome(read.nextLine().trim());

        System.out.print("E-mail: ");
        paciente.setEmail(read.nextLine().trim());

        System.out.print("Senha: ");
        paciente.setSenha(read.nextLine().trim());

        System.out.print("CPF: ");
        paciente.setCpf(read.nextLine().trim());

        System.out.print("Telefone: ");
        paciente.setTelefone(read.nextLine().trim());

        try {

            System.out.print("Data de nascimento (AAAA-MM-DD): ");
            paciente.setDataNascimento(
                    LocalDate.parse(read.nextLine().trim())
            );

        } catch (Exception e) {

            System.out.println("Data inválida!");
            return;
        }

        System.out.print("Endereço: ");
        paciente.setEndereco(read.nextLine().trim());

        System.out.print("É preferencial? (S/N): ");
        paciente.setPreferencial(
                read.nextLine().equalsIgnoreCase("S")
        );

        if (usuarios.buscarPorEmail(paciente.getEmail()) != null ||
                usuarios.buscarPorCPF(paciente.getCpf()) != null) {

            System.out.println(
                    "Já existe um usuário com esse e-mail ou CPF!"
            );

            return;
        }

        usuarios.registrarUsuario(paciente);

        System.out.println("\nPaciente cadastrado com sucesso!");

        exibirPaciente(paciente);
    }

    public void exibirPaciente(Paciente paciente) {

        System.out.println("\nDADOS DO PACIENTE");

        System.out.println(paciente.toString());
    }
}
