/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.controller;

import com.fuzuapp.model.Fachada;
import com.fuzuapp.model.resultados.entidades.Resultado;
import com.fuzuapp.model.usuario.entidades.Login;
import com.fuzuapp.model.usuario.entidades.Senha;
import com.fuzuapp.model.usuario.entidades.Usuario;
import com.fuzuapp.model.usuario.exceptions.AutenticacaoInvalida;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Filipe_2
 */
public class FavoritosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            autenticar(request);

            Fachada fachada = Fachada.getInstance();
            Usuario usuario = new Usuario();
            usuario.setLogin((Login) request.getSession().getAttribute("login"));
            usuario.setSenha((Senha) request.getSession().getAttribute("senha"));
            List<Resultado> favoritos = fachada.verFavoritos(usuario);

            request.setAttribute("favoritos", favoritos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("TelaFavoritos.jsp");
            dispatcher.forward(request, response);

        } catch (AutenticacaoInvalida autenticacaoInvalida) {
            request.getSession().setAttribute("caller", request.getRequestURL().toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("favoritos");
            dispatcher.forward(request, response);
        }


    }

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
        processRequest(request, response);
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


        Resultado r = new Resultado();
        r.setUrl(request.getParameter("url"));
        r.setDescricao(request.getParameter("descricao"));
        r.setNomeUsuario(request.getParameter("nome"));
        r.setFotoUrl(request.getParameter("fotourl"));
        r.setHorario(request.getParameter("horario"));
        r.setTipo(request.getParameter("tipo"));

        Usuario usuario = new Usuario();
        usuario.setLogin((Login) request.getSession().getAttribute("login"));
        usuario.setSenha((Senha) request.getSession().getAttribute("senha"));
        Fachada.getInstance().salvarFavorito(r,usuario);

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

    private void autenticar(HttpServletRequest request) throws AutenticacaoInvalida {

        Login login = (Login) request.getSession().getAttribute("login");
        Senha senha = (Senha) request.getSession().getAttribute("senha");

        Fachada.getInstance().logar(login,senha);
    }
}
