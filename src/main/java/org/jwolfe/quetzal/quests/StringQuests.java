package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.algorithms.StringAlgorithms;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.*;

public class StringQuests {

	// Q: Write a program to print all permutations of a given string
	// ABC, ACB, BCA, BAC, CAB, CBA
	// ABC
	// ABC
	// ABC
	// ACB
	//
	// BAC
	// BAC
	// BCA
	//
	// CAB
	// CAB
	// CBA
	public static void printAllPermutations(String str) {
		printAllPermutations(str, 0, str.length() - 1);
	}

	private static void printAllPermutations(String str, int left, int right) {
		if (left == right) {
			System.out.println(str);
		} else {
			for (int i = left; i <= right; i++) {
				str = Utilities.swap(str, left, i);
				printAllPermutations(str, left + 1, right);
				str = Utilities.swap(str, left, i);
			}
		}
	}

	// Q: Check if two given strings are isomorphic to each other
	public static boolean isIsoMorphic(String str1, String str2) {
		if (str1.length() != str2.length())
			return false;

		int size = 256;

		boolean[] marked = new boolean[size];
		int[] map = new int[size];

		Arrays.fill(marked, false);
		Arrays.fill(map, -1);

		for (int i = 0; i < str1.length(); i++) {
			char c1 = str1.charAt(i);
			char c2 = str2.charAt(i);

			if (map[c1] == -1) {
				if (marked[c2])
					return false;

				marked[c2] = true;
				map[c1] = c2;
			} else if (map[c1] != c2) {
				return false;
			}
		}

		return true;
	}

	// Q: Longest palindrome subsequence with O(n) space
	public static int longestPalindromicSubsequenceLength(String str) {
		int n = str.length();
		int[] lps = new int[n];

		for (int i = n - 1; i >= 0; i--) {
			int backup = 0;
			for (int j = i; j < n; j++) {
				if (i == j) {
					lps[j] = 1;
				} else if (str.charAt(i) == str.charAt(j)) {
					int temp = lps[j];
					lps[j] = backup + 2;
					backup = temp;
				} else {
					backup = lps[j];
					lps[j] = Math.max(lps[j - 1], lps[j]);
				}
			}
		}

		return lps[n - 1];
	}

	public static String reverseWithoutTemporaryVariable(String str) {
		int left = 0;
		int right = str.length() - 1;

		StringBuilder builder = new StringBuilder(str);
		while (left < right) {
			var a = builder.charAt(left);
			var b = builder.charAt(right);

			a ^= b;
			b ^= a;
			a ^= b;

			builder.setCharAt(left, a);
			builder.setCharAt(right, b);

			left++;
			right--;
		}

		return builder.toString();
	}

	public static int countMinimumBracketReversals(String expression) {
		int length = expression.length();
		if (length % 2 != 0) {
			return -1;
		}

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < length; i++) {
			char c = expression.charAt(i);
			if (c == '}' && !stack.isEmpty()) {
				if (stack.peek() == '{') {
					stack.pop();
				} else {
					stack.push(c);
				}
			} else {
				stack.push(c);
			}
		}

		int netLength = stack.size();
		int openingBraceCount = 0;
		while (!stack.isEmpty() && stack.peek() == '{') {
			openingBraceCount++;
			stack.pop();
		}

		int closingBraceCount = stack.size();

