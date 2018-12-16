package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;
import static org.jwolfe.quetzal.test.QuetzalAssertions.*;

class PuzzlesTest {
    @Test
    void towersOfHanoi() {
        Stack<Integer> from;
        Stack<Integer> to;
        Stack<Integer> aux;
        Stack<Integer> expectedFrom;
        Stack<Integer> expectedAux;
        Stack<Integer> expectedTo;

        from = Utilities.createStack(4, 3, 2, 1);
        to = new Stack<>();
        aux = new Stack<>();
        expectedFrom = new Stack<>();
        expectedAux = new Stack<>();
        expectedTo = Utilities.createStack( 4, 3, 2, 1);
        Puzzles.towersOfHanoi(from, to, aux);
        assertStackEquals(expectedFrom, from);
        assertStackEquals(expectedAux, aux);
        assertStackEquals(expectedTo, to);

        System.out.println();
        from = Utilities.createStack(5, 4, 3, 2, 1);
        to = new Stack<>();
        aux = new Stack<>();
        expectedFrom = new Stack<>();
        expectedAux = new Stack<>();
        expectedTo = Utilities.createStack(5, 4, 3, 2, 1);
        Puzzles.towersOfHanoi(from, to, aux);
        assertStackEquals(expectedFrom, from);
        assertStackEquals(expectedAux, aux);
        assertStackEquals(expectedTo, to);
    }

    @Test
    void nQueeens() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};

        boolean solved = Puzzles.nQueeens(board);
        assertEquals(true, solved);
        Utilities.printArray(board);
    }
}