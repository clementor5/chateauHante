/**
 * Potion.java
 * date de creation : 30 déc. 2020
 */
package Objet;

import Main.Game;
import Main.Outils;

/**
 * 
 */
public class Potion extends Objet {

	private int vieRendue = 0;

	/**
	 * constructeur
	 * 
	 * @param nom
	 * @param type
	 */
	public Potion(String nom, String type) {
		super(nom, type);
		this.vieRendue = Outils.alea((int) Math.round(Game.joueur.getHp() * 0.05), (int) Math.round(Game.joueur.getHp() * 0.2));
	}

	/**
	 * constructeur
	 * 
	 * @param nom
	 * @param type
	 * @param vieRendue
	 */
	public Potion(String nom, String type, int vieRendue) {
		super(nom, type);
		this.vieRendue = vieRendue;
	}

	/**
	 * @return vieRendue
	 */
	public int getVieRendue() {
		return vieRendue;
	}

	/**
	 * @param vieRendue le champ a modifier
	 */
	public void setVieRendue(int vieRendue) {
		this.vieRendue = vieRendue;
	}
}
