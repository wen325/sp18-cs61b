package creatures;

import huglife.*;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Clorus extends Creature {
	/** red color. */
	private int r;
	/** green color. */
	private int g;
	/** blue color. */
	private int b;



	/** creates clorus with energy equal to E. */
	public Clorus(double e) {
		super("clorus");
		r = 0;
		g = 0;
		b = 0;
		energy = e;
	}


	/** Cloruses should lose 0.03 units of energy on a MOVE action.*/
	public void move() {
		this.energy -= 0.03;
	}

	/** Cloruses should lose 0.01 units of energy on a STAY action. */
	public void stay() {
		this.energy -= 0.01;
	}

	/** return the color red = 34, green = 0, blue = 231*/
	public Color color() {
		r = 34;
		g = 0;
		b = 231;
		return color(r, g, b);
	}

	/** If a Clorus attacks another creature, it should gain that creature’s energy. */
	@Override
	public void attack(Creature c) {
		this.energy += c.energy();
	}

	/** When a Clorus replicates, it keeps 50% of its energy.
	 * The other 50% goes to its offspring.
	 * No energy is lost in the replication process
	 */
	public Clorus replicate() {
		this.energy = this.energy / 2;
		return new Clorus(this.energy);
	}

	/** Plips take exactly the following actions based on NEIGHBORS:
	 1. If there are no empty squares, the Clorus will STAY (even if there are Plips nearby they could attack).
	 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
	 3. Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
	 4. Otherwise, the Clorus will MOVE to a random empty square.
	 */
	public Action chooseAction(Map<Direction, Occupant> neighbors) {
		List<Direction> empties = getNeighborsOfType(neighbors, "empty");
		List<Direction> plips = getNeighborsOfType(neighbors, "plip");
		if (empties.size() == 0) {
			return new Action(Action.ActionType.STAY);
		}
		if (plips.size() >= 1) {
			Direction attackDir = HugLifeUtils.randomEntry(plips);
			return new Action(Action.ActionType.ATTACK, attackDir);
		}
		if (this.energy() >= 1) {
			Direction replicateDir = HugLifeUtils.randomEntry(empties);
			return new Action(Action.ActionType.REPLICATE, replicateDir);
		}
		Direction moveDir = HugLifeUtils.randomEntry(empties);
		return new Action(Action.ActionType.MOVE, moveDir);
	}
}
