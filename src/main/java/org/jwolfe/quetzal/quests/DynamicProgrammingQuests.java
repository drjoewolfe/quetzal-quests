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

    public static int countWaysToConstructBuildingsOnGivenSectionsWherePlotCanBeOnEitherSidesOfRoadAndSpacesRequiredBetweenSections(int numSections) {
        if (numSections <= 0) {
            return 0;
        }

        // We start with considering one side of the road. This squared is the total # of ways
        // dp[i][0] -> denotes # ways buildings can be constructed for the ith plot ending with a space
        // dp[i][1] -> denotes # ways buildings can be constructed for the ith plot ending with a building
        int[][] dp = new int[numSections][2];
        dp[0][0] = 1;
        dp[0][1] = 1;

        for (int i = 1; i < numSections; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 1][0];
        }

        int numWays = dp[numSections - 1][0] + dp[numSections - 1][1];
        return numWays * numWays;
    }

    public static int maximumRevenueFromSaleOfWinesGivenOnlyFirstOrLastWineCanBeSoldAndPricesMultiplyByYear(int[] winePrices) {
        if (winePrices == null || winePrices.length == 0) {
            return 0;
        }

        int n = winePrices.length;

        int[][] maxRevenues = new int[n][n];
        for (var row : maxRevenues) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        for (int i = 0; i < n; i++) {
            maxRevenues[i][i] = winePrices[i] * n;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                int year = n - (j - i);

                int firstSold = winePrices[i] * year + maxRevenues[i + 1][j];
                int lastSold = maxRevenues[i][j - 1] + winePrices[j] * year;

                maxRevenues[i][j] = Math.max(firstSold, lastSold);
            }
        }

        return maxRevenues[0][n - 1];
    }
}
