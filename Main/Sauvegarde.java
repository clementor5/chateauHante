/**
 * Sauvegarde.java
 * date de creation : 31 déc. 2020
 */
package Main;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * 
 */
public class Sauvegarde {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FileOutputStream fileOut = new FileOutputStream(Game.joueur.getNom());
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(Game.joueur);
			out.close();
			fileOut.close();
			Commande.print("Sauvegarde terminée avec succès !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
