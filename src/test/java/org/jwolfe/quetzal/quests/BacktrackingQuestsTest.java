package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;
import org.jwolfe.quetzal.test.QuetzalAssertions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BacktrackingQuestsTest {

    @Test
    void getBalancedStringsByRemovingMinimumInvalidParanthesis() {
        String str;
        List<String> validStrings;
        List<String> expectedValidStrings;

        str = "()())()";
        expectedValidStrings = Utilities.constructList("()()()", "(())()");
        validStrings = BacktrackingQuests.getBalancedStringsByRemovingMinimumInvalidParanthesis(str);
        QuetzalAssertions.assertListEquals(expectedValidStrings, validStrings);

        str = "(a)())()";
        expectedValidStrings = Utilities.constructList("(a)()()", "(a())()");
        validStrings = BacktrackingQuests.getBalancedStringsByRemovingMinimumInvalidParanthesis(str);
        QuetzalAssertions.assertListEquals(expectedValidStrings, validStrings);

        str = "()z)";
        expectedValidStrings = Utilities.constructList("(z)", "()z");
        validStrings = BacktrackingQuests.getBalancedStringsByRemovingMinimumInvalidParanthesis(str);
        QuetzalAssertions.assertListEquals(expectedValidStrings, validStrings);
    }

    @Test
    void getMinimizedCountOfUniqueCharactersAmongStringsPostSwapping() {
        assertEquals(1, BacktrackingQuests.getMinimizedCountOfUniqueCharactersAmongStringsPostSwapping("ababa", "babab"));
        assertEquals(2, BacktrackingQuests.getMinimizedCountOfUniqueCharactersAmongStringsPostSwapping("abaaa", "bbabb"));
    }
}