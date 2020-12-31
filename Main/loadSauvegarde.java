/**
 * loadSauvegarde.java
 * date de creation : 31 déc. 2020
 */
package Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import EtreVivant.Joueur;

/**
 * 
 */
public class loadSauvegarde {

	public static void main(String[] args) {
		try {
			FileInputStream fileIn = new FileInputStream("test");
			ObjectInputStream ois = new ObjectInputStream(fileIn);
			Game.joueur = (Joueur) ois.readObject();
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
