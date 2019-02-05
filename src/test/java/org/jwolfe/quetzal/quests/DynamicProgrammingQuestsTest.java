package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.algorithms.DynamicProgramming;
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
}