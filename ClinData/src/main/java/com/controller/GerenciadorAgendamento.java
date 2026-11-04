
package main.java.com.controller;

import main.java.com.model.AgendamentoPagamento;
import main.java.com.model.Paciente;
import main.java.com.model.Profissional;
import main.java.com.model.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorAgendamento {

    private List<AgendamentoPagamento> agendamentos = new ArrayList<>();
    private int proximoIdAgendamento = 1;

    private Usuarios usuarios;
    private Scanner read;

    public GerenciadorAgendamento(Usuarios usuarios, Scanner read) {
        this.usuarios = usuarios;
        this.read = read;
    }

    public void agendarConsulta(Paciente paciente) {

        System.out.println("\n===== NOVO AGENDAMENTO =====");

        System.out.print("CPF do profissional: ");
        String cpf = read.nextLine().trim();

        Profissional profissional = buscarProfissional(cpf);

        if (profissional == null) {
            System.out.println("Profissional não encontrado!");
            return;
        }

        LocalDate data;
        LocalTime horario;

        try {

            System.out.print("Data (AAAA-MM-DD): ");
            data = LocalDate.parse(read.nextLine().trim());

            System.out.print("Horário (HH:MM): ");
            horario = LocalTime.parse(read.nextLine().trim());

        } catch (Exception e) {

            System.out.println("Data ou horário inválido!");
            return;
        }

        if (data.isBefore(LocalDate.now())) {
            System.out.println("Não é possível agendar para uma data passada!");
            return;
        }

        if (horario.isBefore(LocalTime.now()) &&
                data.equals(LocalDate.now())) {

            System.out.println("Esse horário já passou!");
            return;
        }

        if (horarioOcupado(profissional, data, horario)) {
            System.out.println("Esse horário já está ocupado!");
            return;
        }

        AgendamentoPagamento agendamento = new AgendamentoPagamento();

        agendamento.setPaciente(paciente);
        agendamento.setProfissional(profissional);
        agendamento.setData(data);
        agendamento.setHorario(horario);
        agendamento.setStatus(Status.PENDENTE);
        agendamento.setIdAgendamento(proximoIdAgendamento++);

        UnidadesClinicas unidadesClinicas = new UnidadesClinicas();
        System.out.println("Qual a unidade do agendamento?\n" + unidadesClinicas.listarUnidades());
        agendamento.setUnidadeClinica(Integer.parseInt(read.nextLine().trim()));

        agendamentos.add(agendamento);

        System.out.println("\nAgendamento realizado com sucesso!");
    }

    private Profissional buscarProfissional(String cpf) {

        for (var usuario : usuarios.getUsuarios()) {

            if (usuario instanceof Profissional &&
                    usuario.getCpf().equals(cpf)) {

                return (Profissional) usuario;
            }
        }

        return null;
    }

    private boolean horarioOcupado(
            Profissional profissional,
            LocalDate data,
            LocalTime horario) {

        for (AgendamentoPagamento agendamento : agendamentos) {

            if (agendamento.getProfissional().equals(profissional) &&
                    agendamento.getData().equals(data) &&
                    agendamento.getHorario().equals(horario) &&
                    !agendamento.getStatus().equals("CANCELADO")) {

                return true;
            }
        }

        return false;
    }

    public void listarAgendamentosPaciente(Paciente paciente) {

        System.out.println("\n===== MEUS AGENDAMENTOS =====");

        boolean encontrado = false;

        for (AgendamentoPagamento agendamento : agendamentos) {

            if (agendamento.getPaciente().equals(paciente)) {

                exibirAgendamento(agendamento);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum agendamento encontrado.");
        }
    }

    public void listarAgendamentosProfissional(
            Profissional profissional) {

        System.out.println("\n===== AGENDAMENTOS RECEBIDOS =====");

        boolean encontrado = false;

        for (AgendamentoPagamento agendamento : agendamentos) {

            if (agendamento.getProfissional().equals(profissional)) {

                exibirAgendamento(agendamento);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum agendamento encontrado.");
        }
    }

    public void cancelarAgendamento(Paciente paciente) {

        listarAgendamentosPaciente(paciente);

        System.out.print("\nInforme a data do agendamento (AAAA-MM-DD): ");

        LocalDate data;

        try {
            data = LocalDate.parse(read.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Data inválida!");
            return;
        }

        System.out.print("Informe o horário (HH:MM): ");

        LocalTime horario;

        try {
            horario = LocalTime.parse(read.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Horário inválido!");
            return;
        }

        for (AgendamentoPagamento agendamento : agendamentos) {

            if (agendamento.getPaciente().equals(paciente) &&
                    agendamento.getData().equals(data) &&
                    agendamento.getHorario().equals(horario) &&
                    !agendamento.getStatus().equals("CANCELADO")) {

                agendamento.setStatus(Status.CANCELADO);

                System.out.println("Agendamento cancelado!");
                return;
            }
        }

        System.out.println("Agendamento não encontrado!");
    }

    public void exibirAgendamento(AgendamentoPagamento agendamento) {

        System.out.println("\n----------------------------");

        System.out.println("Paciente: " +
                agendamento.getPaciente().getNome());

        System.out.println("Profissional: " +
                agendamento.getProfissional().getNome());

        System.out.println("Data: " + agendamento.getData());
        System.out.println("Horário: " + agendamento.getHorario());
        System.out.println("Status: " + agendamento.getStatus());
    }


    public void modificarAgendamentoProfissional(Profissional profissional) {

        listarAgendamentosProfissional(profissional);

        System.out.print("\nID do agendamento: ");

        int id;

        try {
            id = Integer.parseInt(read.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
            return;
        }

        AgendamentoPagamento encontrado = null;

        for (AgendamentoPagamento agendamento : agendamentos) {

            if (agendamento.getIdAgendamento() != null &&
                    agendamento.getIdAgendamento() == id &&
                    agendamento.getProfissional().equals(profissional)) {

                encontrado = agendamento;
                break;
            }
        }

        if (encontrado == null) {
            System.out.println("Agendamento não encontrado!");
            return;
        }

        if (encontrado.getStatus() == Status.CANCELADO) {
            System.out.println("Este agendamento está cancelado!");
            return;
        }

        System.out.println("\n1 - Alterar data");
        System.out.println("2 - Alterar horário");

        int opcao;

        try {
            opcao = Integer.parseInt(read.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida!");
            return;
        }

        try {

            switch (opcao) {

                case 1:
                    System.out.print("Nova data (AAAA-MM-DD): ");

                    encontrado.setData(
                            java.time.LocalDate.parse(read.nextLine())
                    );
                    break;

                case 2:
                    System.out.print("Novo horário (HH:MM): ");

                    encontrado.setHorario(
                            java.time.LocalTime.parse(read.nextLine())
                    );
                    break;

                default:
                    System.out.println("Opção inválida!");
                    return;
            }

            System.out.println("Agendamento atualizado!");

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Data ou horário inválido!");
        }
    }

    public void modificarAgendamentoPaciente(Paciente paciente) {

        System.out.println("\n--- MODIFICAR AGENDAMENTO ---");

        listarAgendamentosPaciente(paciente);

        System.out.print("\nInforme o ID do agendamento que deseja modificar: ");
        int id = read.nextInt();
        read.nextLine();

        for (AgendamentoPagamento agendamento : agendamentos) {

            if (agendamento.getIdAgendamento() == id &&
                    agendamento.getPaciente().equals(paciente)) {

                if (agendamento.getStatus() == Status.CANCELADO) {
                    System.out.println("Não é possível modificar um agendamento cancelado.");
                    return;
                }

                System.out.print("Nova data (AAAA-MM-DD): ");
                LocalDate novaData = LocalDate.parse(read.nextLine());
                agendamento.setData(novaData);

                System.out.print("Novo horário (HH:MM): ");
                LocalTime novoHorario = LocalTime.parse(read.nextLine());
                agendamento.setHorario(novoHorario);

                System.out.println("\nAgendamento modificado com sucesso!");
                return;
            }
        }

        System.out.println("Agendamento não encontrado.");
    }

    public void realizarPagamento(int idAgendamento, Paciente paciente) {

        for (AgendamentoPagamento agendamento : agendamentos) {

            if (agendamento.getIdAgendamento().equals(idAgendamento)
                    && agendamento.getPaciente().equals(paciente)) {

                if (agendamento.getStatus() == Status.CANCELADO) {
                    System.out.println("Agendamento cancelado. Pagamento não permitido.");
                    return;
                }

                if (agendamento.getStatus() == Status.PAGO) {
                    System.out.println("Este agendamento já foi pago.");
                    return;
                }

                System.out.println("\n--- REALIZAR PAGAMENTO ---");

                System.out.println("Valor: R$ " + agendamento.getValor());

                System.out.println("1 - Pix");
                System.out.println("2 - Cartão de crédito");
                System.out.println("3 - Cartão de débito");
                System.out.println("4 - Dinheiro");

                System.out.print("Escolha a forma de pagamento: ");
                int opcao = read.nextInt();
                read.nextLine();

                String formaPagamento;

                switch (opcao) {
                    case 1:
                        formaPagamento = "Pix";
                        break;
                    case 2:
                        formaPagamento = "Cartão de crédito";
                        break;
                    case 3:
                        formaPagamento = "Cartão de débito";
                        break;
                    case 4:
                        formaPagamento = "Dinheiro";
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        return;
                }

                agendamento.setFormaPagamento(formaPagamento);
                agendamento.setDataHoraPagamento(LocalDateTime.now());
                agendamento.setStatus(Status.PAGO);

                System.out.println("Pagamento realizado com sucesso!");
                System.out.println("Forma de pagamento: " + formaPagamento);

                return;
            }
        }

        System.out.println("Agendamento não encontrado.");
    }

    public void cancelarAgendamentoProfissional(Profissional profissional) {

        listarAgendamentosProfissional(profissional);

        System.out.print("\nID do agendamento: ");

        int id;

        try {
            id = Integer.parseInt(read.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
            return;
        }

        for (AgendamentoPagamento agendamento : agendamentos) {

            if (agendamento.getIdAgendamento() != null &&
                    agendamento.getIdAgendamento() == id &&
                    agendamento.getProfissional().equals(profissional)) {

                if (agendamento.getStatus() == Status.CANCELADO) {
                    System.out.println("Este agendamento já foi cancelado!");
                    return;
                }

                System.out.print("Deseja cancelar este agendamento? (S/N): ");

                String confirmacao = read.nextLine();

                if (confirmacao.equalsIgnoreCase("S")) {

                    agendamento.setStatus(Status.CANCELADO);

                    System.out.println("Agendamento cancelado com sucesso!");

                } else {

                    System.out.println("Operação cancelada.");
                }

                return;
            }
        }

        System.out.println("Agendamento não encontrado!");
    }

    public void confirmarAgendamentoProfissional(
            int idAgendamento, Profissional profissional) {

        for (AgendamentoPagamento agendamento : agendamentos) {

            if (agendamento.getIdAgendamento().equals(idAgendamento)
                    && agendamento.getProfissional().equals(profissional)) {

                if (agendamento.getStatus() == Status.CANCELADO) {
                    System.out.println("Agendamento cancelado.");
                    return;
                }

                if (agendamento.getStatus() != Status.PAGO) {
                    System.out.println("O agendamento ainda não foi pago.");
                    return;
                }

                agendamento.setStatus(Status.AGENDADO);

                System.out.println("Agendamento confirmado com sucesso!");
                return;
            }
        }

        System.out.println("Agendamento não encontrado.");
    }

    public List<AgendamentoPagamento> getAgendamentos() {
        return agendamentos;
    }
}
