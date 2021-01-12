/**
 * Gemme.java
 * date de creation : 30 déc. 2020
 */
package Objet;

import java.util.ArrayList;

import Main.Commandes;
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

	@Override
	public String toString() {
		return super.toString() + "\n>>> Degats bonus : " + degatsBonus;
	}

	@Override
	public void utiliser() {
		ArrayList<Arme> armes = new ArrayList<Arme>();
		for (Objet objet : Game.joueur.getInventaire()) {
			if (objet.getType().equals(Objet.getTypeArme())) {
				armes.add((Arme) objet);
			}
		}

		do {
			String reponse = Commandes
					.verifCommandeSpeciale("Saisissez l'id de l'arme sur laquelle vous voulez utiliser la gemme, sinon saisissez \"STOP\"");
			if (reponse.equalsIgnoreCase("STOP")) {
				return;
			} else {
				for (Arme arme : armes) {
					try {
						if (arme.getId() == Integer.parseInt(reponse)) {
							Commandes.print("L'arme a l'id " + arme.getId() + " passe donc de " + arme.getDegats() + " degats a "
									+ (arme.getDegats() + this.degatsBonus) + " degats.");
							arme.setDegats(arme.getDegats() + this.getDegatsBonus());
							super.utiliser(); // on supprime la gemme
							return;
						}
					} catch (Exception e) {
						Commandes.print("Erreur, votre saisie est incorrecte !");
					}
				}
			}
		} while (true);
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
