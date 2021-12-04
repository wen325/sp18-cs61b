import java.math.*;

/**
 * Tests pairwiseForce
 */
public class TestPlanet {

    /**
     * Tests pairwiseForce.
     */
    public static void main(String[] args) {
        CheckpairwiseForce();
    }

    /**
     * Checks whether or not two Doubles are equal and prints the result.
     *
     * @param expected Expected double
     * @param actual   Double received
     * @param label    Label for the 'test' case
     * @param eps      Tolerance for the double comparison.
     */
    private static void checkEquals(double force1, double force2) {
        if (force1 == force2) {
            System.out.println("PASS: " + "They are equal, " + force1);
        } else {
            System.out.println("FAIL: " + "First force is " + force1 + ";" + "Second force is " + force2);
        }
    }

    /**
     * Checks the Planet class to make sure pairwiseForce works.
     */
    private static void CheckpairwiseForce() {
        System.out.println("Checking pairwiseForce...");

        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");

        checkEquals(p1.calcForceExertedBy(p2), p2.calcForceExertedBy(p1));
    }
}
