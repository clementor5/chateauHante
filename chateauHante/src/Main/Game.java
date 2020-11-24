package Main;

import Chateau.Chateau;
import Chateau.Piece;
import Chateau.Sortie;
import EtreVivant.Joueur;
import Objet.Objet;

public class Game {

	public static Synchroniser	thread;

	public final static int		NB_PIECES					= 5;
	public final static int		HP							= 50;
	public final static int		TAILLE_INVENTAIRE_FINALE	= 20;

	private static Joueur		joueur;
	private static Chateau		chateau;
	private static int			score						= 0;
	private static int			nbTresors					= NB_PIECES;

	static boolean				continuer					= true;

	/**
	 * Methode qui lance le jeu Renseigner les constantes NB_PIECES et HP pour
	 * choisir le nombre de pieces du chateau et votre nombre de point de vie
	 */
	public static void main(String[] args) {
		// mettre fin au jeu a tout moment -> a tester
		// /!\ important : equilibre armes et monstre et equilibre cle et pieces /!\
		// nouveau type d'objet : les potions de vie et les gemmes (ameliore les degats
		// d'une arme)
		// gerer objets instance of
		// choix des objets par leur id
		// ajouter un cout en cle aux sorties

		chateau = new Chateau("Sombre Chateau", NB_PIECES);
		String nom = Commande.printAccueil(chateau); // presentation du jeu
		joueur = new Joueur(nom, HP, TAILLE_INVENTAIRE_FINALE);

		thread = new Synchroniser();
		thread.start();

		Commande.printDebut(); // choix de l'inventaire de base + amene a l'interieur du chateau

		Sortie	sortieChoisie		= null;
		Sortie	sortiePrecedente	= null;
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
					sortieChoisie = sortiePrecedente;
					break;
				case 1: // le joueur a gagné
					if (nbTresors == NB_PIECES) { // si on vient de vaincre le monstre de la premiere piece
						joueur.setTailleInventaire(TAILLE_INVENTAIRE_FINALE); // on lui fait gagner un nouveau sac
						Commande.print("Le monstre que vous avez vaincu possede un sac plus grand !");
						Commande.print(
								"Votre sac peut desormais contenir jusqu'a " + TAILLE_INVENTAIRE_FINALE + " objets !");
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

			if (!sortieChoisie.equals(sortiePrecedente) || sortieChoisie == null) { // si le joueur n'a pas choisi de
																					// fuir le monstre
				sortiePrecedente = sortieChoisie;
				sortieChoisie = Commande.printChoixSortie(piece);
			}
		}
		thread.interrupt();
	}

	/**
	 * @param continuer == false ssi le jeu doit se stoper (victoire, defaire ou
	 *                  quitter)
	 */
	public static void setContinuer(boolean continuer) {
		Game.continuer = continuer;
	}

	/**
	 * @return une copie du joueur
	 */
	public static Joueur getJoueur() {
		return joueur;
	}

	/**
	 * Modifie l'etat du joeur
	 * 
	 * @param joueur
	 */
	public static void setJoueur(Joueur joueur) {
		Game.joueur = joueur;
	}

	public static Chateau getChateau() {
		return chateau;
	}

	/**
	 * @return le score actuel de la partie
	 */
	public static int getScore() {
		return score;
	}

	/**
	 * Modifie le score de la partie
	 * 
	 * @param score
	 */
	public static void setScore(int score) {
		Game.score = score;
	}

	/**
	 * @return le nombre total de coffres qui ont été ouverts actuellement
	 */
	public static int getNbCoffres() {
		return nbTresors;
	}

	public static void setNbCoffres(int nbCoffres) {
		Game.nbTresors = nbCoffres;
	}
}