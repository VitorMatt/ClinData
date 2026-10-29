package main.java.com.controller;
import java.util.Scanner;

public class Login {
    private Scanner scanner;

    public Login() {
        this.scanner = new Scanner(System.in);
    }

    public String mostrarMenuLogin() {
        return  "1. Entrar no sistema\n" +
                "2. Cadastrar Usuário\n" +
                "3. Sair";
    }

    public String menuResposta() {
        System.out.println(mostrarMenuLogin());
        System.out.print("Escolha uma opção: ");
        return scanner.nextLine();
    }
}
