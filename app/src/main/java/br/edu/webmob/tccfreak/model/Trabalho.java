package br.edu.webmob.tccfreak.model;

/**
 * Created by rober on 29/09/2016.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Trabalho {
    @DatabaseField(generatedId = true)
    private Integer codigo;
    @DatabaseField
    private String titulo;
    @DatabaseField
    private String curso;

    public Trabalho() {

    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString(){
        return this.getTitulo();
    }

}
