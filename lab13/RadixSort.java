/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        String[] sortedAsciis = new String[asciis.length];
        System.arraycopy(asciis, 0, sortedAsciis, 0, asciis.length);

        //** find the max length of string in asciis
        int maxLen = 0;
        for (String s : asciis) {
            if (s.length() > maxLen) {
                maxLen = s.length();
            }
        }

        for (int d = maxLen - 1; d >= 0; d--) {
            sortHelperLSD(sortedAsciis, d);
        }
        return sortedAsciis;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int R = 256;
        int[] counts = new int[R + 1];
        for (String s : asciis) {
            int i = charAtOrMinChar(index, s);
            counts[i]++;
        }

        int[] starts = new int[R + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted2 = new String[asciis.length];
        for (int i = 0; i < asciis.length; i += 1) {
            String s = asciis[i];
            int j = charAtOrMinChar(index, s);
            int place = starts[j];
            sorted2[place] = s;
            starts[j] += 1;
        }

        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = sorted2[i];
        }

    }

    private static int charAtOrMinChar(int index, String s) {
        if (index < s.length()) {
            return s.charAt(index) + 1;
        }else{
            return 0;
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
