package Chateau;

import java.util.ArrayList;

import EtreVivant.Joueur;
import EtreVivant.Monstre;
import Main.Outils;

public class Chateau {

	public static Joueur		joueur	= null;

	private String				nom;
	private int					nbPieces;
	private ArrayList<Piece>	pieces	= new ArrayList<Piece>();

	/**
	 * Créer le chateau ainsi que toutes les pieces qu'il contient Chaque piece est
	 * créé aléatoirement avec des sorties aléatoires, un monstre et un tresor
	 * aleatoire
	 * 
	 * @param nom
	 * @param _nbPieces
	 */
	public Chateau(String nom, int _nbPieces) {
		super();
		this.nom = nom;
		this.nbPieces = _nbPieces;

		for (int i = 0; i < nbPieces; i++) {
			ArrayList<Sortie> sorties = new ArrayList<Sortie>();
			if (!pieces.isEmpty()) {
				Piece pieceAssociee = pieces.get(Outils.alea(0, pieces.size() - 1)); // on choisi une piece qui n'a pas
																						// deja 4 sorties
				while (pieceAssociee.getSorties().size() == 4) {
					pieceAssociee = pieces.get(Outils.alea(0, pieces.size() - 1));
				}

				String	nomSortie					= null;
				String	nomSortieForPieceAssociee	= null;
				try {
					nomSortie = Sortie.creerNomOppose(pieceAssociee); // on choisi un nom qui ne soit pas deja pris
					nomSortieForPieceAssociee = Sortie.creerNom(pieceAssociee); // on choisi un nom qui ne soit pas deja
																				// pris dans la sortie associee
				} catch (Exception e) {
					e.printStackTrace();
				}
				sorties.add(new Sortie(nomSortie, pieceAssociee)); // on creer notre sorties et on l'associe aux sorties
																	// de la piece que l'on va créer

				ArrayList<Sortie> nouvellesSorties = pieceAssociee.getSorties();
				nouvellesSorties.add(new Sortie(nomSortieForPieceAssociee, pieceAssociee));
				pieceAssociee.setSorties(nouvellesSorties); // on ajoute la sortie a la piece associée
			}
			Monstre	monstreAssocie	= Monstre.creerMonstreAleatoire();													// on
																														// créer
																														// un
																														// monstre
																														// alétoire
			Piece	piece			= new Piece(sorties, monstreAssocie, new Tresor(Tresor.creerContenuAleatoire()));	// on
																														// créer
																														// notre
																														// piece
																														// avec
																														// monstre
																														// et
																														// un
																														// tresor
																														// aleatoire
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
}
