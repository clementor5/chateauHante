package Main;

import java.util.ArrayList;
import java.util.Scanner;

import Chateau.Piece;
import Chateau.Sortie;
import Chateau.Tresor;
import EtreVivant.Joueur;
import EtreVivant.Monstre;
import Objet.Arme;
import Objet.Gemme;
import Objet.Objet;
import Objet.Potion;
import Sauvegarde.LoadSauvegarde;
import Sauvegarde.Sauvegarde;

public class Commande {

	/* Commandes spéciales */
	final static String	COMMANDE_AIDE					= "Aide";
	final static String	COMMANDE_INVENTAIRE				= "Inventaire";
	final static String	COMMANDE_HP						= "HP";
	final static String	COMMANDE_POTION					= "Potion";
	final static String	COMMANDE_GEMME					= "Gemme";
	final static String	COMMANDE_NB_TRESORS_RESTANTS	= "Coffres";
	final static String	COMMANDE_SCORE					= "Score";
	final static String	COMMANDE_QUITTER_JEU			= "Quitter";
	final static String	COMMANDE_SAUVEGARDER			= "Sauvegarder";
	final static String	COMMANDE_REPRENDRE				= "Reprendre";

	final static String	CONTENU_AIDE					= ">>>>> Aide : \n" + ">>>>> Commandes classiques : \n"
			+ ">>> Pour choisir une sortie : \"nomSortie\" \n" + ">>> Pour choisir l'arme avec laquelle attaquer un monstre : \"idArme\" \n"
			+ ">>>>> Commandes spéciales : \n" + ">>> Pour regarder le contenu de votre inventaire : \"" + COMMANDE_INVENTAIRE + "\" \n"
			+ ">>> Pour savoir combien de points de vie tu as : \"" + COMMANDE_HP + "\" \n" + ">>> Pour utiliser une potion : \"" + COMMANDE_POTION
			+ "\" \n" + ">>> Pour utiliser une potion : \"" + COMMANDE_GEMME + "\" \n"
			+ ">>> Pour savoir combien de coffres il reste dans le chateau : \"" + COMMANDE_NB_TRESORS_RESTANTS + "\" \n"
			+ ">>> Pour savoir quel score tu as : \"" + COMMANDE_SCORE + "\" \n" + ">>> Pour quitter le jeu : \"" + COMMANDE_QUITTER_JEU + "\" \n"
			+ ">>> Pour sauvegarder : \"" + COMMANDE_SAUVEGARDER + "\" \n" + ">>> Pour reprendre une partie à partir d'une sauvegarde : \""
			+ COMMANDE_REPRENDRE + "\"";

	static Scanner		entree							= new Scanner(System.in);

	/**
	 * Cette méhode pose la question qu'on lui donne en argument tant que la réponse est une commande spéciale Sinon elle retourne la réponse donnée Si c'est une commande spéciale : affiche le message
	 * associé
	 * 
	 * @param question la question posée
	 * @return la reponse donnée lorsque ce n'est pas une commande spéciale
	 */
	public static String verifCommandeSpeciale(String question) {
		String reponse = "";
		boolean ok = false;
		while (!ok) {
			System.err.println(">>> " + question);
			reponse = entree.nextLine();
			if (reponse.equalsIgnoreCase(COMMANDE_AIDE)) {
				System.out.println(CONTENU_AIDE);
			} else if (reponse.equalsIgnoreCase(COMMANDE_INVENTAIRE)) {
				Game.joueur.afficherInventaire();
			} else if (reponse.equalsIgnoreCase(COMMANDE_HP)) {
				print("Vous avez " + Game.joueur.getHp() + " points de vie.");
			} else if (reponse.equalsIgnoreCase(COMMANDE_POTION)) {
				printChoixPotion();
			} else if (reponse.equalsIgnoreCase(COMMANDE_GEMME)) {
				printChoixGemme();
			} else if (reponse.equalsIgnoreCase(COMMANDE_NB_TRESORS_RESTANTS)) {
				print("Il reste " + Game.chateau.getNbTresorsOuverts() + "/" + Game.NB_PIECES + " coffres dans le chateau.");
			} else if (reponse.equalsIgnoreCase(COMMANDE_SCORE)) {
				print("Votre score est actuellement de " + Game.getScore() + " points.");
			} else if (reponse.equalsIgnoreCase(COMMANDE_QUITTER_JEU)) {
				quitter();
			} else if (reponse.equalsIgnoreCase(COMMANDE_SAUVEGARDER)) {
				boolean continuer = true;
				do {
					print("Vous vous appretez a sauvegarder au nom de " + Game.joueur.getNom());
					System.err.println(
							">>> Voulez vous changer de nom ? Saisissez votre nouveau nom ou bien repondez NON pour sauvegarder avec ce nom");
					reponse = entree.nextLine();

					if (reponse.equals("")) {
						print("Le nom ne peut être null");
					} else {
						if (!reponse.equalsIgnoreCase("NON")) {
							Game.joueur.setNom(reponse);
						} else {
							continuer = false;
						}
					}
				} while (continuer);
				Sauvegarde.save();
			} else if (reponse.equalsIgnoreCase(COMMANDE_REPRENDRE)) {
				boolean continuer = true;
				do {
					print("Vous vous appretez a reprendre la sauvegarde au nom de " + Game.joueur.getNom());
					System.err.println(
							">>> Voulez vous changer de nom ? Saisissez votre nouveau nom ou bien repondez NON pour charger la sauvegarde avec ce nom");
					reponse = entree.nextLine();

					if (reponse.equals("")) {
						print("Le nom ne peut être null");
					} else {
						if (!reponse.equalsIgnoreCase("NON")) {
							Game.joueur.setNom(reponse);
						} else {
							continuer = false;
						}
					}
				} while (continuer);
				LoadSauvegarde.load();
			} else {
				ok = true;
			}
		}
		return reponse;
	}

