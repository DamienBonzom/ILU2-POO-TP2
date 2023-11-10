package controleur;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Etal;
import villagegaulois.Village;

class ControlTrouverEtalVendeurTest {

	private ControlTrouverEtalVendeur control;
	private Village village;
	private ControlEmmenager controlEm;
	private ControlPrendreEtal controlPrendreEtal;
	private ControlVerifierIdentite controlId;
	private Chef chef;

	@BeforeEach
	private void initTest() {
		this.village = new Village("village", 10, 2);
		this.control = new ControlTrouverEtalVendeur(village);
		this.controlEm = new ControlEmmenager(village);
		this.controlId = new ControlVerifierIdentite(village);
		this.controlPrendreEtal = new ControlPrendreEtal(controlId, village);
		this.chef = new Chef("chef", 10, village);
		village.setChef(chef);

	}

	@Test
	void testControlTrouverEtalVendeur() {
		assertNotNull(control);
	}

	@Test
	void testTrouverEtalVendeur() {
		Etal etaltest = new Etal();

		etaltest = control.trouverEtalVendeur("martin");
		assertNull(etaltest);

		controlEm.ajouterGaulois("martin", 2);
		controlPrendreEtal.prendreEtal("martin", "lait", 10);

		etaltest = control.trouverEtalVendeur("martin");
		assertNotNull(etaltest);

	}

}
