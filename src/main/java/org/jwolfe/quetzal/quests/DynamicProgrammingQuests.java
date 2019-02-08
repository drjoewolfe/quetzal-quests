package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.algorithms.ArrayAlgorithms;
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

    public static int[] getSellOrderForMaximumRevenueFromSaleOfWinesGivenOnlyFirstOrLastWineCanBeSoldAndPricesMultiplyByYear(int[] winePrices) {
        if (winePrices == null || winePrices.length == 0) {
            return null;
        }

        int n = winePrices.length;

        int[][] maxRevenues = new int[n][n];
        int[][] sellMatrix = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                if (len == 1) {
                    maxRevenues[i][i] = winePrices[i] * n;
                    sellMatrix[i][i] = i;
                    continue;
                }

                int j = i + len - 1;
                int year = n - (j - i);

                int firstSold = winePrices[i] * year + maxRevenues[i + 1][j];
                int lastSold = maxRevenues[i][j - 1] + winePrices[j] * year;

                maxRevenues[i][j] = Math.max(firstSold, lastSold);
                if (firstSold > lastSold) {
                    sellMatrix[i][j] = 0;
                } else {
                    sellMatrix[i][j] = 1;
                }
            }
        }

        int[] sellOrder = new int[n];
        int i = 0;
        int j = n - 1;
        int k = 0;
        while (i <= j) {
            if (sellMatrix[i][j] == 0) {
                // First item sold
                sellOrder[k++] = i++;
            } else {
                // Last item sold
                sellOrder[k++] = j--;
            }
        }

        return sellOrder;
    }

    public static int minimumTimeForKPaintersToPaintNBoardsSuchThatAnyPainterOnlyPaintsContinuousSectionsOfBoard(int[] boardLengths, int numPainters) {
        // Note: Each painter paints one unit of length in one unit of time

        if (boardLengths == null || boardLengths.length == 0 || numPainters <= 0) {
            return 0;
        }

        int numBoards = boardLengths.length;
        int numPartitions = numPainters;

        int[][] dp = new int[numPartitions + 1][numBoards + 1];

        // One Partition
        for (int i = 1; i <= numBoards; i++) {
            dp[1][i] = ArrayAlgorithms.getSum(boardLengths, 0, i - 1);
        }

        // One Board
        for (int i = 1; i <= numPartitions; i++) {
            dp[i][1] = boardLengths[0];
        }

        for (int i = 2; i <= numPartitions; i++) {
            for (int j = 2; j <= numBoards; j++) {
                int minTime = Integer.MAX_VALUE;

                for (int k = 1; k <= j; k++) {
                    int time = Math.max(ArrayAlgorithms.getSum(boardLengths, k, j - 1),
                            dp[i - 1][k]);
                    minTime = Math.min(minTime, time);
                }

                dp[i][j] = minTime;
            }
        }

        return dp[numPartitions][numBoards];
    }

    public static int minimumTimeForKPaintersToPaintNBoardsSuchThatAnyPainterOnlyPaintsContinuousSectionsOfBoardRecursive(int[] boardLengths, int numPainters) {
        // Note: Each painter paints one unit of length in one unit of time

        if (boardLengths == null || boardLengths.length == 0 || numPainters <= 0) {
            return 0;
        }

        int numBoards = boardLengths.length;
        return minimumTimeForKPaintersToPaintNBoardsSuchThatAnyPainterOnlyPaintsContinuousSectionsOfBoardRecursive(boardLengths, numBoards, numPainters);
    }

    private static int minimumTimeForKPaintersToPaintNBoardsSuchThatAnyPainterOnlyPaintsContinuousSectionsOfBoardRecursive(int[] boardLengths, int boards, int partitions) {
        if (boards == 1) {
            // One board left
            return boardLengths[0];
        }

        if (partitions == 1) {
            // One partition
            return ArrayAlgorithms.getSum(boardLengths, 0, boards - 1);
        }


        int minTime = Integer.MAX_VALUE;
        for (int i = 0; i < boards; i++) {
            int time = Math.max(ArrayAlgorithms.getSum(boardLengths, i, boards - 1),
                    minimumTimeForKPaintersToPaintNBoardsSuchThatAnyPainterOnlyPaintsContinuousSectionsOfBoardRecursive(boardLengths, i, partitions - 1));

            minTime = Math.min(minTime, time);
        }

        return minTime;
    }
}