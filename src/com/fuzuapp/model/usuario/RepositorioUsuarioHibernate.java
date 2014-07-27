/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuzuapp.model.usuario;

import com.fuzuapp.model.usuario.entidades.Login;
import com.fuzuapp.model.usuario.entidades.Usuario;
import com.fuzuapp.frameworks.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 *
 * @author Filipe_2
 */
public class RepositorioUsuarioHibernate implements IRepositorioUsuario {

    @Override
    public void inserir(Usuario usuario) {
        SessionFactory sessFact = HibernateUtil.getSessionFactory();
        Session session = sessFact.getCurrentSession();
        org.hibernate.Transaction tr = session.beginTransaction();
        session.save(usuario);
        tr.commit();

        sessFact.close();

    }

    @Override
    public void remover(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario get(Login login) {
        SessionFactory sessFact = HibernateUtil.getSessionFactory();
        Session session = sessFact.getCurrentSession();

        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        Example example = Example.create(usuario);
        
        Criteria criteria = session.createCriteria(Usuario.class).add(example);
        
        return  criteria.list().size()>0?(Usuario) criteria.list().get(0):null;
    }

}
