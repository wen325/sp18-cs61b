package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class TestClorus {

	@Test
	public void testBasics() {
		Clorus p = new Clorus(2);
		assertEquals(2, p.energy(), 0.01);
		p.move();
		assertEquals(1.97, p.energy(), 0.01);
		p.move();
		assertEquals(1.94, p.energy(), 0.01);
		p.stay();
		assertEquals(1.93, p.energy(), 0.01);
		p.stay();
		assertEquals(1.92, p.energy(), 0.01);
	}




	@Test
	public void testReplicate() {
		Clorus p = new Clorus(1.2);
		Clorus baby = p.replicate();
		assertNotSame(baby, p);
		assertEquals(baby.energy(), p.energy(), 0.01);
	}



	@Test
	public void testAttack() {
		Clorus clorus = new Clorus(1.2);
		Plip fatP = new Plip(1.2);
		HashMap<Direction, Occupant> surroundedclorus = new HashMap<>();
		surroundedclorus.put(Direction.BOTTOM, fatP);
		surroundedclorus.put(Direction.TOP, new Empty());
		surroundedclorus.put(Direction.LEFT, new Impassible());
		surroundedclorus.put(Direction.RIGHT, new Impassible());

		Action expected = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);

		HashMap<Direction, Occupant> surroundedfatP = new HashMap<>();
		surroundedfatP.put(Direction.TOP, clorus);
		surroundedfatP.put(Direction.BOTTOM, new Impassible());
		surroundedfatP.put(Direction.LEFT, new Impassible());
		surroundedfatP.put(Direction.RIGHT, new Impassible());

		Action actual = clorus.chooseAction(surroundedclorus);

		assertEquals(expected, actual);
	}


	public static void main(String[] args) {
		System.exit(jh61b.junit.textui.runClasses(TestClorus.class));
	}
}
