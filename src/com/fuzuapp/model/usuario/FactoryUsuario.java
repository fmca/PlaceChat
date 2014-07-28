package com.fuzuapp.model.usuario;

import com.fuzuapp.model.usuario.IRepositorioUsuario;
import com.fuzuapp.model.usuario.RepositorioList;
import com.fuzuapp.model.usuario.RepositorioUsuarioHibernate;
import com.fuzuapp.util.Prop;

/**
 * Created by filipe on 27/07/14.
 */
public class FactoryUsuario {

    public static IRepositorioUsuario criarRepositorio(){
        String prop = new Prop().getPropriedade(Prop.TYPE);
        if(prop.equals(Prop.TYPE_LIST)){
            return new RepositorioList();
        }else if (prop.equals(Prop.TYPE_HIBERNATE)){
            return new RepositorioUsuarioHibernate();
        }else{
            return null;
        }
    }
}
