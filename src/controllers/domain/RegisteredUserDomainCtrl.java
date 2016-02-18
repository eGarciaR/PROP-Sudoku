/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.domain;

import domain.RegisteredUser;
import java.util.HashMap;
import persistence.RegisteredUserManager;

/**
 *
 * @author eric
 */
public class RegisteredUserDomainCtrl {

    public static final String ATTR_USER = "user";
    public static final String ATTR_GAMES_PLAYED = "games_played";
    public static final String ATTR_GAMES_WON = "games_won";
    public static final String ATTR_TOTAL_TIME = "total_time";
    public static final String ATTR_TOTAL_SCORE = "total_score";
    public static final String ATTR_BEST_TIME = "best_time";
    public static final String ATTR_BOARDS_CREATED = "boards_created";

    private static RegisteredUserDomainCtrl registeredUserDomainCtrl;
    private final RegisteredUserManager registeredUserManager;

    /**
     * Constructor per defecte privat.
     */
    private RegisteredUserDomainCtrl(String gameName) {
        this.registeredUserManager = RegisteredUserManager.getInstance(gameName);
    }

    /**
     * Actua com a constructor de la classe.
     *
     * @param gameName Nom del Joc
     * @return La única instància de la propia classe.
     */
    public static RegisteredUserDomainCtrl getInstance(String gameName) {
        if (registeredUserDomainCtrl == null) {
            registeredUserDomainCtrl = new RegisteredUserDomainCtrl(gameName);
        }
        return registeredUserDomainCtrl;
    }

    /**
     * @pre user existeix
     * @post actualitzats els valors de l'usuari.
     * @param user Usuari
     */
    public void incGamesPlayed(String user) {
        RegisteredUser registeredUser = getRegisteredUser(user);
        registeredUser.incGamesPlayed();
        this.registeredUserManager.incGamesPlayed(user);
    }

    /**
     * @pre user existeix
     * @post actualitzats els valors de l'usuari.
     * @param user Usuari
     */
    public void incBoardsCreated(String user) {
        RegisteredUser registeredUser = getRegisteredUser(user);
        registeredUser.incBoardsCreated();
        this.registeredUserManager.incBoardsCreated(user);
    }

    /**
     * @pre user existeix
     * @post actualitzats els valors de l'usuari.
     *
     * @param user Usuari
     * @param score Puntuacio
     * @param time Temps
     */
    public void winGame(String user, int score, long time) {
        RegisteredUser registeredUser = getRegisteredUser(user);
        registeredUser.winGame(score, time);
        this.registeredUserManager.update(user, registeredUser.getAttrs());
    }

    /**
     * Carrega l'usuari registrat de la base de dades.
     *
     * @param user Usuari
     * @return Instància de l'usuari registrat.
     */
    public RegisteredUser getRegisteredUser(String user) {
        HashMap<String, Object> userFromHashMap = this.registeredUserManager.get(user);
        return loadRegisteredUser(userFromHashMap);
    }

    /**
     * Carrega un usuari registrat amb un map d'atributs.
     *
     * @param userFromHashMap Atributs de l'usuari.
     * @return Instància de l'usuari registrat
     */
    public RegisteredUser loadRegisteredUser(HashMap<String, Object> userFromHashMap) {
        RegisteredUser user = new RegisteredUser((String) userFromHashMap.get(ATTR_USER));
        user.load(userFromHashMap);
        return user;
    }

    /**
     * Inserta un usuari registrat a la BD.
     *
     * @param user Usuari
     * @return True si s'ha afegit, False altrament.
     */
    public boolean addUser(String user) {
        return this.registeredUserManager.add(user);

    }

    /**
     * Elimina un usuari registrat de la Base de Dades.
     *
     * @param user Usuari
     * @return True si s'ha eliminat, False altrament.
     */
    public boolean deleteUser(String user) {
        return this.registeredUserManager.delete(user);
    }

}
