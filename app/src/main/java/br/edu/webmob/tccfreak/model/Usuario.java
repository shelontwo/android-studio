package br.edu.webmob.tccfreak.model;

/**
 * Classe que representa um Usuario.
 *
 * @since 24/09/2016
 */

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "usuario")
public class Usuario implements Serializable {
    @DatabaseField(generatedId = true)
    private Integer codigo;
    @DatabaseField(canBeNull = false)
    private String nome;
    @DatabaseField(canBeNull = false, unique = true)
    private String login;
    @DatabaseField(canBeNull = false)
    private String senha;
    @DatabaseField(canBeNull = false)
    private String email;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private byte[] foto;

    public Usuario() {

    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
