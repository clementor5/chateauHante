/*
 * Outils.java                                             22 nov. 2020
 * mateo, pas de droits : ni copyright ni copyleft
 */
package Main;

/**
 * TODO commenter les responsabilités de cette classe
 * 
 * @author mateo
 *
 */
public class Outils {

	/**
	 * renvoi un entier aleatoire compris entre le min et le max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int alea(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}
}
