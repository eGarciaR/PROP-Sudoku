/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import controllers.domain.StatisticsDomainCtrl;
import domain.record.ConstantRecord;
import domain.record.CreatorRecord;
import domain.record.FastestRecord;
import domain.record.LoserRecord;
import domain.record.PlayerRecord;
import domain.record.Record;
import domain.record.ScorerRecord;
import domain.record.TimerRecord;
import domain.record.WinnerRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author daniel.criado.casas
 */
public class Statistics {

    private static final int NUM_ELEMENTS_RANKING = StatisticsDomainCtrl.NUM_ELEMENTS_RANKING;
    /**
     * Unica instància de la classe per implementar patró singleton.
     */
    private static Statistics statistics;

    private final ArrayList<RankingElement> ranking;
    private final HashSet<Record> records;

    /**
     * Constructor per defecte privat.
     */
    private Statistics() {
        this.ranking = new ArrayList<>(NUM_ELEMENTS_RANKING);
        this.initEmptyRanking();
        this.records = new HashSet<>();
        this.initDefaultRecords();
    }
    
    /**
     * Inicialitza el ranking amb valors nulls.
     */
    private void initEmptyRanking() {
        for (int i = 0; i < NUM_ELEMENTS_RANKING; i++) {
            this.ranking.add(new RankingElement(null, 0));
        }
    }
    
    /**
     * Inicialitza els records amb valors nulls.
     */
    private void initDefaultRecords() {
        this.records.add(ConstantRecord.getInstance());
        this.records.add(CreatorRecord.getInstance());
        this.records.add(FastestRecord.getInstance());
        this.records.add(LoserRecord.getInstance());
        this.records.add(PlayerRecord.getInstance());
        this.records.add(ScorerRecord.getInstance());
        this.records.add(TimerRecord.getInstance());
        this.records.add(WinnerRecord.getInstance());
    }

    /**
     * Actua com a constructor de la classe.
     *
     * @return La única instància de la propia classe.
     */
    public static Statistics getInstance() {
        if (statistics == null) {
            statistics = new Statistics();
        }
        return statistics;
    }

    /**
     * Actualitza el ranking amb una nova puntuació d'un usuari.
     *
     * @param user Usuari
     * @param score Puntuació
     * @return La posició en el Ranking si s'ha afegit, -1 altrament.
     */
    public int updateRanking(String user, int score) {

        RankingElement re;
        boolean colocat = false;
        int pos = 0;
        Iterator<RankingElement> it = this.ranking.iterator();
        /**
         * Itero sobre els elements del ranking per trobar una posicio
         */
        while (it.hasNext() && !colocat) {
            re = it.next();
            if (score > re.getScore()) {
                colocat = true;
            }//en cas de empat de score te preferencia el que ja estava.
            else {
                ++pos;
            }
        }

        //Si el ranking encara no estava ple i no he trobat posicio, es queda la última;
        if (!colocat && this.ranking.size() < NUM_ELEMENTS_RANKING) {
            colocat = true;
        }

        /**
         * Coloco l'element en la posicio trobada si he trobat un lloc en el
         * ranking
         */
        if (colocat) {
            re = new RankingElement(user, score);
            this.ranking.add(pos, re);
            if (this.ranking.size() > NUM_ELEMENTS_RANKING) {
                //Elimino l'ultim si el ranking ja estava ple.
                this.ranking.remove(NUM_ELEMENTS_RANKING);
            }
            ++pos;
        } else {
            pos = -1;
        }
        return pos;
    }

    public void printRanking() {
        System.out.println("");
        System.out.println("RANKING ACTUAL: ");
        int i = 0;
        for (RankingElement ranking1 : this.ranking) {
            System.out.println((i + 1) + ". User: " + ranking1.getUser() + " Score: " + ranking1.getScore());
            ++i;
        }
        System.out.println("");
    }

    public void printRecords() {
        System.out.println("");
        System.out.println("RECORDS ACTUALS: ");
        for (Record record : this.records) {
            record.print();
        }
        System.out.println("");
    }

    public HashSet<HashMap<String, Object>> recordsToMap(HashSet<Record> updatedRecords) {
        HashSet<HashMap<String, Object>> tmpRecords = new HashSet<>();
        HashMap<String, Object> attrs;
        for (Record record : updatedRecords) {
            attrs = new HashMap<>();
            attrs.put("type", record.getType());
            attrs.put("user", record.getUsername());
            attrs.put("value", record.valueToString());
            tmpRecords.add(attrs);
        }
        return tmpRecords;
    }

    private class RankingElement {

        private String user;
        private int score;

        public RankingElement(String user, int score) {
            this.user = user;
            this.score = score;
        }

        public int getScore() {
            return this.score;
        }
        
        public void setScore(int score){
            this.score = score;
        }

        public String getUser() {
            return this.user;
        }
        
        public void setUser(String user) {
            this.user = user;
        }
    }

    /**
     * Torna un set amb els records que s'han actualitzat.
     *
     * @param user RegisteredUser
     * @return Set de records actualitzats.
     */
    public HashSet<Record> updateRecords(RegisteredUser user) {
        HashSet<Record> updated = new HashSet<>();
        for (Record record : this.records) {
            if (record.update(user)) {
                updated.add(record);
            }
        }
        return updated;
    }
    
    /**
     * Carrega el ranking del parametre d'entrada.
     * @param ranking Ranking
     */
    public void loadRanking(ArrayList<HashMap<String, Object>> ranking) {
        RankingElement re;
        String user;
        int score, position;
        for (HashMap<String, Object> ranking1 : ranking) {
            position = (int) ranking1.get("position");
            user = (String) ranking1.get("user");
            score = (int) ranking1.get("score");
            re = this.ranking.get(position-1);
            re.setScore(score);
            re.setUser(user);
        }
    }
    
    /**
     * Retorna un llistat amb el ranking.
     * @return Ranking
     */
    public ArrayList<HashMap<String, Object>> getRanking() {
        ArrayList<HashMap<String, Object>> tmpRanking = new ArrayList<>();
        HashMap<String, Object> rElement;
        RankingElement re;
        int n = this.ranking.size();
        for (int i = 0; i < n; i++) {
            re = this.ranking.get(i);
            rElement = new HashMap<>();
            rElement.put("position", i + 1);
            rElement.put("user", re.getUser());
            rElement.put("score", re.getScore());
            tmpRanking.add(rElement);
        }
        return tmpRanking;
    }
    
    /**
     * Carrega els records d'un conjunt.
     * @param records HashMap de records
     */
    public void loadRecords(HashMap<String, HashMap<String, Object>> records) {
        HashMap<String, Object> attrs;
        for (Record record : this.records) {
            attrs = records.get(record.getType());
            String user = (String) attrs.get("user");
            String value = (String) attrs.get("value");
            record.setUsername(user);
            record.setValue(value);
        }
    }
    
    /**
     * Retorna un conjunt amb els records.
     * @return Records
     */
    public HashSet<HashMap<String, Object>> getRecords() {
        HashSet<HashMap<String, Object>> tmpRecords = new HashSet<>();
        HashMap<String, Object> attrs;
        for (Record record : this.records) {
            attrs = new HashMap<>();
            attrs.put("type", record.getType());
            attrs.put("user", record.getUsername());
            attrs.put("value", record.getValue());
            attrs.put("description", record.getDescription());
            tmpRecords.add(attrs);
        }
        return tmpRecords;
    }
}
