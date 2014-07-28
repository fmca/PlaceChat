package com.fuzuapp.model.favoritos.entidades;

import com.fuzuapp.model.resultados.entidades.Resultado;
import com.fuzuapp.model.usuario.entidades.Usuario;

import javax.persistence.Entity;

/**
 * Created by filipe on 27/07/14.
 */
@Entity
public class Favorito{

    private int id;
    private Resultado resultado;
    private Usuario usuario;

    public Favorito(Resultado r, Usuario usuario){
        this.resultado = r;
        this.setUsuario(usuario);
    }

    public Favorito() {
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
