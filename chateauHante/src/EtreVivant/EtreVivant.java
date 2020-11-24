package EtreVivant;

public class EtreVivant {

	public static Joueur	joueur	= null;

	private String			nom;
	private int				hp;

	/**
	 * @param nom
	 * @param hp
	 */
	public EtreVivant(String nom, int hp) {
		super();
		this.nom = nom;
		this.hp = hp;
	}

	/**
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * @param hp
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}
}
