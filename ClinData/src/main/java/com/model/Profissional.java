package main.java.com.model;

public class Profissional extends Usuario {

    private Integer idProfissional;
    private String especialidade;
    private UnidadeClinica unidadeClinica;

    public Profissional() {
    }

    public Integer getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(Integer idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public UnidadeClinica getUnidadeClinica() {
        return unidadeClinica;
    }

    public void setUnidadeClinica(UnidadeClinica unidadeClinica) {
        this.unidadeClinica = unidadeClinica;
    }
}