/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Score;

/**
 *
 * @author pedago
 */
public class JSPController extends HttpServlet {
    private Random r = new Random();
    private Score highScore;
    
    private int nbGuesses = 0;
    private int answer;
    
    
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
        
        String action = request.getParameter("action");
                
        if(action == null){
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }else{           
           switch(action){
                case "Connexion":{
                    String playerName = request.getParameter("playerName").trim();
                    
                    if(playerName.isEmpty()){
                        request.getSession(true).setAttribute("playerName", "Anonyme");
                    }else{
                        request.getSession(true).setAttribute("playerName", playerName);
                    }
                    
                    nbGuesses = 0;
                    answer = r.nextInt(101);
                    
                    request.getSession(true).setAttribute("nbGuesses", nbGuesses);
                    request.getSession(true).setAttribute("answer", answer);
                    
                    request.getRequestDispatcher("views/game.jsp").forward(request, response);
                    break;
                }

                case "Deviner":{
                    int guess = Integer.valueOf(request.getParameter("guess"));
                    
                    nbGuesses++;

                    request.setAttribute("guess", guess);
                    request.getSession(true).setAttribute("nbGuesses", nbGuesses);
                                        
                    if(guess == answer){
                        String playerName = (String)request.getSession(true).getAttribute("playerName");
                        
                        if(highScore == null || highScore.getNbGuesses()> nbGuesses){
                            highScore = new Score(playerName, nbGuesses);
                            
                            request.setAttribute("isNewRecord", true);
                            getServletContext().setAttribute("highScore", highScore);
                        }else{
                            request.setAttribute("isNewRecord", false);
                        }
                        
                        request.getRequestDispatcher("views/endGame.jsp").forward(request, response);
                    }else{
                        request.getRequestDispatcher("views/game.jsp").forward(request, response);
                    }
                    
                    break;
                }
                
                case "Rejouer":{
                    nbGuesses = 0;
                    request.getSession(true).setAttribute("nbGuesses", nbGuesses);
                    
                    answer = r.nextInt(101);
                    request.getSession(true).setAttribute("answer", answer);
                    
                    request.getRequestDispatcher("views/game.jsp").forward(request, response);
                    break;
                }
                
                case "Deconnexion":{                   
                    request.getSession(true).invalidate();
                    request.getRequestDispatcher("views/login.jsp").forward(request, response);
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
