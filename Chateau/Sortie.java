package Chateau;

import java.util.ArrayList;

public class Sortie {

	private String	nom;
	Piece			pieceAssociee;

	public Sortie(String nom, Piece pieceAssociee) {
		super();
		this.nom = nom;
		this.pieceAssociee = pieceAssociee;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Piece getPieceAssociee() {
		return pieceAssociee;
	}

	public void setPieceAssociee(Piece pieceAssociee) {
		this.pieceAssociee = pieceAssociee;
	}

	/**
	 * @param pieceAssociee
	 * @return le nom adapté a la nouvelle sortie
	 * @throws Exception si la piece possede deja 4 sorties
	 */
	public static String creerNomPieceAssociee(Piece pieceAssociee) throws Exception {
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
	 * @param pieceAssociee
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
}