
package main.java.com.controller;

import main.java.com.model.Procedimento;
import main.java.com.model.Consulta;
import main.java.com.model.Paciente;
import main.java.com.model.Profissional;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorProcedimento {

    private List<Procedimento> procedimentos = new ArrayList<>();

    private Scanner read;
    private GerenciadorConsulta gerenciadorConsulta;

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
        String cpf = read.nextLine().trim();

        List<Consulta> consultas =
                gerenciadorConsulta.getConsultas();

        List<Consulta> consultasEncontradas = new ArrayList<>();

        for (Consulta consulta : consultas) {

            if (consulta.getPaciente() != null &&
                    consulta.getProfissional() != null &&
                    consulta.getPaciente().getCpf().equals(cpf) &&
                    consulta.getProfissional().equals(profissional)) {

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
                    "ID: " + consulta.getIdConsulta() +
                            " | Data: " + consulta.getData() +
                            " | Tipo: " + consulta.getTipo()
            );
        }

        System.out.print("\nInforme o ID da consulta: ");

        int idConsulta;

        try {
            idConsulta = Integer.parseInt(read.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
            return;
        }

        Consulta consultaSelecionada = null;

        for (Consulta consulta : consultasEncontradas) {

            if (consulta.getIdConsulta() != null &&
                    consulta.getIdConsulta() == idConsulta) {

                consultaSelecionada = consulta;
                break;
            }
        }

        if (consultaSelecionada == null) {
            System.out.println("Consulta não encontrada!");
            return;
        }

        Procedimento procedimento = new Procedimento();

        procedimento.setIdProcedimento(proximoId++);

        System.out.print("Nome do procedimento: ");
        procedimento.setNome(read.nextLine().trim());

        System.out.print("Descrição: ");
        procedimento.setDescricao(read.nextLine().trim());

        System.out.print("Valor: ");

        try {

            procedimento.setValor(
                    Double.parseDouble(
                            read.nextLine().trim().replace(",", ".")
                    )
            );

            if (procedimento.getValor() < 0) {
                System.out.println("O valor não pode ser negativo!");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Valor inválido!");
            return;
        }

        procedimento.setConsulta(consultaSelecionada);

        procedimentos.add(procedimento);

        System.out.println("\nProcedimento cadastrado com sucesso!");
    }

    // LISTAR PROCEDIMENTOS DO PROFISSIONAL
    public void listarProcedimentosProfissional(
            Profissional profissional) {

        System.out.println("\n===== MEUS PROCEDIMENTOS =====");

        boolean encontrado = false;

        for (Procedimento procedimento : procedimentos) {

            if (procedimento.getConsulta() != null &&
                    procedimento.getConsulta().getProfissional()
                            .equals(profissional)) {

                exibirProcedimento(procedimento);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum procedimento encontrado.");
        }
    }

    // LISTAR PROCEDIMENTOS DO PACIENTE
    public void listarProcedimentosPaciente(Paciente paciente) {

        System.out.println("\n===== MEUS PROCEDIMENTOS =====");

        boolean encontrado = false;

        for (Procedimento procedimento : procedimentos) {

            if (procedimento.getConsulta() != null &&
                    procedimento.getConsulta().getPaciente()
                            .equals(paciente)) {

                exibirProcedimento(procedimento);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum procedimento encontrado.");
        }
    }

    // MODIFICAR PROCEDIMENTO
    public void modificarProcedimento(Profissional profissional) {

        listarProcedimentosProfissional(profissional);

        System.out.print("\nInforme o ID do procedimento: ");

        int id;

        try {
            id = Integer.parseInt(read.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
            return;
        }

        Procedimento procedimento = buscarPorId(id, profissional);

        if (procedimento == null) {
            System.out.println("Procedimento não encontrado!");
            return;
        }

        System.out.println("\n1 - Alterar nome");
        System.out.println("2 - Alterar descrição");
        System.out.println("3 - Alterar valor");

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
                procedimento.setNome(read.nextLine().trim());
                break;

            case 2:
                System.out.print("Nova descrição: ");
                procedimento.setDescricao(read.nextLine().trim());
                break;

            case 3:

                System.out.print("Novo valor: ");

                try {

                    double valor = Double.parseDouble(
                            read.nextLine().trim().replace(",", ".")
                    );

                    if (valor < 0) {
                        System.out.println("Valor inválido!");
                        return;
                    }

                    procedimento.setValor(valor);

                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido!");
                    return;
                }

                break;

            default:
                System.out.println("Opção inválida!");
                return;
        }

        System.out.println("Procedimento atualizado com sucesso!");
    }

    // EXCLUIR PROCEDIMENTO
    public void excluirProcedimento(Profissional profissional) {

        listarProcedimentosProfissional(profissional);

        System.out.print("\nInforme o ID do procedimento: ");

        int id;

        try {
            id = Integer.parseInt(read.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
            return;
        }

        Procedimento procedimento = buscarPorId(id, profissional);

        if (procedimento == null) {
            System.out.println("Procedimento não encontrado!");
            return;
        }

        System.out.print(
                "Deseja excluir o procedimento " +
                        procedimento.getNome() + "? (S/N): "
        );

        String confirmacao = read.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {

            procedimentos.remove(procedimento);

            System.out.println("Procedimento excluído com sucesso!");

        } else {

            System.out.println("Operação cancelada.");
        }
    }

    // BUSCAR PROCEDIMENTO POR ID E PROFISSIONAL
    private Procedimento buscarPorId(
            int id,
            Profissional profissional) {

        for (Procedimento procedimento : procedimentos) {

            if (procedimento.getIdProcedimento() != null &&
                    procedimento.getIdProcedimento() == id &&
                    procedimento.getConsulta() != null &&
                    procedimento.getConsulta().getProfissional()
                            .equals(profissional)) {

                return procedimento;
            }
        }

        return null;
    }

    // EXIBIR PROCEDIMENTO
    public void exibirProcedimento(Procedimento procedimento) {

        System.out.println("\n------------------------------");

        System.out.println("ID: " +
                procedimento.getIdProcedimento());

        System.out.println("Nome: " +
                procedimento.getNome());

        System.out.println("Descrição: " +
                procedimento.getDescricao());

        System.out.printf("Valor: R$ %.2f%n",
                procedimento.getValor());

        System.out.println("Paciente: " +
                procedimento.getConsulta().getPaciente().getNome());

        System.out.println("Profissional: " +
                procedimento.getConsulta().getProfissional().getNome());

        System.out.println("Data da consulta: " +
                procedimento.getConsulta().getData());
    }

    public List<Procedimento> getProcedimentos() {
        return procedimentos;
    }
}
