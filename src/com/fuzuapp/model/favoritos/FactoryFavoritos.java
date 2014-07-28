package com.fuzuapp.model.favoritos;

import com.fuzuapp.util.Prop;

/**
 * Created by filipe on 27/07/14.
 */
public class FactoryFavoritos {


    public static IRepositorioFavoritos criarRepositorio(){
        String prop = new Prop().getPropriedade(Prop.TYPE);
        if(prop.equals(Prop.TYPE_LIST)){
            return new RepositorioFavoritosList();
        }else if (prop.equals(Prop.TYPE_HIBERNATE)){
            return new RepositorioFavoritosHibernate();
        }else{
            return null;
        }
    }

}
