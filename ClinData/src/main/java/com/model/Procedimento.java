
package main.java.com.model;

public class Procedimento {

    private Integer idProcedimento;
    private String nome;
    private String descricao;
    private Double valor;

    private Consulta consulta;

    public Procedimento() {
    }

    public Procedimento(
            Integer idProcedimento,
            String nome,
            String descricao,
            Double valor,
            Consulta consulta) {

        this.idProcedimento = idProcedimento;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
