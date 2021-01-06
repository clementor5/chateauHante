package EtreVivant;

import java.util.ArrayList;
import java.util.Arrays;

import Main.Game;
import Main.Outils;

public class Monstre extends EtreVivant {

	// #TODO completer
	public static ArrayList<String>	nomsMonstres	= new ArrayList<String>(
			Arrays.asList("un minotaure", "une sirène", "un troll", "une sorcière", "un loup-garou", "un gnome des bois", "un petit lutin vert",
					"une succube", "un vampire", "un dahut", "une Meduse", "un cerbère", "une hydre", "le dieu Odin", "une licorne",
					"le grand Yggdrasil", "Jean Dujardin", "Pascal Obispo", "Pierre Menez", "Bugs Bunny", "capitaine crochet", "Patrick Sebastien"));

	private int						attaque;

	/**
	 * @param nom
	 * @param hp
	 * @param attaque
	 */
	public Monstre(String nom, int hp, int attaque) {
		super(nom, hp);
		this.attaque = attaque;
	}

	/**
	 * @return l'attaque du monstre
	 */
	public int getAttaque() {
		return attaque;
	}

	/**
	 * Modifie l'attaque du monstre
	 * 
	 * @param attaque
	 */
	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}

	/**
	 * @return un monstre aléatoire
	 */
	public static Monstre creerMonstreAleatoire() { // #TODO plus tard en fonction de la difficulté
		String nom = nomsMonstres.get(Outils.alea(0, nomsMonstres.size() - 1)); // on choisi aletoirement un nom dans la liste
		int hp = Outils.alea(0, (int) Math.round(0.6 * Game.HP_INITIAL)); // on choisi des hp aleatoirement en fonction de la vie du joueur
		int attaque = Outils.alea(0, (int) Math.round(0.4 * Game.HP_INITIAL)); // on choisi points d'attaque aleatoirement en fonction de la vie du joueur
		return new Monstre(nom, hp, attaque);
	}
}
