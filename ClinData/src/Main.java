import java.util.Scanner;
import java.util.List;
import static java.lang.String.valueOf;

public class Main {
    public static void main(String[] args) throws Exception {


        CadastroProfissional cadastroProfissional = new CadastroProfissional();

        cadastroProfissional.cadastrarProfissional();

        System.out.println(cadastroProfissional.mostrarCadastroPorNome("Francine"));
    }
}