		// return ((Double) (Math.ceil(openingBraceCount / 2.0) +
		// Math.ceil(closingBraceCount / 2.0))).intValue();
		return (netLength / 2 + openingBraceCount % 2); // for n = even
	}

	public static int maxDepthOfNestedParanthesis(String expression) {
		// Note: This can also be done using a stack (O(n) auxiliary space)

		if (expression == null) {
			return -1;
		}

		int n = expression.length();
		int currentMax = 0;
		int max = 0;

		for (int i = 0; i < n; i++) {
			char c = expression.charAt(i);
			if (c == '(') {
				currentMax++;
				if (currentMax > max) {
					max = currentMax;
				}
			} else if (c == ')') {
				if (currentMax > 0) {
					currentMax--;
				} else {
					// Unbalanced
					return -1;
				}
			}
		}

		if (currentMax != 0) {
			// Unbalanced
			return -1;
		}

		return max;
	}

	public static boolean match(String pattern, String text) {
		return match(pattern, text, 0, 0);
	}

	public static boolean match(String pattern, String text, int patternIndex, int textIndex) {
		int patternLength = pattern.length();
		int textLength = text.length();

		if (patternIndex == patternLength && textIndex == textLength) {
			return true;
		}

		if (patternIndex == patternLength || textIndex == textLength) {
			return false;
		}

		char p = pattern.charAt(patternIndex);
		char t = text.charAt(textIndex);

		if (p == '*' && patternIndex + 1 == patternLength && textIndex + 1 == textLength) {
			return true;
		}

		if (p == t || p == '?') {
			return match(pattern, text, patternIndex + 1, textIndex + 1);
		}

		if (p == '*') {
			return match(pattern, text, patternIndex + 1, textIndex)
					|| match(pattern, text, patternIndex, textIndex + 1);
		}

		return false;
	}

	public static int shortestChain(String start, String target, Set<String> dictionary) {
		// Word Ladder: BFS
		Queue<Pair<String, Integer>> queue = new LinkedList<>();
		queue.offer(new Pair<>(start, 1));

		while (!queue.isEmpty()) {
			var item = queue.poll();
			var word = item.getFirst();
			var chainLength = item.getSecond();

			if (word.equals(target)) {
				return chainLength;
			}

			for (var dictionaryWord : dictionary) {
				if (isAdjacent(word, dictionaryWord)) {
					queue.offer(new Pair<>(dictionaryWord, chainLength + 1));
				}
			}
		}

		return -1;
	}

	private static boolean isAdjacent(String word1, String word2) {
		int length = word1.length();
		if (length != word2.length()) {
			return false;
		}

		int diffCount = 0;
		for (int i = 0; i < length; i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				diffCount++;
			}

			if (diffCount > 1) {
				return false;
			}
		}

		return diffCount == 1;
	}

	public static char getSecondMostFrequentCharacter(String str) {
		int[] counts = new int[256];
		for (int i = 0; i < str.length(); i++) {
			counts[str.charAt(i)]++;
		}

		int first = 0;
		int second = 0;
		char secondMaxChar;
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] > counts[first]) {
				second = first;
				first = i;
			} else if (counts[i] > counts[second] && counts[i] != counts[first]) {
				second = i;
			}
		}

		return (char) second;
	}

	public static boolean canGetSameFrequencyByUtmostOneRemoval(String str) {
		int[] frequency = new int[26];
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			frequency[c - 'a']++;
		}

		if (areFrequenciesSame(frequency)) {
			return true;
		}

		for (int i = 0; i < frequency.length; i++) {
			if (frequency[i] != 0) {
				frequency[i]--;
				if (areFrequenciesSame(frequency)) {
					return true;
				}

				frequency[i]++;
			}
		}

		return false;
	}

	private static boolean areFrequenciesSame(int[] frequency) {
		int expectedFrequency = 0;

		for (int i = 0; i < frequency.length; i++) {
			if (frequency[i] != 0) {
				expectedFrequency = frequency[i];
				break;
			}
		}

		for (int i = 0; i < frequency.length; i++) {
			if (frequency[i] != 0 && frequency[i] != expectedFrequency) {
				return false;
			}
		}

		return true;
	}

	public static char getKthCharacterOfEncodedString(String encodedString, int k) {
		// Repetitions of substrings are represented as substring followed by count of
		// substrings.
		// Ex: if encoded string is “ab2cd2” and k=4 , decoded string is “ababcdcd” and
		// 4th character is ‘b’.

		StringBuilder decodedString = new StringBuilder();

		StringBuilder subString = new StringBuilder();
		StringBuilder frequencyString = new StringBuilder();

		int index = 0;
		int length = encodedString.length();
		while (index < encodedString.length()) {

			while (index < length && !Character.isDigit(encodedString.charAt(index))) {
				subString.append(encodedString.charAt(index));
				index++;
			}

			while (index < length && Character.isDigit(encodedString.charAt(index))) {
				frequencyString.append(encodedString.charAt(index));
				index++;
			}

			if (frequencyString.length() > 0) {
				int frequency = Integer.parseInt(frequencyString.toString());
				String str = subString.toString();
				for (int i = 0; i < frequency; i++) {
					decodedString.append(str);
				}

				frequencyString = new StringBuilder();
				subString = new StringBuilder();
			}
		}

		if (subString.length() > 0) {
			decodedString.append(subString.toString());
		}

		char kChar = '\0';
		if (k <= decodedString.length()) {
			kChar = decodedString.charAt(k - 1);
		}

		System.out.println("Encoded String: " + encodedString);
		System.out.println("Decoded String: " + decodedString);
		System.out.println("Character at index " + k + " is " + kChar);
		System.out.println();

		return kChar;
	}

	public static int countSubStringsWithKDistinctCharacters(String str, int k) {
		int subStringCount = 0;
		Set<Character> map = new HashSet<>();

		for (int i = 0; i < str.length(); i++) {
			map.clear();
			int distinctCharacterCount = 0;
			for (int j = i; j < str.length(); j++) {
				char c = str.charAt(j);
				if (!map.contains(c)) {
					map.add(c);
					distinctCharacterCount++;
				}

				if (distinctCharacterCount == k) {
					subStringCount++;
				} else if (distinctCharacterCount > k) {
					break;
				}
			}
		}

		return subStringCount;
	}

	public static String removeMaskCharacters(String str, String mask) {
		if (str == null || mask == null) {
			return null;
		}

		Set set = new HashSet();
		for (int i = 0; i < mask.length(); i++) {
			set.add(mask.charAt(i));
		}

		int runner = 0;
		int pos = 0;

		char[] characters = str.toCharArray();
		while (runner < str.length()) {
			char c = characters[runner++];
			if (!set.contains(c)) {
				characters[pos++] = c;
			}
		}

		return new String(characters).substring(0, pos);
	}

	public static String longestSubsequenceWhereEachCharacterOccursAtLeastKTimes(String str, int k) {
		if (str == null) {
			return null;
		}

		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (map.containsKey(c)) {
				int count = map.get(c) + 1;
				map.put(c, count);
			} else {
				map.put(c, 1);
			}
		}

		StringBuilder subsequence = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (map.containsKey(c) && map.get(c) >= k) {
				subsequence.append(c);
			}
		}

		return subsequence.toString();
	}

	public static int getCountOfSubstringsThatStartAndEndWithOne(String str) {
		// Approach 1: check all substrings - O(n^2)
		// Approach 2: nC2 = n*(n-1) / 2

		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '1') {
				count++;
			}
		}

		return count * (count - 1) / 2;
	}

	public static List<List<String>> groupWordsWithSameSetOfCharacters(String[] words) {
		if (words == null) {
			return null;
		}

		Map<String, List<String>> map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			String key = getKey(word);

			if (!map.containsKey(key)) {
				map.put(key, new LinkedList<>());
			}

			map.get(key).add(word);
		}

		List<List<String>> groups = new LinkedList<>();
		for (var entry : map.entrySet()) {
			groups.add(entry.getValue());
		}

		return groups;
	}

	private static String getKey(String word) {
		boolean[] keyMap = new boolean[26];
		Arrays.fill(keyMap, false);
		for (int i = 0; i < word.length(); i++) {
			keyMap[word.charAt(i) - 'a'] = true;
		}

		StringBuilder key = new StringBuilder();
		for (int i = 0; i < keyMap.length; i++) {
			if (keyMap[i]) {
				key.append('a' + i);
			}
		}

		return key.toString();
	}

	public static String addBinaryStings(String str1, String str2) {
		if (str1 == null) {
			return str2;
		}

		if (str2 == null) {
			return str1;
		}

		int i = str1.length() - 1;
		int j = str2.length() - 1;
		int carry = 0;

		String result = "";
		while (i >= 0 || j >= 0 || carry > 0) {
			int sum = 0;
			if (i >= 0) {
				sum += str1.charAt(i) - '0';
			}

			if (j >= 0) {
				sum += str2.charAt(j) - '0';
			}

			sum += carry;

			carry = sum / 2;
			sum = sum % 2;
			result = sum + result;

			i--;
			j--;
		}

		return result;
	}

	public static String getLexicographicConcatenationOfAllSubStrings(String str) {
		int n = str.length();
		int numberOfSubStrings = n * (n + 1) / 2;
		String[] subStrings = new String[numberOfSubStrings];

		int index = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				subStrings[index++] = str.substring(i, j + 1);
			}
		}

		Arrays.sort(subStrings);

		StringBuilder result = new StringBuilder();
		for (String subStr : subStrings) {
			result.append(subStr);
		}

		return result.toString();
	}

	public static String removeDuplicates(String str) {
		// Approaches
		// 1) Simple: Outer loop from start to end; Inner loop from start to i. Add to
		// 		result if not found in inner loop
		// 2) Set: add to set & retrieve (orginal order is lost in case of HashSet, get
		// 		a sorted result; Not a probem with TreeSet)
		// 3) Sort & remove duplicates
		// 4) Hashing

		Set<Character> set = new TreeSet<>();
		for (int i = 0; i < str.length(); i++) {
			set.add(str.charAt(i));
		}

		StringBuilder sb = new StringBuilder();
		for (var c : set) {
			sb.append(c);
		}

		return sb.toString();
	}

	public static List<String> getAllStringsObtainedByPlacingSpaces(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}

		int n = str.length();
		List<String> allStrings = new ArrayList<>();

		char[] buffer = new char[2 * n];
		buffer[0] = str.charAt(0);
		generateAllStringsObtainedByPlacingSpaces(str, 1, n, buffer, 1, allStrings);

		return allStrings;
	}

	private static void generateAllStringsObtainedByPlacingSpaces(String str, int i, int n, char[] buffer, int b, List<String> allStrings) {
		if (i == n) {
			buffer[b] = '\0';
			allStrings.add(new String(buffer, 0, b));
			return;
		}

		// Add i-th character into buffer
		buffer[b] = str.charAt(i);
		generateAllStringsObtainedByPlacingSpaces(str, i + 1, n, buffer, b + 1, allStrings);

		// Add space, and then i-th character into buffer
		buffer[b] = ' ';
		buffer[b + 1] = str.charAt(i);
		generateAllStringsObtainedByPlacingSpaces(str, i + 1, n, buffer, b + 2, allStrings);
	}

	public static List<String> getAllStringsObtainedByPlacingSpacesA2(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}

		int n = str.length();
		List<String> allStrings = new ArrayList<>();

		int count = (int) Math.pow(2, n - 1);
		for (int counter = 0; counter < count; counter++) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < n; i++) {
				sb.append(str.charAt(i));

				if ((counter & (1 << i)) > 0) {
					sb.append(' ');
				}
			}

			allStrings.add(sb.toString());
		}

		return allStrings;
	}

	public static int getLengthOfSubstringWithMaxDifferenceOfZerosAndOnesInBinaryString(String binaryString) {
		if (binaryString == null || binaryString.length() == 0 || !StringAlgorithms.isBinaryString(binaryString)) {
			return 0;
		}

		int n = binaryString.length();
		int[] arr = new int[n];
		int oneCount = 0;
		for (int i = 0; i < n; i++) {
			if (binaryString.charAt(i) == '0') {
				arr[i] = 1;
			} else {
				arr[i] = -1;
				oneCount++;
			}
		}

		if (oneCount == n) {
			// All 1-s
			return -1;
		}

		// dp[i][0] -> denotes the maximum difference till i, exclusive of i
		// dp[i][1] -> denotes the maximum difference till i, inclusive of i
		int[][] dp = new int[n][2];
		for (var row : dp) {
			Arrays.fill(row, -1);
		}

		int val = getLengthOfSubstringWithMaxDifferenceOfZerosAndOnesInBinaryString(binaryString, arr, 0, false, n, dp);
		return val;
	}

	private static int getLengthOfSubstringWithMaxDifferenceOfZerosAndOnesInBinaryString(String binaryString, int[] arr, int index, boolean inclusive, int size, int[][] dp) {
		if (index >= size) {
			// Outside of the string
			return 0;
		}

		int status = inclusive ? 1 : 0;

		if (dp[index][status] != -1) {
			// Already computed
			return dp[index][status];
		}

		if (inclusive) {
			dp[index][status] = Math.max(0,
					arr[index] + getLengthOfSubstringWithMaxDifferenceOfZerosAndOnesInBinaryString(binaryString, arr, index + 1, true, size, dp));
		} else {
			dp[index][status] = Math.max(getLengthOfSubstringWithMaxDifferenceOfZerosAndOnesInBinaryString(binaryString, arr, index + 1, false, size, dp),
					arr[index] + getLengthOfSubstringWithMaxDifferenceOfZerosAndOnesInBinaryString(binaryString, arr, index + 1, true, size, dp));
		}

		return dp[index][status];
	}
}