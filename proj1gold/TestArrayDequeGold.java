import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
	/**
	* @source StudentArrayDequeLauncher
	*/
	@Test
	public void testPart1(){
		StudentArrayDeque<Integer> stdDeque = new StudentArrayDeque<>();
		ArrayDequeSolution<Integer> trueDeque = new ArrayDequeSolution<>();
		String message = "";
		for (int i = 0; i < 1000; i += 1) {
			int randomMethod = StdRandom.uniform(4);
			int randomInt = StdRandom.uniform(0, 10);
			if (randomMethod == 0) {
				stdDeque.addLast(randomInt);
				trueDeque.addLast(randomInt);
				message += "addLast(" + randomInt + ")\n";
				assertEquals(message, stdDeque.size(), trueDeque.size());
			} else if (randomMethod == 1) {
				stdDeque.addFirst(randomInt);
				trueDeque.addFirst(randomInt);
				message += "addFirst(" + randomInt + ")\n";
				assertEquals(message, stdDeque.size(), trueDeque.size());
			} else if (randomMethod == 2){
				if(trueDeque.size() > 0) {
					message += "removeFirst()\n";
					assertEquals(message, stdDeque.removeFirst(), trueDeque.removeFirst());
					assertEquals(message, stdDeque.size(), trueDeque.size());
				}
			} else{
				if (trueDeque.size() > 0) {
					message += "removeLast()\n";
					assertEquals(message, stdDeque.removeLast(), trueDeque.removeLast());
					assertEquals(message, stdDeque.size(), trueDeque.size());
				}
			}
		}

	}

//	/** This main method is optional. */
//	public static void main(String[] args) {
//
//	}
}
