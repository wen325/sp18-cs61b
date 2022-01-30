import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
	public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {

		Map<Character, Integer> frequencyTable = new HashMap<>();

		for (char symbol : inputSymbols) {
			if (!frequencyTable.containsKey(symbol)) {
				frequencyTable.put(symbol, 1);
			} else {
				frequencyTable.put(symbol, frequencyTable.get(symbol) + 1);
			}
		}

		return frequencyTable;

	}


	public static void main(String[] args) {

//		char[] inputSymbols = new char[]{'a','b','b','c','c','c','c','d','d','d','d','d','e','e','e','e','e','e'};
//		Map<Character, Integer> frequencyTable = buildFrequencyTable(inputSymbols);

		String filename = args[0];
		char[] inputSymbols = FileUtils.readFile(filename);
		Map<Character, Integer> frequencyTable = buildFrequencyTable(inputSymbols);

		BinaryTrie trie = new BinaryTrie(frequencyTable);
		ObjectWriter ow = new ObjectWriter(filename + ".huf");
		ow.writeObject(trie);
		ow.writeObject(frequencyTable.size());

		Map<Character, BitSequence> lookupTable = trie.buildLookupTable();
		List<BitSequence> bitSequences = new ArrayList<>();
//		for (Map.Entry<Character, BitSequence> entry: lookupTable.entrySet()) {
//			bitSequences.add(entry.getValue());
//		}
		for (Character symbol : inputSymbols) {
				bitSequences.add(lookupTable.get(symbol));
		}
		BitSequence hugbitSequence = BitSequence.assemble(bitSequences);
		ow.writeObject(hugbitSequence);

	}
}