	/**
	 * Demande au joueur s'il souhaite commencer le jeu et lui fait choisir un nom
	 * 
	 * @return le nom du joueur
	 */
	public static String printAccueil() {
		print("Bienvenue dans le jeu du Chateau Hanté ! ! !");
		print("Lorsque le texte est rouge, cela signifie qu'une reponse est attendue de votre part.");
		print("Pour répondre, il vous suffit de saisir le texte dans la console et de valider avec la touche entrée.");
		print("Pas d'inquiétude, la réponse n'est pas sensible à la casse, mais cela ne fonctionnera pas si vous faites des fautes de frappe.");
		print("Lorsqu'une réponse est attendue, vous pouvez toujours si vous le souhaitez saisir une commande spéciale pour obtenir une information, utiliser une potion ou une gemme...");
		print("Entrez \"" + COMMANDE_AIDE + "\" pour obtenir de l'aide sur les commandes spéciales.");
		String reponse = "";

		do {
			System.err.println(">>> Tout d'abord, quel est votre nom ?");
			reponse = entree.nextLine();
			if (reponse.equals("")) {
				print("le nom ne peut être null !");
			} else if (reponse.equalsIgnoreCase("reprendre")) {
				boolean continuer = true;
				do {
					loop: while (true) {
						System.err.println(">>> Tout d'abord, quel est le nom de votre sauvegarde ?");
						reponse = entree.nextLine();
						if (reponse.equals("")) {
							print("le nom ne peut être null !");
						} else {
							Game.joueur = new Joueur(reponse, Game.HP_INITIAL, Game.TAILLE_INVENTAIRE_FINALE);
							break loop;
						}
					}
					print("Vous vous appretez a reprendre la sauvegarde au nom de " + Game.joueur.getNom());
					System.err.println(
							">>> Voulez vous changer de nom ? Saisissez votre nouveau nom ou bien repondez NON pour charger la sauvegarde avec ce nom");
					reponse = entree.nextLine();

					if (reponse.equals("")) {
						print("Le nom ne peut être null");
					} else {
						if (!reponse.equalsIgnoreCase("NON")) {
							Game.joueur.setNom(reponse);
						} else {
							continuer = false;
						}
					}
				} while (continuer);
				LoadSauvegarde.load();
			} else {
				return reponse;
			}
		} while (true);
	}

