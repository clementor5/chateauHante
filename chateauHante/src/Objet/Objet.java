package Objet;

import java.util.ArrayList;
import java.util.Arrays;

import EtreVivant.Joueur;

public class Objet {

	public static Joueur					joueur		= null;

	public final static ArrayList<String>	typeList	= new ArrayList<String>(Arrays.asList("Cle", "Arme", "Potion"));

	private static int						idAuto		= 0;

	private int								id;
	private String							nom;
	private String							type;

	public Objet(String nom, String type) {
		super();
		this.nom = nom;
		this.type = type;
		idAuto++;
		this.id = idAuto;
	}

	@Override
	public String toString() {
		return "nom : " + nom;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static String getTypeCle() {
		return typeList.get(0);
	}

	public static String getTypeArme() {
		return typeList.get(1);
	}

	public static String getTypePotion() {
		return typeList.get(2);
	}

	public static int getIdAuto() {
		return idAuto;
	}
}
