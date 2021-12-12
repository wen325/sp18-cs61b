import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
	/** Task 1
	* @source StudentArrayDequeLauncher
	*/

	@Test
	public void testPart1(){
		StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
		ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();
		String message = "\n";
		for (int i = 0; i < 1000; i += 1) {
			double numberBetweenZeroAndOne = StdRandom.uniform();
			int numberBetweenZeroAndNine = StdRandom.uniform(0, 10);
			if (numberBetweenZeroAndOne < 0.25) {
				sad1.addLast(numberBetweenZeroAndNine);
				sad2.addLast(numberBetweenZeroAndNine);
				message = message + "addLast(" + numberBetweenZeroAndNine + ")\n";
			} else if (numberBetweenZeroAndOne < 0.5) {
				sad1.addFirst(numberBetweenZeroAndNine);
				sad2.addFirst(numberBetweenZeroAndNine);
				message = message + "addFirst(" + numberBetweenZeroAndNine + ")\n";
			} else if (numberBetweenZeroAndOne < 0.75){
				if(sad2.size() > 1) {
					Integer a = sad1.removeFirst();
					Integer b = sad2.removeFirst();
					message = message + "removeFirst(): " + a + "\n";
					assertEquals(message, a, b);
				}
			} else{
				if (sad2.size() > 1) {
					Integer a = sad1.removeLast();
					Integer b = sad2.removeLast();
					message = message + "removeLast(): " + b + "\n";
					assertEquals(message, a, b);
				}
			}
		}

	}

//	/** This main method is optional. */
	public static void main(String[] args) {

	}
}
