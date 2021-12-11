public class OffByN implements CharacterComparator{
	private int offvalue;
	public OffByN (int N){
		offvalue = N;
	}
	@Override
	public boolean equalChars(char x, char y){
		return Math.abs(x - y) == offvalue;
	}

}
