package Chateau;

import java.util.ArrayList;

import Main.Commande;
import Main.Game;
import Main.Outils;

public class Sortie {

	private static int	compteur	= 0;

	private String		nom;
	private Piece		pieceAssociee;
	private int			coutCle;
	private int			id;

	/**
	 * constructeur
	 * 
	 * @param nom
	 * @param pieceAssociee
	 */
	public Sortie(String nom, Piece pieceAssociee) {
		compteur++;
		this.id = compteur;
		this.nom = nom;
		this.pieceAssociee = pieceAssociee;
		if (Piece.compteur > 1) { // si la sortie porte sur la 10ème pièce au minimum
			this.coutCle = Outils.alea(0, 2); // alors la sortie aura 0 à 2 serrures
		} else {
			this.coutCle = 0; // sinon la sortie n'aura pas de serrures
		}
	}

	/**
	 * Ouvre la porte de la sortie (utilise les clés du joueurs si necessaire et reduit à 0 le cout en cles de la sortie ainsi que celle dans la piece associée
	 */
	public void ouvrirPorte() {
		if (coutCle > 0) {
			Game.joueur.setNbCles(Game.joueur.getNbCles() - this.coutCle);
			Commande.print("Vous utilisez " + coutCle + " clés pour ouvrir la porte.");
			Commande.print("Il vous en reste " + Game.joueur.getNbCles() + " maintenant.");
			this.coutCle = 0;
			this.save();
		}
		Sortie sortieDansPieceAssociee = getSortieDansPieceAssociee(this, pieceAssociee);
		sortieDansPieceAssociee.setCoutCle(0);
		sortieDansPieceAssociee.save();
	}

	/**
	 * enregistre les modifications de la sortie (modifie l'ancienne sortie dans la piece avec la nouvelle sortie associée)
	 */
	public void save() {
		for (int i = 0; i < Game.chateau.getPieces().size(); i++) {
			Piece piece = Game.chateau.getPieces().get(i);

			ArrayList<Sortie> sorties = piece.getSorties();
			for (int y = 0; y < sorties.size(); y++) {
				Sortie sortie = sorties.get(y);
				if (sortie.getId() == this.getId()) {
					sorties.set(y, this);
					piece.setSorties(sorties);
					Game.chateau.getPieces().set(i, piece);
					return;
				}
			}
		}
	}

	/**
	 * @param pieceAssociee
	 * @return le nom adapté a la nouvelle sortie
	 * @throws Exception si la piece possede deja 4 sorties
	 */
	public static String creerNomSortieDansPieceAssociee(Piece pieceAssociee) throws Exception {
		ArrayList<String> sorties = new ArrayList<String>();
		for (Sortie sortie : pieceAssociee.getSorties()) {
			sorties.add(sortie.getNom());
		}
		if (!sorties.contains("NORD")) {
			return "NORD";
		} else if (!sorties.contains("EST")) {
			return "EST";
		} else if (!sorties.contains("SUD")) {
			return "SUD";
		} else if (!sorties.contains("OUEST")) {
			return "OUEST";
		}
		throw new Exception("deja 4 sorties dans cette piece !");
	}

	/**
	 * @param sortie
	 * @param pieceAssociee
	 * @return la sortie dans la piece associee
	 */
	public static Sortie getSortieDansPieceAssociee(Sortie sortie, Piece pieceAssociee) {
		String nomSortie = sortie.getNom();
		String nomSortieDansPieceAssociee = "";
		if (nomSortie.equals("NORD")) {
			nomSortieDansPieceAssociee = "SUD";
		} else if (nomSortie.equals("EST")) {
			nomSortieDansPieceAssociee = "OUEST";
		} else if (nomSortie.equals("SUD")) {
			nomSortieDansPieceAssociee = "NORD";
		} else if (nomSortie.equals("OUEST")) {
			nomSortieDansPieceAssociee = "EST";
		}
		for (Sortie sortieDansPieceAssociee : pieceAssociee.getSorties()) {
			if (sortieDansPieceAssociee.getNom().equals(nomSortieDansPieceAssociee)) {
				return sortieDansPieceAssociee;
			}
		}
		return null;
	}

	/**
	 * @param nomSortiePieceAssociee
	 * @return le nom adapté a la nouvelle sortie dans la nouvelle piece exemple : si le nom dans la piece associée est "SUD" alors la methode renverra "NORD"
	 */
	public static String creerNomSortie(String nomSortiePieceAssociee) {
		if (nomSortiePieceAssociee.equals("NORD")) {
			return "SUD";
		} else if (nomSortiePieceAssociee.equals("EST")) {
			return "OUEST";
		} else if (nomSortiePieceAssociee.equals("SUD")) {
			return "NORD";
		} else if (nomSortiePieceAssociee.equals("OUEST")) {
			return "EST";
		}
		return null;
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return le nom de la sortie
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return la pièce associée
	 */
	public Piece getPieceAssociee() {
		return pieceAssociee;
	}

	/**
	 * @param pieceAssociee
	 */
	public void setPieceAssociee(Piece pieceAssociee) {
		this.pieceAssociee = pieceAssociee;
	}

	/**
	 * @return coutCle
	 */
	public int getCoutCle() {
		return coutCle;
	}

	/**
	 * @param coutCle le champ a modifier
	 */
	public void setCoutCle(int coutCle) {
		this.coutCle = coutCle;
	}
}
