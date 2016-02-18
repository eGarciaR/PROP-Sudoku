/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.record;

import domain.RegisteredUser;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author daniel.criado.casas
 */
public class ScorerRecord extends Record {

    /**
     * Instancia de la classe
     */
    private static ScorerRecord record;

    /**
     * Valor del tipus de record
     */
    private long value;

    /**
     * Contructor privat amb tipus de record
     *
     * @pre cert
     * @post crea un record de tipus ScorerRecord
     * @param tipus indica el tipus de record
     */
    private ScorerRecord(RecordType tipus) {
        super(tipus);
        this.value = 0;
        this.description = "Jugador amb més puntuació";
    }

    /**
     * Instancia la classe
     *
     * @pre cert
     * @post si no existeix la instancia de la classe, es crea una nova
     * @return retorna la instancia de la classe
     */
    public static ScorerRecord getInstance() {
        if (record == null) {
            record = new ScorerRecord(RecordType.SCORER);
        }
        return record;
    }

    /**
     * Actualitza el record en funció del seu tipus
     *
     * @param user Usuari del qual es vols consultar les dades del record a
     * superar.
     * @return True si s'ha modificat el record.
     */
    @Override
    public boolean update(RegisteredUser user) {
        boolean actualitzat = false;
        long value = (long) user.getTotalScore();
        if (value != 0 && (value > this.value || this.value == 0)) {
            this.user = user.getName();
            this.value = value;
            actualitzat = true;
        }
        return actualitzat;
    }

    /**
     * Getter del valor
     *
     * @return retorna el valor del tipus de record
     */
    @Override
    public String getValue() {
        return Long.toString(this.value);
    }

    /**
     * Conversor a string
     *
     * @return retorna el tipus en format string
     */
    @Override
    public String toString() {
        String s = super.toString();
        s += "value=" + Long.toString(this.value) + ";";
        return s;
    }

    /**
     * Setter del valor
     *
     * @param value Valor
     * @return retorna el valor del tipus de record
     */
    @Override
    public boolean setValue(String value) {
        boolean b = super.setValue(value);
        if (b) {
            this.value = Long.valueOf(value);
        }
        return b;
    }

    /**
     * Conversor a string del value
     *
     * @return retorna el valor en format string
     */
    @Override
    public String valueToString() {
        String s = Long.toString(this.value);
        return s;
    }

}
