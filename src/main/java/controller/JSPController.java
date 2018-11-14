/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedago
 */
public class JSPController extends HttpServlet {

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
        
        /*
        nb joueurs connectés -> nbPlayers
        
        nom joueur -> playerName (si nul, "Anonyme")
        action -> action
        proposition -> guess
        valeur à trouver -> value (à calculer qu'une fois)
        nombre d'essais -> nbGuesses (à incrémenter)
        
        meilleur score -> 
        */
        String action = request.getParameter("action");
        
        if(action == null){
            request.getRequestDispatcher("views/view.jsp").forward(request, response);
        }else{           
           switch(action){
                case "Connexion":{
                    String playerName = request.getParameter("playerName");
                    request.getSession(true).setAttribute("playerName", playerName);
                    request.setAttribute("nbGuesses", 0);
                    request.getSession(true).setAttribute("answer", new Random().nextInt(101));
                    request.getRequestDispatcher("views/jeu.jsp").forward(request, response);
                    break;
                }

                case "Deviner":
                    String guess = request.getParameter("guess");
                    System.out.println(guess);
                    request.getRequestDispatcher("views/jeu.jsp").forward(request, response);
                    break;
                case "Rejouer":
                    break;
                case "Deconnexion":{
                    request.getSession(true).invalidate();
                    request.getRequestDispatcher("views/view.jsp").forward(request, response);
                    break;
                }
            
            } 
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
        processRequest(request, response);
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
