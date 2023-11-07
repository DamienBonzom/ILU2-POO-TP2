package frontiere;

import java.util.Scanner;

import controleur.ControlPrendreEtal;

public class BoundaryPrendreEtal {
	private ControlPrendreEtal controlPrendreEtal;
	private Scanner scan = new Scanner(System.in);

	public BoundaryPrendreEtal(ControlPrendreEtal controlChercherEtal) {
		this.controlPrendreEtal = controlChercherEtal;
	}

	public void prendreEtal(String nomVendeur) {
		boolean nomVendeurConnu = controlPrendreEtal.verifierIdentite(nomVendeur);
		if (!nomVendeurConnu) {
			StringBuilder vendeurInconnu = new StringBuilder();
			vendeurInconnu.append(String.format(
					"Je suis désolée %s mais il faut être un habitant de notre village pour commercer ici.\n",
					nomVendeur));
			System.out.println(vendeurInconnu.toString());
		} else {
			StringBuilder reponse = new StringBuilder();
			reponse.append(
					String.format("Bonjour %s, je vais regarder si je peux vous trouver un étal.\n", nomVendeur));
			boolean etalDisponible = controlPrendreEtal.resteEtals();
			if (!etalDisponible) {
				StringBuilder etalNonDispo = new StringBuilder();
				etalNonDispo.append(
						String.format("Désolée %s je n'ai plus d'étal qui ne soit pas déjà occupé.\n", nomVendeur));
				System.out.println(etalNonDispo.toString());
			} else {
				installerVendeur(nomVendeur);
			}
		}
	}

	private void installerVendeur(String nomVendeur) {
		StringBuilder questionProduit = new StringBuilder();
		questionProduit.append("C'est parfait, il me reste un étal pour vous !\n");
		questionProduit.append("Il me faudrait quelques renseignements :\n");
		questionProduit.append("Quel produit souhaitez-vous vendre ?\n");
		System.out.println(questionProduit.toString());
		String produit = scan.nextLine();

		StringBuilder questionQuantite = new StringBuilder();
		questionQuantite.append("Combien souhaitez vous en vendre ?\n");
		int nbProduit = Clavier.entrerEntier(questionQuantite.toString());

		int numeroEtal = controlPrendreEtal.prendreEtal(nomVendeur, produit, nbProduit);

		if (numeroEtal != -1) {
			StringBuilder installation = new StringBuilder();
			installation.append(String.format("Le vendeur %s s'est installé à l'étal n°%d", nomVendeur, numeroEtal));
			System.out.println(installation.toString());
		}

	}
}
