
package main.java.com.util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Validator {

    private Validator() {
    }

    public static boolean isEmpty(String valor) {
        return valor == null || valor.isBlank();
    }

    public static boolean isInteger(String valor) {
        if (isEmpty(valor)) return false;

        try {
            Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String valor) {
        if (isEmpty(valor)) return false;

        try {
            Double.parseDouble(valor);
            return Double.isFinite(Double.parseDouble(valor));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isBoolean(String valor) {
        return "true".equalsIgnoreCase(valor)
                || "false".equalsIgnoreCase(valor);
    }

    public static boolean isDate(String valor) {
        if (isEmpty(valor)) return false;

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/uuuu")
                        .withResolverStyle(
                                java.time.format.ResolverStyle.STRICT
                        );

        try {
            LocalDate.parse(valor, formato);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isEmail(String valor) {
        if (isEmpty(valor)) return false;

        String regex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";

        return Pattern.matches(regex, valor);
    }

    public static boolean isCpfFormatValid(String cpf) {
        if (isEmpty(cpf)) return false;

        String numeros = cpf.replaceAll("\\D", "");

        return numeros.matches("\\d{11}");
    }

    public static boolean isTelefoneFormatValid(String telefone) {
        if (isEmpty(telefone)) return false;

        String numeros = telefone.replaceAll("\\D", "");

        return numeros.matches("\\d{10,11}");
    }
}