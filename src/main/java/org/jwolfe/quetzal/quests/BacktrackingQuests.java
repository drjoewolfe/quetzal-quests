package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.algorithms.StringAlgorithms;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BacktrackingQuests {
    public static List<String> getBalancedStringsByRemovingMinimumInvalidParanthesis(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        // Generate all strings that have one character removed & check if it is balanced.
        // BFS Implementation

        List<String> balancedStrings = new ArrayList<>();

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        boolean balancedLevelReached = false;

        queue.add(str);
        visited.add(str);

        while (!queue.isEmpty()) {
            var currentString = queue.poll();

            if (isBalancedString(currentString)) {
                balancedStrings.add(currentString);

                // Current string is balanced. No need to split up & try further for this string.
                balancedLevelReached = true;
            }

            if (balancedLevelReached) {
                // Don't try with lower levels.
                continue;
            }

            for (int i = 0; i < currentString.length(); i++) {
                char c = currentString.charAt(i);
                if (c != '(' && c != ')') {
                    continue;
                }

                // Generate string by removing i-th character
                var newString = new StringBuilder(currentString).deleteCharAt(i).toString();
                if (!visited.contains(newString)) {
                    queue.add(newString);
                    visited.add(newString);
                }
            }
        }

        return balancedStrings;
    }

    private static boolean isBalancedString(String str) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                counter++;
            } else if (c == ')') {
                counter--;
            }

            if (counter < 0) {
                return false;
            }
        }

        return counter == 0;
    }

    public static int getMinimizedCountOfUniqueCharactersAmongStringsPostSwapping(String str1, String str2) {
        // At any index, it is allowed to swap the characters between the 2 strings if it helps in minimizing the count of unique characters.
        // Ex: str1[1] & str2[1] may be swapped if it helps the cause.

        if (str1 == null || str1.length() == 0
                || str2 == null
                || str1.length() != str2.length()) {
            return 0;
        }

        AtomicInteger minimumCount = new AtomicInteger(Integer.MAX_VALUE);
        generateMinimizedCountOfUniqueCharactersAmongStringsPostSwapping(new StringBuilder(str1), new StringBuilder(str2), str1.length(), 0, minimumCount);

        return minimumCount.intValue();
    }

    private static void generateMinimizedCountOfUniqueCharactersAmongStringsPostSwapping(StringBuilder sb1, StringBuilder sb2, int length, int index, AtomicInteger minimumCount) {
        if (index >= length) {
            int minTillNow = Math.min(StringAlgorithms.getUniqueCharacterCount(sb1),
                                    StringAlgorithms.getUniqueCharacterCount(sb2));
            if (minTillNow < minimumCount.intValue()) {
                minimumCount.set(minTillNow);
            }

            return;
        }

        // Try without swapping
        generateMinimizedCountOfUniqueCharactersAmongStringsPostSwapping(sb1, sb2, length, index + 1, minimumCount);

        // Try with swapping
        char c1 = sb1.charAt(index);
        char c2 = sb2.charAt(index);
        sb1.setCharAt(index, c2);
        sb2.setCharAt(index, c1);

        generateMinimizedCountOfUniqueCharactersAmongStringsPostSwapping(sb1, sb2, length, index + 1, minimumCount);

        // Backtrack
        sb1.setCharAt(index, c1);
        sb2.setCharAt(index, c2);
    }
}
