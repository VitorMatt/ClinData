
package br.edu.ifsc.fln.model;

import br.edu.ifsc.fln.controller.UnidadesClinicas;
import br.edu.ifsc.fln.util.DateFormatter;
import br.edu.ifsc.fln.util.HorarioFormatter;

import java.time.LocalDate;
import java.time.Duration;
import java.time.LocalTime;

public class Consulta {

    private Integer idConsulta;
    private String tipo;
    private LocalDate data;
    private LocalTime horario;
    private Duration duracao;
    private boolean realizada;

    private Paciente paciente;
    private Profissional profissional;
    private UnidadeClinica unidadeClinica;

    public Consulta() {
    }

    public Consulta(
            Integer idConsulta,
            String tipo,
            LocalDate data,
            LocalTime horario,
            Duration duracao,
            boolean realizada,
            Paciente paciente,
            Profissional profissional,
            UnidadeClinica unidadeClinica) {

        this.idConsulta = idConsulta;
        this.tipo = tipo;
        this.data = data;
        this.horario = horario;
        this.duracao = duracao;
        this.realizada = realizada;
        this.paciente = paciente;
        this.profissional = profissional;
        this.unidadeClinica = unidadeClinica;
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

    public String getData() {

        DateFormatter formatador = new DateFormatter();

        return formatador.exibirData(this.data);
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHorario() {

        HorarioFormatter formatador = new HorarioFormatter();
        return formatador.exibirHorario(this.horario);
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public Duration getDuracao() {
        return this.duracao;
    }

    public void setDuracao(Duration duracao) {
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

    public void setUnidadeClinica(int id) { this.unidadeClinica = new UnidadesClinicas().getUnidade(id); }

    public UnidadeClinica getUnidadeClinica() {
        return this.unidadeClinica;
    }

    @Override
    public String toString() {
        return  "\nId: " + idConsulta +
                "\nTipo: " + tipo +
                "\nData: " + getData() +
                "\nHorário: " + getHorario() +
                "\nDuração: " + duracao.toMinutes() +
                "\nRealizada? " + (realizada ? "Sim" : "Não") +
                "\nPaciente: " + paciente.getNome() +
                "\nProfissional: " + profissional.getNome() +
                "\nUnidade Clínica: " + unidadeClinica.getIdUnidade() + " - " +
                unidadeClinica.getNome() + " (" + unidadeClinica.getEstado() + ")";
    }
}
