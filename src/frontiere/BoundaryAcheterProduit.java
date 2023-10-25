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
		// TODO VERIFIER QUE NOMACHETEUR EST BIEN UN HABITANT SINON FIN DE FONCTION
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
			question.append(String.format("%d - %s\n", i, nom_vendeurs[i]));
		}
		int indice_vendeur_choisis = Clavier.entrerEntier(question.toString());
		while (indice_vendeur_choisis < 1 || indice_vendeur_choisis > nom_vendeurs.length) {
			System.out.println(String.format("Choisissez un nombre entre %d et %d\n", 1, nom_vendeurs.length));
		}
		String nom_vendeur_choisis = nom_vendeurs[indice_vendeur_choisis - 1];

		StringBuilder question_achat = new StringBuilder();
		question_achat.append(
				String.format("%s se déplace jusqu'à l'étal du vendeur %s\n", nomAcheteur, nom_vendeur_choisis));
		question_achat.append(String.format("Combien de %s voulez-vous acheter ?\n", produit));
		int quantite = Clavier.entrerEntier(question_achat.toString());
		int etat_achat = controlAcheterProduit.acheterEtalVendeur(nom_vendeur_choisis, quantite);
		StringBuilder resultat_achat = new StringBuilder();
		switch (etat_achat) {
		case 1:
			resultat_achat.append(String.format("%s veut acheter %d %s, malheureusement il n'y en a plus !\n",
					nomAcheteur, quantite, produit));
			break;
		case 2:
			int quantite_etal = 0; // TODO TROUVER COMMENT RECUPERER LA QUANTITE DE LETAL
			resultat_achat.append(String.format(
					"%s veut acheter %d %s, malheureusement %s n'en a plus que %d. %s achète tout le stock de %s.\n !",
					nomAcheteur, quantite, produit, nom_vendeur_choisis, quantite_etal, nomAcheteur));
			break;
		case 3:
			resultat_achat.append(
					String.format("%s achète %d %s à %s\n", nomAcheteur, quantite, produit, nom_vendeur_choisis));
			break;
		}
		System.out.println(resultat_achat.toString());
	}

}