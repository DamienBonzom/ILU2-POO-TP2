package controleur;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlVerifierIdentiteTest {

	private Village village;
	private ControlVerifierIdentite control;
	private ControlEmmenager controlEmmenager;
	private Chef chef;

	@BeforeEach
	private void initTest() {
		this.village = new Village("village", 10, 10);
		this.control = new ControlVerifierIdentite(village);
		this.controlEmmenager = new ControlEmmenager(village);
		this.chef = new Chef("chef", 10, village);
		village.setChef(chef);
	}

	@Test
	void testControlVerifierIdentite() {
		assertNotNull(control);
	}

	@Test
	void testVerifierIdentite() {
		assertFalse(control.verifierIdentite("habitant"));
		controlEmmenager.ajouterGaulois("habitant", 10);
		assertTrue(control.verifierIdentite("habitant"));
	}

}
