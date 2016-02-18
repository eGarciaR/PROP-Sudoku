/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.record;

import domain.RegisteredUser;

/**
 *
 * @author daniel.criado.casas
 */
public abstract class Record {

    /**
     * Tipus de record
     */
    private RecordType type;

    /**
     * Usuari que té el record
     */
    protected String user;

    /**
     * Descripció del record
     */
    protected String description;

    /**
     * Enumeració de tots els tipus que pot tindre un record
     */
    public enum RecordType {

        CONSTANT("CONSTANT"),
        CREATOR("CREATOR"),
        FASTEST("FASTEST"),
        LOSER("LOSER"),
        PLAYER("PLAYER"),
        SCORER("SCORER"),
        TIMER("TIMER"),
        WINNER("WINNER");

        // Nom del tipus
        private final String name;

        // Creadora del tipus de record amb nom s
        private RecordType(String s) {
            this.name = s;
        }

        // Iguala el nom
        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : this.name.equals(otherName);
        }

        // Canvia a format String
        @Override
        public String toString() {
            return this.name;
        }
    }

    /**
     * Creadora del record amb un tipus especificat
     *
     * @pre type és un tipus vàlid
     * @post crea un record del tipus type
     * @param type tipus de record
     */
    public Record(RecordType type) {
        this.type = type;
        this.user = null;
        this.description = null;
    }

    /**
     * Getter del tipus de record
     *
     * @return retorna el tipus de record
     */
    public String getType() {
        return this.type.toString();
    }

    /**
     * Getter de la descripció del record
     *
     * @return retorna la descripció del record
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter del nom del usuari que ha fet el record
     *
     * @return retorna el nom del usuari
     */
    public String getUsername() {
        return this.user;
    }

    /**
     * Setter del nom del usuari que ha fet el record
     * @param userName nom de l'usuari
     * @return retorna el nom del usuari
     */
    public boolean setUsername(String userName) {
        if (userName == null) return false;
        this.user = userName;
        return true;
    }

    /**
     * Mostra pel canal estàndard de sortida el record
     */
    public void print() {
        System.out.println("Record: " + this.getType().toString());
        System.out.println("--User: " + this.getUsername());
        System.out.println("--Descripció: " + this.getDescription());
        System.out.println("--Value: " + this.getValue());
    }

    /**
     * Métode abstracte d'actualització
     *
     * @param user usuari a actualitzar
     * @return cert si s'ha actualitzat, false altrament
     */
    public abstract boolean update(RegisteredUser user);

    /**
     * Getter del valor del record
     *
     * @return retorna el valor del record
     */
    public abstract String getValue();

    /**
     * Setter del valor del record
     *
     * @param value Valor
     * @return retorna el valor del record
     */
    public boolean setValue(String value) {
        if (value == null) return false;
        else return true;
    }

    /**
     * Conversor a string
     *
     * @return retorna l'usuari en format string
     */
    @Override
    public String toString() {
        String s = "user=" + this.user + ";";
        return s;
    }

    /**
     * Conversor a string del value
     *
     * @return retorna el valor en format string
     */
    public abstract String valueToString();

    /**
     * Igualador del tipus
     *
     * @param obj objecte comparat
     * @return retorna si els objectes son iguals
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }

        final Record other = (Record) obj;
        return this.type == other.type;
    }

    /**
     * Indicador hash
     *
     * @return retorna el codi Hash
     */
    @Override
    public int hashCode() {
        return this.type.toString().hashCode();
    }
}
