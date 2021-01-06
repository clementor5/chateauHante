/**
 * Potion.java
 * date de creation : 30 déc. 2020
 */
package Objet;

import Main.Commande;
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

	@Override
	public String toString() {
		return super.toString() + "\n>>> Points de vie : " + vieRendue;
	}

	@Override
	public void utiliser() {
		if ((Game.joueur.getHp() + this.vieRendue) > Game.HP_INITIAL) { // si la potion plus de points vie au joueur que son nombre de HP initial
			Commande.print("Vous utilisez la potion avec l'id " + this.getId() + ", cette derniere vous rend "
					+ (Game.HP_INITIAL - Game.joueur.getHp()) + " points de vies.");
			Game.joueur.setHp(Game.HP_INITIAL); // on rend toute sa vie au joueur sans lui en rajouter
		} else {
			Commande.print("Vous utilisez la potion avec l'id " + this.getId() + ", cette derniere vous rend " + this.vieRendue);
			Game.joueur.setHp(Game.joueur.getHp() + this.vieRendue); // on rend la vie au joueur
		}
		Commande.print("Vous avez donc maintenant " + Game.joueur.getHp());
		super.utiliser(); // on supprime l'objet
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
