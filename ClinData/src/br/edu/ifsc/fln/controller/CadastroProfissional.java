package br.edu.ifsc.fln.controller;

import java.util.Scanner;

import br.edu.ifsc.fln.dao.ProfissionalDAO;
import br.edu.ifsc.fln.model.Profissional;
import br.edu.ifsc.fln.util.Validator;

public class CadastroProfissional {

    private Usuarios usuarios;
    private Scanner read;

    public CadastroProfissional(Usuarios usuarios, Scanner read) {
        this.usuarios = usuarios;
        this.read = read;
    }


    public void cadastrarProfissional() {

        Profissional profissional = new Profissional();

        System.out.println("\nCADASTRO DE PROFISSIONAL\n");

        System.out.print("Nome: ");
        String nome = read.nextLine().trim();

        if (Validator.isEmpty(nome)) {
            System.out.println("O nome não pode ser vazio.");
            return;
        }

        profissional.setNome(nome);

        System.out.print("E-mail: ");
        String email = read.nextLine().trim();

        if (Validator.isEmpty(email) || !Validator.isEmail(email)) {
            System.out.println("Você deve informar um e-mail válido.");
            return;
        }

        profissional.setEmail(email);

        System.out.print("Senha: ");
        String senha = read.nextLine().trim();

        if (Validator.isEmpty(senha)) {
            System.out.println("A senha não pode ser vazia.");
            return;
        }

        profissional.setSenha(senha);

        System.out.print("CPF: ");
        String cpf = read.nextLine().trim();

        if (Validator.isEmpty(cpf) || !Validator.isCpfFormatValid(cpf)) {
            System.out.println("O CPF não pode ser vazio ou inválido.");
            return;
        }

        profissional.setCpf(cpf);

        System.out.print("Telefone: ");
        String telefone = read.nextLine().trim();

        if (Validator.isEmpty(telefone)
                || !Validator.isTelefoneFormatValid(telefone)) {
            System.out.println("Informe um telefone válido.");
            return;
        }

        profissional.setTelefone(telefone);

        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        String dataNascimento = read.nextLine().trim();

        if (!Validator.isDate(dataNascimento)) {
            System.out.println(
                    "Informe uma data válida no formato dd/MM/yyyy."
            );
            return;
        }

        profissional.setDataNascimento(dataNascimento);

        System.out.print("Endereço: ");
        String endereco = read.nextLine().trim();

        if (Validator.isEmpty(endereco)) {
            System.out.println("O endereço não pode ser vazio.");
            return;
        }

        profissional.setEndereco(endereco);

        System.out.print("Especialidade: ");
        String especialidade = read.nextLine().trim();

        if (Validator.isEmpty(especialidade)) {
            System.out.println("A especialidade não pode ser vazia.");
            return;
        }

        profissional.setEspecialidade(especialidade);

        if (usuarios.buscarPorEmail(profissional.getEmail()) != null
                || usuarios.buscarPorCPF(profissional.getCpf()) != null) {

            System.out.println(
                    "Já existe um usuário com esse e-mail ou CPF!"
            );
            return;
        }

        UnidadesClinicas unidadesClinicas = new UnidadesClinicas();

        System.out.println(
                "\nQual sua unidade de atuação?\n"
                        + unidadesClinicas.listarUnidades()
        );

        System.out.println("Informe o código da unidade: ");
        String entradaUnidade = read.nextLine().trim();

        if (!Validator.isInteger(entradaUnidade)) {
            System.out.println("Informe um código de unidade numérico válido.");
            return;
        }

        int codigoUnidade = Integer.parseInt(entradaUnidade);

        profissional.setUnidadeClinica(codigoUnidade);

        ProfissionalDAO insert = new ProfissionalDAO();

        insert.inserir(profissional);

        usuarios.registrarUsuario(profissional);

        System.out.println("\nProfissional cadastrado com sucesso!\n");

        exibirProfissional(profissional);
    }

    public void exibirProfissional(Profissional profissional) {

        System.out.println("\nDADOS DO PROFISSIONAL");
        System.out.println(profissional.toString());
    }
}