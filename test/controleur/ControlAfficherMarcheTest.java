package controleur;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlAfficherMarcheTest {

	private Village village;
	private ControlAfficherMarche control;
	private ControlPrendreEtal controlPrendreEtal;
	private ControlEmmenager controlEm;
	private ControlVerifierIdentite controlId;
	private Chef chef;

	@BeforeEach
	private void initTest() {
		this.village = new Village("le village", 10, 2);
		this.control = new ControlAfficherMarche(village);
		this.controlEm = new ControlEmmenager(village);
		this.controlId = new ControlVerifierIdentite(village);
		this.controlPrendreEtal = new ControlPrendreEtal(controlId, village);
		this.chef = new Chef("le chef", 10, village);
		village.setChef(chef);
	}

	@Test
	void testControlAfficherMarche() {
		assertNotNull(control);
	}

	@Test
	void testDonnerInfosMarche() {
		String[] info = control.donnerInfosMarche();
		String[] vide = new String[0];
		assertArrayEquals(info, vide);

		controlEm.ajouterGaulois("martin", 10);
		controlPrendreEtal.prendreEtal("martin", "lait", 10);
		info = control.donnerInfosMarche();
		String[] sortieTheorique = new String[] { "martin", "10", "lait" };
		assertArrayEquals(info, sortieTheorique);
	}

}
