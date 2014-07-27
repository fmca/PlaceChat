/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuzuapp.controller;

import com.fuzuapp.model.Fachada;
import com.fuzuapp.model.usuario.entidades.Login;
import com.fuzuapp.model.usuario.entidades.Nome;
import com.fuzuapp.model.usuario.entidades.Senha;
import com.fuzuapp.model.usuario.entidades.Usuario;
import com.fuzuapp.model.usuario.entidades.Email;
import com.fuzuapp.model.usuario.exceptions.EmailInvalidoException;
import com.fuzuapp.model.usuario.exceptions.LoginInvalidoException;
import com.fuzuapp.model.usuario.exceptions.NomeInvalidoException;
import com.fuzuapp.model.usuario.exceptions.SenhaInvalidaException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Filipe_2
 */
public class CadastroServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("TelaCadastro.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        cadastrar(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeErro = null, loginErro = null, emailErro = null, senhaErro = null;
        
        boolean cadastrado = false;
        try {
            Nome nome = new Nome(request.getParameter("nome"));
            Login login = new Login(request.getParameter("login"));
            Email email = new Email(request.getParameter("email"));
            Senha senha = new Senha(request.getParameter("senha"));
            
            Usuario usuario = new Usuario(email, login, senha, nome);
            
            Fachada fachada = Fachada.getInstance();
            fachada.cadastrar(usuario);
            cadastrado = true;
            
        }catch(NomeInvalidoException e){
            nomeErro = e.getMessage();
        }catch(LoginInvalidoException e2){
            loginErro = e2.getMessage();
        }catch(EmailInvalidoException e3){
            emailErro = e3.getMessage();
        }catch(SenhaInvalidaException e4){
            senhaErro = e4.getMessage();
        }

        
        if(cadastrado){
            response.sendRedirect("TelaLogin.jsp");
        }else{
            response.setContentType("text/html;charset=UTF-8");
            request.setAttribute("nome_erro", nomeErro);
            request.setAttribute("login_erro", loginErro);
            request.setAttribute("email_erro", emailErro);
            request.setAttribute("senha_erro", senhaErro);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("TelaCadastro.jsp");
            dispatcher.forward(request, response);
        }

            
            
            
        }
    }
