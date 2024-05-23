
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		// String word2 = args[1];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
		// int x = levenshtein(word, word2);
		// String str = head(word);
		// System.out.println(x);
	}

	public static String tail(String str) {
		if (str.length() == 1) {
			return "";
		}
		String ans = str.substring(1);
		return ans;
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (word1.length() == 0) {
			return word2.length();
		}
		if (word2.length() == 0) {
			return word1.length();
		}
		if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));
		} else {
			int x = (int) Math.min(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2)));
			return 1 + (int) Math.min(x, levenshtein(tail(word1), tail(word2)));
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int[] sim = new int[dictionary.length];
		for (int i = 0; i < dictionary.length; i++) {
			sim[i] = levenshtein(word, dictionary[i]);
		}
		String ans = word;
		int min = threshold + 1;
		for (int i = 0; i < dictionary.length; i++) {
			if (sim[i] < min) {
				min = sim[i];
				ans = dictionary[i];
			}
		}
		return ans;

	}

}
