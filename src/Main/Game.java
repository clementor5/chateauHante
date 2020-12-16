package Main;

import Chateau.Chateau;
import Chateau.Piece;
import Chateau.Sortie;
import EtreVivant.Joueur;
import Objet.Objet;

public class Game {

	public final static int		NB_PIECES					= 5;
	public final static int		HP							= 50;
	public final static int		TAILLE_INVENTAIRE_FINALE	= 20;

	public static Synchroniser	thread;

	public static boolean		continuer					= true;

	public static Joueur		joueur;
	public static Chateau		chateau;
	public static int			nbTresors					= NB_PIECES;
	private static int			score						= 0;

	/**
	 * Methode qui lance le jeu Renseigner les constantes NB_PIECES et HP pour choisir le nombre de pieces du chateau et votre nombre de point de vie
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// /!\ important : equilibre armes et monstre et equilibre cle et pieces /!\
		// nouveau type d'objet : les potions de vie et les gemmes (ameliore les degats d'une arme)
		// ajouter un cout en cle aux sorties : check
		// ajouter armes et monstres aux listes
		// entree.next() ou meilleure idée
		// améliorer affichage des objets
		// plus tard sauvegarde
		// c'est la merde pour les sorties
		// check arment se deteriorent et l'afficher

		chateau = new Chateau("Sombre Chateau", NB_PIECES);
		String nom = Commande.printAccueil(); // presentation du jeu
		joueur = new Joueur(nom, HP, TAILLE_INVENTAIRE_FINALE);

		thread = new Synchroniser();
		thread.start();

		Commande.printDebut(); // choix de l'inventaire de base + amene a l'interieur du chateau

		Sortie sortieChoisie = null;
		Sortie sortiePrecedente = null;
		while (continuer) {
			Piece piece = null;
			if (sortieChoisie == null) { // si le joueur vient tout juste de rentrer dans le chateau
				piece = new Piece(); // on cree la premiere piece du chateau et on la choisi
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
						sortieChoisie = sortiePrecedente;
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
						for (int i = 0; i < 3; i++) {
							joueur.addToInventaire(new Objet("cle", Objet.getTypeCle()));
						}
					}
					piece.setMonstreAssocie(null); // le monstre associé a la piece est mort
					Commande.ouvrirTresor(piece.getTresor()); // affiche le contenu du tresor et met a jour l'inventaire
					piece.setTresor(null); // le tresor de la piece a été ouvert
					break;
				}

				if (nbTresors == 0) {
					System.out.println("Fecilitation ! Vous avez ouvert tout les coffres du Chateau !");
					Commande.finDuJeu();
				}

			} else { // si le monstre de cet piece a deja été battu
				Commande.print("Vous avez deja vaincu le monstre de cet piece et vous avez deja ouvert son tresor.");
			}

			if (piece.getMonstreAssocie() == null) { // si le joueur de la piece a été vaincu
				sortieChoisie = Commande.printChoixSortie(piece);
				sortiePrecedente = sortieChoisie;
			} else { // si le joueur a choisi de fuir
				if (nbTresors != NB_PIECES) { // si le joueur n'est pas dans la premiere piece
					sortieChoisie = sortiePrecedente;
				}
			}
		}
		thread.continuer = false;
		thread.interrupt();
	}

	/**
	 * @return le score actuel de la partie
	 */
	public static int getScore() {
		score = nbTresors * 5 + joueur.getInventaire().size();
		return score;
	}
}