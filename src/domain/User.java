package domain;

/**
 * Un usuari
 */
public class User{
	
	/**
	 * El nom
	 */
	private String name;
	
	/**
	 * Creadora
	 * @pre: true
	 * @param name El nom de l'usuari desitjat
	 * @post: Es crea un usuari amb nom name
	 */
	public User(String name){
		this.name = name;
	}
	
	/**
	 * Getter del nom
	 * @pre: true
	 * @return El nom de l'usuari
	 */
	public String getName(){
		return name;
	}
	
}