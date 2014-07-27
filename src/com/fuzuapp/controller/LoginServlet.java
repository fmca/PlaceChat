/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.controller;

import com.fuzuapp.model.Fachada;
import com.fuzuapp.model.usuario.entidades.Login;
import com.fuzuapp.model.usuario.entidades.Senha;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Filipe_2
 */
public class LoginServlet extends HttpServlet {


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
        RequestDispatcher dispatcher = request.getRequestDispatcher("TelaLogin.jsp");
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
        logar(request, response);
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

    private void logar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        boolean logado = false;
        boolean credenciais = false;
        try{
        Login login = new Login(request.getParameter("login"));
        Senha senha = new Senha(request.getParameter("senha"));       
        credenciais = true;
        Fachada fachada = Fachada.getInstance();       
        fachada.logar(login, senha);
        
        logado = true;
        
        
        }catch(Exception e){
                request.setAttribute("erro", e.getMessage());
        }
        
        if(logado){
            response.sendRedirect("resultados");
        }else{ 
            RequestDispatcher dispatcher = request.getRequestDispatcher("TelaLogin.jsp");
            dispatcher.forward(request, response);
        }
        
        
        
    }

}
