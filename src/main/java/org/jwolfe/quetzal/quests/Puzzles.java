package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.library.general.Rod;

import java.util.Stack;

public class Puzzles {

    public static void towersOfHanoi(Stack<Integer> from, Stack<Integer> to, Stack<Integer> aux) {
        if(from == null
                || to == null
                || aux == null) {
            return;
        }

        int numDisks = from.size();
        if(numDisks == 0) {
            return;
        }

        Rod fromRod = new Rod("A", from);
        Rod toRod = new Rod("B", to);
        Rod auxRod = new Rod("C", aux);

        towersOfHanoi(fromRod, toRod, auxRod, numDisks);
    }

    private static void towersOfHanoi(Rod fromRod, Rod toRod, Rod auxRod, int numDisks) {
        if(numDisks < 1) {
            return;
        }

        towersOfHanoi(fromRod, auxRod, toRod, numDisks - 1);
        System.out.println("Move disk " + fromRod.getDisks().peek() + " from " + fromRod.getName() + " to " + toRod.getName());
        toRod.getDisks().push(fromRod.getDisks().pop());
        towersOfHanoi(auxRod, toRod, fromRod, numDisks - 1);
    }

    public static boolean nQueeens(int[][] board) {
        if(board == null) {
            return false;
        }

        int n = board.length;
        for (int i = 0; i < n; i++) {
            if(board[i].length != n) {
                return false;
            }
        }

        return nQueeens(board, 0);
    }

    private static boolean nQueeens(int[][] board, int column) {
        int n = board.length;
        if(column >= board.length) {
            return true;
        }

        for (int i = 0; i < n; i++) {
            if(isSafeForQueen(board, i, column)) {
                board[i][column] = 1;
                if(nQueeens(board, column + 1)) {
                    return true;
                }
                board[i][column] = 0;
            }
        }

        return false;
    }

    private static boolean isSafeForQueen(int[][] board, int row, int column) {
        int n = board.length;

        // Vertical & Horizontal
        for (int i = 0; i < n; i++) {
            if(board[i][column] != 0
                    || board[row][i] != 0) {
                return false;
            }
        }

        // Diagonal - 1 & 2
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if( ((i - j) == (row - column))
                        || (i + j) == (row + column)) {
                    if(board[i][j] != 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
