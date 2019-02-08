package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;
import org.jwolfe.quetzal.test.QuetzalAssertions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DynamicProgrammingQuestsTest {

    @Test
    void getMinimizedListAfterRepeatedDeletionsOfLongestIncreasingSubsequencesOfSizeMoreThanOne() {
        int[] arr;
        List<Integer> minimizedList;
        List<Integer> expectedMinimizedList;

        arr = new int[]{1, 2, 5, 3, 6, 4, 1};
        expectedMinimizedList = Utilities.constructList(1);
        minimizedList = DynamicProgrammingQuests.getMinimizedListAfterRepeatedDeletionsOfLongestIncreasingSubsequencesOfSizeMoreThanOne(arr);
        QuetzalAssertions.assertListEquals(expectedMinimizedList, minimizedList);

        arr = new int[]{1, 2, 3, 1, 5, 2};
        expectedMinimizedList = new ArrayList<>();
        minimizedList = DynamicProgrammingQuests.getMinimizedListAfterRepeatedDeletionsOfLongestIncreasingSubsequencesOfSizeMoreThanOne(arr);
        QuetzalAssertions.assertListEquals(expectedMinimizedList, minimizedList);

        arr = new int[]{5, 3, 2};
        expectedMinimizedList = Utilities.constructList(5, 3, 2);
        minimizedList = DynamicProgrammingQuests.getMinimizedListAfterRepeatedDeletionsOfLongestIncreasingSubsequencesOfSizeMoreThanOne(arr);
        QuetzalAssertions.assertListEquals(expectedMinimizedList, minimizedList);
    }

    @Test
    void countWaysToConstructBuildingsOnGivenSectionsWherePlotCanBeOnEitherSidesOfRoadAndSpacesRequiredBetweenSections() {
        assertEquals(4, DynamicProgrammingQuests.countWaysToConstructBuildingsOnGivenSectionsWherePlotCanBeOnEitherSidesOfRoadAndSpacesRequiredBetweenSections(1));
        assertEquals(25, DynamicProgrammingQuests.countWaysToConstructBuildingsOnGivenSectionsWherePlotCanBeOnEitherSidesOfRoadAndSpacesRequiredBetweenSections(3));
        assertEquals(64, DynamicProgrammingQuests.countWaysToConstructBuildingsOnGivenSectionsWherePlotCanBeOnEitherSidesOfRoadAndSpacesRequiredBetweenSections(4));
    }

    @Test
    void maximumRevenueFromSaleOfWinesGivenOnlyFirstOrLastWineCanBeSoldAndPricesMultiplyByYear() {
        int[] winePrices;

        winePrices = new int[]{2, 4, 6, 2, 5};
        assertEquals(64, DynamicProgrammingQuests.maximumRevenueFromSaleOfWinesGivenOnlyFirstOrLastWineCanBeSoldAndPricesMultiplyByYear(winePrices));
    }

    @Test
    void getSellOrderForMaximumRevenueFromSaleOfWinesGivenOnlyFirstOrLastWineCanBeSoldAndPricesMultiplyByYear() {
        int[] winePrices;
        int[] sellOrder;
        int[] expectedSellOrder;

        winePrices = new int[]{2, 4, 6, 2, 5};
        expectedSellOrder = new int[]{0, 4, 3, 1, 2};
        sellOrder = DynamicProgrammingQuests.getSellOrderForMaximumRevenueFromSaleOfWinesGivenOnlyFirstOrLastWineCanBeSoldAndPricesMultiplyByYear(winePrices);
        assertArrayEquals(expectedSellOrder, sellOrder);
    }

    @Test
    void minimumTimeForKPaintersToPaintNBoardsSuchThatAnyPainterOnlyPaintsContinuousSectionsOfBoard() {
        int[] boardLengths;
        int numPainters;

        boardLengths = new int[]{10, 10, 10, 10};
        numPainters = 2;
        assertEquals(20, DynamicProgrammingQuests.minimumTimeForKPaintersToPaintNBoardsSuchThatAnyPainterOnlyPaintsContinuousSectionsOfBoard(boardLengths, numPainters));

        boardLengths = new int[]{10, 20, 30, 40};
        numPainters = 2;
        assertEquals(60, DynamicProgrammingQuests.minimumTimeForKPaintersToPaintNBoardsSuchThatAnyPainterOnlyPaintsContinuousSectionsOfBoard(boardLengths, numPainters));

        boardLengths = new int[]{10, 20, 60, 50, 30, 40};
        numPainters = 3;
        assertEquals(90, DynamicProgrammingQuests.minimumTimeForKPaintersToPaintNBoardsSuchThatAnyPainterOnlyPaintsContinuousSectionsOfBoard(boardLengths, numPainters));
    }

    @Test
    void minimumTimeForKPaintersToPaintNBoardsSuchThatAnyPainterOnlyPaintsContinuousSectionsOfBoardRecursive() {
        int[] boardLengths;
        int numPainters;

        boardLengths = new int[]{10, 10, 10, 10};
        numPainters = 2;
        assertEquals(20, DynamicProgrammingQuests.minimumTimeForKPaintersToPaintNBoardsSuchThatAnyPainterOnlyPaintsContinuousSectionsOfBoardRecursive(boardLengths, numPainters));

        boardLengths = new int[]{10, 20, 30, 40};
        numPainters = 2;
        assertEquals(60, DynamicProgrammingQuests.minimumTimeForKPaintersToPaintNBoardsSuchThatAnyPainterOnlyPaintsContinuousSectionsOfBoardRecursive(boardLengths, numPainters));

        boardLengths = new int[]{10, 20, 60, 50, 30, 40};
        numPainters = 3;
        assertEquals(90, DynamicProgrammingQuests.minimumTimeForKPaintersToPaintNBoardsSuchThatAnyPainterOnlyPaintsContinuousSectionsOfBoardRecursive(boardLengths, numPainters));
    }
}