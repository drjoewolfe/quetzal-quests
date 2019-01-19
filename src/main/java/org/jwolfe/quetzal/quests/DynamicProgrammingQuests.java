package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.algorithms.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicProgrammingQuests {
    public static List<Integer> getMinimizedListAfterRepeatedDeletionsOfLongestIncreasingSubsequencesOfSizeMoreThanOne(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        List<Integer> minimizedList = new ArrayList<>();
        Arrays.stream(arr).forEach(minimizedList::add);

        List<Integer> lisList = DynamicProgramming.getLongestIncreasingSubsequence(minimizedList);
        while (lisList != null && lisList.size() > 1) {
            for (var number : lisList) {
                int i;
                for (i = 0; i < minimizedList.size(); i++) {
                    if (minimizedList.get(i) == number) {
                        break;
                    }
                }

                minimizedList.remove(i);
            }

            lisList = DynamicProgramming.getLongestIncreasingSubsequence(minimizedList);
        }

        return minimizedList;
    }
}
