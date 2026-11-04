package main.java.com.model;

import main.java.com.controller.UnidadesClinicas;

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

    public void setUnidadeClinica(int id) {

        this.unidadeClinica = new UnidadesClinicas().getUnidade(id);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nEspecialidade: " + especialidade +
                "\nUnidade Clínica: " + unidadeClinica.getIdUnidade() + " - " +
                unidadeClinica.getNome() + " (" + unidadeClinica.getEstado() + ")";
    }
}