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

    @Test
    void solveSoduku() {
        int[][] board = {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}};

        int[][] expectedBoard = {
                {3, 1, 6, 5, 7, 8, 4, 9, 2},
                {5, 2, 9, 1, 3, 4, 7, 6, 8},
                {4, 8, 7, 6, 2, 9, 5, 3, 1},
                {2, 6, 3, 4, 1, 5, 9, 8, 7},
                {9, 7, 4, 8, 6, 3, 1, 2, 5},
                {8, 5, 1, 7, 9, 2, 6, 4, 3},
                {1, 3, 8, 9, 4, 7, 2, 5, 6},
                {6, 9, 2, 3, 5, 1, 8, 7, 4},
                {7, 4, 5, 2, 8, 6, 3, 1, 9}};

        Utilities.printArray(board);
        System.out.println();
        boolean solved = Puzzles.soduku(board);
        Utilities.printArray(board);
        assertTrue(solved);
        assertTwoDimensionalArrayEquals(expectedBoard, board);
    }
    
    @Test
    void celebrityProblem() {
        int[][] acquaintanceMatrix = {{0, 0, 1, 0}, 
						                {0, 0, 1, 0}, 
						                {0, 0, 0, 0}, 
						                {0, 0, 1, 0}};
        
        assertEquals(2, Puzzles.celebrityProblem(4, acquaintanceMatrix));
    }
    
    @Test
    void getWaysToTile2xNBoardWith2x1Tiles() {        
        assertEquals(3, Puzzles.getWaysToTile2xNBoardWith2x1Tiles(3));
        assertEquals(5, Puzzles.getWaysToTile2xNBoardWith2x1Tiles(4));
    }
    
    @Test
    void getWaysToTile2xNBoardWith2x1TilesRecursive() {        
        assertEquals(3, Puzzles.getWaysToTile2xNBoardWith2x1TilesRecursive(3));
        assertEquals(5, Puzzles.getWaysToTile2xNBoardWith2x1TilesRecursive(4));
    }
}