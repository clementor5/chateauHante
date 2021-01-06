/**
 * loadSauvegarde.java
 * date de creation : 31 déc. 2020
 */
package Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import Chateau.Chateau;
import EtreVivant.Joueur;

/**
 * 
 */
public class LoadSauvegarde {

	/**
	 * Recupere une sauvegarde
	 */
	public static void load(String nomJoueur) {
		try {
			/* on recupere l'objet joueur */
			FileInputStream fileIn = new FileInputStream(nomJoueur + "_Joueur");
			ObjectInputStream ois = new ObjectInputStream(fileIn);
			Game.joueur = (Joueur) ois.readObject();
			ois.close();
			fileIn.close();

			/* on recupere l'objet chateau */
			fileIn = new FileInputStream(nomJoueur + "_Chateau");
			ois = new ObjectInputStream(fileIn);
			Game.chateau = (Chateau) ois.readObject();
			ois.close();
			fileIn.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
