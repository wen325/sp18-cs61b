public class Palindrome {

	public Deque<Character> wordToDeque(String word){
		int size = word.length();
		Deque B = new LinkedListDeque();

		for (int i = 0; i < size; i++){
			B.addLast(word.charAt(i));
		}
		return B;
	}

	public boolean isPalindrome(String word){
		Deque B = wordToDeque(word);

		return isPalindromeHelp(B);
	}

	private boolean isPalindromeHelp(Deque B){
		if(B.size() == 1 || B.size() == 0){
			return true;
		}
		if (B.removeFirst() != B.removeLast()) {
			return false;
		} else {
			return isPalindromeHelp(B);
		}
	}


	public boolean isPalindrome(String word, CharacterComparator cc){
		Deque B = wordToDeque(word);
		while (B.size() > 1) {
			if (!cc.equalChars((Character) B.removeFirst(), (Character) B.removeLast())){
					return false;
				}
			}
		return true;
	}

}
