
package main.java.com.controller;

import main.java.com.model.Paciente;
import main.java.com.model.Profissional;
import main.java.com.model.Usuario;

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

            System.out.println("\nCLINDATA | PROFISSIONAL\n");
            System.out.println("Bem-vindo(a), " + profissional.getNome());

            System.out.println("1 - Visualizar meus dados");
            System.out.println("2 - Visualizar agendamentos");
            System.out.println("3 - Modificar agendamento");
            System.out.println("4 - Cancelar agendamento");
            System.out.println("5 - Confirmar agendamento");
            System.out.println("6 - Registrar consulta");
            System.out.println("7 - Visualizar consultas");
            System.out.println("8 - Finalizar consulta");
            System.out.println("9 - Excluir consulta");
            System.out.println("10 - Gerenciar procedimentos");
            System.out.println("11 - Alterar Prontuário do Paciente");
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

                case 11:
                    modificarProntuarioPaciente();
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

            System.out.println("\nGERENCIAR PROCEDIMENTOS");
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

    private void modificarProntuarioPaciente() {

        System.out.print("\nInforme o CPF do paciente: ");
        String cpfPaciente = read.nextLine().trim();

        Usuarios usuarios = new Usuarios();
        Usuario usuario = usuarios.buscarPorCPF(cpfPaciente);

        if (usuario == null || !(usuario instanceof Paciente)) {

            System.out.println("Paciente não encontrado!");
            return;
        }

        Paciente paciente = (Paciente) usuario;

        int opcao;

        do {

            System.out.println("\nMODIFICAR PRONTUÁRIO");

            System.out.println("Paciente: " + paciente.getNome());

            System.out.println("\n1 - Relatório Médico");
            System.out.println("2 - Alergias do Paciente");
            System.out.println("3 - Medicamentos em Uso");
            System.out.println("4 - Observações");
            System.out.println("5 - Paciente preferencial?");
            System.out.println("0 - Voltar");

            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = Integer.parseInt(read.nextLine().trim());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {

                case 1:

                    System.out.print("Novo relatório médico: ");
                    paciente.setRelatorioMedico(read.nextLine());

                    System.out.println("Relatório médico atualizado!");
                    break;

                case 2:

                    System.out.print("Informe as alergias do paciente: ");
                    paciente.setAlergias(read.nextLine());

                    System.out.println("Alergias atualizadas!");
                    break;

                case 3:

                    System.out.print("Informe os medicamentos em uso: ");
                    paciente.setMedicamentosEmUso(read.nextLine());

                    System.out.println("Medicamentos atualizados!");
                    break;

                case 4:

                    System.out.print("Informe as observações: ");
                    paciente.setObservacoes(read.nextLine());

                    System.out.println("Observações atualizadas!");
                    break;

                case 5:

                    System.out.print("Paciente preferencial? (S/N): ");
                    String resposta = read.nextLine().trim();

                    if (resposta.equalsIgnoreCase("S")) {

                        paciente.setPreferencial(true);
                        System.out.println("Paciente definido como preferencial!");

                    } else if (resposta.equalsIgnoreCase("N")) {

                        paciente.setPreferencial(false);
                        System.out.println("Paciente não é mais preferencial!");

                    } else {

                        System.out.println("Resposta inválida!");
                    }

                    break;

                case 0:
                    System.out.println("Voltando ao menu profissional...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void mostrarDados() {

        System.out.println("\nMEUS DADOS\n");
        System.out.println(profissional.toString());
    }
}
