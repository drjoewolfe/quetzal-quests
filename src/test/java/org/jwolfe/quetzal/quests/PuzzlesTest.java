package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.List;
import java.util.Map;
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
		expectedTo = Utilities.createStack(4, 3, 2, 1);
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
		int[][] board = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

		boolean solved = Puzzles.nQueeens(board);
		assertEquals(true, solved);
		Utilities.printArray(board);
	}

	@Test
	void solveSoduku() {
		int[][] board = {{3, 0, 6, 5, 0, 8, 4, 0, 0}, {5, 2, 0, 0, 0, 0, 0, 0, 0}, {0, 8, 7, 0, 0, 0, 0, 3, 1},
				{0, 0, 3, 0, 1, 0, 0, 8, 0}, {9, 0, 0, 8, 6, 3, 0, 0, 5}, {0, 5, 0, 0, 9, 0, 6, 0, 0},
				{1, 3, 0, 0, 0, 0, 2, 5, 0}, {0, 0, 0, 0, 0, 0, 0, 7, 4}, {0, 0, 5, 2, 0, 6, 3, 0, 0}};

		int[][] expectedBoard = {{3, 1, 6, 5, 7, 8, 4, 9, 2}, {5, 2, 9, 1, 3, 4, 7, 6, 8},
				{4, 8, 7, 6, 2, 9, 5, 3, 1}, {2, 6, 3, 4, 1, 5, 9, 8, 7}, {9, 7, 4, 8, 6, 3, 1, 2, 5},
				{8, 5, 1, 7, 9, 2, 6, 4, 3}, {1, 3, 8, 9, 4, 7, 2, 5, 6}, {6, 9, 2, 3, 5, 1, 8, 7, 4},
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
		int[][] acquaintanceMatrix = {{0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}, {0, 0, 1, 0}};

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

	@Test
	void buySellStocksForMaximumProfit() {
		int[] pricesByDay;
		List<Pair<Integer, Integer>> buySellPairs;

		pricesByDay = Utilities.constructArray(100, 180, 260, 310, 40, 535, 695);
		buySellPairs = Puzzles.buySellStocksForMaximumProfit(pricesByDay);
		assertTrue(buySellPairs.contains(new Pair<>(0, 3)));
		assertTrue(buySellPairs.contains(new Pair<>(4, 6)));
	}

	@Test
	void minCostToConvert3x3MatrixToMagicSquare() {
		int[][] matrix = new int[][]{{4, 9, 2}, {3, 5, 7}, {8, 1, 5}};
		assertEquals(1, Puzzles.minCostToConvert3x3MatrixToMagicSquare(matrix));

		matrix = new int[][]{{4, 8, 2}, {4, 5, 7}, {6, 1, 6}};
		assertEquals(4, Puzzles.minCostToConvert3x3MatrixToMagicSquare(matrix));
	}

	@Test
	void travellingSalesman() {
		int[][] citesAndDistances = new int[][]{{0, 10, 15, 20},
				{10, 0, 35, 25},
				{15, 35, 0, 30},
				{20, 25, 30, 0}};
		assertEquals(80, Puzzles.travellingSalesman(citesAndDistances, 0));
	}

	@Test
	void travellingSalesmanRecursive() {
		int[][] citesAndDistances = new int[][]{{0, 10, 15, 20},
				{10, 0, 35, 25},
				{15, 35, 0, 30},
				{20, 25, 30, 0}};
		assertEquals(80, Puzzles.travellingSalesmanRecursive(citesAndDistances, 0));
	}

	@Test
	void travellingSalesmanNaive() {
		int[][] citesAndDistances = new int[][]{{0, 10, 15, 20},
				{10, 0, 35, 25},
				{15, 35, 0, 30},
				{20, 25, 30, 0}};
		assertEquals(80, Puzzles.travellingSalesmanNaive(citesAndDistances, 0));
	}

	@Test
	void knightsTour() {
		boolean solvable;
		int[][] tour;
		int[][] expectedTour;

		tour = new int[3][3];
		solvable = Puzzles.knightsTour(tour);
		assertFalse(solvable);
		Utilities.printArray(tour);
		System.out.println();

		tour = new int[4][4];
		solvable = Puzzles.knightsTour(tour);
		assertFalse(solvable);
		Utilities.printArray(tour);
		System.out.println();

		tour = new int[5][5];
		solvable = Puzzles.knightsTour(tour);
		assertTrue(solvable);
		Utilities.printArray(tour);
		System.out.println();

		tour = new int[6][6];
		solvable = Puzzles.knightsTour(tour);
		assertTrue(solvable);
		Utilities.printArray(tour);
		System.out.println();

		tour = new int[8][8];
		solvable = Puzzles.knightsTour(tour);
		assertTrue(solvable);
		Utilities.printArray(tour);
		System.out.println();
	}

	@Test
	void knightsTourBacktracking() {
		boolean solvable;
		int[][] tour;
		int[][] expectedTour;

		tour = new int[3][3];
		solvable = Puzzles.knightsTourBacktracking(tour);
		assertFalse(solvable);
		Utilities.printArray(tour);
		System.out.println();

		tour = new int[4][4];
		solvable = Puzzles.knightsTourBacktracking(tour);
		assertFalse(solvable);
		Utilities.printArray(tour);
		System.out.println();

		tour = new int[5][5];
		solvable = Puzzles.knightsTourBacktracking(tour);
		assertTrue(solvable);
		Utilities.printArray(tour);
		System.out.println();

		tour = new int[6][6];
		solvable = Puzzles.knightsTourBacktracking(tour);
		assertTrue(solvable);
		Utilities.printArray(tour);
		System.out.println();

		// The below will run for a very long time. Commenting out.
		//		expectedTour = new int[][]
		//					{{0, 59, 38, 33, 30, 17, 8, 63},
		//				 {37, 34, 31, 60,  9, 62, 29, 16},
		//				 {58, 1, 36, 39, 32, 27, 18, 7},
		//				 {35, 48, 41, 26, 61, 10, 15, 28},
		//				 {42, 57, 2, 49, 40, 23, 6, 19},
		//				 {47, 50, 45, 54, 25, 20, 11, 14},
		//				 {56, 43, 52, 3, 22, 13, 24, 5},
		//				 {51, 46, 55, 44, 53, 4, 21, 12}};
		//		tour = new int[8][8];
		//		solvable = Puzzles.knightsTour(tour);
		//		assertTrue(solvable);
		//		Utilities.printArray(tour);
		//		System.out.println();
		//		assertTwoDimensionalArrayEquals(expectedTour, tour);
	}

	@Test
	void colorGraph() {
		int[][] graph;
		Map<Integer, Integer> colorMappings;

		graph = new int[][]{{0, 1, 1, 1},
				{1, 0, 1, 0},
				{1, 1, 0, 1},
				{1, 0, 1, 0}};
		colorMappings = Puzzles.colorGraph(graph, 3);
		assertNotNull(colorMappings);
		assertEquals(4, colorMappings.size());
		assertEquals(1, (int) colorMappings.get(0));
		assertEquals(2, (int) colorMappings.get(1));
		assertEquals(3, (int) colorMappings.get(2));
		assertEquals(2, (int) colorMappings.get(3));
	}

	@Test
	void getMaxGoldFromMine() {
		int[][] mine;
		int maxGold;

		mine = new int[][]{{1, 3, 1, 5},
				{2, 2, 4, 1},
				{5, 0, 2, 3},
				{0, 6, 1, 2}};
		maxGold = Puzzles.getMaxGoldFromMine(mine);
		assertEquals(16, maxGold);
	}

	@Test
	void getMaxGoldFromMineRecursive() {
		int[][] mine;
		int maxGold;

		mine = new int[][]{{1, 3, 1, 5},
				{2, 2, 4, 1},
				{5, 0, 2, 3},
				{0, 6, 1, 2}};
		maxGold = Puzzles.getMaxGoldFromMineRecursive(mine);
		assertEquals(16, maxGold);


		mine = new int[][]{{1, 3, 3},
				{2, 1, 4},
				{0, 6, 4}};
		maxGold = Puzzles.getMaxGoldFromMineRecursive(mine);
		assertEquals(12, maxGold);


		mine = new int[][]{{10, 33, 13, 15},
				{22, 21, 04, 1},
				{5, 0, 2, 3},
				{0, 6, 14, 2}};
		maxGold = Puzzles.getMaxGoldFromMineRecursive(mine);
		assertEquals(83, maxGold);
	}

	@Test
	void solveMagnetPuzzle() {
		char[][] boardConfiguration;
		int[] topSpecifications;
		int[] leftSpecifications;
		int[] bottomSpecifications;
		int[] rightSpecifications;
		char[][] board;
		char[][] expectedBoard;

		boardConfiguration = new char[][]{
				{'T', 'T', 'T'},
				{'B', 'B', 'B'},
				{'T', 'L', 'R'},
				{'B', 'L', 'R'}};
		topSpecifications = Utilities.constructArray(2, -1, -1);
		leftSpecifications = Utilities.constructArray(-1, -1, 2, -1);
		bottomSpecifications = Utilities.constructArray(-1, -1, 2);
		rightSpecifications = Utilities.constructArray(0, -1, -1, -1);
		expectedBoard = new char[][]{
				{'+', 'X', '+'},
				{'-', 'X', '-'},
				{'+', '-', '+'},
				{'-', '+', '-'}
		};
		board = Puzzles.solveMagnetPuzzle(boardConfiguration, topSpecifications, leftSpecifications, bottomSpecifications, rightSpecifications);
		assertNotNull(board);
		assertTwoDimensionalArrayEquals(expectedBoard, board);

		boardConfiguration = new char[][]{
				{'L', 'R', 'L', 'R', 'T', 'T'},
				{'L', 'R', 'L', 'R', 'B', 'B'},
				{'T', 'T', 'T', 'T', 'L', 'R'},
				{'B', 'B', 'B', 'B', 'T', 'T'},
				{'L', 'R', 'L', 'R', 'B', 'B'}};
		topSpecifications = Utilities.constructArray(1, -1, -1, 2, 1, -1);
		leftSpecifications = Utilities.constructArray(2, 3, -1, -1, -1);
		bottomSpecifications = Utilities.constructArray(2, -1, -1, 2, -1, 3);
		rightSpecifications = Utilities.constructArray(-1, -1, -1, 1, -1);
		expectedBoard = new char[][]{
				{'+', '-', '+', '-', 'X', '-'},
				{'-', '+', '-', '+', 'X', '+'},
				{'X', 'X', '+', '-', '+', '-'},
				{'X', 'X', '-', '+', 'X', '+'},
				{'-', '+', 'X', 'X', 'X', '-'}
		};
		board = Puzzles.solveMagnetPuzzle(boardConfiguration, topSpecifications, leftSpecifications, bottomSpecifications, rightSpecifications);
		assertNotNull(board);
		assertTwoDimensionalArrayEquals(expectedBoard, board);
	}
}