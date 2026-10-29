package main.java.com.model;
import java.time.LocalDate;

public class Paciente extends Usuario{

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

    public LocalDate getDataNascimento() {
        return getDataNascimento();
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        setDataNascimento(dataNascimento);
    }

    public String getEmail() {
        return getEmail();
    }

    public void setEmail(String email) {
        setEmail(email);
    }

    public String getCpf() {
        return getCpf();
    }

    public void setCpf(String cpf) {
        setCpf(cpf);
    }

    public String getTelefone() {
        return getTelefone();
    }

    public void setTelefone(String telefone) {
        setTelefone(telefone);
    }

    public String getNome() {
        return getNome();
    }

    public void setNome(String nome) {
        setNome(nome);
    }

    public String getEndereco() {
        return getEndereco();
    }

    public void setEndereco(String endereco) {
        setEndereco(endereco);
    }

    public Boolean getPreferencial() {
        return preferencial;
    }

    public void setPreferencial(Boolean preferencial) {
        this.preferencial = preferencial;
    }
}