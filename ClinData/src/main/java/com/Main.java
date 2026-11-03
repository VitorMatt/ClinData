
package main.java.com;

import main.java.com.controller.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);

        Usuarios usuarios = new Usuarios();

        CadastroProfissional cadastroProfissional =
                new CadastroProfissional(usuarios, read);

        CadastroPaciente cadastroPaciente =
                new CadastroPaciente(usuarios, read);

        GerenciadorAgendamento gerenciadorAgendamento =
                new GerenciadorAgendamento(usuarios, read);

        GerenciadorConsulta gerenciadorConsulta =
                new GerenciadorConsulta(
                        gerenciadorAgendamento,
                        read
                );

        GerenciadorProcedimento gerenciadorProcedimento =
                new GerenciadorProcedimento(
                        read,
                        gerenciadorConsulta
                );

        MenuUsuario menu = new MenuUsuario(
                usuarios,
                read,
                cadastroProfissional,
                cadastroPaciente,
                gerenciadorAgendamento,
                gerenciadorConsulta,
                gerenciadorProcedimento
        );

        menu.inicializarMenu();
        read.close();
    }
}