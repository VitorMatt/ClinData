package main.java.com.model;

import java.time.LocalDate;

public class Paciente extends Usuario {

    private Integer idPaciente;
    private String relatorioMedico;
    private String alergias;
    private String medicamentosEmUso;
    private String observacoes;
    private Boolean preferencial;

    public Paciente() {
    }

    public Paciente(Integer idPaciente, String nome, String cpf,
                    LocalDate dataNascimento, String telefone,
                    String email, String endereco, Boolean preferencial) {

        this.idPaciente = idPaciente;
        this.preferencial = preferencial;

        setNome(nome);
        setCpf(cpf);
        setDataNascimento(dataNascimento);
        setTelefone(telefone);
        setEmail(email);
        setEndereco(endereco);
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getRelatorioMedico() {
        return relatorioMedico;
    }

    public void setRelatorioMedico(String relatorioMedico) {
        this.relatorioMedico = relatorioMedico;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getMedicamentosEmUso() {
        return medicamentosEmUso;
    }

    public void setMedicamentosEmUso(String medicamentosEmUso) {
        this.medicamentosEmUso = medicamentosEmUso;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Boolean getPreferencial() {
        return preferencial;
    }

    public void setPreferencial(Boolean preferencial) {
        this.preferencial = preferencial;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nRelatorio Médico: " + (relatorioMedico != null ? relatorioMedico : " ") +
                "\nAlergias: " + (alergias != null ? alergias : " ")  +
                "\nMedicamentos Em Uso: " + (medicamentosEmUso != null ? medicamentosEmUso : " ") +
                "\nObservacoes: " + (observacoes != null ? observacoes : " ") +
                "\nPreferencial: " + (preferencial ? "Sim" : "Não");
    }
}