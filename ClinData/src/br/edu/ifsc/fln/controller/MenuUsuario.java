
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.Usuario;
import br.edu.ifsc.fln.model.Profissional;
import br.edu.ifsc.fln.model.Paciente;

import java.util.Scanner;

public class MenuUsuario {

    private Usuarios usuarios;
    private Scanner read;

    private CadastroProfissional cadastroProfissional;
    private CadastroPaciente cadastroPaciente;

    private GerenciadorAgendamento gerenciadorAgendamento;
    private GerenciadorConsulta gerenciadorConsulta;
    private GerenciadorProcedimento gerenciadorProcedimento;

    public MenuUsuario(
            Usuarios usuarios,
            Scanner read,
            CadastroProfissional cadastroProfissional,
            CadastroPaciente cadastroPaciente,
            GerenciadorAgendamento gerenciadorAgendamento,
            GerenciadorConsulta gerenciadorConsulta,
            GerenciadorProcedimento gerenciadorProcedimento) {

        this.usuarios = usuarios;
        this.read = read;
        this.cadastroProfissional = cadastroProfissional;
        this.cadastroPaciente = cadastroPaciente;
        this.gerenciadorAgendamento = gerenciadorAgendamento;
        this.gerenciadorConsulta = gerenciadorConsulta;
        this.gerenciadorProcedimento = gerenciadorProcedimento;
    }

    public void inicializarMenu() {

        int opcao;

        do {

            opcao = mostrarMenu();

            switch (opcao) {

                case 1:
                    entrar();
                    break;

                case 2:
                    cadastrarUsuario();
                    break;

                case 3:
                    encontrarUsuario();
                    break;

                case 4:
                    modificarUsuario();
                    break;

                case 5:
                    deletarUsuario();
                    break;

                case 0:
                    System.out.println("Encerrando ClinData...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    public int mostrarMenu() {

        System.out.println(
                "\n========== CLINDATA ==========\n" +
                        "1 - Entrar no sistema\n" +
                        "2 - Cadastrar usuário\n" +
                        "3 - Encontrar usuário\n" +
                        "4 - Modificar usuário\n" +
                        "5 - Deletar usuário\n" +
                        "0 - Sair\n" +
                        "=============================="
        );

        try {
            return Integer.parseInt(read.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void cadastrarUsuario() {

        System.out.println(
                "\n1 - Profissional\n" +
                        "2 - Paciente"
        );

        int opcao;

        try {
            opcao = Integer.parseInt(read.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida!");
            return;
        }

        switch (opcao) {

            case 1:
                cadastroProfissional.cadastrarProfissional();
                break;

            case 2:
                cadastroPaciente.cadastrarPaciente();
                break;

            default:
                System.out.println("Opção inválida!");
        }
    }


    public void entrar() {

        System.out.println("\n===== LOGIN CLINDATA =====");

        System.out.print("E-mail: ");
        String email = read.nextLine().trim();

        System.out.print("Senha: ");
        String senha = read.nextLine().trim();

        Usuario usuario = usuarios.buscarPorEmail(email);

        if (usuario == null ||
                !usuario.getSenha().equals(senha)) {

            System.out.println("E-mail ou senha incorretos!");
            return;
        }

        System.out.println("\nLogin realizado com sucesso!");

        if (usuario instanceof Profissional) {

            MenuProfissional menu = new MenuProfissional(
                    (Profissional) usuario,
                    read,
                    usuarios,
                    gerenciadorAgendamento,
                    gerenciadorConsulta,
                    gerenciadorProcedimento
            );

            menu.inicializarMenu();

        } else if (usuario instanceof Paciente) {

            MenuPaciente menu = new MenuPaciente(
                    (Paciente) usuario,
                    read,
                    gerenciadorAgendamento,
                    gerenciadorConsulta,
                    gerenciadorProcedimento,
                    cadastroPaciente
            );

            menu.inicializarMenu();
        }
    }

    public void encontrarUsuario() {

        System.out.println("\n1 - Buscar por e-mail");
        System.out.println("2 - Buscar por CPF");

        int opcao;

        try {
            opcao = Integer.parseInt(read.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida!");
            return;
        }

        Usuario usuario = null;

        switch (opcao) {

            case 1:
                System.out.print("E-mail: ");
                usuario = usuarios.buscarPorEmail(read.nextLine().trim());
                break;

            case 2:
                System.out.print("CPF: ");
                usuario = usuarios.buscarPorCPF(read.nextLine().trim());
                break;

            default:
                System.out.println("Opção inválida!");
                return;
        }

        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.println("\n===== USUÁRIO ENCONTRADO =====");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("E-mail: " + usuario.getEmail());
        System.out.println("CPF: " + usuario.getCpf());

        if (usuario instanceof Profissional) {

            Profissional profissional = (Profissional) usuario;

            System.out.println("Tipo: Profissional");
            System.out.println("Especialidade: " +
                    profissional.getEspecialidade());

        } else if (usuario instanceof Paciente) {

            System.out.println("Tipo: Paciente");
        }
    }

    public void modificarUsuario() {

        System.out.print("\nCPF do usuário: ");
        String cpf = read.nextLine().trim();

        Usuario usuario = usuarios.buscarPorCPF(cpf);

        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.println("\n1 - Nome");
        System.out.println("2 - E-mail");
        System.out.println("3 - Senha");
        System.out.println("4 - Telefone");
        System.out.println("5 - Endereço");

        int opcao;

        try {
            opcao = Integer.parseInt(read.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida!");
            return;
        }

        switch (opcao) {

            case 1:
                System.out.print("Novo nome: ");
                usuario.setNome(read.nextLine().trim());
                break;

            case 2:
                System.out.print("Novo e-mail: ");
                usuario.setEmail(read.nextLine().trim());
                break;

            case 3:
                System.out.print("Nova senha: ");
                usuario.setSenha(read.nextLine().trim());
                break;

            case 4:
                System.out.print("Novo telefone: ");
                usuario.setTelefone(read.nextLine().trim());
                break;

            case 5:
                System.out.print("Novo endereço: ");
                usuario.setEndereco(read.nextLine().trim());
                break;

            default:
                System.out.println("Opção inválida!");
                return;
        }

        System.out.println("Usuário atualizado com sucesso!");
    }

    public void deletarUsuario() {

        System.out.print("\nCPF do usuário: ");
        String cpf = read.nextLine().trim();

        Usuario usuario = usuarios.buscarPorCPF(cpf);

        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.println(
                "Deseja realmente excluir " +
                        usuario.getNome() + "? (S/N)"
        );

        String confirmacao = read.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {

            usuarios.removerUsuario(cpf);

            System.out.println("Usuário removido com sucesso!");

        } else {

            System.out.println("Exclusão cancelada.");
        }
    }
}