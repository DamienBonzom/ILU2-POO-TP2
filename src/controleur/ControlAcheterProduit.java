package controleur;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur, Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	// TODO a completer

	public String[] trouverNomCommercant(String produit) {
		Gaulois[] vendeurs = village.rechercherVendeursProduit(produit);
		String[] nom_vendeurs = null;
		if (vendeurs.length > 0) {
			nom_vendeurs = new String[vendeurs.length];
			for (int i = 0; i < vendeurs.length; i++) {
				nom_vendeurs[i] = vendeurs[i].getNom();
			}
		}
		return nom_vendeurs;
	}

	public int acheterEtalVendeur(String nomVendeur, int quantite) {
		Etal etalVendeur = village.rechercherEtal(village.trouverHabitant(nomVendeur));
		int quantite_etal = etalVendeur.getQuantite();
		int etat_achat;
		if (quantite_etal == 0) {
			etat_achat = 1;
		} else if (quantite > quantite_etal) {
			etat_achat = 2;
		} else {
			etat_achat = 3;
		}
		etalVendeur.acheterProduit(quantite);
		return etat_achat;
	}
}
