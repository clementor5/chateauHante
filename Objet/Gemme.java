/**
 * Gemme.java
 * date de creation : 30 déc. 2020
 */
package Objet;

import Main.Game;
import Main.Outils;

/**
 * 
 */
public class Gemme extends Objet {

	private int degatsBonus = 0;

	/**
	 * constructeur
	 * 
	 * @param nom
	 * @param type
	 */
	public Gemme(String nom, String type) {
		super(nom, type);
		this.degatsBonus = Outils.alea((int) Math.round(Game.joueur.getHp() * 0.05), (int) Math.round(Game.joueur.getHp() * 0.2));
	}

	/**
	 * constructeur
	 * 
	 * @param nom
	 * @param type
	 * @param degatsBonus
	 */
	public Gemme(String nom, String type, int degatsBonus) {
		super(nom, type);
		this.degatsBonus = degatsBonus;
	}

	/**
	 * @return degatsBonus
	 */
	public int getDegatsBonus() {
		return degatsBonus;
	}

	/**
	 * @param degatsBonus le champ a modifier
	 */
	public void setDegatsBonus(int degatsBonus) {
		this.degatsBonus = degatsBonus;
	}

}
