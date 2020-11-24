package Main;

import Chateau.Chateau;
import Chateau.Piece;
import Chateau.Sortie;
import Chateau.Tresor;
import EtreVivant.EtreVivant;
import EtreVivant.Monstre;
import Objet.Arme;
import Objet.Objet;

public class Synchroniser extends Thread {

	@Override
	public void run() {
		while (true) {
			try {

				Chateau.joueur = Game.getJoueur();
				Piece.joueur = Game.getJoueur();
				Sortie.joueur = Game.getJoueur();
				Tresor.joueur = Game.getJoueur();
				EtreVivant.joueur = Game.getJoueur();
				Monstre.joueur = Game.getJoueur();
				Commande.joueur = Game.getJoueur();
				Arme.joueur = Game.getJoueur();
				Objet.joueur = Game.getJoueur();
				sleep(500);
			} catch (InterruptedException e) {
				Commande.print("Le thread s'est arreté.");
			}
		}
	}
}
