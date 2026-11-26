package br.edu.ifsc.fln.util;

public class CpfFormatter {

    public CpfFormatter() {
    }

    public String formatarCpf(String cpf) {

        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}
