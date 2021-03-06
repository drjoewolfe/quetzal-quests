package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.general.Board;
import org.jwolfe.quetzal.library.general.Coordinate;
import org.jwolfe.quetzal.library.general.IntPair;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.utilities.Utilities;
import org.jwolfe.quetzal.test.QuetzalAssertions;

import java.util.*;

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
		int[][] expectedBoard = {{0, 0, 1, 0}, {1, 0, 0, 0}, {0, 0, 0, 1}, {0, 1, 0, 0}};

		boolean solved = Puzzles.nQueeens(board);
		assertEquals(true, solved);
		assertTwoDimensionalArrayEquals(expectedBoard, board);
		Utilities.printArray(board);

		System.out.println();
		board = new int[5][5];
		Utilities.fillArray(board, 0);
		solved = Puzzles.nQueeens(board);
		assertEquals(true, solved);
		Utilities.printArray(board);

		System.out.println();
		board = new int[6][6];
		Utilities.fillArray(board, 0);
		solved = Puzzles.nQueeens(board);
		assertEquals(true, solved);
		Utilities.printArray(board);

		System.out.println();
		board = new int[7][7];
		Utilities.fillArray(board, 0);
		solved = Puzzles.nQueeens(board);
		assertEquals(true, solved);
		Utilities.printArray(board);

		System.out.println();
		board = new int[8][8];
		Utilities.fillArray(board, 0);
		solved = Puzzles.nQueeens(board);
		assertEquals(true, solved);
		Utilities.printArray(board);
	}

	@Test
	void nQueeensWithBranchAndBound() {
		int[][] board = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
		int[][] expectedBoard = {{0, 0, 1, 0}, {1, 0, 0, 0}, {0, 0, 0, 1}, {0, 1, 0, 0}};

		boolean solved = Puzzles.nQueeensWithBranchAndBound(board);
		assertEquals(true, solved);
		assertTwoDimensionalArrayEquals(expectedBoard, board);
		Utilities.printArray(board);

		System.out.println();
		board = new int[5][5];
		Utilities.fillArray(board, 0);
		solved = Puzzles.nQueeensWithBranchAndBound(board);
		assertEquals(true, solved);
		Utilities.printArray(board);

		System.out.println();
		board = new int[6][6];
		Utilities.fillArray(board, 0);
		solved = Puzzles.nQueeensWithBranchAndBound(board);
		assertEquals(true, solved);
		Utilities.printArray(board);

		System.out.println();
		board = new int[7][7];
		Utilities.fillArray(board, 0);
		solved = Puzzles.nQueeensWithBranchAndBound(board);
		assertEquals(true, solved);
		Utilities.printArray(board);

		System.out.println();
		board = new int[8][8];
		Utilities.fillArray(board, 0);
		solved = Puzzles.nQueeensWithBranchAndBound(board);
		assertEquals(true, solved);
		Utilities.printArray(board);
	}

	@Test
	void nQueeensWithBacktracking() {
		int[][] board = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
		int[][] expectedBoard = {{0, 0, 1, 0}, {1, 0, 0, 0}, {0, 0, 0, 1}, {0, 1, 0, 0}};

		boolean solved = Puzzles.nQueeensWithBacktracking(board);
		assertEquals(true, solved);
		assertTwoDimensionalArrayEquals(expectedBoard, board);
		Utilities.printArray(board);

		System.out.println();
		board = new int[5][5];
		Utilities.fillArray(board, 0);
		solved = Puzzles.nQueeensWithBacktracking(board);
		assertEquals(true, solved);
		Utilities.printArray(board);

		System.out.println();
		board = new int[6][6];
		Utilities.fillArray(board, 0);
		solved = Puzzles.nQueeensWithBacktracking(board);
		assertEquals(true, solved);
		Utilities.printArray(board);

		System.out.println();
		board = new int[7][7];
		Utilities.fillArray(board, 0);
		solved = Puzzles.nQueeensWithBacktracking(board);
		assertEquals(true, solved);
		Utilities.printArray(board);

		System.out.println();
		board = new int[8][8];
		Utilities.fillArray(board, 0);
		solved = Puzzles.nQueeensWithBacktracking(board);
		assertEquals(true, solved);
		Utilities.printArray(board);
	}

	@Test
	void getAllSolutionsForNQueens() {
		int[][] board;
		List<Board> solutions;
		List<Board> expectedSolutions;
		int[][] tempBoard;

		board = new int[4][4];
		Utilities.fillArray(board, 0);
		solutions = Puzzles.getAllSolutionsForNQueens(board);

		expectedSolutions = new ArrayList<>();

		Board b1 = new Board(4);
		tempBoard = b1.getBoard();
		tempBoard[0][2] = 1;
		tempBoard[1][0] = 1;
		tempBoard[2][3] = 1;
		tempBoard[3][1] = 1;
		expectedSolutions.add(b1);

		Board b2 = new Board(4);
		tempBoard = b2.getBoard();
		tempBoard[0][1] = 1;
		tempBoard[1][3] = 1;
		tempBoard[2][0] = 1;
		tempBoard[3][2] = 1;
		expectedSolutions.add(b2);

		assertListEquals(expectedSolutions, solutions);
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
		int[][] citiesAndDistances = new int[][]{{0, 10, 15, 20},
				{10, 0, 35, 25},
				{15, 35, 0, 30},
				{20, 25, 30, 0}};
		assertEquals(80, Puzzles.travellingSalesman(citiesAndDistances, 0));
	}

	@Test
	void travellingSalesmanRecursive() {
		int[][] citiesAndDistances = new int[][]{{0, 10, 15, 20},
				{10, 0, 35, 25},
				{15, 35, 0, 30},
				{20, 25, 30, 0}};
		assertEquals(80, Puzzles.travellingSalesmanRecursive(citiesAndDistances, 0));
	}

	@Test
	void travellingSalesmanNaive() {
		int[][] citiesAndDistances = new int[][]{{0, 10, 15, 20},
				{10, 0, 35, 25},
				{15, 35, 0, 30},
				{20, 25, 30, 0}};
		assertEquals(80, Puzzles.travellingSalesmanNaive(citiesAndDistances, 0));
	}

	@Test
	void travellingSalesmanApproximationUsingMST() {
		int[][] citiesAndDistances;
		int startCity;
		List<Integer> route;
		List<Integer> expectedRoute;


		citiesAndDistances = new int[][]{
				{0, 10, 15, 20},
				{10, 0, 35, 25},
				{15, 0, 35, 30},
				{20, 25, 30, 0}
		};
		startCity = 0;
		expectedRoute = Utilities.constructList(0, 1, 2, 3, 0);
		route = Puzzles.travellingSalesmanApproximationUsingMST(citiesAndDistances, startCity);
		assertNotNull(route);

		int cost = 0;
		for (int i = 0; i < route.size() - 1; i++) {
			cost += citiesAndDistances[route.get(i)][route.get(i + 1)];
		}
		assertEquals(95, cost);

		assertListStrictEquals(expectedRoute, route);
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

	@Test
	void getMaxSurvivalTimeWithAreas() {
		IntPair startingPowers;
		IntPair areaX;
		IntPair areaY;
		IntPair areaZ;
		int maxSurvivalTime;

		startingPowers = new IntPair(20, 8);
		areaX = new IntPair(3, 2);
		areaY = new IntPair(-5, -10);
		areaZ = new IntPair(-20, 5);
		maxSurvivalTime = Puzzles.getMaxSurvivalTimeWithAreas(startingPowers, areaX, areaY, areaZ);
		assertEquals(5, maxSurvivalTime);
	}

	@Test
	void tugOfWar() {
		int[] participantStrengths;
		Pair<List<Integer>, List<Integer>> teams;
		List<Integer> team1;
		List<Integer> team2;

		participantStrengths = Utilities.constructArray(3, 4, 5, -3, 100, 1, 89, 54, 23, 20);
		team1 = Utilities.constructList(3, 5, -3, 89, 54);
		team2 = Utilities.constructList(4, 100, 1, 23, 20);
		teams = Puzzles.tugOfWar(participantStrengths);
		assertNotNull(teams);
		assertListEquals(team1, teams.getFirst());
		assertListEquals(team2, teams.getSecond());

		participantStrengths = Utilities.constructArray(23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4);
		team1 = Utilities.constructList(45, -34, 12, 98, 0);
		team2 = Utilities.constructList(23, -1, -99, 4, 189, 4);
		teams = Puzzles.tugOfWar(participantStrengths);
		assertNotNull(teams);
		assertListEquals(team1, teams.getFirst());
		assertListEquals(team2, teams.getSecond());
	}

	@Test
	void solveCryptarithmeticSumPuzzle() {
		String first;
		String second;
		String sum;
		Map<Character, Integer> solution;
		Map<Character, Integer> expectedSolution;

		first = "SEND";
		second = "MORE";
		sum = "MONEY";
		expectedSolution = new HashMap<>();
		expectedSolution.put('D', 1);
		expectedSolution.put('E', 5);
		expectedSolution.put('M', 0);
		expectedSolution.put('N', 3);
		expectedSolution.put('O', 8);
		expectedSolution.put('R', 2);
		expectedSolution.put('S', 7);
		expectedSolution.put('Y', 6);
		solution = Puzzles.solveCryptarithmeticSumPuzzle(first, second, sum);
		assertNotNull(solution);
		QuetzalAssertions.assertMapEquals(expectedSolution, solution);

		first = "SENDWAY";
		second = "MOREANDMORE";
		sum = "MONEYANDCASH";
		solution = Puzzles.solveCryptarithmeticSumPuzzle(first, second, sum);
		assertNull(solution);
	}

	@Test
	void boggle() {
		char[][] board;
		Set<String> dictionary;
		Set<String> solution;
		Set<String> expectedSolution;

		board = new char[][]{{'I', 'S', 'T'}};
		dictionary = Utilities.constructSet("BLOGS", "TIP", "DEAL", "IS");
		expectedSolution = Utilities.constructSet("IS");
		solution = Puzzles.boggle(board, dictionary);
		assertSetEquals(expectedSolution, solution);

		board = new char[][]{{'I'},
				{'S'}};
		dictionary = Utilities.constructSet("BLOGS", "TIP", "DEAL", "IS");
		expectedSolution = Utilities.constructSet("IS");
		solution = Puzzles.boggle(board, dictionary);
		assertSetEquals(expectedSolution, solution);

		board = new char[][]{{'I', 'G'},
				{'S', 'K'}};
		dictionary = Utilities.constructSet("BLOGS", "TIP", "DEAL", "IS");
		expectedSolution = Utilities.constructSet("IS");
		solution = Puzzles.boggle(board, dictionary);
		assertSetEquals(expectedSolution, solution);

		board = new char[][]{{'I', 'G'},
				{'K', 'S'}};
		dictionary = Utilities.constructSet("BLOGS", "TIP", "DEAL", "IS");
		expectedSolution = Utilities.constructSet("IS");
		solution = Puzzles.boggle(board, dictionary);
		assertSetEquals(expectedSolution, solution);

		board = new char[][]{{'B', 'A', 'L'},
				{'E', 'L', 'G'},
				{'D', 'S', 'O'}};
		dictionary = Utilities.constructSet("BLOGS", "TIP", "DEAL", "AS");
		expectedSolution = Utilities.constructSet("BLOGS", "DEAL");
		solution = Puzzles.boggle(board, dictionary);
		assertSetEquals(expectedSolution, solution);
	}

	@Test
	void boggleNaive() {
		char[][] board;
		Set<String> dictionary;
		Set<String> solution;
		Set<String> expectedSolution;

		board = new char[][]{{'I', 'S', 'T'}};
		dictionary = Utilities.constructSet("BLOGS", "TIP", "DEAL", "IS");
		expectedSolution = Utilities.constructSet("IS");
		solution = Puzzles.boggleNaive(board, dictionary);
		assertSetEquals(expectedSolution, solution);

		board = new char[][]{{'I'},
				{'S'}};
		dictionary = Utilities.constructSet("BLOGS", "TIP", "DEAL", "IS");
		expectedSolution = Utilities.constructSet("IS");
		solution = Puzzles.boggleNaive(board, dictionary);
		assertSetEquals(expectedSolution, solution);

		board = new char[][]{{'I', 'G'},
				{'S', 'K'}};
		dictionary = Utilities.constructSet("BLOGS", "TIP", "DEAL", "IS");
		expectedSolution = Utilities.constructSet("IS");
		solution = Puzzles.boggleNaive(board, dictionary);
		assertSetEquals(expectedSolution, solution);

		board = new char[][]{{'I', 'G'},
				{'K', 'S'}};
		dictionary = Utilities.constructSet("BLOGS", "TIP", "DEAL", "IS");
		expectedSolution = Utilities.constructSet("IS");
		solution = Puzzles.boggleNaive(board, dictionary);
		assertSetEquals(expectedSolution, solution);

		board = new char[][]{{'B', 'A', 'L'},
				{'E', 'L', 'G'},
				{'D', 'S', 'O'}};
		dictionary = Utilities.constructSet("BLOGS", "TIP", "DEAL", "AS");
		expectedSolution = Utilities.constructSet("BLOGS", "DEAL");
		solution = Puzzles.boggleNaive(board, dictionary);
		assertSetEquals(expectedSolution, solution);
	}

	@Test
	void ratInAMaze() {
		int[][] maze;
		int[][] solution;
		int[][] expectedSolution;

		maze = new int[][]{{1, 0, 0, 0},
				{1, 1, 0, 1},
				{0, 1, 0, 0},
				{1, 1, 1, 1}};
		expectedSolution = new int[][]{
				{1, 0, 0, 0},
				{1, 1, 0, 0},
				{0, 1, 0, 0},
				{0, 1, 1, 1}};
		solution = Puzzles.ratInAMaze(maze);
		assertArrayEquals(expectedSolution, solution);

		maze = new int[][]{{1, 0, 0, 0},
				{1, 1, 0, 1},
				{0, 0, 0, 0},
				{1, 1, 1, 1}};
		solution = Puzzles.ratInAMaze(maze);
		assertNull(solution);
	}

	@Test
	void ratInAMazeWithMultipleJumpsAllowed() {
		int[][] maze;
		int[][] solution;
		int[][] expectedSolution;

		maze = new int[][]{{1, 0, 0, 0},
				{1, 1, 0, 1},
				{0, 1, 0, 0},
				{1, 1, 1, 1}};
		expectedSolution = new int[][]{
				{1, 0, 0, 0},
				{1, 1, 0, 0},
				{0, 1, 0, 0},
				{0, 1, 1, 1}};
		solution = Puzzles.ratInAMazeWithMultipleJumpsAllowed(maze);
		assertArrayEquals(expectedSolution, solution);

		maze = new int[][]{{1, 0, 0, 0},
				{1, 1, 0, 1},
				{0, 0, 0, 0},
				{1, 1, 1, 1}};
		solution = Puzzles.ratInAMazeWithMultipleJumpsAllowed(maze);
		assertNull(solution);

		maze = new int[][]{{2, 1, 0, 0},
				{3, 0, 0, 1},
				{0, 1, 0, 1},
				{0, 0, 0, 1}};
		expectedSolution = new int[][]{{1, 0, 0, 0},
				{1, 0, 0, 1},
				{0, 0, 0, 1},
				{0, 0, 0, 1}};
		solution = Puzzles.ratInAMazeWithMultipleJumpsAllowed(maze);
		assertArrayEquals(expectedSolution, solution);

		maze = new int[][]{{2, 1, 0, 0},
				{2, 0, 0, 1},
				{0, 1, 0, 1},
				{0, 0, 0, 1}};
		solution = Puzzles.ratInAMazeWithMultipleJumpsAllowed(maze);
		assertNull(solution);

		maze = new int[][]{
				{3, 0, 1, 0},
				{0, 0, 1, 0},
				{0, 0, 1, 1},
				{0, 0, 0, 1}};
		expectedSolution = new int[][]{
				{1, 0, 1, 0},
				{0, 0, 1, 0},
				{0, 0, 1, 1},
				{0, 0, 0, 1}};
		solution = Puzzles.ratInAMazeWithMultipleJumpsAllowed(maze);
		assertArrayEquals(expectedSolution, solution);
	}

	@Test
	void getPathsFromAnyCornerToMiddleCellInMaze() {
		int[][] maze;
		List<List<IntPair>> paths;
		List<List<IntPair>> expectedPaths;

		maze = new int[][]{
				{3, 5, 4, 4, 7, 3, 4, 6, 3},
				{6, 7, 5, 6, 6, 2, 6, 6, 2},
				{3, 3, 4, 3, 2, 5, 4, 7, 2},
				{6, 5, 5, 1, 2, 3, 6, 5, 6},
				{3, 3, 4, 3, 0, 1, 4, 3, 4},
				{3, 5, 4, 3, 2, 2, 3, 3, 5},
				{3, 5, 4, 3, 2, 6, 4, 4, 3},
				{3, 5, 1, 3, 7, 5, 3, 6, 4},
				{6, 2, 4, 3, 4, 5, 4, 5, 1}
		};
		var solutionPath = Utilities.constructList(new IntPair(0, 0), new IntPair(0, 3), new IntPair(0, 7), new IntPair(6, 7), new IntPair(6, 3), new IntPair(3, 3),
				new IntPair(3, 4), new IntPair(5, 4), new IntPair(5, 2), new IntPair(1, 2), new IntPair(1, 7), new IntPair(7, 7),
				new IntPair(7, 1), new IntPair(2, 1), new IntPair(2, 4), new IntPair(4, 4));
		expectedPaths = new ArrayList<>();
		expectedPaths.add(solutionPath);
		paths = Puzzles.getPathsFromAnyCornerToMiddleCellInMaze(maze);
		QuetzalAssertions.assertListOfListEquals(expectedPaths, paths);
	}

	@Test
	void getShortestSafeRouteFromFirstToLastColumnInAMazeWithLandmines() {
		int[][] maze;
		List<Coordinate> shortestPath;
		List<Coordinate> expectedShortestPath;

		maze = new int[][]{
				{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
				{1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
				{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 0, 1, 1, 1, 1, 1, 1}};
		expectedShortestPath = Utilities.constructList(new Coordinate(4, 0), new Coordinate(4, 1), new Coordinate(4, 2), new Coordinate(4, 3),
				new Coordinate(5, 3), new Coordinate(6, 3), new Coordinate(6, 4),
				new Coordinate(7, 4), new Coordinate(7, 5), new Coordinate(7, 6), new Coordinate(7, 7),
				new Coordinate(8, 7), new Coordinate(8, 8), new Coordinate(8, 9));

		shortestPath = Puzzles.getShortestSafeRouteFromFirstToLastColumnInAMazeWithLandmines(maze);
		assertListEquals(expectedShortestPath, shortestPath);
	}
}