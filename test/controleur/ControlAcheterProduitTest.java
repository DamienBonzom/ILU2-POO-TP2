package controleur;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlAcheterProduitTest {

	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlId;
	private ControlAcheterProduit control;
	private ControlPrendreEtal controlPrendreEtal;
	private ControlEmmenager controlEm;
	private Chef chef;

	@BeforeEach
	private void initTest() {
		this.village = new Village("le village", 10, 2);
		this.controlId = new ControlVerifierIdentite(village);
		this.controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		this.control = new ControlAcheterProduit(controlId, controlTrouverEtalVendeur, village);
		this.controlEm = new ControlEmmenager(village);
		this.controlPrendreEtal = new ControlPrendreEtal(controlId, village);
		this.chef = new Chef("le chef", 10, village);
		village.setChef(chef);
	}

	@Test
	void testControlAcheterProduit() {
		assertNotNull(control);
	}

	@Test
	void testTrouverNomCommercant() {
		assertNull(control.trouverNomCommercant("lait"));
		controlEm.ajouterGaulois("martin", 10);
		controlPrendreEtal.prendreEtal("martin", "lait", 10);
		String[] nomsTest = control.trouverNomCommercant("lait");
		String[] attendu = new String[] { "martin" };
		assertArrayEquals(nomsTest, attendu);
	}

	@Test
	void testAcheterProduit() {
		controlEm.ajouterGaulois("martin", 10);
		controlPrendreEtal.prendreEtal("martin", "lait", 10);
		assert (control.acheterProduit("martin", 5) == 5);
		assert (control.acheterProduit("martin", 0) == 0);
		assert (control.acheterProduit("martin", 10) == 5);
	}

}
