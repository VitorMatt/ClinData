
package br.edu.ifsc.fln.model;

public class Procedimento {

    private Integer idProcedimento;
    private String nome;
    private String descricao;
    private String orientacoes;

    private Consulta consulta;

    public Procedimento() {
    }

    public Procedimento(
            Integer idProcedimento,
            String nome,
            String descricao,
            String orientacoes,
            Consulta consulta) {

        this.idProcedimento = idProcedimento;
        this.nome = nome;
        this.descricao = descricao;
        this.orientacoes = orientacoes;
        this.consulta = consulta;
    }

    public Integer getIdProcedimento() {
        return idProcedimento;
    }

    public void setIdProcedimento(Integer idProcedimento) {
        this.idProcedimento = idProcedimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOrientacoes() {
        return orientacoes;
    }

    public void setOrientacoes(String orientacoes) {
        this.orientacoes = orientacoes;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    @Override
    public String toString() {
        return "\nId: " + idProcedimento +
                "\nNome: " + nome +
                "\nDescrição: " + descricao +
                "\nOrientações: " + orientacoes +
                "\nConsulta referente: " +
                (consulta != null ? consulta.toString() : "Não vinculada");
    }

}