public class HuffmanDecoder {


	public static void main(String[] args){
		String encoderfile = args[0];
		String decoderfile = args[1];
		ObjectReader or = new ObjectReader(encoderfile);

		//** read codingtrie, numofSymbol, hugebitSequence */
		Object first = or.readObject();
		BinaryTrie codingtrie = (BinaryTrie) first;

		Object second = or.readObject();
		int numofSymbol = (Integer) second;

		Object third = or.readObject();
		BitSequence hugebitSequence = (BitSequence) third;

		//** decoder */
		StringBuilder decodeChars = new StringBuilder();
		while (hugebitSequence.length() != 0) {
			Match m = codingtrie.longestPrefixMatch(hugebitSequence);
			decodeChars.append(m.getSymbol());
			hugebitSequence = hugebitSequence.allButFirstNBits(m.getSequence().length());
		}
		String decodeString = decodeChars.toString();
		char[] chars = decodeString.toCharArray();
		FileUtils.writeCharArray(decoderfile,chars);
	}
}
