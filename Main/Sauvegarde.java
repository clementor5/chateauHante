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
	 * Sauvegarde la partie en cour
	 */
	public static void save() {
		try {
			// enregistrement de l'objet joueur
			FileOutputStream fileOut = new FileOutputStream(Game.joueur.getNom() + "_Joueur");
			// #TODO
			//			File f = new File(filePathString);
			//			if(f.exists() && !f.isDirectory()) { 
			//			    // do something
			//			}
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(Game.joueur);
			out.close();
			fileOut.close();

			// enregistrement de l'objet chateau
			fileOut = new FileOutputStream(Game.joueur.getNom() + "_Chateau");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(Game.chateau);
			out.close();
			fileOut.close();

			Commande.print("Sauvegarde terminée avec succès !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
