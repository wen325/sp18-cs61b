public class OffByN implements CharacterComparator{
	int N = 3;
	@Override
	public boolean equalChars(char x, char y){
		return Math.abs(x - y) == N;
	}

}
