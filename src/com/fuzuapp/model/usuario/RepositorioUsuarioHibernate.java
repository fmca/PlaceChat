/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuzuapp.model.usuario;

import com.fuzuapp.model.usuario.entidades.Login;
import com.fuzuapp.model.usuario.entidades.Usuario;
import com.fuzuapp.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 *
 * @author Filipe_2
 */
public class RepositorioUsuarioHibernate implements IRepositorioUsuario {

    SessionFactory sessFact = HibernateUtil.getSessionFactory();
    Session session;

    @Override
    public void inserir(Usuario usuario){
        session = sessFact.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();
        session.save(usuario);
        tr.commit();

        session.close();

    }

    @Override
    public void remover(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario get(Login login) {

        session = sessFact.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        Example example = Example.create(usuario);
        
        Criteria criteria = session.createCriteria(Usuario.class).add(example);
        
        Usuario resp =  criteria.list().size()>0?(Usuario) criteria.list().get(0):null;

        tr.commit();
        session.close();

        return resp;
    }

}
