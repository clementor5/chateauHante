package Chateau;

import java.util.ArrayList;

import EtreVivant.Joueur;
import EtreVivant.Monstre;
import Main.Game;
import Main.Outils;
import Objet.Objet;

public class Piece {

	public static Joueur		joueur		= null;

	private static int			compteur	= 0;

	private int					num;
	private ArrayList<Sortie>	sorties;
	private Monstre				monstreAssocie;
	private Tresor				tresor;

	/**
	 * Constructeur de la premiere piece du chateau
	 */
	public Piece() {
		this.num = 0; // le numero de cette piece est toujours 0
		for (int i = 0; i < 4; i++) { // on creer 4 sorties
			sorties = new ArrayList<Sortie>();
			Piece pieceAssociee = null;
			do {
				pieceAssociee = Game.getChateau().getPieces()
						.get(Outils.alea(0, Game.getChateau().getPieces().size() - 1)); // on choisi une piece aléatoire
																						// du chateau
			} while (pieceAssociee.getSorties().size() == 4); // tant qu'on en trouve pas une qui n'a pas deja 4 sorties
			sorties.add(new Sortie(Sortie.creerNomOppose(pieceAssociee), pieceAssociee)); // on ajoute cette sortie a la
																							// piece

			ArrayList<Sortie> sortiesPieceAssociee = pieceAssociee.getSorties();
			try {
				sortiesPieceAssociee.add(new Sortie(Sortie.creerNom(pieceAssociee), pieceAssociee)); // on ajoute la
																										// nouvelle
																										// sortie dans
																										// la piece
																										// associee
			} catch (Exception e) {
				e.printStackTrace();
			}
			pieceAssociee.setSorties(sortiesPieceAssociee);
		}
		monstreAssocie = Monstre.creerMonstreAleatoire(); // on créer un monstre alétoire
		ArrayList<Objet> contenu = Tresor.creerContenuAleatoire();
		tresor = new Tresor(Tresor.creerContenuAleatoire());
	}

	/**
	 * @param sorties
	 * @param monstreAssocie
	 * @param tresor
	 */
	public Piece(ArrayList<Sortie> sorties, Monstre monstreAssocie, Tresor tresor) {
		compteur++;
		this.num = compteur;
		this.sorties = sorties;
		this.monstreAssocie = monstreAssocie;
		this.tresor = tresor;
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