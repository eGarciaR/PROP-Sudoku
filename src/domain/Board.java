package domain;

public class Board {
    
    /**
     * L'identificador del tauler.
     */
    private int id;
    /**
     * El tamany del tauler.
     */
    private int n;

    /**
     * Constructora per defecte.
     */
    public Board(){
        
    }
    
    /**
     * Constructora amb id i tamany N.
     * Pre: n &gt; 0
     * @param id Identificador del tauler.
     * @param n Tamany del tauler.
     */
    public Board(int id, int n) {
        this.id = id;
        this.n = n;
    }

    /**
     * Getter de l'atribut id.
     * @return Identificador del tauler.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Setter de l'atribut id.
     * @param id Identificador del tauler.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter de l'atribut n.
     * @return El tamany del tauler.
     */
    public int getN() {
        return n;
    }
    
    /**
     * Setter de l'atribut n.
     * @param n Tamany del tauler.
     * @return True si es canvia el valor correctament
     */
    public boolean setN(int n) {
        this.n = n;
        return true;
    }

}
