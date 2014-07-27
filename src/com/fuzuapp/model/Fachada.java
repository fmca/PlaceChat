/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model;

import com.fuzuapp.model.favoritos.CadastroFavoritos;
import com.fuzuapp.model.favoritos.ControladorFavoritos;
import com.fuzuapp.model.favoritos.RepositorioFavoritosList;
import com.fuzuapp.model.resultados.ControladorResultados;
import com.fuzuapp.model.resultados.entidades.GeoPoint;
import com.fuzuapp.model.resultados.entidades.Resultado;
import com.fuzuapp.model.usuario.ControladorUsuario;
import com.fuzuapp.model.usuario.RepositorioList;
import com.fuzuapp.model.usuario.RepositorioUsuarioHibernate;
import com.fuzuapp.model.usuario.entidades.Login;
import com.fuzuapp.model.usuario.entidades.Senha;
import com.fuzuapp.model.usuario.entidades.Usuario;
import com.fuzuapp.model.usuario.exceptions.AutenticacaoInvalida;

import java.util.ArrayList;
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
        this.controladorUsuario = new ControladorUsuario(new RepositorioList());
        this.controladorFavoritos = new ControladorFavoritos(new CadastroFavoritos(new RepositorioFavoritosList()));
    }
    
    public void logar(Login login, Senha senha) throws AutenticacaoInvalida{
        controladorUsuario.autenticar(login, senha);
    }
    public void cadastrar(Usuario usuario){
        controladorUsuario.cadastrar(usuario);
    }
    
    public List<Resultado> buscarResultados(GeoPoint geopoint, double raio){
        return controladorResultados.buscarResultados(geopoint, raio);
    }

    public void salvarFavorito(Resultado fav, Usuario usuario){
        this.controladorFavoritos.salvar(fav,usuario);
    }

    public List<Resultado> verFavoritos(Usuario usuario){
        return this.controladorFavoritos.getFavoritos(usuario);
    }
    
}
