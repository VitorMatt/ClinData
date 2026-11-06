
package main.java.com.controller;

import main.java.com.model.Procedimento;
import main.java.com.model.Consulta;
import main.java.com.model.Paciente;
import main.java.com.model.Profissional;
import main.java.com.util.Validator;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorProcedimento {

    private final List<Procedimento> procedimentos = new ArrayList<>();
    private final Scanner read;
    private final GerenciadorConsulta gerenciadorConsulta;
    private int proximoId = 1;

    public GerenciadorProcedimento(
            Scanner read,
            GerenciadorConsulta gerenciadorConsulta) {
        this.read = read;
        this.gerenciadorConsulta = gerenciadorConsulta;
    }

    public void cadastrarProcedimento(Profissional profissional) {
        System.out.println("\n===== CADASTRAR PROCEDIMENTO =====");

        System.out.print("CPF do paciente: ");
        String cpf = read.nextLine().trim().replaceAll("\\D", "");

        if (!Validator.isCpfFormatValid(cpf)) {
            System.out.println("CPF inválido!");
            return;
        }

        List<Consulta> consultas = gerenciadorConsulta.getConsultas();
        List<Consulta> consultasEncontradas = new ArrayList<>();

        for (Consulta consulta : consultas) {
            if (consulta.getPaciente() != null
                    && consulta.getProfissional() != null
                    && consulta.getPaciente().getCpf().replaceAll("\\D", "").equals(cpf)
                    && consulta.getProfissional().equals(profissional)) {
                consultasEncontradas.add(consulta);
            }
        }

        if (consultasEncontradas.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada para esse paciente.");
            return;
        }

        System.out.println("\nConsultas encontradas:");

        for (Consulta consulta : consultasEncontradas) {
            System.out.println(
                    "ID: " + consulta.getIdConsulta()
                            + " | Data: " + consulta.getData()
                            + " | Horário: " + consulta.getHorario()
                            + " | Tipo: " + consulta.getTipo()
            );
        }

        System.out.print("\nInforme o ID da consulta: ");
        String entradaIdConsulta = read.nextLine().trim();

        if (!Validator.isInteger(entradaIdConsulta)) {
            System.out.println("ID inválido!");
            return;
        }

        int idConsulta = Integer.parseInt(entradaIdConsulta);
        Consulta consultaSelecionada = null;

        for (Consulta consulta : consultasEncontradas) {
            if (consulta.getIdConsulta() != null
                    && consulta.getIdConsulta() == idConsulta) {
                consultaSelecionada = consulta;
                break;
            }
        }

        if (consultaSelecionada == null) {
            System.out.println("Consulta não encontrada!");
            return;
        }

        Procedimento procedimento = new Procedimento();
        procedimento.setIdProcedimento(proximoId);

        System.out.print("Nome do procedimento: ");
        String nome = read.nextLine().trim();

        if (nome.isEmpty()) {
            System.out.println("O nome do procedimento não pode ficar vazio!");
            return;
        }

        System.out.print("Descrição: ");
        String descricao = read.nextLine().trim();

        if (descricao.isEmpty()) {
            System.out.println("A descrição não pode ficar vazia!");
            return;
        }

        System.out.print("Orientações: ");
        String orientacoes = read.nextLine().trim();

        if (orientacoes.isEmpty()) {
            System.out.println("As orientações não podem ficar vazias!");
            return;
        }

        procedimento.setNome(nome);
        procedimento.setDescricao(descricao);
        procedimento.setOrientacoes(orientacoes);
        procedimento.setConsulta(consultaSelecionada);

        procedimentos.add(procedimento);
        proximoId++;

        System.out.println("\nProcedimento cadastrado com sucesso!");
    }

    public void listarProcedimentosProfissional(Profissional profissional) {
        System.out.println("\n===== PROCEDIMENTOS REGISTRADOS =====");

        boolean encontrado = false;

        for (Procedimento procedimento : procedimentos) {
            if (procedimento.getConsulta() != null
                    && procedimento.getConsulta().getProfissional() != null
                    && procedimento.getConsulta().getProfissional().equals(profissional)) {
                exibirProcedimento(procedimento);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum procedimento encontrado.");
        }
    }

    public void listarProcedimentosPaciente(Paciente paciente) {
        System.out.println("\n===== MEUS PROCEDIMENTOS =====");

        boolean encontrado = false;

        for (Procedimento procedimento : procedimentos) {
            if (procedimento.getConsulta() != null
                    && procedimento.getConsulta().getPaciente() != null
                    && procedimento.getConsulta().getPaciente().equals(paciente)) {
                exibirProcedimento(procedimento);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum procedimento encontrado.");
        }
    }

    public void modificarProcedimento(Profissional profissional) {
        listarProcedimentosProfissional(profissional);

        System.out.print("\nInforme o ID do procedimento: ");
        String entradaId = read.nextLine().trim();

        if (!Validator.isInteger(entradaId)) {
            System.out.println("ID inválido!");
            return;
        }

        int id = Integer.parseInt(entradaId);
        Procedimento procedimento = buscarPorId(id, profissional);

        if (procedimento == null) {
            System.out.println("Procedimento não encontrado!");
            return;
        }

        System.out.println("\n1 - Alterar nome");
        System.out.println("2 - Alterar descrição");
        System.out.println("3 - Alterar orientações");
        System.out.print("Escolha uma opção: ");

        String entradaOpcao = read.nextLine().trim();

        if (!Validator.isInteger(entradaOpcao)) {
            System.out.println("Opção inválida!");
            return;
        }

        int opcao = Integer.parseInt(entradaOpcao);
        String novoValor;

        switch (opcao) {
            case 1:
                System.out.print("Novo nome: ");
                novoValor = read.nextLine().trim();

                if (novoValor.isEmpty()) {
                    System.out.println("O nome não pode ficar vazio!");
                    return;
                }

                procedimento.setNome(novoValor);
                break;

            case 2:
                System.out.print("Nova descrição: ");
                novoValor = read.nextLine().trim();

                if (novoValor.isEmpty()) {
                    System.out.println("A descrição não pode ficar vazia!");
                    return;
                }

                procedimento.setDescricao(novoValor);
                break;

            case 3:
                System.out.print("Novas orientações: ");
                novoValor = read.nextLine().trim();

                if (novoValor.isEmpty()) {
                    System.out.println("As orientações não podem ficar vazias!");
                    return;
                }

                procedimento.setOrientacoes(novoValor);
                break;

            default:
                System.out.println("Opção inválida!");
                return;
        }

        System.out.println("Procedimento atualizado com sucesso!");
    }

    public void excluirProcedimento(Profissional profissional) {
        listarProcedimentosProfissional(profissional);

        System.out.print("\nInforme o ID do procedimento: ");
        String entradaId = read.nextLine().trim();

        if (!Validator.isInteger(entradaId)) {
            System.out.println("ID inválido!");
            return;
        }

        int id = Integer.parseInt(entradaId);
        Procedimento procedimento = buscarPorId(id, profissional);

        if (procedimento == null) {
            System.out.println("Procedimento não encontrado!");
            return;
        }

        System.out.print(
                "Deseja excluir o procedimento "
                        + procedimento.getNome()
                        + "? (S/N): "
        );

        String confirmacao = read.nextLine().trim();

        if (confirmacao.equalsIgnoreCase("S")) {
            procedimentos.remove(procedimento);
            System.out.println("Procedimento excluído com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private Procedimento buscarPorId(int id, Profissional profissional) {
        for (Procedimento procedimento : procedimentos) {
            if (procedimento.getIdProcedimento() != null
                    && procedimento.getIdProcedimento() == id
                    && procedimento.getConsulta() != null
                    && procedimento.getConsulta().getProfissional() != null
                    && procedimento.getConsulta().getProfissional().equals(profissional)) {
                return procedimento;
            }
        }

        return null;
    }

    public void exibirProcedimento(Procedimento procedimento) {
        System.out.println(procedimento);
    }

    public List<Procedimento> getProcedimentos() {
        return procedimentos;
    }
}