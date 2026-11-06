package main.java.com.util;

public class TelefoneFormatter {

    public TelefoneFormatter() {
    }

    public String formatarTelefone(String telefone) {

        return telefone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
    }
}