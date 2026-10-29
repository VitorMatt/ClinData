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

    public String menuOpcoes() {
        System.out.println(mostrarMenuLogin());
        System.out.print("Escolha uma opção: ");
        return scanner.nextLine();
    }

    public void processarOpcao(String opcao) {
        switch (opcao) {
            case "1":
                System.out.println("Opção 1 selecionada: Entrar no sistema");
                entrarNoSistema();
                break;
            case "2":
                System.out.println("Opção 2 selecionada: Cadastrar Usuário");
                break;
            case "3":
                System.out.println("Saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
    
    public void entrarNoSistema() {
        System.out.print("Digite seu e-mail: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();
        System.out.println("Tentando entrar com e-mail: " + email);
    }
    
    public void encontrarUsuarioPorEmail(String email) {
        
        System.out.println("Procurando usuário com e-mail: " + email);
    }
}
