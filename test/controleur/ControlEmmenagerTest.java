package controleur;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlEmmenagerTest {

	private Village village;
	private ControlEmmenager control;
	private Chef chef;

	@BeforeEach
	private void initTest() {
		this.village = new Village("le village", 10, 10);
		this.control = new ControlEmmenager(village);
		this.chef = new Chef("Le Chef", 10, village);
		village.setChef(chef);
	}

	@Test
	void testControlEmmenager() {
		assertNotNull(control);
	}

	@Test
	void testIsHabitant() {
		assertFalse(control.isHabitant("Astérix"));
		control.ajouterGaulois("Astérix", 12);
		assertTrue(control.isHabitant("Astérix"));
	}

	@Test
	void testAjouterDuide() {
		assertFalse(control.isHabitant("Druide"));
		control.ajouterDuide("Druide", 10, 0, 10);
		assertTrue(control.isHabitant("Druide"));
	}

	@Test
	void testAjouterGaulois() {
		assertFalse(control.isHabitant("Astérix"));
		control.ajouterGaulois("Astérix", 12);
		assertTrue(control.isHabitant("Astérix"));
	}

}
