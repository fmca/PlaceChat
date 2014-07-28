/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model.resultados.entidades;

import javax.persistence.Entity;

/**
 *
 * @author Filipe_2
 */
@Entity
public class Resultado {
    
    public static final String TEXTO = "texto";
    public static final String IMAGEM = "imagem";
    public static final String VIDEO = "video";

    private  int id;
    private String url;
    private String descricao;
    private String tipo;
    private GeoPoint local;
    private String horario;
    private String fotoUrl;
    private String nomeUsuario;
    
    public Resultado(){
        
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the local
     */
    public GeoPoint getLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(GeoPoint local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return getNomeUsuario() + ", " + getDescricao();
    }

    /**
     * @return the horario
     */
    public String getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }


    public String getFotoUrl(){
        return this.fotoUrl;
    }
    public void setFotoUrl(String miniProfileImageURL) {
        this.fotoUrl = miniProfileImageURL;
    }

    public void setNomeUsuario(String name) {
        this.nomeUsuario = name;
    }

    /**
     * @return the nomeUsuario
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
