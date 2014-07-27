/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.controller;

import com.fuzuapp.model.Fachada;
import com.fuzuapp.model.resultados.entidades.GeoPoint;
import com.fuzuapp.model.resultados.entidades.Resultado;
import com.fuzuapp.model.usuario.entidades.Login;
import com.fuzuapp.model.usuario.entidades.Senha;
import com.fuzuapp.model.usuario.exceptions.AutenticacaoInvalida;

import java.io.IOException;
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
public class ResultadosServlet extends HttpServlet {


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
        pesquisar(request, response);
        
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
       
        pesquisar(request, response);
    }
    
    private void pesquisar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {
            autenticar(request);

            Fachada fachada = Fachada.getInstance();
            String latStr = request.getParameter("lat"); latStr = latStr==null || latStr.isEmpty() ?"-22.912214":latStr;
            String lonStr = request.getParameter("lon"); lonStr = lonStr==null || lonStr.isEmpty() ?"-43.230182":lonStr;
            String rStr = request.getParameter("raio"); rStr = rStr==null || rStr.trim().length()<1?"1":rStr;

            double latitude = Double.valueOf(latStr);
            double longitude = Double.valueOf(lonStr);
            double raio = Double.valueOf(rStr);

            GeoPoint ponto = new GeoPoint(latitude, longitude);
            List<Resultado> resultados = fachada.buscarResultados(ponto, raio);
            request.setAttribute("resultados", resultados);
            request.setAttribute("latitude", ponto.getLatitude());
            request.setAttribute("longitude", ponto.getLongitude());
            request.setAttribute("raio", raio*1000);
            RequestDispatcher dispatcher = request.getRequestDispatcher("TelaResultados.jsp");
            dispatcher.forward(request, response);

        }catch (AutenticacaoInvalida autenticacaoInvalida){
            request.getSession().setAttribute("caller", request.getRequestURL().toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("login");
            dispatcher.forward(request, response);
        }

    }

    private void autenticar(HttpServletRequest request) throws AutenticacaoInvalida {
        Login login = (Login) request.getSession().getAttribute("login");
        Senha senha = (Senha) request.getSession().getAttribute("senha");

        Fachada.getInstance().logar(login,senha);
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

}
