package main.java.com.model;
import main.java.com.controller.UnidadesClinicas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AgendamentoPagamento {

    private Integer idAgendamento;
    private LocalTime horario;
    private LocalDate data;
    private Status status;
    private LocalDateTime dataHoraPagamento;
    private String formaPagamento;
    private Double valor = 50.00;

    private UnidadeClinica unidadeClinica;
    private Paciente paciente;
    private Profissional profissional;

    public AgendamentoPagamento() {
    }

    public Integer getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Integer idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDataHoraPagamento() {
        return dataHoraPagamento;
    }

    public void setDataHoraPagamento(LocalDateTime dataHoraPagamento) {
        this.dataHoraPagamento = dataHoraPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public UnidadeClinica getUnidadeClinica() {
        return unidadeClinica;
    }

    public void setUnidadeClinica(int id) {

        this.unidadeClinica = new UnidadesClinicas().getUnidade(id);
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


    @Override
    public String toString() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return "\nId: " + idAgendamento +
                "\nHorário: " + (horario != null ? horario.format(formatoHora) : "Não informado") +
                "\nData: " + (data != null ? data.format(formatoData) : "Não informada") +
                "\nStatus: " + status +
                "\nData e hora do pagamento: " +
                (dataHoraPagamento != null ? dataHoraPagamento.format(formatoDataHora) : "Não realizado") +
                "\nForma de pagamento: " + (formaPagamento != null ? formaPagamento : "Não informada") +
                "\nValor: " + valor +
                "\nUnidade Clínica: " + (unidadeClinica != null
                ? unidadeClinica.getIdUnidade() + " - " +
                unidadeClinica.getNome() + " (" + unidadeClinica.getEstado() + ")"
                : "Não informada") +
                "\nPaciente: " + (paciente != null ? paciente.getNome() : "Não informado") +
                "\nProfissional: " + (profissional != null ? profissional.getNome() : "Não informado");
    }
}
