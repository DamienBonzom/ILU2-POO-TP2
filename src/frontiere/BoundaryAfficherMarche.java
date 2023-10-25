package frontiere;

import controleur.ControlAfficherMarche;

public class BoundaryAfficherMarche {
	private ControlAfficherMarche controlAfficherMarche;

	public BoundaryAfficherMarche(ControlAfficherMarche controlAfficherMarche) {
		this.controlAfficherMarche = controlAfficherMarche;
	}

	public void afficherMarche(String nomAcheteur) {
		String[] infosMarche = controlAfficherMarche.donnerInfosMarche();
		if (infosMarche.length == 0) {
			System.out.println("Le marché est vide, revenez plus tard.\n");
		} else {
			StringBuilder affichage = new StringBuilder();
			affichage.append(String.format("%s, vous trouverez au marché :\n", nomAcheteur));
			int i = 0;
			System.out.println(infosMarche.length);
			while (i < infosMarche.length - 1) {
				String vendeur = infosMarche[i];
				i++;
				String quantite = infosMarche[i];
				i++;
				String produit = infosMarche[i];
				affichage.append(String.format("- %s qui vend %s %s\n", vendeur, quantite, produit));
			}
			System.out.println(affichage.toString());
		}
	}
}
