package controleur;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlAfficherVillageTest {

	private Village village;
	private ControlAfficherVillage control;
	private ControlEmmenager controlEmmenager;
	private Chef chef;

	@BeforeEach
	private void initTest() {
		this.village = new Village("Le village", 10, 10);
		this.control = new ControlAfficherVillage(village);
		this.controlEmmenager = new ControlEmmenager(village);
		this.chef = new Chef("chef", 10, village);
		village.setChef(chef);
	}

	@Test
	void testControlAfficherVillage() {
		assertNotNull(control);
	}

	@Test
	void testDonnerNomsVillageois() {
		controlEmmenager.ajouterGaulois("habitant1", 10);
		controlEmmenager.ajouterDuide("druide", 10, 0, 10);
		String[] liste_noms = control.donnerNomsVillageois();
		String[] vrai_noms = new String[] { "chef", "habitant1", "le druide druide" };
		assertArrayEquals(liste_noms, vrai_noms);
	}

	@Test
	void testDonnerNomVillage() {
		assertTrue(control.donnerNomVillage().equals("Le village"));
	}

	@Test
	void testDonnerNbEtals() {
		assertTrue(control.donnerNbEtals() == 10);
	}

}
