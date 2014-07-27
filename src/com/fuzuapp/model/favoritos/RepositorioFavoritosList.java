package com.fuzuapp.model.favoritos;

import com.fuzuapp.model.resultados.entidades.Resultado;
import com.fuzuapp.model.usuario.entidades.Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by filipe on 27/07/14.
 */
public class RepositorioFavoritosList implements IRepositorioFavoritos{

    private Map<String,List<Resultado>> favoritos = new HashMap<>();

    @Override
    public void inserir(Resultado favorito, Usuario usuario) {
        List<Resultado> favs = favoritos.get(usuario.getLogin().toString());
        favs.add(favorito);
        favoritos.put(usuario.getLogin().toString(), favs);
    }

    @Override
    public void remover(Resultado favorito, Usuario usuario) {
        List<Resultado> favs = favoritos.get(usuario.getLogin().toString());
        for (int i = 0; i < favs.size(); i++) {
            Resultado fav = favs.get(i);
            if (favorito.getUrl().equals(favorito.getUrl())){
                favs.remove(i);
            }
        }
        favoritos.put(usuario.getLogin().toString(), favs);
    }

    @Override
    public List<Resultado> getTodos(Usuario usuario) {
        return favoritos.get(usuario.getLogin().toString());
    }
}
