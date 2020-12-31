package Main;

public class Synchroniser extends Thread {

	public boolean continuer = true;

	@Override
	public void run() {
		while (continuer) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				Commande.print("Le thread s'est arreté.");
			}
		}
	}

	/**
	 * Fait dormir le thread 1 seconde
	 * 
	 * @throws InterruptedException
	 */
	public void attendre1Seconde() throws InterruptedException {
		this.sleep(1000);
	}
}
