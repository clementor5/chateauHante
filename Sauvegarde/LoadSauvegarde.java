/**
 * loadSauvegarde.java
 * date de creation : 31 déc. 2020
 */
package Sauvegarde;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import Main.Commandes;
import Main.Game;

/**
 * 
 */
public class LoadSauvegarde {

	/**
	 * Recupere une sauvegarde
	 */
	public static void load() {
		try {
			/* on recupere la partie */
			FileInputStream fileIn = new FileInputStream(Game.joueur.getNom());
			ObjectInputStream ois = new ObjectInputStream(fileIn);
			Partie partie = (Partie) ois.readObject();
			Game.chateau = partie.chateau;
			Game.joueur = partie.joueur;
			Game.sortieChoisie = partie.sortieChoisie;
			Game.sortiePrecedente = partie.sortiePrecedente;
			Game.boucle();
			ois.close();
			fileIn.close();
		} catch (Exception e) {
			Commandes.print("Il n'existe aucune sauvegarde a ce nom");
		}
	}
}
