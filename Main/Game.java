package Main;

import Chateau.Chateau;
import Chateau.Piece;
import Chateau.Sortie;
import EtreVivant.Joueur;

public class Game {

	public final static int	NB_PIECES					= 5;
	public final static int	HP_INITIAL					= 50;
	public final static int	TAILLE_INVENTAIRE_FINALE	= 20;

	public static Joueur	joueur;
	public static Chateau	chateau;
	public static int		nbTresors					= NB_PIECES;

	/**
	 * Methode qui lance le jeu Renseigner les constantes NB_PIECES et HP pour choisir le nombre de pieces du chateau et votre nombre de point de vie
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// /!\ important : equilibre armes et monstre et equilibre cle et pieces /!\
		// améliorer affichage ?
		// sauvegarde a faire (sauvegarde auto ?, quand ?)
		// thread Synchroniser ne fait que dormir

		String nom = Commande.printAccueil();
		joueur = new Joueur(nom, HP_INITIAL, TAILLE_INVENTAIRE_FINALE);
		chateau = new Chateau("Sombre Chateau", NB_PIECES);

		Commande.printDebut(); // choix de l'inventaire de base + amene a l'interieur du chateau

		Sortie sortieChoisie = null;
		Sortie sortiePiecePrecedente = null;
		while (true) {
			Piece piece = null;
			if (sortieChoisie == null) { // si le joueur vient tout juste de rentrer dans le chateau
				piece = chateau.getPieces().get(0); // on choisi une piece du chateau
			} else {
				piece = sortieChoisie.getPieceAssociee(); // on recupere la piece associée a la sortie que le joueur
															// vient de choisir
			}
			Commande.print("Vous voila maintenant dans la piece n°" + piece.getNum() + ".");

			if (piece.getMonstreAssocie() != null) { // si le monstre de cet piece n'a pas encore été battu
				int victoire = Commande.printCombat(piece.getMonstreAssocie()); // combat contre le monstre
				switch (victoire) {
				case -1: // le joueur a perdu
					Commande.finDuJeu();
					break;
				case 0: // le joueur a choisi de s'enfuir
					if (sortieChoisie == null) { // si le joueur est dans la premiere piece
						Commande.print("La porte d'entrée du chateau est verouillée ! Vous ne pouvez pas vous enfuir !");
					} else {
						sortieChoisie = sortiePiecePrecedente;
					}
					break;
				case 1: // le joueur a gagné
					if (nbTresors == NB_PIECES) { // si on vient de vaincre le monstre de la premiere piece
						joueur.setTailleInventaire(TAILLE_INVENTAIRE_FINALE); // on lui fait gagner un nouveau sac
						Commande.print("Le monstre que vous avez vaincu possede un sac plus grand !");
						Commande.print("Votre sac peut desormais contenir jusqu'a " + TAILLE_INVENTAIRE_FINALE + " objets !");
						int nbClesTrouvees = 3;
						Commande.print("Vous avez trouvé " + nbClesTrouvees + " cles dans le sac !");
						Commande.print("Elle sont ajoutés a votre inventaire.");
						joueur.setNbCles(joueur.getNbCles() + nbClesTrouvees);
					}
					piece.setMonstreAssocie(null); // le monstre associé a la piece est mort
					Commande.ouvrirTresor(piece); // affiche le contenu du tresor et met a jour l'inventaire
					piece.save();
					break;
				}

				if (nbTresors == 0) {
					System.out.println("Fecilitation ! Vous avez ouvert tout les coffres du Chateau !");
					Commande.finDuJeu();
				}

			} else { // si le monstre de cet piece a deja été battu
				Commande.print("Vous avez deja vaincu le monstre de cet piece et vous avez deja ouvert son tresor.");
			}

			if (piece.getMonstreAssocie() == null) { // si le monstre de la piece a été vaincu
				sortieChoisie = Commande.printChoixSortie(piece);
				sortiePiecePrecedente = new Sortie("RetourPiecePrecedente", piece);
			}
		}
	}

	/**
	 * @return le score actuel de la partie
	 *         Chaque coffre ouvert rapporte 10 points
	 *         Chaque objet dans l'inventaire rapporte 2 points
	 *         Chaque cle rapporte un point
	 *         Le joueur pert un point sur son score par point de vie perdu
	 */
	public static int getScore() {
		return (NB_PIECES - nbTresors) * 10 + joueur.getInventaire().size() * 2 + joueur.getNbCles() - (Game.HP_INITIAL - joueur.getHp());
	}
}