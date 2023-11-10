package frontiere;

import java.util.Scanner;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private Scanner scan = new Scanner(System.in);
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		StringBuilder menu = new StringBuilder();
		menu.append("Quel produit voulez vous acheter ?\n");
		System.out.println(menu.toString());
		String choixUtilisateur = scan.next();
		String[] nom_vendeurs = controlAcheterProduit.trouverNomCommercant(choixUtilisateur);
		if (nom_vendeurs == null) {
			System.out.println("Désolé, personne ne vend ce produit au marché\n");
		} else {
			menuAchatVendeur(nomAcheteur, nom_vendeurs, choixUtilisateur);
		}
	}

	public void menuAchatVendeur(String nomAcheteur, String[] nom_vendeurs, String produit) {
		StringBuilder question = new StringBuilder();
		question.append(String.format("Chez quel commerçant voulez-vous acheter des %s ?\n", produit));
		for (int i = 0; i < nom_vendeurs.length; i++) {
			question.append(String.format("%d - %s\n", i + 1, nom_vendeurs[i]));
		}
		int indice_vendeur_choisis = Clavier.entrerEntier(question.toString());
		while (indice_vendeur_choisis < 1 || indice_vendeur_choisis > nom_vendeurs.length) {
			System.out.println(String.format("Choisissez un nombre entre %d et %d\n", 1, nom_vendeurs.length));
		}
		String nom_vendeur_choisis = nom_vendeurs[indice_vendeur_choisis - 1];

		StringBuilder question_achat = new StringBuilder();
		question_achat.append(
				String.format("%s se déplace jusqu'à l'étal du vendeur %s\n", nomAcheteur, nom_vendeur_choisis));
		question_achat.append(String.format("Bonjour %s\n", nomAcheteur));
		question_achat.append(String.format("Combien de %s voulez-vous acheter ?\n", produit));
		int quantite_demandee = Clavier.entrerEntier(question_achat.toString());
		while (quantite_demandee <= 0) {
			System.out.println("Choisissez une quantite positive !\n");
			quantite_demandee = Clavier.entrerEntier(question_achat.toString());
		}

		int quantite_achetee = controlAcheterProduit.acheterProduit(nom_vendeur_choisis, quantite_demandee);
		StringBuilder resultat_achat = new StringBuilder();

		if (quantite_achetee == quantite_demandee) {
			resultat_achat.append(String.format("%s achète %d %s à %s\n", nomAcheteur, quantite_achetee, produit,
					nom_vendeur_choisis));
		} else if (quantite_achetee < quantite_demandee) {
			resultat_achat.append(String.format(
					"%s veut acheter %d %s, malheureusement %s n'en a plus que %d. %s achète tout le stock de %s.\n",
					nomAcheteur, quantite_demandee, produit, nom_vendeur_choisis, quantite_achetee, nomAcheteur,
					nom_vendeur_choisis));
		} else {
			resultat_achat.append(String.format("%s veut acheter %d %s, malheureusement il n'y en a plus !\n",
					nomAcheteur, quantite_demandee, produit));
		}

		System.out.println(resultat_achat.toString());
	}

}