package Main;

import java.util.ArrayList;
import java.util.Scanner;

import Chateau.Chateau;
import Chateau.Piece;
import Chateau.Sortie;
import Chateau.Tresor;
import EtreVivant.Joueur;
import EtreVivant.Monstre;
import Objet.Arme;
import Objet.Objet;

public class Commande {

	public static Joueur	joueur							= null;

	/* Commandes spéciales */
	final static String		COMMANDE_AIDE					= "Aide";
	final static String		COMMANDE_INVENTAIRE				= "Inventaire";
	final static String		COMMANDE_HP						= "HP";
	final static String		COMMANDE_NB_TRESORS_RESTANTS	= "Coffres";
	final static String		COMMANDE_SCORE					= "Score";
	final static String		COMMANDE_QUITTER_JEU			= "Quitter";

	/* Commandes classiques */
	final static String		COMMANDE_CHOISIR_SORTIE			= "Choisir ";												// +
																														// nomSortie
	final static String		COMMANDE_CHOISIR_ARME			= "Choisir ";												// +
																														// nomArme

	final static String		CONTENU_AIDE					= ">>>>> Aide : \n" + ">>>>> Commandes classiques : \n"
			+ ">>> Pour choisir une sortie : \"Choisir nomSortie\" \n"
			+ ">>> Pour choisir l'arme avec laquelle attaquer un monstre : \"Choisir nomArme\" \n"
			+ ">>>>> Commandes spéciales : \n" + ">>> Pour regarder le contenu de votre inventaire : \""
			+ COMMANDE_INVENTAIRE + "\" \n" + ">>> Pour savoir combien de points de vie tu as : \"" + COMMANDE_HP
			+ "\" \n" + ">>> Pour savoir combien de coffres il reste dans le chateau : \""
			+ COMMANDE_NB_TRESORS_RESTANTS + "\"" + ">>> Pour savoir quel score tu as : \"" + COMMANDE_SCORE + "\" \n"
			+ ">>> Pour quitter le jeu : \"" + COMMANDE_QUITTER_JEU + "\"";

	static Scanner			entree							= new Scanner(System.in);

	/**
	 * Cette méhode pose la question qu'on lui donne en argument tant que la réponse
	 * est une commande spéciale Sinon elle retourne la réponse donnée Si c'est une
	 * commande spéciale : affiche le message associé
	 * 
	 * @param question la question posée
	 * @return la reponse donnée lorsque ce n'est pas une commande spéciale
	 */
	public static String verifCommandeSpeciale(String question) {
		String	reponse	= "";
		boolean	ok		= false;
		int		nbCles	= 0;
		while (!ok) {
			print(question);
			reponse = entree.next();
			if (reponse.equalsIgnoreCase(COMMANDE_AIDE)) {
				System.out.println(CONTENU_AIDE);
			} else if (reponse.equalsIgnoreCase(COMMANDE_INVENTAIRE)) {
				ArrayList<Objet> inventaire = joueur.getInventaire();
				for (int i = 0; i < joueur.getInventaire().size(); i++) { // pour chaque objet dans
																			// l'inventaire du joueur
					Objet objet = joueur.getInventaire().get(i);
					if (objet.getType() == Objet.getTypeCle()) { // si c'est une cle
						nbCles++;
					} else if (objet instanceof Arme) {
						Arme arme = (Arme) objet;
						printTitre("arme n°" + i + " :");
						print(arme.toString());
					} else {
						print(objet.toString());
					}
				}
				print("Vous avez " + nbCles + " cles.");
			} else if (reponse.equalsIgnoreCase(COMMANDE_HP)) {
				print("Vous avez " + joueur.getHp() + "points de vie.");
			} else if (reponse.equalsIgnoreCase(COMMANDE_NB_TRESORS_RESTANTS)) {
				print("Il reste " + Game.getNbCoffres() + " coffres dans le chateau.");
			} else if (reponse.equalsIgnoreCase(COMMANDE_SCORE)) {
				print("Votre score est actuellement de " + Game.getScore() + " points.");
			} else if (reponse.equalsIgnoreCase(COMMANDE_QUITTER_JEU)) {
				quitter();
			} else {
				ok = true;
			}
		}
		return reponse;
	}

	/**
	 * Presente le jeu et demande le nom du joueur
	 * 
	 * @param chateau
	 * @return le nom du joueur
	 */
	public static String printAccueil(Chateau chateau) {
		print("Bienvenue dans le jeu du " + chateau.getNom() + " ! ! ! \n" + ">>> Entrez \"" + COMMANDE_AIDE
				+ "\" si vous avez besoin d'aide.");
		String reponse;
		do {
			reponse = verifCommandeSpeciale("Souhaitez vous commencer la partie ? \n>>> Repondez par \"Oui\" \"Non\".");

		} while (!reponse.equalsIgnoreCase("OUI"));
		return reponse;
	}

