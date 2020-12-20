package Chateau;

import java.util.ArrayList;

import EtreVivant.Monstre;

public class Piece {

	private static int			compteur	= 0;

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
		tresor = new Tresor(Tresor.creerContenuAleatoire());
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
		tresor = new Tresor(Tresor.creerContenuAleatoire());
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