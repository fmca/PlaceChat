package com.fuzuapp.model.favoritos;

import com.fuzuapp.model.resultados.entidades.Resultado;
import com.fuzuapp.model.usuario.entidades.Usuario;

import java.util.List;

/**
 * Created by filipe on 27/07/14.
 */
public class ControladorFavoritos {

    private final CadastroFavoritos cadastroFavoritos;


    public ControladorFavoritos(CadastroFavoritos cadastroFavoritos){
       this.cadastroFavoritos = cadastroFavoritos;
   }

    public void salvar(Resultado favorito, Usuario usuario){
        this.cadastroFavoritos.inserir(favorito,usuario);
    }

    public List<Resultado> getFavoritos(Usuario usuario){
        return this.cadastroFavoritos.verTodos(usuario);
    }

}
