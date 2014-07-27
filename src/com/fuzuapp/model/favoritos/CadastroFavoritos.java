package com.fuzuapp.model.favoritos;

import com.fuzuapp.model.resultados.entidades.Resultado;
import com.fuzuapp.model.usuario.entidades.Usuario;

import java.util.List;

/**
 * Created by filipe on 27/07/14.
 */
public class CadastroFavoritos {

    IRepositorioFavoritos repositorioFavoritos;

    public CadastroFavoritos(IRepositorioFavoritos repositorioFavoritos){
        this.repositorioFavoritos = repositorioFavoritos;
    }

    public void inserir (Resultado fav, Usuario usuario){
        repositorioFavoritos.inserir(fav,usuario);
    }

    public void remover(Resultado fav, Usuario usuario){
        repositorioFavoritos.remover(fav,usuario);
    }

    public List<Resultado> verTodos(Usuario usuario) {
        return repositorioFavoritos.getTodos(usuario);
    }
}
