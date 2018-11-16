package model;

/**
 * Classe pour représenter le score obtenu
 * @author bbardy
 */
public class Score {
    private final String playerName;
    private final int nbGuesses;
    
    /**
     * Constructeur
     * @param playerName le nom du joueur
     * @param nbGuesses le nombre d'essais
     */
    public Score(String playerName, int nbGuesses){
        this.playerName = playerName;
        this.nbGuesses = nbGuesses;
    }
    
    /**
     * @return le nom du joueur
     */
    public String getPlayerName(){
        return playerName;
    }
    
    /**
     * @return le nombre d'essais du joueur
     */
    public int getNbGuesses(){
        return nbGuesses;
    }
    
}
