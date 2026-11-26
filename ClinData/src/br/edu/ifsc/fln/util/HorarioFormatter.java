package br.edu.ifsc.fln.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HorarioFormatter {

    public HorarioFormatter () {
    }

    public LocalTime formatarHorario (String horario) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime HorarioFormatado = LocalTime.parse(horario, formato);

        return HorarioFormatado;
    }

    public String exibirHorario(LocalTime horario) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");

        String HorarioFormatado = horario.format(formato);

        return HorarioFormatado;
    }
}
