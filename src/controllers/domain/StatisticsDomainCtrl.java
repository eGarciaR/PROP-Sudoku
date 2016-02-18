/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package controllers.domain;

import domain.RegisteredUser;
import domain.Statistics;
import domain.record.Record;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import persistence.RankingManager;
import persistence.RecordManager;

/**
 *
 * @author dani
 */
public class StatisticsDomainCtrl {

    /**
     * Unica instància de la classe per implementar patró singleton.
     */
    private static StatisticsDomainCtrl statisticsDomainCtrl;
    private final RankingManager rankingManager;
    private final RecordManager recordManager;
    private final Statistics statistics;

    /**
     * Atributs del Ranking
     */
    public static final String RANKING_POSITION = "position";
    public static final String RANKING_USER = "user";
    public static final String RANKING_SCORE = "score";
    public static final int NUM_ELEMENTS_RANKING = 15;

    /**
     * Atributs dels Records
     */
    public static final String RECORD_TYPE = "type";
    public static final String RECORD_USER = "user";
    public static final String RECORD_VALUE = "value";

    /**
     * Constructor per defecte privat.
     */
    private StatisticsDomainCtrl(String gameName) {
        this.rankingManager = RankingManager.getInstance(gameName);
        this.recordManager = RecordManager.getInstance(gameName);
        this.statistics = Statistics.getInstance();
        this.loadStatistics();
    }

    /**
     * Actua com a constructor de la classe.
     *
     * @param gameName Nom del Joc
     * @return La única instància de la propia classe.
     */
    public static StatisticsDomainCtrl getInstance(String gameName) {
        if (statisticsDomainCtrl == null) {
            statisticsDomainCtrl = new StatisticsDomainCtrl(gameName);
        }
        return statisticsDomainCtrl;
    }

    /**
     * Actualitza el Ranking.
     *
     * @param userName Usuari
     * @param score Puntuacio
     * @return Enter en la posició que està el Ranking, -1 si no s'ha afegit al
     * Ranking.
     */
    public int updateRanking(String userName, int score) {
        int pos = this.statistics.updateRanking(userName, score);
        if (pos != -1) {//Ranking modificat
            this.rankingManager.updateAll(this.statistics.getRanking());
        }
        return pos;
    }

    /**
     * Actualitza els Records.
     *
     * @param user Usuari
     * @return Conjunt de records que he aconseguit.
     */
    public HashSet<HashMap<String, Object>> updateRecords(RegisteredUser user) {
        HashSet<Record> updatedRecords = this.statistics.updateRecords(user);
        HashSet<HashMap<String, Object>> uRecords = this.statistics.recordsToMap(updatedRecords);
        if (!updatedRecords.isEmpty()) {
            this.recordManager.updateAll(uRecords);
        }
        return uRecords;
    }

    /**
     * Llistat del Ranking.
     *
     * @return Llistat del ranking.
     */
    public ArrayList<HashMap<String, Object>> getRanking() {
        return statistics.getRanking();
    }

    /**
     * Conjunt de Records.
     *
     * @return Set de records.
     */
    public HashSet<HashMap<String, Object>> getRecords() {
        return statistics.getRecords();
    }

    /**
     * Carrega les estaditiques de la base de dades.
     */
    private void loadStatistics() {
        this.statistics.loadRanking(this.rankingManager.getAll());
        this.statistics.loadRecords(this.recordManager.getAll());
    }
}
