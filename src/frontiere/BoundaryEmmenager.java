package frontiere;

import controleur.ControlEmmenager;

public class BoundaryEmmenager {
	private ControlEmmenager controlEmmenager;

	public BoundaryEmmenager(ControlEmmenager controlEmmenager) {
		this.controlEmmenager = controlEmmenager;
	}

	public void emmenager(String nomVisiteur) {
		if (controlEmmenager.isHabitant(nomVisiteur)) {
			System.out.println(
					"Mais vous êtes déjà un habitant du village !");
		} else {
			StringBuilder question = new StringBuilder();
			question.append("ÃŠtes-vous :\n");
			question.append("1 - un druide.\n");
			question.append("2 - un gaulois.\n");
			int choixUtilisateur = -1;
			do {
				choixUtilisateur = Clavier.entrerEntier(question.toString());
				switch (choixUtilisateur) {
				case 1:
					emmenagerDruide(nomVisiteur);
					break;

				case 2:
					StringBuilder questionForce = new StringBuilder();
					questionForce.append(String.format("Bienvenu villageois %s\n", nomVisiteur));
					questionForce.append("Quelle est votre force ?\n");
					int force = Clavier.entrerEntier(questionForce.toString());
					controlEmmenager.ajouterGaulois(nomVisiteur, force);
					break;

				default:
					System.out
							.println("Vous devez choisir le chiffre 1 ou 2 !");
					break;
				}
			} while (choixUtilisateur != 1 && choixUtilisateur != 2);
		}
	}

	private void emmenagerDruide(String nomVisiteur) {
		StringBuilder questionForce = new StringBuilder();
		questionForce.append(String.format("Bienvenu druide %s\n", nomVisiteur));
		questionForce.append("Quelle est votre force ?\n");
		int forceDruide = Clavier.entrerEntier(questionForce.toString());
		
		int effetPotionMin;
		int effetPotionMax;
		StringBuilder questionPotionMin = new StringBuilder();
		questionPotionMin.append("Quelle est la force de potion la plus faible que vous produisez ?\n");
		StringBuilder questionPotionMax = new StringBuilder();
		questionPotionMax.append("Quelle est la force de potion la plus forte que vous produisez ?\n");
		
		do {
			effetPotionMin = Clavier.entrerEntier(questionPotionMin.toString());
			effetPotionMax = Clavier.entrerEntier(questionPotionMax.toString());
			
			if(effetPotionMax < effetPotionMin) {
				System.out.println("Attention Druide, vous vous êtes trompé entre le minimum et le maximum\n");
			}
			
		}while(effetPotionMax < effetPotionMin);
		
		controlEmmenager.ajouterDuide(nomVisiteur, forceDruide, effetPotionMin, effetPotionMax);
	}
}