	/**
	 * Explique les regles du jeu puis fais choisir un inventaire de debut au joueur
	 * puis amene le joueur dans la premiere piece
	 * 
	 * @param _joueur
	 */
	public static void printDebut() {
		print("Vous êtes un aventurier à la recherche de tresors.");
		print("Des villageois du coin vous ont parler d'un chateau hanté qui abriterait d'apres les legendes de nombreux tresors.");
		print("Les legendes disent aussi que les tresors sont fermements gardées par des monstres plus effrayants les uns que les autres !");
		print("Saurez vous relever le défi ?");
		print("Apres quelques recherches vous avez finalement réussi a localiser le chateau en question.");
		print("Avant de partir, vous devez vous equiper, votre sac peut contenir jusque 3 objets.");
		print("Vous disposez de : ");
		ArrayList<Objet>	objetsChoisi	= new ArrayList<Objet>();
		ArrayList<Objet>	objets			= new ArrayList<Objet>();
		for (int i = 1; i < Outils.alea(6, 11); i++) { // On propose de 5 a 10 objets
			String type = Objet.typeList.get(Outils.alea(0, Objet.typeList.size() - 1)); // on choisi un type d'objet
																							// aleatoire
			if (type.equals(Objet.getTypeArme()) || type.equals(Objet.getTypeCle())) { // 2 chances sur 3 de faire
																						// apparaitre une arme
				String	nom		= Arme.nomList.get(Outils.alea(0, Arme.nomList.size() - 1));	// on choisi un nom
																								// d'arme
																								// aleatoire
				Arme	arme	= new Arme(nom, Objet.getTypeArme(),
						Arme.etatList.get(Outils.alea(0, Arme.etatList.size() - 1)),
						Outils.alea(1, (int) Math.round(0.4 * Game.HP)));						// on cree une arme dans
																								// un etat aleatoire
																								// avec des degats
																								// aleatoires
				objets.add(arme); // on l'ajoute au tresor
			} else if (type.equals(Objet.getTypePotion())) {
				Objet objet = new Objet("potion", type); // #TODO gerer d'autres types d'objet plus tard
				objets.add(objet); // on l'ajoute au tresor
			}
		}
		for (Objet objet : objets) {
			print(objet.toString() + "\n");
		}
		for (int i = 0; i < 3; i++) { // on choisi 3 objets
			Objet objetChoisi = null;
			do {
				String reponse = verifCommandeSpeciale("Veuillez choisir l'objet n°" + i + " en entrant son nom :");
				for (Objet objet : objets) { // pour chaque objet
					if (!objetsChoisi.contains(objet)) { // s'il n'a pas deja été choisi
						if (reponse.equalsIgnoreCase(objet.getNom()) && objetChoisi == null) { // si on a pas deja
																								// trouvé un objet qui
																								// correspond
							objetChoisi = objet;
						}
					}
				}
			} while (objetChoisi == null);
			objetsChoisi.add(objetChoisi);
		}
		try {
			Game.thread.wait(500);
			joueur.setInventaire(objetsChoisi);
			Game.setJoueur(joueur); // on met a jour l'inventaire du joueur
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		joueur.afficherInventaire();
		print("Vous voila maintenant devant l'immense porte d'entrée du chateau,");
		print("mais a votre arrivée, celle-ci s'ouvre toute seule comme par magie !");
		print("Vous entrez donc dans le chateau...");
	}

	/**
	 * Presente les sorties de la piece et demande au joueur de choisir une sortie
	 * 
	 * @param piece
	 * @return la sortie choisie par le joueur
	 */
	public static Sortie printChoixSortie(Piece piece) {
		print("Vous voila maintenant dans une nouvelle piece.");
		print("Elle possède " + piece.getSorties().size() + "sorties :");
		for (int i = 0; i < piece.getSorties().size(); i++) {
			Sortie sortie = piece.getSorties().get(i);
			print("Sortie n°" + i + " : " + sortie.getNom());
		}
		String	reponse;
		Sortie	sortieChoisie	= null;
		do {
			reponse = verifCommandeSpeciale("Quel sortie choisissez vous ?");
			for (Sortie sortie : piece.getSorties()) {
				if (reponse.equalsIgnoreCase(sortie.getNom())) {
					sortieChoisie = sortie;
				}
			}
			if (sortieChoisie == null) {
				print("ERREUR: Veuillez entrer une sortie valide.");
			}
		} while (sortieChoisie == null);
		return sortieChoisie;
	}

	/**
	 * effectue le combat entre le joueur et le monstre
	 * 
	 * @param monstre
	 * @return -1 si le joueur a perdu, 0 si il a choisir de s'enfuir et 1 s'il a
	 *         gagné
	 */
	public static int printCombat(Monstre monstre) {
		printTitre("Un monstre protege le coffre de cette piece !");
		print("C'est " + monstre.getNom());
		print("Il a " + monstre.getHp() + " points de vie et " + monstre.getAttaque() + " points d'attaques");
		print("Il faudra le vaincre pour pouvoir ouvrir le tresor. ");
		String reponse = verifCommandeSpeciale(
				"Voulez vous le combattre ? Vous ne pourrez plus vous enfuir si vous engagez le combat ! \n>>> Entrez \"Oui\" si vous souhaitez le combattre, \"Non\" sinon.");
		if (reponse.equalsIgnoreCase("non")) { // si le joueur decide de s'enfuir
			return 0;
		}

		boolean	continuer	= true;
		int		victoire	= 0;
		while (continuer) {
			print("Pour attaquer le monstre choisissez une arme.");
			Arme armeChoisie = printChoixArme();
			print("Vous infligez " + armeChoisie.getDegats() + "points de degats au monstre.");
			monstre.setHp(monstre.getHp() - armeChoisie.getDegats()); // on met a jour la vie du monstre
			if (monstre.getHp() > 0) { // si le monstre a survecu
				print("Il lui reste encore " + monstre.getHp() + " points de vie.");
				print("Le monstre vous attaque et vous inflige " + monstre.getAttaque() + "points de degats !");
				int hp = joueur.getHp() - monstre.getAttaque();
				try {
					Game.thread.wait(500);
					joueur.setHp(hp);
					Game.setJoueur(joueur);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (joueur.getHp() > 0) { // si le joueur a survecu
					print("Desormais, il vous reste " + joueur.getHp() + " points de vie.");
				} else { // si le joueur est mort
					return -1;
				}
			} else { // si le monstre est mort
				print("Felicitation ! Vous avez vaincu le monstre !");
				print("Vous pouvez maintenant ouvrir le tresor !");
				return 1;
			}
			if (armeChoisie.getEtat() == Arme.getMAUVAISE_ETAT()) {
				try {
					Game.thread.wait(500);
					joueur.removeToInventaire(armeChoisie);
					Game.setJoueur(joueur);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (armeChoisie.getEtat() == Arme.getBON_ETAT()) {
				armeChoisie.setEtat(Arme.getMAUVAISE_ETAT());
			} else {
				armeChoisie.setEtat(Arme.getBON_ETAT());
			}
		}
		return victoire;
	}

	/**
	 * demande au joueur de choisir une arme
	 * 
	 * @return l'arme chosie
	 */
	public static Arme printChoixArme() {
		String	reponse;
		Arme	armeChoisie	= null;
		do {
			reponse = verifCommandeSpeciale("Quel arme souhaitez vous choisir ?");
			for (Objet objet : joueur.getInventaire()) {
				if (objet.getType().equals(Objet.getTypeArme())) {
					if (reponse.equalsIgnoreCase(objet.getNom())) {
						armeChoisie = (Arme) objet;
					}
				}
			}
			if (armeChoisie == null) {
				print("ERREUR: Veuillez entrer une arme valide.");
			}
		} while (armeChoisie == null);
		return armeChoisie;
	}

	/**
	 * Ouvre le tresor et ajoute les nouveaux objets a l'inventaire du joueur
	 * 
	 * @param tresor
	 */
	public static void ouvrirTresor(Tresor tresor) {
		print(tresor.toString());
		for (Objet objet : tresor.getContenu()) {
			try {
				Game.thread.wait(500);
				joueur.addToInventaire(objet);
				Game.setJoueur(joueur);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		print("Felicitation ! Les nouveaux objets ont étés ajoutés a votre inventaire !");
		Game.setNbCoffres(Game.getNbCoffres() - 1); // on reduit de 1 le nombre de coffres dans le chateau
	}

	/**
	 * Affiche les infos finale du jeu et met fin au jeu
	 */
	public static void finDuJeu() {
		if (joueur.getHp() <= 0) {
			print("Vous êtes mort avant d'avoir pu ouvrir tout les tresors...");
		} else {
			print("Il vous restait " + joueur.getHp() + " points de vie.");
		}
		print("Votre score final est de :" + Game.getScore() + " points.");
		print("Vous avez ouvert " + Game.getNbCoffres() + " coffres au total.");
		Game.thread.interrupt();
		System.exit(0); // a tester
	}

	/**
	 * Demande au joueur s'il souhaite sauvegarder, si oui, sauvegarde puis met fin
	 * au jeu
	 */
	public static void quitter() {
		String	reponse;
		boolean	continuer	= true;
		do {
			reponse = verifCommandeSpeciale("Voulez vous sauvegader votre progression avant de quitter ?");
			if (reponse.equalsIgnoreCase("OUI")) {
				sauvegarder();
				continuer = false;
			}
			if (reponse.equalsIgnoreCase("NON")) {
				continuer = false;
			}
		} while (continuer);
		System.exit(0); // a tester
	}

	/**
	 * Sauvegarde la progression dans un fichier texte
	 */
	public static void sauvegarder() {
		// plus tard quand le jeu sera terminé
	}

	/**
	 * affiche le texte avec les >>> devant
	 * 
	 * @param message le message a afficher
	 */
	public static void print(String message) {
		System.out.println(">>> " + message);
	}

	/**
	 * affiche le texte avec les >>>>> devant
	 * 
	 * @param message le message a afficher
	 */
	public static void printTitre(String message) {
		System.out.println(">>>>> " + message);
	}
}
