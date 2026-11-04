package main.java.com.controller;

import main.java.com.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorConsulta {

    private List<Consulta> consultas = new ArrayList<>();

    private GerenciadorAgendamento gerenciadorAgendamento;
    private int proximoId = 1;
    private Scanner read;

    public GerenciadorConsulta(
            GerenciadorAgendamento gerenciadorAgendamento,
            Scanner read) {

        this.gerenciadorAgendamento = gerenciadorAgendamento;
        this.read = read;
    }

    public void registrarConsulta(Profissional profissional) {

        gerenciadorAgendamento.listarAgendamentosProfissional(
                profissional
        );

        System.out.print("\nCPF do paciente: ");
        String cpf = read.nextLine().trim();

        AgendamentoPagamento agendamentoEncontrado = null;

        for (AgendamentoPagamento agendamento :
                gerenciadorAgendamento.getAgendamentos()) {

            if (agendamento.getPaciente().getCpf().equals(cpf) &&
                    agendamento.getProfissional().equals(profissional) &&
                    (agendamento.getStatus() == Status.PAGO || agendamento.getStatus() == Status.AGENDADO)) {

                agendamentoEncontrado = agendamento;
                break;
            }
        }

        if (agendamentoEncontrado == null) {
            System.out.println("Agendamento não encontrado!");
            return;
        }

        Consulta consulta = new Consulta();

        consulta.setIdConsulta(proximoId++);

        consulta.setPaciente(agendamentoEncontrado.getPaciente());
        consulta.setProfissional(profissional);
        consulta.setData(agendamentoEncontrado.getData());
        consulta.setHorario(agendamentoEncontrado.getHorario());

        System.out.print("Tipo de consulta: ");
        consulta.setTipo(read.nextLine().trim());

        consulta.setRealizada(false);

        UnidadesClinicas unidadesClinicas = new UnidadesClinicas();
        System.out.println("Qual a unidade da consulta?\n" + unidadesClinicas.listarUnidades());
        consulta.setUnidadeClinica(Integer.parseInt(read.nextLine().trim()));

        consultas.add(consulta);

        agendamentoEncontrado.setStatus(Status.AGENDADO);

        System.out.println("\nConsulta registrada com sucesso!");
    }

    public void listarConsultasProfissional(
            Profissional profissional) {

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

        System.out.print("\nCPF do paciente: ");
        String cpf = read.nextLine().trim();

        for (Consulta consulta : consultas) {

            if (consulta.getPaciente().getCpf().equals(cpf) &&
                    consulta.getProfissional().equals(profissional) &&
                    !consulta.isRealizada()) {

                consulta.setRealizada(true);

                System.out.println("Consulta finalizada com sucesso!");
                return;
            }
        }

        System.out.println("Consulta não encontrada ou já finalizada.");
    }

    public void exibirConsulta(Consulta consulta) {

        System.out.println("\n----------------------------");

        System.out.println(consulta.toString());
    }


    public void excluirConsulta(Profissional profissional) {

        listarConsultasProfissional(profissional);

        System.out.print("\nID da consulta que deseja excluir: ");

        int id;

        try {
            id = Integer.parseInt(read.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
            return;
        }

        Consulta consultaEncontrada = null;

        for (Consulta consulta : consultas) {

            if (consulta.getIdConsulta() != null &&
                    consulta.getIdConsulta() == id &&
                    consulta.getProfissional().equals(profissional)) {

                consultaEncontrada = consulta;
                break;
            }
        }

        if (consultaEncontrada == null) {
            System.out.println("Consulta não encontrada!");
            return;
        }

        System.out.println(
                "\nPaciente: " +
                        consultaEncontrada.getPaciente().getNome()
        );

        System.out.println(
                "Data: " + consultaEncontrada.getData()
        );

        System.out.print("\nDeseja realmente excluir esta consulta? (S/N): ");

        String confirmacao = read.nextLine();

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