package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/** Tests the plip class   
 *  @authr FIXME
 */

public class TestPlip {

    /* Replace with the magic word given in lab.
     * If you are submitting early, just put in "early" */
    public static final String MAGIC_WORD = "";

    @Test
    public void testBasics() {
        Plip p = new Plip(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(99, 255, 76), p.color());
        p.move();
        assertEquals(1.85, p.energy(), 0.01);
        p.move();
        assertEquals(1.70, p.energy(), 0.01);
        p.stay();
        assertEquals(1.90, p.energy(), 0.01);
        p.stay();
        assertEquals(2.00, p.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Plip p = new Plip(1.2);
        Plip baby = p.replicate();
        assertNotSame(baby, p);
        assertEquals(baby.energy(), p.energy(), 0.01);
    }

    @Test
    public void testChoose() {
        Plip p = new Plip(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        //You can create new empties with new Empty();
        //Despite what the spec says, you cannot test for Cloruses nearby yet.
        //Sorry!  

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        //**Test the second rule: if the Plip has energy greater than 1.0, it should replicate to an available space. */
        Plip fatP = new Plip(1.2);
        HashMap<Direction, Occupant> surroundedfatP = new HashMap<>();
        surroundedfatP.put(Direction.TOP, new Empty());
        surroundedfatP.put(Direction.BOTTOM, new Impassible());
        surroundedfatP.put(Direction.LEFT, new Impassible());
        surroundedfatP.put(Direction.RIGHT, new Impassible());

        actual = fatP.chooseAction(surroundedfatP);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(actual, expected);



    }

    public static void main(String[] args) {
        System.exit(jh61b.junit.textui.runClasses(TestPlip.class));
    }
} 
