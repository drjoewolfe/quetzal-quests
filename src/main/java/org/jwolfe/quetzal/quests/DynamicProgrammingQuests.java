package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.algorithms.ArrayAlgorithms;
import org.jwolfe.quetzal.algorithms.DynamicProgramming;
import org.jwolfe.quetzal.library.utilities.Utilities;

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

    public static int minimumStartingPointsToTraverseGridAndReachDestination(int[][] grid) {
        // Each cell in the grid has a (+)ve, (-)ve or zero value.
        // We can visit the right, or down cell from any cell.
        // Visiting each cell increases, decreases or keeps the aggregate points based on the value of the cell.
        // If at or less than zero, movement to another cell is not possible
        // Start from (0, 0) & reach the bottom right corner of the grid.
        // Calculate the minimum points to start with to complete the journey

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        // dp[i][j] -> minimum starting points so that if the player starts from (i, j), he can reach the destination
        int[][] dp = new int[m][n];

        // Destination cell
        dp[m - 1][n - 1] = grid[m - 1][n - 1] > 0 ? 1 : Math.abs(grid[m - 1][n - 1]) + 1;

        // Right column
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - grid[i][n - 1], 1);
        }

        // Bottom row
        for (int j = n - 2; j >= 0; j--) {
            dp[m - 1][j] = Math.max(dp[m - 1][j + 1] - grid[m - 1][j], 1);
        }

        // Rest of the grid
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int fromRight = dp[i][j + 1];
                int fromDown = dp[i + 1][j];

                int minEntry = Math.min(fromRight, fromDown);

                dp[i][j] = Math.max(minEntry - grid[i][j], 1);
            }
        }

        return dp[0][0];
    }

    public static int waysToReachNthStairsUsingOneTwoOrThreeStepsAtATime(int n) {
        if (n <= 1) {
            return 1;
        }

        if (n == 2) {
            // 2 ways -> 1 Two Step, or 2 One Steps
            return 2;
        }

        int[] ways = new int[n + 1];
        ways[0] = 1;
        ways[1] = 1;
        ways[2] = 2;

        for (int i = 3; i <= n; i++) {
            ways[i] = ways[i - 1] + ways[i - 2] + ways[i - 3];
        }

        return ways[n];
    }

    public static int waysToReachNthStairsUsingOneTwoOrThreeStepsAtATimeRecursive(int n) {
        if (n <= 1) {
            return 1;
        }

        if (n == 2) {
            // 2 ways -> 1 Two Step, or 2 One Steps
            return 2;
        }

        int ways = 0;

        // One Step
        ways += waysToReachNthStairsUsingOneTwoOrThreeStepsAtATimeRecursive(n - 1);

        // Two Steps
        ways += waysToReachNthStairsUsingOneTwoOrThreeStepsAtATimeRecursive(n - 2);

        // Three Steps
        ways += waysToReachNthStairsUsingOneTwoOrThreeStepsAtATimeRecursive(n - 3);

        return ways;
    }

    public static int minimumCostUsingXorToMakeLongestCommonSubsequenceOfLengthK(String str1, String str2, int k) {
        // Convert str1 with minimal cost so that the LCS of str1 & str2 is of length k
        // Cost of changing a character is the XOR of their values. Character value of 'a' is 0 & so on.
        if (str1 == null || str2 == null || k < 0) {
            return Integer.MAX_VALUE;
        }

        int m = str1.length();
        int n = str2.length();

        int[][][] dp = new int[m + 1][n + 1][k + 1];

        // For k = 0, cost is zero
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j][0] = 0;
            }
        }

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int l = 1; l <= k; l++) {
                    if (i == 0 || j == 0) {
                        dp[i][j][l] = Integer.MAX_VALUE;
                    } else {
                        char a = str1.charAt(i - 1);
                        char b = str2.charAt(j - 1);

                        if (a == b) {
                            dp[i][j][k] = Math.min(
                                    dp[i - 1][j][l],
                                    dp[i][j - 1][l]
                            );
                        } else {
                            int changeCost = dp[i - 1][j - 1][l - 1] == Integer.MAX_VALUE ? Integer.MAX_VALUE
                                    : dp[i - 1][j - 1][l - 1] + ((a - 'a') ^ (b - 'a'));


                            dp[i][j][k] = Utilities.min(
                                    changeCost,
                                    dp[i - 1][j][l],
                                    dp[i][j - 1][l]
                            );
                        }
                    }
                }
            }
        }

        return dp[m][n][k];
    }

    public static int waysToExpressNAsSumOfOneThreeAndFour(int n) {
        if (n < 0) {
            return 0;
        }

        if (n <= 2) {
            return 1;
        }

        if (n == 3) {
            return 2;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;

        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 3] + dp[i - 4];
        }

        return dp[n];
    }

    public static int numberOfPathsWithExactlyKCoinsInGridForTripFromTopLeftToBottomRight(int[][] grid, int k) {
        // From any cell (i, j), travel to (i+1, j) or (i, j + 1) is allowed

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        return numberOfPathsWithExactlyKCoinsInGridForTrip(grid, k, m - 1, n - 1);
    }

    private static int numberOfPathsWithExactlyKCoinsInGridForTrip(int[][] grid, int k, int rowIndex, int colIndex) {
        if (k < 0 || rowIndex < 0 || colIndex < 0) {
            return 0;
        }

        int coins = grid[rowIndex][colIndex];
        if (rowIndex == 0 && colIndex == 0) {
            if (k == coins) {
                return 1;
            } else {
                return 0;
            }
        }

        return numberOfPathsWithExactlyKCoinsInGridForTrip(grid, k - coins, rowIndex - 1, colIndex) +
                numberOfPathsWithExactlyKCoinsInGridForTrip(grid, k - coins, rowIndex, colIndex - 1);
    }
}