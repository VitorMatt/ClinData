package main.java.com.controller;
import java.time.LocalDate;
import java.util.Scanner;

import main.java.com.model.Profissional;

import java.util.ArrayList;
import java.util.List;

public class CadastroProfissional {

    List<Profissional> profissionais = new ArrayList<>();

    public void cadastrarProfissional() {

        Scanner read = new Scanner(System.in);

        Profissional profissional = new Profissional();

        System.out.print("Nome: ");
        String nome = read.nextLine().trim();
        profissional.setNome(nome);

        System.out.print("E-mail: ");
        String email = read.nextLine().trim();
        profissional.setEmail(email);

        System.out.print("Senha: ");
        String senha = read.nextLine().trim();
        profissional.setSenha(senha);

        System.out.print("CPF: ");
        String cpf = read.nextLine().trim();
        profissional.setCpf(cpf);

        System.out.print("Telefone: ");
        String telefone = read.nextLine().trim();
        profissional.setTelefone(telefone);

        System.out.print("Data de nascimento (AAAA-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(read.nextLine().trim());
        profissional.setDataNascimento(dataNascimento);

        System.out.print("Endereço: ");
        String endereco = read.nextLine().trim();
        profissional.setEndereco(endereco);

        System.out.print("Especialidade: ");
        String especialidade = read.nextLine().trim();
        profissional.setEspecialidade(especialidade);

        System.out.println("\nProfissional cadastrado!");
        System.out.println("Nome: " + profissional.getNome());
        System.out.println("E-mail: " + profissional.getEmail());
        System.out.println("CPF: " + profissional.getCpf());
        System.out.println("Especialidade: " + profissional.getEspecialidade());

        Usuarios usuario = new Usuarios();
        usuario.registrarProfissional(profissional);
    }

    public Profissional mostrarCadastroPorId(int idProfissional) {

        for (Profissional profissional : profissionais) {

            if (profissional.getIdProfissional() != null &&
                    profissional.getIdProfissional() == idProfissional) {

                exibirProfissional(profissional);
                return profissional;
            }
        }

        System.out.println("Profissional não encontrado.");
        return null;
    }

    public Profissional mostrarCadastroPorNome(String nome) {

        for (Profissional profissional : profissionais) {

            if (profissional.getNome() != null &&
                    profissional.getNome().equalsIgnoreCase(nome)) {

                exibirProfissional(profissional);
                return profissional;
            }
        }

        System.out.println("Profissional não encontrado.");
        return null;
    }

    public void exibirProfissional(Profissional profissional) {

        System.out.println("\n--- DADOS DO PROFISSIONAL ---");

        System.out.println("ID: " + profissional.getIdProfissional());
        System.out.println("Nome: " + profissional.getNome());
        System.out.println("E-mail: " + profissional.getEmail());
        System.out.println("CPF: " + profissional.getCpf());
        System.out.println("Telefone: " + profissional.getTelefone());
        System.out.println("Data de nascimento: " + profissional.getDataNascimento());
        System.out.println("Endereço: " + profissional.getEndereco());
        System.out.println("Especialidade: " + profissional.getEspecialidade());

        if (profissional.getUnidadeClinica() != null) {
            System.out.println("Unidade clínica: " +
                    profissional.getUnidadeClinica().getNome());
        } else {
            System.out.println("Unidade clínica: não cadastrada");
        }
    }
}