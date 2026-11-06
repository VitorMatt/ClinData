
package main.java.com.controller;

import main.java.com.model.AgendamentoPagamento;
import main.java.com.model.Paciente;
import main.java.com.model.Profissional;
import main.java.com.model.Status;
import main.java.com.util.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorAgendamento {

    private final List<AgendamentoPagamento> agendamentos = new ArrayList<>();
    private int proximoIdAgendamento = 1;

    private final Usuarios usuarios;
    private final Scanner read;

    private final DateTimeFormatter formatoData =
            DateTimeFormatter.ofPattern("dd/MM/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

    public GerenciadorAgendamento(Usuarios usuarios, Scanner read) {
        this.usuarios = usuarios;
        this.read = read;
    }

    private LocalDate converterData(String entrada) {
        if (!Validator.isDate(entrada)) {
            throw new DateTimeParseException("Data inválida", entrada, 0);
        }

        return LocalDate.parse(entrada, formatoData);
    }

    public void agendarConsulta(Paciente paciente) {

        System.out.println("\n===== NOVO AGENDAMENTO =====");

        System.out.print("CPF do profissional: ");
        String cpf = read.nextLine().trim();

        if (Validator.isEmpty(cpf) || !Validator.isCpfFormatValid(cpf)) {
            System.out.println("Informe um CPF válido!");
            return;
        }

        Profissional profissional = buscarProfissional(cpf);

        if (profissional == null) {
            System.out.println("Profissional não encontrado!");
            return;
        }

        LocalDate data;
        LocalTime horario;

        try {
            System.out.print("Data (dd/MM/yyyy): ");
            data = converterData(read.nextLine().trim());

            System.out.print("Horário (HH:mm): ");
            horario = LocalTime.parse(read.nextLine().trim());

        } catch (DateTimeParseException e) {
            System.out.println("Data ou horário inválido!");
            return;
        }

        if (data.isBefore(LocalDate.now())) {
            System.out.println("Não é possível agendar para uma data passada!");
            return;
        }

        if (data.equals(LocalDate.now())
                && !horario.isAfter(LocalTime.now())) {
            System.out.println("Esse horário já passou!");
            return;
        }

        UnidadesClinicas unidadesClinicas = new UnidadesClinicas();

        System.out.println(
                "Qual a unidade do agendamento?\n"
                        + unidadesClinicas.listarUnidades()
        );

        System.out.print("Código da unidade: ");
        String entradaUnidade = read.nextLine().trim();

        if (!Validator.isInteger(entradaUnidade)) {
            System.out.println("Código de unidade inválido!");
            return;
        }

        int unidade = Integer.parseInt(entradaUnidade);

        AgendamentoPagamento agendamento = new AgendamentoPagamento();

        agendamento.setPaciente(paciente);
        agendamento.setProfissional(profissional);
        agendamento.setData(data);
        agendamento.setHorario(horario);
        agendamento.setStatus(Status.PENDENTE);
        agendamento.setIdAgendamento(proximoIdAgendamento);
        agendamento.setUnidadeClinica(unidade);

        if (horarioOcupado(profissional, data, horario)) {
            System.out.println("Esse horário já está ocupado!");
            return;
        }

        agendamentos.add(agendamento);
        proximoIdAgendamento++;

        System.out.println("\nAgendamento realizado com sucesso!");
    }

    private Profissional buscarProfissional(String cpf) {

        for (var usuario : usuarios.getUsuarios()) {

            if (usuario instanceof Profissional
                    && usuario.getCpf() != null
                    && usuario.getCpf().replaceAll("\\D", "")
                    .equals(cpf.replaceAll("\\D", ""))) {
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

            if (agendamento.getProfissional().equals(profissional)
                    && agendamento.getData().equals(data)
                    && agendamento.getHorario().equals(horario)
                    && agendamento.getStatus() != Status.CANCELADO) {
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

    public void listarAgendamentosProfissional(Profissional profissional) {

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

        System.out.print("\nInforme a data (dd/MM/yyyy): ");

        LocalDate data;

        try {
            data = converterData(read.nextLine().trim());
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida!");
            return;
        }

        System.out.print("Informe o horário (HH:mm): ");

        LocalTime horario;

        try {
            horario = LocalTime.parse(read.nextLine().trim());
        } catch (DateTimeParseException e) {
            System.out.println("Horário inválido!");
            return;
        }

        for (AgendamentoPagamento agendamento : agendamentos) {

            if (agendamento.getPaciente().equals(paciente)
                    && agendamento.getData().equals(data)
                    && agendamento.getHorario().equals(horario)) {

                if (agendamento.getStatus() == Status.CANCELADO) {
                    System.out.println("Este agendamento já foi cancelado!");
                    return;
                }

                agendamento.setStatus(Status.CANCELADO);
                System.out.println("Agendamento cancelado com sucesso!");
                return;
            }
        }

        System.out.println("Agendamento não encontrado!");
    }

    public void exibirAgendamento(AgendamentoPagamento agendamento) {
        System.out.println("\n----------------------------");
        System.out.println(agendamento);
    }

    public void modificarAgendamentoProfissional(Profissional profissional) {

        listarAgendamentosProfissional(profissional);

        System.out.print("\nID do agendamento: ");
        String entradaId = read.nextLine().trim();

        if (!Validator.isInteger(entradaId)) {
            System.out.println("ID inválido!");
            return;
        }

        int id = Integer.parseInt(entradaId);
        AgendamentoPagamento encontrado = null;

        for (AgendamentoPagamento agendamento : agendamentos) {

            if (agendamento.getIdAgendamento() != null
                    && agendamento.getIdAgendamento() == id
                    && agendamento.getProfissional().equals(profissional)) {
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
        System.out.print("Escolha: ");

        String entradaOpcao = read.nextLine().trim();

        if (!Validator.isInteger(entradaOpcao)) {
            System.out.println("Opção inválida!");
            return;
        }

        int opcao = Integer.parseInt(entradaOpcao);
        LocalDate novaData = encontrado.getData();
        LocalTime novoHorario = encontrado.getHorario();

        try {
            switch (opcao) {
                case 1:
                    System.out.print("Nova data (dd/MM/yyyy): ");
                    novaData = converterData(read.nextLine().trim());
                    break;

                case 2:
                    System.out.print("Novo horário (HH:mm): ");
                    novoHorario = LocalTime.parse(read.nextLine().trim());
                    break;

                default:
                    System.out.println("Opção inválida!");
                    return;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Data ou horário inválido!");
            return;
        }

        if (novaData.isBefore(LocalDate.now())) {
            System.out.println("Não é possível alterar para uma data passada!");
            return;
        }

        if (novaData.equals(LocalDate.now())
                && !novoHorario.isAfter(LocalTime.now())) {
            System.out.println("Esse horário já passou!");
            return;
        }

        if (horarioOcupadoPorOutro(encontrado, novaData, novoHorario)) {
            System.out.println("Esse horário já está ocupado!");
            return;
        }

        encontrado.setData(novaData);
        encontrado.setHorario(novoHorario);

        System.out.println("Agendamento atualizado com sucesso!");
    }

    public void modificarAgendamentoPaciente(Paciente paciente) {

        System.out.println("\n--- MODIFICAR AGENDAMENTO ---");

        listarAgendamentosPaciente(paciente);

        System.out.print("\nInforme o ID do agendamento: ");
        String entradaId = read.nextLine().trim();

        if (!Validator.isInteger(entradaId)) {
            System.out.println("ID inválido!");
            return;
        }

        int id = Integer.parseInt(entradaId);
        AgendamentoPagamento encontrado = null;

        for (AgendamentoPagamento agendamento : agendamentos) {

            if (agendamento.getIdAgendamento() != null
                    && agendamento.getIdAgendamento() == id
                    && agendamento.getPaciente().equals(paciente)) {
                encontrado = agendamento;
                break;
            }
        }

        if (encontrado == null) {
            System.out.println("Agendamento não encontrado!");
            return;
        }

        if (encontrado.getStatus() == Status.CANCELADO) {
            System.out.println(
                    "Não é possível modificar um agendamento cancelado."
            );
            return;
        }

        LocalDate novaData;
        LocalTime novoHorario;

        try {
            System.out.print("Nova data (dd/MM/yyyy): ");
            novaData = converterData(read.nextLine().trim());

            System.out.print("Novo horário (HH:mm): ");
            novoHorario = LocalTime.parse(read.nextLine().trim());

        } catch (DateTimeParseException e) {
            System.out.println("Data ou horário inválido!");
            return;
        }

        if (novaData.isBefore(LocalDate.now())) {
            System.out.println("Não é possível alterar para uma data passada!");
            return;
        }

        if (novaData.equals(LocalDate.now())
                && !novoHorario.isAfter(LocalTime.now())) {
            System.out.println("Esse horário já passou!");
            return;
        }

        if (horarioOcupadoPorOutro(encontrado, novaData, novoHorario)) {
            System.out.println("Esse horário já está ocupado!");
            return;
        }

        encontrado.setData(novaData);
        encontrado.setHorario(novoHorario);

        System.out.println("\nAgendamento modificado com sucesso!");
    }

    private boolean horarioOcupadoPorOutro(
            AgendamentoPagamento atual,
            LocalDate data,
            LocalTime horario) {

        for (AgendamentoPagamento outro : agendamentos) {

            if (outro != atual
                    && outro.getProfissional().equals(
                    atual.getProfissional())
                    && outro.getData().equals(data)
                    && outro.getHorario().equals(horario)
                    && outro.getStatus() != Status.CANCELADO) {
                return true;
            }
        }

        return false;
    }

    public void realizarPagamento(int idAgendamento, Paciente paciente) {

        for (AgendamentoPagamento agendamento : agendamentos) {

            if (agendamento.getIdAgendamento() != null
                    && agendamento.getIdAgendamento() == idAgendamento
                    && agendamento.getPaciente().equals(paciente)) {

                if (agendamento.getStatus() == Status.CANCELADO) {
                    System.out.println(
                            "Agendamento cancelado. Pagamento não permitido."
                    );
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
                String entradaOpcao = read.nextLine().trim();

                if (!Validator.isInteger(entradaOpcao)) {
                    System.out.println("Opção inválida!");
                    return;
                }

                int opcao = Integer.parseInt(entradaOpcao);
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
        String entradaId = read.nextLine().trim();

        if (!Validator.isInteger(entradaId)) {
            System.out.println("ID inválido!");
            return;
        }

        int id = Integer.parseInt(entradaId);

        for (AgendamentoPagamento agendamento : agendamentos) {

            if (agendamento.getIdAgendamento() != null
                    && agendamento.getIdAgendamento() == id
                    && agendamento.getProfissional().equals(profissional)) {

                if (agendamento.getStatus() == Status.CANCELADO) {
                    System.out.println("Este agendamento já foi cancelado!");
                    return;
                }

                System.out.print(
                        "Deseja cancelar este agendamento? (S/N): "
                );

                String confirmacao = read.nextLine().trim();

                if (confirmacao.equalsIgnoreCase("S")) {
                    agendamento.setStatus(Status.CANCELADO);
                    System.out.println("Agendamento cancelado com sucesso!");
                } else if (confirmacao.equalsIgnoreCase("N")) {
                    System.out.println("Operação cancelada.");
                } else {
                    System.out.println("Resposta inválida!");
                }

                return;
            }
        }

        System.out.println("Agendamento não encontrado!");
    }

    public void confirmarAgendamentoProfissional(
            int idAgendamento,
            Profissional profissional) {

        for (AgendamentoPagamento agendamento : agendamentos) {

            if (agendamento.getIdAgendamento() != null
                    && agendamento.getIdAgendamento() == idAgendamento
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