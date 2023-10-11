package controleur;

import personnages.Gaulois;
import villagegaulois.Village;

public class ControlVerifierIdentite {
	private Village village;

	public ControlVerifierIdentite(Village village) {
		this.village = village;
	}

	public boolean verifierIdentite(String nomGaulois) {
		Gaulois gaulois = village.trouverHabitant(nomGaulois);
		return gaulois != null;
	}
}
