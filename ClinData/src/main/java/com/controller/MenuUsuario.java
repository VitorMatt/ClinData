package main.java.com.controller;

public class MenuUsuario {

    public MenuUsuario() {
    }

    public String mostrarMenu() {
        return  "Bem-vindo ao sistema ClinData!\n" +
                "O que você deseja fazer?\n\n" +
                "Entrar no sistema\n" +
                "Cadastrar Usuário\n" +
                "Encontrar Usuário\n" +
                "Modificar usuário\n" +
                "Deletar usuário\n" +
                "Sair";
    }

    public String mostrarMenuAgendamento() {
        return  "1. Agendar consulta\n" +
                "2. Modificar dados do agendamento\n" +
                "3. Cancelar agendamento\n" +
                "4. Voltar";
    }

    public String mostrarMenuConsultas() {
        return  "1. Ver suas consultas\n" +
                "2. Voltar";
    }
}