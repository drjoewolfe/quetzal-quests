package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StringQuestsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void printAllPermutations() {
        StringQuests.printAllPermutations("ABC");
        StringQuests.printAllPermutations("Wolfe");
    }

    @Test
    void isIsoMorphic() {
        assertEquals(true, StringQuests.isIsoMorphic("aab", "xxy"));
        assertEquals(false, StringQuests.isIsoMorphic("aab", "xyz"));
    }

    @Test
    void longestPalindromicSubsequenceLength() {
        assertEquals(4, StringQuests.longestPalindromicSubsequenceLength("abbaab"));
        assertEquals(5, StringQuests.longestPalindromicSubsequenceLength("geeksforgeeks"));
    }

    @Test
    void reverseWithoutTemporaryVariable() {
        String str1 = "reverse string";
        String str2 = StringQuests.reverseWithoutTemporaryVariable(str1);

        System.out.println(str1);
        System.out.println(str2);
    }

    @Test
    void countMinimumBracketReversals() {
        String expression;
        int reversals;

        expression = "}}{{";
        reversals = StringQuests.countMinimumBracketReversals(expression);
        System.out.println(reversals);
        assertEquals(2, reversals);

        expression = "}{";
        reversals = StringQuests.countMinimumBracketReversals(expression);
        System.out.println(reversals);
        assertEquals(2, reversals);

        expression = "{{{";
        reversals = StringQuests.countMinimumBracketReversals(expression);
        System.out.println(reversals);
        assertEquals(-1, reversals);

        expression = "{{{{";
        reversals = StringQuests.countMinimumBracketReversals(expression);
        System.out.println(reversals);
        assertEquals(2, reversals);

        expression = "{{{{}}";
        reversals = StringQuests.countMinimumBracketReversals(expression);
        System.out.println(reversals);
        assertEquals(1, reversals);

        expression = "}{{}}{{{";
        reversals = StringQuests.countMinimumBracketReversals(expression);
        System.out.println(reversals);
        assertEquals(3, reversals);

        expression = "{{{{}}}}{{}}{}{}";
        reversals = StringQuests.countMinimumBracketReversals(expression);
        System.out.println(reversals);
        assertEquals(0, reversals);
    }

    @Test
    void maxDepthOfNestedParanthesis() {
        String expression;
        int maxDepth;

        expression = "( a(b) (c) (d(e(f)g)h) I (j(k)l)m)";
        maxDepth = StringQuests.maxDepthOfNestedParanthesis(expression);
        System.out.println(maxDepth);
        assertEquals(4, maxDepth);

        expression = "( p((q)) ((s)t) )";
        maxDepth = StringQuests.maxDepthOfNestedParanthesis(expression);
        System.out.println(maxDepth);
        assertEquals(3, maxDepth);

        expression = "";
        maxDepth = StringQuests.maxDepthOfNestedParanthesis(expression);
        System.out.println(maxDepth);
        assertEquals(0, maxDepth);

        expression = "b) (c) ()";
        maxDepth = StringQuests.maxDepthOfNestedParanthesis(expression);
        System.out.println(maxDepth);
        assertEquals(-1, maxDepth);

        expression = "(b) ((c) ()";
        maxDepth = StringQuests.maxDepthOfNestedParanthesis(expression);
        System.out.println(maxDepth);
        assertEquals(-1, maxDepth);
    }

    @Test
    void match() {
        String pattern;
        String text;
        boolean match;

        pattern = "g*ks";
        text = "geeks";
        match = StringQuests.match(pattern, text);
        System.out.println(match);
        assertEquals(true, match);

        pattern = "ge?ks*";
        text = "geeksforgeeks";
        match = StringQuests.match(pattern, text);
        System.out.println(match);
        assertEquals(true, match);

        pattern = "g*k";
        text = "gee";
        match = StringQuests.match(pattern, text);
        System.out.println(match);
        assertEquals(false, match);

        pattern = "pqrs";
        text = "pqrst";
        match = StringQuests.match(pattern, text);
        System.out.println(match);
        assertEquals(false, match);

        pattern = "abc*bcd";
        text = "abcdhghgbcd";
        match = StringQuests.match(pattern, text);
        System.out.println(match);
        assertEquals(true, match);

        pattern = "abc*c?d";
        text = "abcd";
        match = StringQuests.match(pattern, text);
        System.out.println(match);
        assertEquals(false, match);

        pattern = "*c*d";
        text = "abcd";
        match = StringQuests.match(pattern, text);
        System.out.println(match);
        assertEquals(true, match);

        pattern = "*?c*d";
        text = "abcd";
        match = StringQuests.match(pattern, text);
        System.out.println(match);
        assertEquals(true, match);
    }

    @Test
    void shortestChain() {
        String start;
        String target;
        Set<String> dictonary;
        int chainLength;

        dictonary = new HashSet<>();
        dictonary.add("poon");
        dictonary.add("plee");
        dictonary.add("same");
        dictonary.add("poie");
        dictonary.add("plie");
        dictonary.add("poin");
        dictonary.add("plea");
        start = "toon";
        target = "plea";
        chainLength = StringQuests.shortestChain(start, target, dictonary);
        System.out.println(chainLength);
        assertEquals(7, chainLength);
    }

    @Test
    void getSecondMostFrequentCharacter() {
        String str;
        char c;

        str = "aabababa";
        c = StringQuests.getSecondMostFrequentCharacter(str);
        System.out.println(str + " -> " + c);
        assertEquals('b', c);

        str = "geeksquiz";
        c = StringQuests.getSecondMostFrequentCharacter(str);
        System.out.println(str + " -> " + c);
        assertEquals('g', c);
    }

    @Test
    void canGetSameFrequencyByUtmostOneRemoval() {
        String str;
        boolean attainable;

        str = "x";
        attainable = StringQuests.canGetSameFrequencyByUtmostOneRemoval(str);
        assertEquals(true, attainable);

        str = "xyyyy";
        attainable = StringQuests.canGetSameFrequencyByUtmostOneRemoval(str);
        assertEquals(true, attainable);

        str = "xy";
        attainable = StringQuests.canGetSameFrequencyByUtmostOneRemoval(str);
        assertEquals(true, attainable);

        str = "xxyy";
        attainable = StringQuests.canGetSameFrequencyByUtmostOneRemoval(str);
        assertEquals(true, attainable);

        str = "xyyz";
        attainable = StringQuests.canGetSameFrequencyByUtmostOneRemoval(str);
        assertEquals(true, attainable);

        str = "xyyzz";
        attainable = StringQuests.canGetSameFrequencyByUtmostOneRemoval(str);
        assertEquals(true, attainable);

        str = "xxxxyyzz";
        attainable = StringQuests.canGetSameFrequencyByUtmostOneRemoval(str);
        assertEquals(false, attainable);
    }

    @Test
    void getKthCharacterOfEncodedString() {
        String encodedString;
        char kthChar;

        encodedString = "a2b2c3";
        kthChar = StringQuests.getKthCharacterOfEncodedString(encodedString, 5);
        assertEquals('c', kthChar);

        encodedString = "ab4c2ed3";
        kthChar = StringQuests.getKthCharacterOfEncodedString(encodedString, 9);
        assertEquals('c', kthChar);

        encodedString = "ab4c12ed3";
        kthChar = StringQuests.getKthCharacterOfEncodedString(encodedString, 21);
        assertEquals('e', kthChar);
    }

    @Test
    void countSubStringsWithKDistinctCharacters() {
        String str;
        int charCount;

        str = "abc";
        charCount = StringQuests.countSubStringsWithKDistinctCharacters(str, 2);
        assertEquals(2, charCount);

        str = "aba";
        charCount = StringQuests.countSubStringsWithKDistinctCharacters(str, 2);
        assertEquals(3, charCount);

        str = "aa";
        charCount = StringQuests.countSubStringsWithKDistinctCharacters(str, 1);
        assertEquals(3, charCount);

        str = "abcbaa";
        charCount = StringQuests.countSubStringsWithKDistinctCharacters(str, 3);
        assertEquals(8, charCount);
    }

    @Test
    void removeMaskCharacters() {
        String str;
        String mask;
        String modifiedString;
        String expectedString;

        str = "abcdefghijklmnopqrstuvwxyz";
        mask = "bcdexyz";
        expectedString = "afghijklmnopqrstuvw";
        modifiedString = StringQuests.removeMaskCharacters(str, mask);
        assertEquals(expectedString, modifiedString);
    }

    @Test
    void longestSubsequenceWhereEachCharacterOccursAtLeastKTimes() {
        String str;
        String result;
        String expected;

        str = "baaabaacba";
        expected = "baaabaaba";
        result = StringQuests.longestSubsequenceWhereEachCharacterOccursAtLeastKTimes(str, 3);
        assertEquals(expected, result);

        str = "hggpztinhggpz";
        expected = "hggpzhggpz";
        result = StringQuests.longestSubsequenceWhereEachCharacterOccursAtLeastKTimes(str, 2);
        assertEquals(expected, result);
    }

    @Test
    void getCountOfSubstringsThatStartAndEndWithOne() {
        String str;
        int result;
        int expected;

        str = "00100101";
        expected = 3;
        result = StringQuests.getCountOfSubstringsThatStartAndEndWithOne(str);
        assertEquals(expected, result);
    }

    @Test
    void groupWordsWithSameSetOfCharacters() {
        String[] words = { "may", "student", "students", "dog",
                "studentssess", "god", "cat", "act", "tab",
                "bat", "flow", "wolf", "lambs", "amy", "yam",
                "balms", "looped", "poodle"};
        var groups = StringQuests.groupWordsWithSameSetOfCharacters(words);
        for(var group : groups) {
            for(var word: group) {
                System.out.print(word + " ");
            }
            System.out.println();
        }

        assertEquals(8, groups.size());
    }
}