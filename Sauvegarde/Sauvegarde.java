/**
 * Sauvegarde.java
 * date de creation : 31 déc. 2020
 */
package Sauvegarde;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import Main.Commande;
import Main.Game;

/**
 * 
 */
public class Sauvegarde {

	/**
	 * Sauvegarde la partie en cour
	 */
	public static void save() {
		try {
			// enregistrement de l'objet joueur
			FileOutputStream fileOut = new FileOutputStream(Game.joueur.getNom());
			// #TODO
			//			File f = new File(filePathString);
			//			if(f.exists() && !f.isDirectory()) { 
			//			    // do something
			//			}
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			Partie partie = new Partie(Game.chateau, Game.joueur, Game.sortieChoisie, Game.sortiePrecedente);
			out.writeObject(partie);
			out.close();
			fileOut.close();

			Commande.print("Sauvegarde terminée avec succès !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
