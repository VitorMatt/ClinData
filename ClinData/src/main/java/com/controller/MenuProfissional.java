
package main.java.com.controller;

import main.java.com.model.Profissional;
import java.util.Scanner;

public class MenuProfissional {

    private Profissional profissional;
    private Scanner read;

    private GerenciadorAgendamento gerenciadorAgendamento;
    private GerenciadorConsulta gerenciadorConsulta;
    private GerenciadorProcedimento gerenciadorProcedimento;

    public MenuProfissional(
            Profissional profissional,
            Scanner read,
            GerenciadorAgendamento gerenciadorAgendamento,
            GerenciadorConsulta gerenciadorConsulta,
            GerenciadorProcedimento gerenciadorProcedimento) {

        this.profissional = profissional;
        this.read = read;
        this.gerenciadorAgendamento = gerenciadorAgendamento;
        this.gerenciadorConsulta = gerenciadorConsulta;
        this.gerenciadorProcedimento = gerenciadorProcedimento;
    }

    public void inicializarMenu() {

        int opcao;

        do {

            System.out.println("\n===== CLINDATA | PROFISSIONAL =====");
            System.out.println("Bem-vindo, " + profissional.getNome());

            System.out.println("\n1 - Visualizar meus dados");
            System.out.println("2 - Visualizar agendamentos");
            System.out.println("3 - Modificar agendamento");
            System.out.println("4 - Cancelar agendamento");
            System.out.println("5 - Confirmar agendamento");
            System.out.println("6 - Registrar consulta");
            System.out.println("7 - Visualizar consultas");
            System.out.println("8 - Finalizar consulta");
            System.out.println("9 - Excluir consulta");
            System.out.println("10 - Gerenciar procedimentos");
            System.out.println("0 - Sair");

            try {
                opcao = Integer.parseInt(read.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {

                case 1:
                    mostrarDados();
                    break;

                case 2:
                    gerenciadorAgendamento
                            .listarAgendamentosProfissional(profissional);
                    break;

                case 3:
                    gerenciadorAgendamento
                            .modificarAgendamentoProfissional(profissional);
                    break;

                case 4:
                    gerenciadorAgendamento
                            .cancelarAgendamentoProfissional(profissional);
                    break;

                case 5:
                    System.out.print("Informe o ID do agendamento: ");
                    int id = read.nextInt();
                    read.nextLine();

                    gerenciadorAgendamento.confirmarAgendamentoProfissional(
                            id, profissional);
                    break;

                case 6:
                    gerenciadorConsulta.registrarConsulta(profissional);
                    break;

                case 7:
                    gerenciadorConsulta
                            .listarConsultasProfissional(profissional);
                    break;

                case 8:
                    gerenciadorConsulta.finalizarConsulta(profissional);
                    break;

                case 9:
                    gerenciadorConsulta.excluirConsulta(profissional);
                    break;

                case 10:
                    menuProcedimentos();
                    break;

                case 0:
                    System.out.println("Saindo da área profissional...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void menuProcedimentos() {

        int opcao;

        do {

            System.out.println("\n===== GERENCIAR PROCEDIMENTOS =====");
            System.out.println("1 - Cadastrar procedimento");
            System.out.println("2 - Visualizar procedimentos");
            System.out.println("3 - Modificar procedimento");
            System.out.println("4 - Excluir procedimento");
            System.out.println("0 - Voltar");

            try {
                opcao = Integer.parseInt(read.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {

                case 1:
                    gerenciadorProcedimento
                            .cadastrarProcedimento(profissional);
                    break;

                case 2:
                    gerenciadorProcedimento
                            .listarProcedimentosProfissional(profissional);
                    break;

                case 3:
                    gerenciadorProcedimento
                            .modificarProcedimento(profissional);
                    break;

                case 4:
                    gerenciadorProcedimento
                            .excluirProcedimento(profissional);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void mostrarDados() {

        System.out.println("\n===== MEUS DADOS =====");

        System.out.println("Nome: " + profissional.getNome());
        System.out.println("E-mail: " + profissional.getEmail());
        System.out.println("CPF: " + profissional.getCpf());
        System.out.println("Telefone: " + profissional.getTelefone());
        System.out.println("Especialidade: " +
                profissional.getEspecialidade());
    }
}
