package main.java.com.model;
import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {

    private Integer idConsulta;
    private String tipo;
    private LocalDate data;
    private LocalTime horario;
    private LocalTime duracao;
    private String realizada;

    private UnidadeClinica unidadeClinica;
    private Paciente paciente;
    private Profissional profissional;

    public Consulta() {
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

    public String getRealizada() {
        return realizada;
    }

    public void setRealizada(String realizada) {
        this.realizada = realizada;
    }

    public UnidadeClinica getUnidadeClinica() {
        return unidadeClinica;
    }

    public void setUnidadeClinica(UnidadeClinica unidadeClinica) {
        this.unidadeClinica = unidadeClinica;
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