/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model;

import com.fuzuapp.model.favoritos.*;
import com.fuzuapp.model.resultados.ControladorResultados;
import com.fuzuapp.model.resultados.entidades.GeoPoint;
import com.fuzuapp.model.resultados.entidades.Resultado;
import com.fuzuapp.model.usuario.ControladorUsuario;
import com.fuzuapp.model.usuario.entidades.Login;
import com.fuzuapp.model.usuario.entidades.Senha;
import com.fuzuapp.model.usuario.entidades.Usuario;
import com.fuzuapp.model.usuario.exceptions.AutenticacaoInvalida;
import com.fuzuapp.model.usuario.FactoryUsuario;
import com.fuzuapp.model.usuario.exceptions.UsuarioJaExisteException;

import java.util.List;

/**
 *
 * @author Filipe_2
 */
public class Fachada {
    private ControladorUsuario controladorUsuario;
    private ControladorResultados controladorResultados;
    private ControladorFavoritos controladorFavoritos;

    private static Fachada fachada = new Fachada();
    
    public static Fachada getInstance(){
        return fachada;     
    }
    
    private Fachada(){
        this.controladorResultados = new ControladorResultados();
        this.controladorUsuario = new ControladorUsuario(FactoryUsuario.criarRepositorio());
        this.controladorFavoritos = new ControladorFavoritos(new CadastroFavoritos(FactoryFavoritos.criarRepositorio()));
    }
    
    public void logar(Login login, Senha senha) throws AutenticacaoInvalida{
        controladorUsuario.autenticar(login, senha);
    }
    public void cadastrar(Usuario usuario) throws UsuarioJaExisteException {
        controladorUsuario.cadastrar(usuario);
    }
    
    public List<Resultado> buscarResultados(GeoPoint geopoint, double raio){
        return controladorResultados.buscarResultados(geopoint, raio);
    }

    public Usuario getUsuario(Login login){return this.controladorUsuario.buscarUsuario(login);}

    public void salvarFavorito(Resultado fav, Usuario usuario){
        this.controladorFavoritos.salvar(fav,usuario);
    }

    public List<Resultado> verFavoritos(Usuario usuario){
        return this.controladorFavoritos.getFavoritos(usuario);
    }
    
}
