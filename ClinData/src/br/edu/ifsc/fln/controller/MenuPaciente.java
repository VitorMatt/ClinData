
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.Paciente;
import java.util.Scanner;

public class MenuPaciente {

    private Paciente paciente;
    private Scanner read;

    private CadastroPaciente cadastroPaciente;
    private GerenciadorAgendamento gerenciadorAgendamento;
    private GerenciadorConsulta gerenciadorConsulta;
    private GerenciadorProcedimento gerenciadorProcedimento;

    public MenuPaciente(
            Paciente paciente,
            Scanner read,
            GerenciadorAgendamento gerenciadorAgendamento,
            GerenciadorConsulta gerenciadorConsulta,
            GerenciadorProcedimento gerenciadorProcedimento,
            CadastroPaciente cadastroPaciente) {

        this.paciente = paciente;
        this.read = read;
        this.gerenciadorAgendamento = gerenciadorAgendamento;
        this.gerenciadorConsulta = gerenciadorConsulta;
        this.gerenciadorProcedimento = gerenciadorProcedimento;
        this.cadastroPaciente = cadastroPaciente;
    }

    public void inicializarMenu() {

        int opcao;

        do {

            System.out.println("\nCLINDATA | PACIENTE\n");
            System.out.println("Bem-vindo(a), " + paciente.getNome());

            System.out.println("1 - Visualizar meus dados");
            System.out.println("2 - Agendar consulta");
            System.out.println("3 - Visualizar agendamentos");
            System.out.println("4 - Modificar agendamento");
            System.out.println("5 - Cancelar agendamento");
            System.out.println("6 - Visualizar consultas");
            System.out.println("7 - Visualizar procedimentos");
            System.out.println("8 - Visualizar prontuário");
            System.out.println("9 - Realizar pagamento");
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
                    gerenciadorAgendamento.agendarConsulta(paciente);
                    break;

                case 3:
                    gerenciadorAgendamento
                            .listarAgendamentosPaciente(paciente);
                    break;

                case 4:
                    gerenciadorAgendamento
                            .modificarAgendamentoPaciente(paciente);
                    break;

                case 5:
                    gerenciadorAgendamento
                            .cancelarAgendamento(paciente);
                    break;

                case 6:
                    gerenciadorConsulta
                            .listarConsultasPaciente(paciente);
                    break;

                case 7:
                    gerenciadorProcedimento
                            .listarProcedimentosPaciente(paciente);
                    break;

                case 8:
                    cadastroPaciente.exibirProntuario(paciente);
                    break;
                case 9:
                    System.out.print("Informe o ID do agendamento: ");
                    int id = read.nextInt();
                    read.nextLine();

                    gerenciadorAgendamento.realizarPagamento(id, paciente);
                    break;

                case 0:
                    System.out.println("Saindo da área do paciente...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void mostrarDados() {

        System.out.println("\nMEUS DADOS\n");

        System.out.println("Nome: " + paciente.getNome());
        System.out.println("E-mail: " + paciente.getEmail());
        System.out.println("CPF: " + paciente.getCpf());
        System.out.println("Telefone: " + paciente.getTelefone());
        System.out.println("Nascimento: " + paciente.getDataNascimento());
        System.out.println("Endereço: " + paciente.getEndereco());
    }
}
