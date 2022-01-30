import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class BinaryTrie implements Serializable {

	private static final int R = 256;
	private char ch = 0;
	private int freq = 0;
	private BinaryTrie left = null;
	private BinaryTrie right = null;
	HashMap<Character, BitSequence> myTable = new HashMap<>();

	public BinaryTrie (BinaryTrie k) {
		this(k.ch, k.freq, k.left, k.right);
	}

	public BinaryTrie(char ch, int freq, BinaryTrie left, BinaryTrie right) {
		this.ch = ch;
		this.freq = freq;
		this.left = left;
		this.right = right;
	}

	private boolean isLeaf() {
		return (left == null) && (right == null);
	}

	public BinaryTrie(Map<Character, Integer> frequencyTable) {

		MinPQ<BinaryTrie> pq = new MinPQ<>(new Comparator<BinaryTrie>() {
			@Override
			public int compare(BinaryTrie o1, BinaryTrie o2) {
				return o1.freq - o2.freq;
			}
		});

		//** the for loop to search c is not a wise method. The correct way is get Entry from frequencyTable.
		// Currently, it can only encode ASCII from 1 to 256, might be not suitable for Chinese character.
		// */
		for (char c = 1; c < R - 1; c++) {
			if (frequencyTable.get(c) != null) {
				pq.insert(new BinaryTrie(c, frequencyTable.get(c), null, null));
			}
		}
		while (pq.size() > 1) {
			BinaryTrie left = pq.delMin();
			BinaryTrie right = pq.delMin();
			BinaryTrie parent = new BinaryTrie('\0', left.freq + right.freq, left, right);
			pq.insert(parent);

		}

		//** not know how to construct directly, so use temp for help */
		BinaryTrie temp = new BinaryTrie(pq.min());
		this.ch = temp.ch;
		this.freq = temp.freq;
		this.left = temp.left;
		this.right = temp.right;
	}

	public Match longestPrefixMatch(BitSequence querySequence) {
		Character symbol = null;
		StringBuilder bits = new StringBuilder();
		BinaryTrie curr = this;
		for (int i = 0; i < querySequence.length() + 1; i++) {
			if (curr.isLeaf()){
				symbol = curr.ch;
				break;
			}
			if (querySequence.bitAt(i) == 0 ) {
				bits.append('0');
				curr = curr.left;
			}else{
				bits.append('1');
				curr = curr.right;
			}
		}
		BitSequence longestPrefix = new BitSequence(String.valueOf(bits));
		return new Match(longestPrefix, symbol);
	}


	public Map<Character, BitSequence> buildLookupTable() {

		BinaryTrie curr = this;
		lookup(curr, "");

		return myTable;
	}

	public void lookup (BinaryTrie curr, String bits) {
		if (curr.isLeaf()) {
			myTable.put(curr.ch, new BitSequence(bits));
		}else{
			String leftbits = bits + '0';
			lookup(curr.left, leftbits);
			String rightbits = bits + '1';
			lookup(curr.right, rightbits);
		}
	}

//	public static void main(String[] args) {
//		Map<Character, Integer> frequencyTable = new HashMap<Character, Integer>();
//		frequencyTable.put('a', 1);
//		frequencyTable.put('b', 2);
//		frequencyTable.put('c', 4);
//		frequencyTable.put('d', 5);
//		frequencyTable.put('e', 6);
//		BinaryTrie trie = new BinaryTrie(frequencyTable);
//	}

}
