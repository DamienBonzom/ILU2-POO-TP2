package controleur;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlPrendreEtalTest {

	private ControlPrendreEtal control;
	private ControlVerifierIdentite controlId;
	private ControlEmmenager controlEm;
	private Village village;
	private Chef chef;

	@BeforeEach
	private void initTest() {
		this.village = new Village("village", 10, 2);
		this.controlId = new ControlVerifierIdentite(village);
		this.control = new ControlPrendreEtal(controlId, village);
		this.controlEm = new ControlEmmenager(village);
		this.chef = new Chef("chef", 10, village);
		village.setChef(chef);
	}

	@Test
	void testControlPrendreEtal() {
		assertNotNull(control);
	}

	@Test
	void testResteEtals() {
		assertTrue(control.resteEtals());

		controlEm.ajouterGaulois("melissa", 20);
		control.prendreEtal("melissa", "chat", 10);
		assertTrue(control.resteEtals());

		control.prendreEtal("emily", "gamepass", 10);
		assertTrue(control.resteEtals());

		controlEm.ajouterGaulois("martin", 1);
		control.prendreEtal("martin", "gamepass", 10);
		assertFalse(control.resteEtals());
	}

	// TODO continuer à partir de ce test
	@Test
	void testPrendreEtal() {
		assertTrue(control.resteEtals());

		controlEm.ajouterGaulois("melissa", 20);
		control.prendreEtal("melissa", "chat", 10);
		assertTrue(control.resteEtals());

		control.prendreEtal("emily", "gamepass", 10);
		assertTrue(control.resteEtals());

		controlEm.ajouterGaulois("martin", 1);
		control.prendreEtal("martin", "gamepass", 10);
		assertFalse(control.resteEtals());
	}

	@Test
	void testVerifierIdentite() {
		fail("Not yet implemented");
	}

}
