package com.fuzuapp.model.favoritos;

import com.fuzuapp.util.HibernateUtil;
import com.fuzuapp.model.favoritos.entidades.Favorito;
import com.fuzuapp.model.resultados.entidades.Resultado;
import com.fuzuapp.model.usuario.entidades.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filipe on 27/07/14.
 */
public class RepositorioFavoritosHibernate implements IRepositorioFavoritos {

    SessionFactory sessFact = HibernateUtil.getSessionFactory();
    Session session;

    @Override
    public void inserir(Resultado favorito, Usuario usuario) {
        session = sessFact.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();
        session.save(new Favorito(favorito,usuario));
        tr.commit();

        session.close();
    }

    @Override
    public void remover(Resultado favorito, Usuario usuario) {

    }

    @Override
    public List<Resultado> getTodos(Usuario usuario) {
        session = sessFact.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();
        Favorito favorito = new Favorito();
        favorito.setUsuario(usuario);
        Example example = Example.create(favorito);

        Criteria criteria = session.createCriteria(Favorito.class).add(example);

        List<Favorito> favs =  criteria.list();
        List<Resultado> resp = new ArrayList<Resultado>();

        for(Favorito fav : favs){
            resp.add(fav.getResultado());
        }

        tr.commit();
        session.close();

        return resp;
    }
}
