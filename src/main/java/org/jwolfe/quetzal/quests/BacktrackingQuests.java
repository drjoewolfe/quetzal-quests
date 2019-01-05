package org.jwolfe.quetzal.quests;

import java.util.*;

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
}
