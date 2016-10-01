package br.edu.webmob.tccfreak.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rober on 29/09/2016.
 */
@DatabaseTable
public class Frequencia {
    @DatabaseField(generatedId = true)
    private Integer codigo;
    @DatabaseField(canBeNull = false)
    private Date data;
    @DatabaseField(foreign = true, canBeNull = false, foreignColumnName = "codigo"
            , foreignAutoRefresh = true, columnName = "codigo_trabalho")
    private Trabalho trabalho;
    @DatabaseField
    private String aluno;
    @DatabaseField
    private String assinatura;

    public Frequencia() {

    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Trabalho getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(Trabalho trabalho) {
        this.trabalho = trabalho;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }

    public String toString(){
        return getData() + " - "
                + getTrabalho().getTitulo()+ " - "
                + getAluno();
    }
}
