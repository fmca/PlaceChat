/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model.usuario;

import com.fuzuapp.model.usuario.entidades.Login;
import com.fuzuapp.model.usuario.entidades.Senha;
import com.fuzuapp.model.usuario.entidades.Usuario;
import com.fuzuapp.model.usuario.exceptions.AutenticacaoInvalida;

/**
 *
 * @author Filipe_2
 */
public class CadastroUsuario {
   private IRepositorioUsuario repositorioUsuario;
    
    
    public CadastroUsuario(IRepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }
    public Usuario getUsuario(Login login){
        return repositorioUsuario.get(login);
    }
    
    public void autenticar (Login login, Senha senha) throws AutenticacaoInvalida{
        
        Usuario u = getUsuario(login);
        
        if (u == null){
             throw new AutenticacaoInvalida("Usuário não existe");
        }
        if (! u.validar(senha.toString())){
            throw new AutenticacaoInvalida("Senha inválida");
        }
        
    }
    
    public void inserirUsuario(Usuario usuario){
        this.repositorioUsuario.inserir(usuario);
    }
    
    public void remover(Usuario usuario){
       this.repositorioUsuario.remover(usuario);
    }
}
