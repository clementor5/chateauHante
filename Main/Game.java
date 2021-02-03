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
	public static Sortie	sortieChoisie				= null;
	public static Sortie	sortiePrecedente			= null;

	/**
	 * Methode qui lance le jeu Renseigner les constantes NB_PIECES et HP pour choisir le nombre de pieces du chateau et votre nombre de point de vie
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String nom = Commandes.printAccueil();
		joueur = new Joueur(nom, HP_INITIAL, TAILLE_INVENTAIRE_FINALE);
		chateau = new Chateau("Sombre Chateau", NB_PIECES);

		Commandes.printDebut(); // choix de l'inventaire de base + amene a l'interieur du chateau

		boucle();
	}

	/**
	 * boucle du jeu
	 */
	public static void boucle() {
		while (true) {
			Piece piece = null;
			if (sortieChoisie == null) { // si le joueur vient tout juste de rentrer dans le chateau
				piece = chateau.getPieces().get(0); // on choisi une piece du chateau
			} else {
				piece = sortieChoisie.getPieceAssociee(); // on recupere la piece associée a la sortie que le joueur vient de choisir
			}
			Commandes.print("Vous voila maintenant dans la piece n°" + piece.getNum() + ".");

			if (piece.getMonstreAssocie() != null) { // si le monstre de cet piece n'a pas encore été battu
				int victoire = Commandes.printCombat(piece.getMonstreAssocie()); // combat contre le monstre
				switch (victoire) {
				case -1: // le joueur a perdu
					Commandes.finDuJeu();
					break;
				case 0: // le joueur a choisi de s'enfuir
					if (sortieChoisie == null) { // si le joueur est dans la premiere piece
						Commandes.print("La porte d'entrée du chateau est verouillée ! Vous ne pouvez pas vous enfuir !");
					} else {
						sortieChoisie = sortiePrecedente;
						Commandes.print("Vous vous enfuyez et retournez dans la pièce d'où vous venez");
					}
					break;
				case 1: // le joueur a gagné
					if (chateau.getNbTresorsOuverts() == NB_PIECES) { // si on vient de vaincre le monstre de la premiere piece
						joueur.setTailleInventaire(TAILLE_INVENTAIRE_FINALE); // on lui fait gagner un nouveau sac
						Commandes.print("Le monstre que vous avez vaincu possede un sac plus grand !");
						Commandes.print("Votre sac peut desormais contenir jusqu'a " + TAILLE_INVENTAIRE_FINALE + " objets !");
						int nbClesTrouvees = 3;
						Commandes.print("Vous avez trouvé " + nbClesTrouvees + " cles dans le sac !");
						Commandes.print("Elle sont ajoutés a votre inventaire.");
						joueur.setNbCles(joueur.getNbCles() + nbClesTrouvees);
					}
					piece.setMonstreAssocie(null); // le monstre associé a la piece est mort
					Commandes.ouvrirTresor(piece); // affiche le contenu du tresor et met a jour l'inventaire
					piece.save();
					break;
				}

				if (chateau.getNbTresorsOuverts() == 0) {
					System.out.println("Fecilitation ! Vous avez ouvert tout les coffres du Chateau !");
					Commandes.finDuJeu();
				}

			} else { // si le monstre de cet piece a deja été battu
				Commandes.print("Vous avez deja vaincu le monstre de cet piece et vous avez deja ouvert son tresor.");
			}

			if (piece.getMonstreAssocie() == null) { // si le monstre de la piece a été vaincu
				sortieChoisie = Commandes.printChoixSortie(piece);
				sortiePrecedente = new Sortie("RetourPiecePrecedente", piece);
			}
		}
	}

	/**
	 * Chaque coffre ouvert rapporte 10 points Chaque objet dans l'inventaire rapporte 2 points Chaque cle rapporte un point Le joueur pert un point sur son score par point de vie perdu
	 * 
	 * @return le score actuel de la partie
	 */
	public static int getScore() {
		return (NB_PIECES - chateau.getNbTresorsOuverts()) * 10 + joueur.getInventaire().size() * 2 + joueur.getNbCles()
				- (Game.HP_INITIAL - joueur.getHp());
	}
}