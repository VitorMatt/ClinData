package main.java.com.util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    public DateFormatter () {
    }

    public LocalDate formatarData (String data) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataFormatada = LocalDate.parse(data, formato);

        return dataFormatada;
    }

    public String exibirData(LocalDate data) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dataFormatada = data.format(formato);

        return dataFormatada;
    }
}