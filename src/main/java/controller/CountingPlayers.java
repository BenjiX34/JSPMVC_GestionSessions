package controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author bbardy
 */
public class CountingPlayers implements HttpSessionListener{
    
    private int nbPlayers = 0;
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        nbPlayers++;
        
        se.getSession().getServletContext().setAttribute("nbPlayers", nbPlayers);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        nbPlayers--;
        
        se.getSession().getServletContext().setAttribute("nbPlayers", nbPlayers);
    }
    
}
