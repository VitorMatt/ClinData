
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.Paciente;
import br.edu.ifsc.fln.util.Validator;
import java.util.Scanner;

public class CadastroPaciente {

    private Usuarios usuarios;
    private Scanner read;

    public CadastroPaciente(Usuarios usuarios, Scanner read) {
        this.usuarios = usuarios;
        this.read = read;
    }


    public void cadastrarPaciente() {

        Paciente paciente = new Paciente();

        System.out.println("\nCADASTRO DE PACIENTE\n");

        System.out.print("Nome: ");
        String nome = read.nextLine().trim();

        if (Validator.isEmpty(nome)) {
            System.out.println("O nome não pode ser vazio.");
            return;
        }

        paciente.setNome(nome);

        System.out.print("E-mail: ");
        String email = read.nextLine().trim();

        if (Validator.isEmpty(email) || !Validator.isEmail(email)) {
            System.out.println("Você deve informar um e-mail válido.");
            return;
        }

        paciente.setEmail(email);

        System.out.print("Senha: ");
        String senha = read.nextLine().trim();

        if (Validator.isEmpty(senha)) {
            System.out.println("A senha não pode ser vazia.");
            return;
        }

        paciente.setSenha(senha);

        System.out.print("CPF: ");
        String cpf = read.nextLine().trim();

        if (Validator.isEmpty(cpf) || !Validator.isCpfFormatValid(cpf)) {
            System.out.println("O CPF não pode ser vazio ou inválido.");
            return;
        }

        paciente.setCpf(cpf);

        System.out.print("Telefone: ");
        String telefone = read.nextLine().trim();

        if (Validator.isEmpty(telefone)
                || !Validator.isTelefoneFormatValid(telefone)) {
            System.out.println("Informe um telefone válido.");
            return;
        }

        paciente.setTelefone(telefone);

        System.out.print("Data de nascimento (dd/mm/aaaa): ");
        String dataNascimento = read.nextLine().trim();

        if (!Validator.isDate(dataNascimento)) {
            System.out.println("Informe uma data válida no formato dd/mm/aaaa.");
            return;
        }

        paciente.setDataNascimento(dataNascimento);

        System.out.print("Endereço: ");
        String endereco = read.nextLine().trim();

        if (Validator.isEmpty(endereco)) {
            System.out.println("O endereço não pode ser vazio.");
            return;
        }

        paciente.setEndereco(endereco);

        System.out.print("É preferencial? (S/N): ");
        String resposta = read.nextLine().trim();

        if (!resposta.equalsIgnoreCase("S")
                && !resposta.equalsIgnoreCase("N")) {
            System.out.println("Informe S para sim ou N para não.");
            return;
        }

        paciente.setPreferencial(resposta.equalsIgnoreCase("S"));

        if (usuarios.buscarPorEmail(paciente.getEmail()) != null
                || usuarios.buscarPorCPF(paciente.getCpf()) != null) {

            System.out.println(
                    "Já existe um usuário com esse e-mail ou CPF!"
            );
            return;
        }

        usuarios.registrarUsuario(paciente);

        System.out.println("\nPaciente cadastrado com sucesso!\n");

        exibirPaciente(paciente);
    }


    public void exibirPaciente(Paciente paciente) {

        System.out.println("\nDADOS DO PACIENTE");

        System.out.println(paciente.toString());
    }

    public void exibirProntuario(Paciente paciente) {

        System.out.println("\nDADOS DO PACIENTE");

        System.out.println(paciente.toString());
    }
}
