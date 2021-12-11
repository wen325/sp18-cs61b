import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
	//    /*
	// You must use this CharacterComparator and not instantiate
	// new ones, or the autograder might be upset.
	static CharacterComparator OffByN = new OffByN(3);

	// Your tests go here.
	// Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/

	@Test
	public void testequalChars(){
		assertTrue(OffByN.equalChars('b', 'e'));
		assertTrue(OffByN.equalChars('a', 'd'));
		assertFalse(OffByN.equalChars('&', '%'));
		assertFalse(OffByN.equalChars('a', 'c'));

	}
}
