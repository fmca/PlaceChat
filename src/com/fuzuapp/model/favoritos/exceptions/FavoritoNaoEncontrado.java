package com.fuzuapp.model.favoritos.exceptions;

/**
 * Created by filipe on 27/07/14.
 */
public class FavoritoNaoEncontrado extends Exception {

    public FavoritoNaoEncontrado (){
        super("Favorito não existe");
    }
}