	/**
	 * Explique les regles du jeu puis fais choisir un inventaire de debut au joueur puis amene le joueur dans la premiere piece
	 */
	public static void printDebut() {
		print("Vous êtes un aventurier à la recherche de tresors.");
		print("Des villageois du coin vous ont parler d'un chateau hanté qui abriterait d'apres les legendes de nombreux tresors.");
		print("Les legendes disent aussi que les tresors sont fermements gardées par des monstres plus effrayants les uns que les autres !");
		print("Saurez vous relever le défi ?");
		print("Apres quelques recherches vous avez finalement réussi a localiser le chateau en question.");
		print("Avant de partir, vous devez vous equiper : votre sac peut contenir jusqu'à 3 objets.");
		print("Vous disposez de : ");
		ArrayList<Objet> objetsChoisi = new ArrayList<Objet>();
		ArrayList<Objet> objets = new ArrayList<Objet>();
		for (int i = 1; i < Outils.alea(6, 11); i++) { // On propose de 5 a 10 objets
			// on ne propose que des armes au debut
			String nom = Arme.nomList.get(Outils.alea(0, Arme.nomList.size() - 1)); // on choisi un nom d'arme aleatoire
			Arme arme = new Arme(nom, Objet.getTypeArme(), Arme.etatList.get(Outils.alea(0, Arme.etatList.size() - 1)),
					Outils.alea(1, (int) Math.round(0.4 * Game.HP_INITIAL))); // on cree une arme dans un etat aleatoire avec des degats aleatoires
			objets.add(arme); // on l'ajoute au tresor
		}
		for (Objet objet : objets) {
			print(objet.toString() + "\n");
		}
		for (int i = 0; i < 3; i++) { // on choisi 3 objets
			Objet objetChoisi = null;
			do {
				int reponse = 0;
				try {
					reponse = Integer.parseInt(verifCommandeSpeciale("Veuillez choisir l'objet n°" + i + " en entrant son id :"));
					loop: for (Objet objet : objets) { // pour chaque objet
						if (objetChoisi == null) {
							if (!objetsChoisi.contains(objet)) { // s'il n'a pas deja été choisi
								if (reponse == objet.getId()) { // si on a pas deja trouvé un objet qui correspond
									objetChoisi = objet;
								}
							}
						} else {
							break loop;
						}
					}
				} catch (Exception e) {
					// ignorer
				}
				if (objetChoisi == null) {
					Commande.print("ERREUR : L'id que vous avez saisi ne correspond a aucun objet parmis ceux proposés !");
				}
			} while (objetChoisi == null);
			objetsChoisi.add(objetChoisi);
		}
		Game.joueur.setInventaire(objetsChoisi);// on met a jour l'inventaire du joueur
		Game.joueur.afficherInventaire();
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
		boolean ok = false;

		print("La pièce possède " + piece.getSorties().size() + " sorties :");
		for (int i = 0; i < piece.getSorties().size(); i++) {
			Sortie sortie = piece.getSorties().get(i);
			print("Sortie n°" + i + " : " + sortie.getNom());
		}
		String reponse;
		Sortie sortieChoisie = null;
		do {
			sortieChoisie = null;
			reponse = verifCommandeSpeciale("Quel sortie choisissez vous ?");
			for (Sortie sortie : piece.getSorties()) {
				if (reponse.equalsIgnoreCase(sortie.getNom())) {
					sortieChoisie = sortie;
				}
			}
			if (sortieChoisie == null) {
				print("ERREUR: Veuillez entrer un nom de sortie valide.");
			} else {
				if (sortieChoisie.getCoutCle() > Game.joueur.getNbCles()) { // si le joueur n'a pas assez de cles pour prendre cette sortie
					print("Cette sortie dispose de " + sortieChoisie.getCoutCle() + " serrures mais vous n'avez que " + Game.joueur.getNbCles()
							+ " clés...");
				} else {
					if (sortieChoisie.getCoutCle() == 0) {
						sortieChoisie.ouvrirPorte();
						ok = true;
					} else {
						boolean correct = false;
						do {
							reponse = verifCommandeSpeciale("Cette sortie dispose de " + sortieChoisie.getCoutCle() + " serrures, \n>>> Vous avez "
									+ Game.joueur.getNbCles() + " clés. Voulez vous ouvrir la porte ? Repondez par \"OUI\" ou \"NON\"");
							if (reponse.equalsIgnoreCase("OUI")) {
								sortieChoisie.ouvrirPorte();
								correct = true;
								ok = true;
							} else if (reponse.equalsIgnoreCase("NON")) {
								correct = true;
							}
						} while (!correct);
					}
				}
			}
		} while (!ok);
		return sortieChoisie;
	}

