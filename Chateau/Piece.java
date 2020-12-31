package Chateau;

import java.util.ArrayList;

import EtreVivant.Monstre;
import Main.Game;
import Main.Outils;

public class Piece {

	public static int			compteur	= 0;

	private int					num;
	private ArrayList<Sortie>	sorties;
	private Monstre				monstreAssocie;
	private Tresor				tresor;

	/**
	 * Constructeur d'une piece sans sorties, avec un monstre et un trésor aleatoire
	 */
	public Piece() {
		compteur++;
		num = compteur;
		sorties = new ArrayList<Sortie>();
		monstreAssocie = Monstre.creerMonstreAleatoire(); // on créer un monstre alétoire
		tresor = new Tresor(Tresor.creerContenuAleatoire(), Outils.alea(0, 5)); // on créer un coffre avec un contenu aleatoire (objets et nombre de clés)
	}

	/**
	 * Constructeur d'une piece avec un monstre et un trésor aleatoire
	 * 
	 * @param _sorties
	 */
	public Piece(ArrayList<Sortie> _sorties) {
		compteur++;
		num = compteur;
		sorties = _sorties;
		monstreAssocie = Monstre.creerMonstreAleatoire(); // on créer un monstre alétoire
		tresor = new Tresor(Tresor.creerContenuAleatoire(), Outils.alea(0, 5)); // on créer un coffre avec un contenu aleatoire (objets et nombre de clés)
	}

	/**
	 * Enregistre la piece apres une modification de celle ci
	 */
	public void save() {
		for (int i = 0; i < Game.chateau.getPieces().size(); i++) {
			Piece piece = Game.chateau.getPieces().get(i);
			if (piece.getNum() == this.num) {
				Game.chateau.getPieces().set(i, this);
			}
		}
	}

	/**
	 * @return le numero de la piece
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @return les sorties de la piece
	 */
	public ArrayList<Sortie> getSorties() {
		return sorties;
	}

	/**
	 * modifie les sorties de la piece
	 * 
	 * @param sorties
	 */
	public void setSorties(ArrayList<Sortie> sorties) {
		this.sorties = sorties;
	}

	/**
	 * @return le monstre de la piece
	 */
	public Monstre getMonstreAssocie() {
		return monstreAssocie;
	}

	/**
	 * modifie le monstre de la piece
	 * 
	 * @param monstreAssocié
	 */
	public void setMonstreAssocie(Monstre monstreAssocie) {
		this.monstreAssocie = monstreAssocie;
	}

	/**
	 * @return le tresor de la piece
	 */
	public Tresor getTresor() {
		return tresor;
	}

	/**
	 * modifie le tresor de la piece
	 * 
	 * @param tresor
	 */
	public void setTresor(Tresor tresor) {
		this.tresor = tresor;
	}
}