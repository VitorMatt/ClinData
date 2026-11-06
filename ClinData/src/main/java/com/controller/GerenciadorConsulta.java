
package main.java.com.controller;

import main.java.com.model.*;
import main.java.com.util.Validator;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorConsulta {

    private final List<Consulta> consultas = new ArrayList<>();
    private final GerenciadorAgendamento gerenciadorAgendamento;
    private final int[] controleId = {1};
    private final Scanner read;

    public GerenciadorConsulta(
            GerenciadorAgendamento gerenciadorAgendamento,
            Scanner read) {
        this.gerenciadorAgendamento = gerenciadorAgendamento;
        this.read = read;
    }

    public void registrarConsulta(Profissional profissional) {
        gerenciadorAgendamento.listarAgendamentosProfissional(profissional);

        System.out.print("\nCPF do paciente: ");
        String cpf = read.nextLine().trim().replaceAll("\\D", "");

        if (!Validator.isCpfFormatValid(cpf)) {
            System.out.println("CPF inválido!");
            return;
        }

        AgendamentoPagamento agendamentoEncontrado = null;

        for (AgendamentoPagamento agendamento : gerenciadorAgendamento.getAgendamentos()) {
            String cpfAgendamento = agendamento.getPaciente().getCpf()
                    .replaceAll("\\D", "");

            if (cpfAgendamento.equals(cpf)
                    && agendamento.getProfissional().equals(profissional)
                    && (agendamento.getStatus() == Status.PAGO
                    || agendamento.getStatus() == Status.AGENDADO)) {
                agendamentoEncontrado = agendamento;
                break;
            }
        }

        if (agendamentoEncontrado == null) {
            System.out.println("Agendamento não encontrado ou ainda não está pago!");
            return;
        }

        for (Consulta consulta : consultas) {
            if (consulta.getPaciente().equals(agendamentoEncontrado.getPaciente())
                    && consulta.getProfissional().equals(profissional)
                    && consulta.getData().equals(agendamentoEncontrado.getData())
                    && consulta.getHorario().equals(agendamentoEncontrado.getHorario())) {
                System.out.println("Já existe uma consulta registrada para esse agendamento!");
                return;
            }
        }

        Consulta consulta = new Consulta();
        consulta.setIdConsulta(controleId[0]++);
        consulta.setPaciente(agendamentoEncontrado.getPaciente());
        consulta.setProfissional(profissional);
        consulta.setData(agendamentoEncontrado.getData());
        consulta.setHorario(agendamentoEncontrado.getHorario());

        System.out.print("Tipo de consulta: ");
        String tipo = read.nextLine().trim();

        if (tipo.isEmpty()) {
            System.out.println("O tipo de consulta não pode ficar vazio!");
            return;
        }

        consulta.setTipo(tipo);

        System.out.print("Duração da consulta (min): ");
        String duracao = read.nextLine().trim();

        if (!Validator.isInteger(duracao)) {
            System.out.println("Duração inválida!");
            return;
        }

        long minutos = Long.parseLong(duracao);

        if (minutos < 20 || minutos > 50) {

            System.out.println("A duração da consulta deve ser entre 20 a 50 min.");
            return;
        }

        consulta.setDuracao(Duration.ofMinutes(minutos));
        consulta.setRealizada(false);

        UnidadesClinicas unidadesClinicas = new UnidadesClinicas();
        System.out.println("Qual a unidade da consulta?\n" + unidadesClinicas.listarUnidades());
        System.out.print("Código da unidade: ");

        String entradaUnidade = read.nextLine().trim();

        if (!Validator.isInteger(entradaUnidade)) {
            System.out.println("Código da unidade inválido!");
            return;
        }

        int unidade = Integer.parseInt(entradaUnidade);

        if (unidade <= 0) {
            System.out.println("Código da unidade inválido!");
            return;
        }

        consulta.setUnidadeClinica(unidade);
        consultas.add(consulta);
        agendamentoEncontrado.setStatus(Status.AGENDADO);

        System.out.println("\nConsulta registrada com sucesso!");
    }

    public void listarConsultasProfissional(Profissional profissional) {
        System.out.println("\n===== MINHAS CONSULTAS =====");

        boolean encontrado = false;

        for (Consulta consulta : consultas) {
            if (consulta.getProfissional().equals(profissional)) {
                exibirConsulta(consulta);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhuma consulta registrada.");
        }
    }

    public void listarConsultasPaciente(Paciente paciente) {
        System.out.println("\n===== MINHAS CONSULTAS =====");

        boolean encontrado = false;

        for (Consulta consulta : consultas) {
            if (consulta.getPaciente().equals(paciente)) {
                exibirConsulta(consulta);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhuma consulta encontrada.");
        }
    }

    public void finalizarConsulta(Profissional profissional) {
        listarConsultasProfissional(profissional);

        System.out.print("\nID da consulta que deseja finalizar: ");
        String entradaId = read.nextLine().trim();

        if (!Validator.isInteger(entradaId)) {
            System.out.println("ID inválido!");
            return;
        }

        int id = Integer.parseInt(entradaId);

        for (Consulta consulta : consultas) {
            if (consulta.getIdConsulta() != null
                    && consulta.getIdConsulta() == id
                    && consulta.getProfissional().equals(profissional)
                    && !consulta.isRealizada()) {

                consulta.setRealizada(true);
                System.out.println("Consulta finalizada com sucesso!");
                return;
            }
        }

        System.out.println("Consulta não encontrada ou já finalizada.");
    }

    public void exibirConsulta(Consulta consulta) {
        System.out.println("\n----------------------------");
        System.out.println(consulta);
    }

    public void excluirConsulta(Profissional profissional) {
        listarConsultasProfissional(profissional);

        System.out.print("\nID da consulta que deseja excluir: ");
        String entradaId = read.nextLine().trim();

        if (!Validator.isInteger(entradaId)) {
            System.out.println("ID inválido!");
            return;
        }

        int id = Integer.parseInt(entradaId);
        Consulta consultaEncontrada = null;

        for (Consulta consulta : consultas) {
            if (consulta.getIdConsulta() != null
                    && consulta.getIdConsulta() == id
                    && consulta.getProfissional().equals(profissional)) {
                consultaEncontrada = consulta;
                break;
            }
        }

        if (consultaEncontrada == null) {
            System.out.println("Consulta não encontrada!");
            return;
        }

        System.out.println("\nPaciente: " + consultaEncontrada.getPaciente().getNome());
        System.out.println("Data: " + consultaEncontrada.getData());
        System.out.println("Horário: " + consultaEncontrada.getHorario());

        System.out.print("\nDeseja realmente excluir esta consulta? (S/N): ");
        String confirmacao = read.nextLine().trim();

        if (confirmacao.equalsIgnoreCase("S")) {
            consultas.remove(consultaEncontrada);
            System.out.println("Consulta excluída com sucesso!");
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }
}