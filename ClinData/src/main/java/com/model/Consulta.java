
package main.java.com.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {

    private Integer idConsulta;
    private String tipo;
    private LocalDate data;
    private LocalTime horario;
    private LocalTime duracao;
    private boolean realizada;

    private Paciente paciente;
    private Profissional profissional;

    public Consulta() {
    }

    public Consulta(
            Integer idConsulta,
            String tipo,
            LocalDate data,
            LocalTime horario,
            LocalTime duracao,
            boolean realizada,
            Paciente paciente,
            Profissional profissional) {

        this.idConsulta = idConsulta;
        this.tipo = tipo;
        this.data = data;
        this.horario = horario;
        this.duracao = duracao;
        this.realizada = realizada;
        this.paciente = paciente;
        this.profissional = profissional;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public LocalTime getDuracao() {
        return duracao;
    }

    public void setDuracao(LocalTime duracao) {
        this.duracao = duracao;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }
}
