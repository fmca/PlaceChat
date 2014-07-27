package com.fuzuapp.model.favoritos;

import com.fuzuapp.model.resultados.entidades.Resultado;
import com.fuzuapp.model.usuario.entidades.Usuario;

import java.util.List;

/**
 * Created by filipe on 27/07/14.
 */
public interface IRepositorioFavoritos {

    public void inserir(Resultado favorito, Usuario usuario);

    public void remover(Resultado favorito, Usuario usuario);

    public List<Resultado> getTodos(Usuario usuario);
}
