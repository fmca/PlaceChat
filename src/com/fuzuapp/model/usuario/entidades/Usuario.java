/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model.usuario.entidades;

/**
 *
 * @author Filipe_2
 */

public class Usuario {
    

    private int id;
    
    private Email email;
    

    private Login login;
    

    private Senha senha;
    

    private Nome nome;
    
    public Usuario(){}
    
    public Usuario(Email email, Login login, Senha senha, Nome nome){
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }

    /**
     * @return the email
     */
    public Email getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(Email email) {
        this.email = email;
    }

    /**
     * @return the login
     */
    public Login getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(Login login) {
        this.login = login;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(Senha senha) {
        this.senha = senha;
    }

    /**
     * @return the nome
     */
    public Nome getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(Nome nome) {
        this.nome = nome;
    }
    
    public boolean validar(String senha){
        return senha.equals(this.getSenha().toString());
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the senha
     */
    public Senha getSenha() {
        return senha;
    }
    
}
