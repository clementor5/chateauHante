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
	 * Demande au thread de s'arreter et attend qu'il s'arrete
	 * 
	 * @throws InterruptedException
	 */
	public void attendre1Seconde() throws InterruptedException {
		this.sleep(1000);
	}
}