	/**
	 * effectue le combat entre le joueur et le monstre
	 * 
	 * @param monstre
	 * @return -1 si le joueur a perdu, 0 si il a choisir de s'enfuir et 1 s'il a gagné
	 */
	public static int printCombat(Monstre monstre) {
		printTitre("Un monstre protege le coffre de cette piece !");
		print("C'est " + monstre.getNom());
		print("Ce monstre a " + monstre.getHp() + " points de vie et " + monstre.getAttaque() + " points d'attaques");
		print("Il faudra le vaincre pour pouvoir ouvrir le tresor. ");
		String reponse = null;
		do {
			reponse = verifCommandeSpeciale(
					"Voulez vous le combattre ? Vous ne pourrez plus vous enfuir si vous engagez le combat ! \n>>> Entrez \"Oui\" si vous souhaitez le combattre, \"Non\" sinon.");
			if (reponse.equalsIgnoreCase("non")) { // si le joueur decide de s'enfuir
				return 0;
			}
		} while (!reponse.equalsIgnoreCase("oui"));

		boolean continuer = true;
		int victoire = 0;
		while (continuer) {
			Arme armeChoisie = printChoixArme();
			armeChoisie.utiliser();
			print("Vous infligez " + armeChoisie.getDegats() + " points de degats au monstre.");
			monstre.setHp(monstre.getHp() - armeChoisie.getDegats()); // on met a jour la vie du monstre
			if (monstre.getHp() > 0) { // si le monstre a survecu
				print("Il lui reste encore " + monstre.getHp() + " points de vie.");
				print("Le monstre vous attaque et vous inflige " + monstre.getAttaque() + " points de degats !");
				int hp = Game.joueur.getHp() - monstre.getAttaque();
				Game.joueur.setHp(hp);
				if (Game.joueur.getHp() > 0) { // si le joueur a survecu
					print("Desormais, il vous reste " + Game.joueur.getHp() + " points de vie.");
				} else { // si le joueur est mort
					return -1;
				}
			} else { // si le monstre est mort
				print("Felicitation ! Vous avez vaincu le monstre !");
				print("Vous pouvez maintenant ouvrir le tresor !");
				return 1;
			}
			if (armeChoisie.getEtat() == Arme.getMAUVAISE_ETAT()) {
				Game.joueur.removeToInventaire(armeChoisie);
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
		int reponse;
		Arme armeChoisie = null;
		do {
			try {
				reponse = Integer.parseInt(verifCommandeSpeciale("Avec quel arme souhaitez vous attaquer le monstre ? (saisissez l'id de l'arme)"));
				loop: for (Objet objet : Game.joueur.getInventaire()) {
					if (objet.getType().equals(Objet.getTypeArme())) {
						if (armeChoisie == null) {
							if (reponse == objet.getId()) {
								armeChoisie = (Arme) objet;
							}
						} else {
							break loop;
						}
					}
				}
			} catch (Exception e) {
				// ignorer
			}
			if (armeChoisie == null) {
				Commande.print("ERREUR : L'id que vous avez saisi ne correspond a aucune arme dans votre inventaire !");
			}
		} while (armeChoisie == null);
		return armeChoisie;
	}

	/**
	 * Demande au joueur quel potion il souhaite utiliser si il souhaite en utiliser une s'il en choisit une, l'utilise
	 */
	private static void printChoixPotion() {
		boolean continuer = true;

		ArrayList<Potion> potions = new ArrayList<Potion>();
		for (Objet objet : Game.joueur.getInventaire()) {
			if (objet.getType().equals(Objet.getTypePotion())) {
				potions.add((Potion) objet);
			}
		}
		do {
			print("Vous avez " + potions.size() + " potions dans votre inventaire.");
			if (potions.size() > 0) {
				for (Potion potion : potions) {
					print(potion.toString());
				}

				String reponse = "";
				reponse = verifCommandeSpeciale("Saisissez l'id d'une potion si vous voulez l'utiliser, sinon saisissez \"STOP\"");
				if (reponse.equalsIgnoreCase("STOP")) {
					continuer = false;
				} else {
					loop: for (Potion potion : potions) {
						try {
							if (potion.getId() == Integer.parseInt(reponse)) {
								potion.utiliser();
								potions.remove(potion);
								break loop;
							}
						} catch (Exception e) {
							print("Erreur, votre saisie est incorrecte !");
						}
					}
				}
			} else {
				continuer = false;
			}
		} while (continuer);

	}

	/**
	 * Demande au joueur quel gemme il souhaite utiliser si il souhaite en utiliser une, s'il en choisit une, demande sur quelle arme il souhaite l'utiliser puis l'utilise
	 */
	private static void printChoixGemme() {
		boolean continuer = true;

		ArrayList<Gemme> gemmes = new ArrayList<Gemme>();
		for (Objet objet : Game.joueur.getInventaire()) {
			if (objet.getType().equals(Objet.getTypeGemme())) {
				gemmes.add((Gemme) objet);
			}
		}
		do {
			print("Vous avez " + gemmes.size() + " gemmes dans votre inventaire.");
			if (gemmes.size() > 0) {
				for (Gemme gemme : gemmes) {
					print(gemme.toString());
				}

				String reponse = "";
				reponse = verifCommandeSpeciale("Saisissez l'id d'une gemme si vous voulez l'utiliser, sinon saisissez \"STOP\"");
				if (reponse.equalsIgnoreCase("STOP")) {
					continuer = false;
				} else {
					loop: for (Gemme gemme : gemmes) {
						try {
							if (gemme.getId() == Integer.parseInt(reponse)) {
								gemme.utiliser();
								gemmes.remove(gemme);
								break loop;
							}
						} catch (Exception e) {
							print("Erreur, votre saisie est incorrecte !");
						}
					}
				}
			} else {
				continuer = false;
			}
		} while (continuer);

	}

	/**
	 * Ouvre le tresor et ajoute les nouveaux objets a l'inventaire du joueur
	 * 
	 * @param piece
	 */
	public static void ouvrirTresor(Piece piece) {
		Tresor tresor = piece.getTresor();
		print(tresor.toString());
		boolean ok = true;
		loop: for (Objet objet : tresor.getContenu()) { // on ajoute chaque objet à l'inventaire du joueur (sauf si l'inventaire est plein)
			ok = Game.joueur.addToInventaire(objet);
			if (!ok) {
				break loop;
			}
		}
		Game.joueur.setNbCles(Game.joueur.getNbCles() + tresor.getNbCles()); // on donne les cles du tresor au joueur
		print("Felicitation ! Les nouveaux objets ont étés ajoutés a votre inventaire !");
		print("Vous possedez maintenant " + Game.joueur.getNbCles() + " clés.");
		piece.setTresor(null); // le tresor de la piece a été ouvert
		Game.chateau.setNbTresorsOuverts(Game.chateau.getNbTresorsOuverts() - 1); // on reduit de 1 le nombre de coffres dans le chateau
	}

	/**
	 * Affiche les infos finale du jeu et met fin au jeu
	 */
	public static void finDuJeu() {
		if (Game.joueur.getHp() <= 0) {
			print("Vous êtes mort avant d'avoir pu ouvrir tout les tresors...");
		} else {
			print("Il vous restait " + Game.joueur.getHp() + " points de vie.");
		}
		print("Votre score final est de :" + Game.getScore() + " points.");
		print("Vous avez ouvert " + (Game.NB_PIECES - Game.chateau.getNbTresorsOuverts()) + " coffres au total.");
		System.exit(0);
	}

	/**
	 * Demande au joueur s'il souhaite sauvegarder, si oui, sauvegarde puis met fin au jeu
	 */
	public static void quitter() {
		String reponse;
		boolean continuer = true;
		do {
			reponse = verifCommandeSpeciale(
					"Voulez vous sauvegarder votre progression avant de quitter ? Repondez par \"OUI\", \"NON\", ou \"ANNULER\"");
			if (reponse.equalsIgnoreCase("OUI")) {
				Sauvegarde.save();
				System.exit(0);
			}
			if (reponse.equalsIgnoreCase("NON")) {
				System.exit(0);
			}
			if (reponse.equalsIgnoreCase("ANNULER")) {
				continuer = false;
			}
		} while (continuer);
	}

	/**
	 * affiche le texte avec les >>> devant
	 * 
	 * @param message le message a afficher
	 */
	public static void print(String message) {
		System.out.print(">>> " + message);
		entree.nextLine();
	}

	/**
	 * affiche le texte avec les >>>>> devant
	 * 
	 * @param message le message a afficher
	 */
	public static void printTitre(String message) {
		System.out.print(">>>>> " + message);
		entree.nextLine();
	}
}
