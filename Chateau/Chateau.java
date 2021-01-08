package Chateau;

import java.io.Serializable;
import java.util.ArrayList;

import Main.Game;
import Main.Outils;

public class Chateau implements Serializable {

	private String				nom;
	private int					nbPieces;
	private ArrayList<Piece>	pieces				= new ArrayList<Piece>();
	private int					nbTresorsOuverts	= Game.NB_PIECES;

	/**
	 * Créer le chateau ainsi que toutes les pieces qu'il contient Chaque piece est créé aléatoirement avec des sorties aléatoires, un monstre et un tresor aleatoire
	 * 
	 * @param nom
	 * @param _nbPieces
	 */
	public Chateau(String nom, int _nbPieces) {
		super();
		this.nom = nom;
		this.nbPieces = _nbPieces;

		for (int i = 0; i < nbPieces; i++) {
			Piece piece = new Piece(); // on créer notre piece avec un monstre et un tresor aleatoire
			ArrayList<Sortie> sorties = new ArrayList<Sortie>();
			if (!pieces.isEmpty()) {
				Integer alea = Outils.alea(0, pieces.size() - 1);
				Piece pieceAssociee = pieces.get(alea); // on choisi une piece qui n'a pas deja 4 sorties
				while (pieceAssociee.getSorties().size() == 4) {
					alea = Outils.alea(0, pieces.size() - 1);
					pieceAssociee = pieces.get(alea);
				}

				String nomSortie = null;
				String nomSortieForPieceAssociee = null;
				try {
					nomSortieForPieceAssociee = Sortie.creerNomSortieDansPieceAssociee(pieceAssociee); // on choisi un nom qui ne soit pas deja pris dans la sortie associee
					nomSortie = Sortie.creerNomSortie(nomSortieForPieceAssociee); // on choisi le nom correspondant pour cette piece
				} catch (Exception e) {
					e.printStackTrace();
				}
				sorties.add(new Sortie(nomSortie, pieceAssociee)); // on creer notre sortie et on l'associe aux sorties de la piece que l'on va créer

				piece.setSorties(sorties); // on ajoute la sortie a notre piece

				ArrayList<Sortie> sortiesPieceAssociee = pieceAssociee.getSorties();
				sortiesPieceAssociee.add(new Sortie(nomSortieForPieceAssociee, piece));
				pieceAssociee.setSorties(sortiesPieceAssociee); // on ajoute la sortie a la piece associée
				pieces.set(alea, pieceAssociee);
			}

			pieces.add(piece); // et on l'ajoute aux pieces du chateau
		}
	}

	/**
	 * @return le nom du chateau
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * modifie le nom du chateau
	 * 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return le nombre de pieces du chateau
	 */
	public int getNbPiece() {
		return nbPieces;
	}

	/**
	 * modifie le nombre de pieces du chateau
	 * 
	 * @param nbPiece
	 */
	public void setNbPiece(int nbPiece) {
		this.nbPieces = nbPiece;
	}

	/**
	 * @return les pieces du chateau
	 */
	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	/**
	 * modifie les pieces du chateau
	 * 
	 * @param pieces
	 */
	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}

	/**
	 * @return nbPieces
	 */
	public int getNbPieces() {
		return nbPieces;
	}

	/**
	 * @param nbPieces le champ a modifier
	 */
	public void setNbPieces(int nbPieces) {
		this.nbPieces = nbPieces;
	}

	/**
	 * @return nbTresorsOuverts
	 */
	public int getNbTresorsOuverts() {
		return nbTresorsOuverts;
	}

	/**
	 * @param nbTresorsOuverts le champ a modifier
	 */
	public void setNbTresorsOuverts(int nbTresorsOuverts) {
		this.nbTresorsOuverts = nbTresorsOuverts;
	}
}
