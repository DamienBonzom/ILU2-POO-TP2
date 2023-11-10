package controleur;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlLibererEtalTest {

	private Village village;
	private Chef chef;

	private ControlLibererEtal control;
	private ControlTrouverEtalVendeur controlTrouverVendeur;
	private ControlPrendreEtal controlPrendreEtal;
	private ControlEmmenager controlEm;
	private ControlVerifierIdentite controlId;

	@BeforeEach
	private void initTest() {
		this.village = new Village("le village", 10, 2);
		this.controlTrouverVendeur = new ControlTrouverEtalVendeur(village);
		this.control = new ControlLibererEtal(controlTrouverVendeur);
		this.controlEm = new ControlEmmenager(village);
		this.controlId = new ControlVerifierIdentite(village);
		this.controlPrendreEtal = new ControlPrendreEtal(controlId, village);
		this.chef = new Chef("le chef", 10, village);
		village.setChef(chef);
	}

	@Test
	void testControlLibererEtal() {
		assertNotNull(control);
	}

	@Test
	void testIsVendeur() {
		assertFalse(control.isVendeur("martin"));
		controlEm.ajouterGaulois("martin", 2);
		controlPrendreEtal.prendreEtal("martin", "lait", 10);
		assertTrue(control.isVendeur("martin"));
		control.libererEtal("martin");
		assertFalse(control.isVendeur("martin"));
	}

	@Test
	void testLibererEtal() {
		assertNull(control.libererEtal("martin"));
		controlEm.ajouterGaulois("martin", 2);
		controlPrendreEtal.prendreEtal("martin", "lait", 10);

		String[] vraiRetour = control.libererEtal("martin");
		String[] retourTheorique = new String[] { "true", "martin", "lait", "10", "0" };

		assertNotNull(vraiRetour);
		System.out.println(vraiRetour[1]);
		assertArrayEquals(vraiRetour, retourTheorique);

		assertFalse(control.isVendeur("martin"));
	}

}
