package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.algorithms.ListAlgorithms;
import org.jwolfe.quetzal.library.general.Coordinate;
import org.jwolfe.quetzal.library.general.IntPair;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.general.Rod;
import org.jwolfe.quetzal.library.trie.Trie;
import org.jwolfe.quetzal.library.trie.TrieNode;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Puzzles {

	public static void towersOfHanoi(Stack<Integer> from, Stack<Integer> to, Stack<Integer> aux) {
		if (from == null || to == null || aux == null) {
			return;
		}

		int numDisks = from.size();
		if (numDisks == 0) {
			return;
		}

		Rod fromRod = new Rod("A", from);
		Rod toRod = new Rod("B", to);
		Rod auxRod = new Rod("C", aux);

		towersOfHanoi(fromRod, toRod, auxRod, numDisks);
	}

	private static void towersOfHanoi(Rod fromRod, Rod toRod, Rod auxRod, int numDisks) {
		if (numDisks < 1) {
			return;
		}

		towersOfHanoi(fromRod, auxRod, toRod, numDisks - 1);
		System.out.println(
				"Move disk " + fromRod.getDisks().peek() + " from " + fromRod.getName() + " to " + toRod.getName());
		toRod.getDisks().push(fromRod.getDisks().pop());
		towersOfHanoi(auxRod, toRod, fromRod, numDisks - 1);
	}

	public static boolean nQueeens(int[][] board) {
		if (board == null) {
			return false;
		}

		int n = board.length;
		for (int i = 0; i < n; i++) {
			if (board[i].length != n) {
				return false;
			}
		}

		return nQueeens(board, 0);
	}

	private static boolean nQueeens(int[][] board, int column) {
		int n = board.length;
		if (column >= board.length) {
			return true;
		}

		for (int i = 0; i < n; i++) {
			if (isSafeForQueen(board, i, column)) {
				board[i][column] = 1;
				if (nQueeens(board, column + 1)) {
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
			if (board[i][column] != 0 || board[row][i] != 0) {
				return false;
			}
		}

		// Diagonal - 1 & 2
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (((i - j) == (row - column)) || (i + j) == (row + column)) {
					if (board[i][j] != 0) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public static boolean soduku(int[][] board) {
		if (board == null || board.length != 9) {
			return false;
		}

		for (int i = 0; i < 9; i++) {
			if (board[i].length != 9) {
				return false;
			}
		}

		return solveSoduku(board);
	}

	private static boolean solveSoduku(int[][] board) {
		boolean isSolved = true;
		int row = 0;
		int column = 0;
		for (row = 0; row < 9; row++) {
			for (column = 0; column < 9; column++) {
				if (board[row][column] == 0) {
					isSolved = false;
					break;
				}
			}

			if (!isSolved) {
				break;
			}
		}

		if (isSolved) {
			return true;
		}

		// Not complete. Solve for cell (row, col)
		for (int num = 1; num <= 9; num++) {
			if (isAllowedInSoduku(board, row, column, num)) {
				board[row][column] = num;
				isSolved = solveSoduku(board);
				if (isSolved) {
					return true;
				}

				// Backtrack
				board[row][column] = 0;
			}
		}

		return false;
	}

	private static boolean isAllowedInSoduku(int[][] board, int row, int column, int number) {
		// Check row & column for number
		for (int i = 0; i < 9; i++) {
			if ((i != column && board[row][i] == number) || (i != row) && board[i][column] == number) {
				return false;
			}
		}

		// Check 3x3 grid for number
		int startRow = row - (row % 3);
		int startColumn = column - (column % 3);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int gridRow = startRow + i;
				int gridColumn = startColumn + j;
				if (gridRow != row && gridColumn != column && board[gridRow][gridColumn] == number) {
					return false;
				}

			}
		}

		return true;
	}

	public static int celebrityProblem(int n, int[][] acquaintanceMatrix) {
		// Alternate ways of solving the celebrity problem
		// 1) Create a graph & search for the vertex that has outdegree of 0.
		// 2) Push all elements into a stack & pop top 2. push the possible celebrity
		// alone back to the stack & repeat till only one is left.

		int i = 0;
		int j = n - 1;

		while (i < j) {
			boolean knows = hasAcquaintance(i, j, acquaintanceMatrix);
			if (knows) {
				// i knows j. i is is not a celebrity
				i++;
			} else {
				// i does not know j. i could be a celebrity
				j--;
			}
		}

		// Confirm that i is the celebrity
		for (int k = 0; k < n; k++) {
			if (i != k) {
				if (!hasAcquaintance(k, i, acquaintanceMatrix)) {
					return -1;
				}
			}
		}

		return i;
	}

	private static boolean hasAcquaintance(int a, int b, int[][] acquaintanceMatrix) {
		if (acquaintanceMatrix == null) {
			return false;
		}

		if (acquaintanceMatrix[a][b] != 0) {
			return true;
		}

		return false;
	}

	public static int getWaysToTile2xNBoardWith2x1Tiles(int n) {
		if (n <= 2) {
			return n;
		}

		int[] dp = new int[n];
		dp[0] = 1;
		dp[1] = 2;

		for (int i = 2; i < n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		return dp[n - 1];
	}

	public static int getWaysToTile2xNBoardWith2x1TilesRecursive(int n) {
		// Recursion:
		// Count = n, for n = 1 or 2
		// Count = (n+1) + (n+2) otherwise
		//
		// Note: Dynamic programming will be better than recursion here.

		if (n <= 2) {
			return n;
		}

		return getWaysToTile2xNBoardWith2x1TilesRecursive(n - 1) + getWaysToTile2xNBoardWith2x1TilesRecursive(n - 2);
	}

	public static List<Pair<Integer, Integer>> buySellStocksForMaximumProfit(int[] pricesByDay) {
		if (pricesByDay == null) {
			return null;
		}

		List<Pair<Integer, Integer>> buySellPairs = new ArrayList<>();
		int n = pricesByDay.length;
		int index = 0;
		while (index < n) {
			// Find local minima
			while (index < n - 1 && pricesByDay[index] > pricesByDay[index + 1]) {
				index++;
			}

			if (index == n - 1) {
				break;
			}

			int buyOn = index;

			// Find local maxima
			while (index < n - 1 && pricesByDay[index] < pricesByDay[index + 1]) {
				index++;
			}

			int sellOn = index;
			buySellPairs.add(new Pair<>(buyOn, sellOn));
		}

		return buySellPairs;
	}

	public static int minCostToConvert3x3MatrixToMagicSquare(int[][] matrix) {
		if (matrix == null) {
			return Integer.MAX_VALUE;
		}

		if (matrix.length != 3) {
			return Integer.MAX_VALUE;
		}

		for (int i = 0; i < 3; i++) {
			if (matrix[i].length != 3) {
				return Integer.MAX_VALUE;
			}
		}

		var allMagicSquares = getAllMagicSquares();

		int cost = Integer.MAX_VALUE;
		for (var square : allMagicSquares) {
			cost = Math.min(cost, getCostToConvertToSquare(matrix, square));
		}

		return cost;
	}

	private static List<List<List<Integer>>> getAllMagicSquares() {
		List<List<List<Integer>>> allMagicSquares = new ArrayList<>();

		var numbers = Utilities.createArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		var allPermutations = ListAlgorithms.getAllPermutations(numbers);
		for (var permutation : allPermutations) {
			var square = get3x3SquareFromList(permutation);
			if (isMagicSquare(square)) {
				allMagicSquares.add(square);
			}
		}

		return allMagicSquares;
	}

	private static List<List<Integer>> get3x3SquareFromList(List<Integer> list) {
		List<List<Integer>> square = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			square.add(new ArrayList<>());
			for (int j = 0; j < 3; j++) {
				square.get(i).add(list.get(3 * i + j));
			}
		}

		return square;
	}

	private static boolean isMagicSquare(List<List<Integer>> square) {
		int length = square.size();

		int magicSum = 0;
		for (int j = 0; j < length; j++) {
			magicSum += square.get(0).get(j);
		}

		// All rows should add up to sum.
		for (int i = 0; i < length; i++) {
			int sum = 0;
			for (int j = 0; j < length; j++) {
				sum += square.get(i).get(j);
			}

			if (sum != magicSum) {
				return false;
			}
		}

		// All columns should add up to sum.
		for (int j = 0; j < length; j++) {
			int sum = 0;
			for (int i = 0; i < length; i++) {
				sum += square.get(i).get(j);
			}

			if (sum != magicSum) {
				return false;
			}
		}

		// Left diagonal should add up to sum.
		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum += square.get(i).get(i);
		}

		if (sum != magicSum) {
			return false;
		}

		// Right diagonal should add up to sum.
		sum = 0;
		for (int i = 0; i < length; i++) {
			sum += square.get(i).get(length - i - 1);
		}

		if (sum != magicSum) {
			return false;
		}

		return true;
	}

	private static int getCostToConvertToSquare(int[][] matrix, List<List<Integer>> square) {
		int cost = 0;
		int length = matrix.length;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				cost += Math.abs(square.get(i).get(j) - matrix[i][j]);
			}
		}

		return cost;
	}

	public static int travellingSalesman(int[][] citiesAndDistancesesAndDistances, int startCity) {
		// Dynamic Programming solution
		// Held Karp Algorithm

		if (citiesAndDistancesesAndDistances == null || citiesAndDistancesesAndDistances.length < 2) {
			return 0;
		}

		// Get list of all cities to visit
		int numCities = citiesAndDistancesesAndDistances.length;
		if (startCity >= numCities) {
			return 0;
		}

		List<Integer> citiesToVisit = new ArrayList<>();
		for (int i = 0; i < numCities; i++) {
			if (i != startCity) {
				citiesToVisit.add(i);
			}
		}

		var citySets = ListAlgorithms.getAllSubsetsSorted(citiesToVisit);
		Map<CityTour, Integer> tourCosts = new HashMap<>();
		for (var intermediateCities : citySets) {
			for (var endCity : citiesToVisit) {
				if (intermediateCities.contains(endCity)) {
					continue;
				}

				var tour = new Puzzles.CityTour(startCity, intermediateCities, endCity);
				int minTourCost = Integer.MAX_VALUE;
				if (intermediateCities.size() == 0) {
					minTourCost = citiesAndDistancesesAndDistances[startCity][endCity];
				} else {
					for (var city : intermediateCities) {
						ArrayList<Integer> subIntermediateCities = new ArrayList<>(intermediateCities);
						subIntermediateCities.remove(city);

						var subTour = new Puzzles.CityTour(startCity, subIntermediateCities, city);
						int subTourCost = tourCosts.get(subTour).intValue()
								+ citiesAndDistancesesAndDistances[city][endCity];
						minTourCost = Math.min(minTourCost, subTourCost);
					}
				}

				tourCosts.put(tour, minTourCost);
			}
		}

		// Find cost for source -> {all cities to visit} -> source
		int minTourCost = Integer.MAX_VALUE;
		for (var city : citiesToVisit) {
			ArrayList<Integer> subIntermediateCities = new ArrayList<>(citiesToVisit);
			subIntermediateCities.remove(city);

			var subTour = new Puzzles.CityTour(startCity, subIntermediateCities, city);
			int subTourCost = tourCosts.get(subTour).intValue() + citiesAndDistancesesAndDistances[city][startCity];
			minTourCost = Math.min(minTourCost, subTourCost);
		}

		return minTourCost;
	}

	private static class CityTour {
		int startCity;
		List<Integer> intermediateCities;
		int endCity;

		public CityTour(int startCity, List<Integer> intermediateCities, int endCity) {
			super();
			this.startCity = startCity;
			this.intermediateCities = intermediateCities;
			this.endCity = endCity;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;

			if (obj == null)
				return false;

			if (getClass() != obj.getClass())
				return false;

			CityTour other = (CityTour) obj;
			if (endCity != other.endCity)
				return false;

			if (intermediateCities == null) {
				if (other.intermediateCities != null)
					return false;
			} else if (!intermediateCities.equals(other.intermediateCities))
				return false;

			if (startCity != other.startCity)
				return false;

			return true;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + endCity;
			result = prime * result + ((intermediateCities == null) ? 0 : intermediateCities.hashCode());
			result = prime * result + startCity;
			return result;
		}
	}

	public static int travellingSalesmanRecursive(int[][] citiesAndDistancesesAndDistances, int startCity) {
		// Recursive solution

		if (citiesAndDistancesesAndDistances == null || citiesAndDistancesesAndDistances.length < 2) {
			return 0;
		}

		// Get list of all cities to visit
		int numCities = citiesAndDistancesesAndDistances.length;
		if (startCity >= numCities) {
			return 0;
		}

		List<Integer> citiesToVisit = new ArrayList<>();
		for (int i = 0; i < numCities; i++) {
			if (i != startCity) {
				citiesToVisit.add(i);
			}
		}

		return travellingSalesmanRecursive(citiesAndDistancesesAndDistances, citiesToVisit, startCity);
	}

	private static int travellingSalesmanRecursive(int[][] citiesAndDistancesesAndDistances,
												   List<Integer> citiesToVisit, int startCity) {
		int minDistance = Integer.MAX_VALUE;
		for (int i = 0; i < citiesToVisit.size(); i++) {
			Integer endCity = citiesToVisit.get(i);
			citiesToVisit.remove(i);

			int tourDistance = getTravelDistanceToCity(citiesAndDistancesesAndDistances, startCity, citiesToVisit,
					endCity) + citiesAndDistancesesAndDistances[endCity][startCity];

			citiesToVisit.add(i, endCity);
			minDistance = Math.min(minDistance, tourDistance);
		}

		return minDistance;
	}

	private static int getTravelDistanceToCity(int[][] citiesAndDistancesesAndDistances, int startCity,
											   List<Integer> intermediateCities, int endCity) {
		if (intermediateCities.size() == 0) {
			return citiesAndDistancesesAndDistances[startCity][endCity];
		}

		int minDistance = Integer.MAX_VALUE;
		for (int i = 0; i < intermediateCities.size(); i++) {
			Integer city = intermediateCities.get(i);
			intermediateCities.remove(i);

			int tourDistance = getTravelDistanceToCity(citiesAndDistancesesAndDistances, startCity, intermediateCities,
					city) + citiesAndDistancesesAndDistances[city][endCity];

			intermediateCities.add(i, city);
			minDistance = Math.min(minDistance, tourDistance);
		}

		return minDistance;
	}

	public static int travellingSalesmanNaive(int[][] citiesAndDistancesesAndDistances, int startCity) {
		// Naive (Brute) solution
		// Time complexity: O(n!)

		if (citiesAndDistancesesAndDistances == null || citiesAndDistancesesAndDistances.length < 2) {
			return 0;
		}

		// Get list of all cities to visit
		int numCities = citiesAndDistancesesAndDistances.length;
		if (startCity >= numCities) {
			return 0;
		}

		List<Integer> citiesToVisit = new ArrayList<>();
		for (int i = 0; i < numCities; i++) {
			if (i != startCity) {
				citiesToVisit.add(i);
			}
		}

		var allPermutationsOfCitiesToVisit = ListAlgorithms.getAllPermutations(citiesToVisit);
		int minDistance = Integer.MAX_VALUE;
		for (var permutation : allPermutationsOfCitiesToVisit) {
			int distance = 0;
			int fromCity = startCity;
			for (int city : permutation) {
				distance += citiesAndDistancesesAndDistances[fromCity][city];
				fromCity = city;
			}

			distance += citiesAndDistancesesAndDistances[fromCity][startCity];

			if (minDistance > distance) {
				minDistance = distance;
			}
		}

		return minDistance;
	}

	public static boolean knightsTour(int[][] board) {
		// Warndorf's algorithm

		if (board == null) {
			return false;
		}

		int boardSize = board.length;
		for (var row : board) {
			if (row == null || row.length != boardSize) {
				return false;
			}
		}

		// Reset the board
		for (var row : board) {
			Arrays.fill(row, -1);
		}

		// Moves that a knight can make relative to its current position
		List<Coordinate> validRelativeMoves = getValidRelativeMovesForKnightsTour();

		// Note: This algorithm will work with any starting co-ordinate, not necessarily
		// 0.
		int moveNumber = 0;
		board[0][0] = moveNumber;
		Coordinate currentCoordinate = new Coordinate(0, 0);

		int numCells = boardSize * boardSize;

		for (moveNumber = 1; moveNumber < numCells; moveNumber++) {
			if (!moveNextForKnight(board, boardSize, moveNumber, currentCoordinate, validRelativeMoves)) {
				return false;
			}
		}

		return true;
	}

	private static boolean moveNextForKnight(int[][] board, int boardSize, int moveNumber, Coordinate currentCoordinate,
											 List<Coordinate> validRelativeMoves) {
		if (moveNumber >= boardSize * boardSize) {
			return false;
		}

		int minDegreeOfAccessibility = Integer.MAX_VALUE;
		Coordinate minAccessibilityCoordinate = null;
		for (var relativeCoordinate : validRelativeMoves) {
			var newCoordinate = new Coordinate(currentCoordinate.getX() + relativeCoordinate.getX(),
					currentCoordinate.getY() + relativeCoordinate.getY());

			if (isValidKnightMoveForTour(board, boardSize, currentCoordinate, newCoordinate)) {
				int degreeOfAccessibility = getDegreeOfAccessibilityForKnightMove(board, boardSize, newCoordinate,
						validRelativeMoves);
				if (degreeOfAccessibility < minDegreeOfAccessibility) {
					minDegreeOfAccessibility = degreeOfAccessibility;
					minAccessibilityCoordinate = newCoordinate;
				}
			}
		}

		if (minAccessibilityCoordinate == null) {
			return false;
		}

		currentCoordinate.set(minAccessibilityCoordinate);
		board[currentCoordinate.getX()][currentCoordinate.getY()] = moveNumber;
		return true;
	}

	private static int getDegreeOfAccessibilityForKnightMove(int[][] board, int boardSize, Coordinate coordinate,
															 List<Coordinate> validRelativeMoves) {
		int degree = 0;
		for (var relativeCoordinate : validRelativeMoves) {
			var newCoordinate = new Coordinate(coordinate.getX() + relativeCoordinate.getX(),
					coordinate.getY() + relativeCoordinate.getY());
			if (newCoordinate.getX() >= 0 && newCoordinate.getX() < boardSize && newCoordinate.getY() >= 0
					&& newCoordinate.getY() < boardSize && board[newCoordinate.getX()][newCoordinate.getY()] == -1) {
				// new co-ordinate is accessible from co-ordinate
				degree++;
			}
		}

		return degree;
	}

	public static boolean knightsTourBacktracking(int[][] board) {
		if (board == null) {
			return false;
		}

		int boardSize = board.length;
		for (var row : board) {
			if (row == null || row.length != boardSize) {
				return false;
			}
		}

		// Reset the board
		for (var row : board) {
			Arrays.fill(row, -1);
		}

		// Moves that a knight can make relative to its current position
		List<Coordinate> validRelativeMoves = getValidRelativeMovesForKnightsTour();

		int moveNumber = 0;
		board[0][0] = moveNumber;
		Coordinate currentCoordinate = new Coordinate(0, 0);

		return knightsTourBacktracking(board, boardSize, moveNumber, currentCoordinate, validRelativeMoves);
	}

	// private static int counter = 0;

	private static boolean knightsTourBacktracking(int[][] board, int boardSize, int moveNumber,
												   Coordinate currentCoordinate, List<Coordinate> validRelativeMoves) {
		moveNumber++;

		if (moveNumber == boardSize * boardSize) {
			// Solved. All cells have been visited.
			return true;
		}

		for (var relativeCoordinate : validRelativeMoves) {
			var newCoordinate = new Coordinate(currentCoordinate.getX() + relativeCoordinate.getX(),
					currentCoordinate.getY() + relativeCoordinate.getY());

			if (isValidKnightMoveForTour(board, boardSize, currentCoordinate, newCoordinate)) {
				// System.out.println(counter++ + " Move #" + moveNumber + ": trying with
				// co-ordinates (" + newCoordinate.getX() + ", " + newCoordinate.getY() + ")");
				board[newCoordinate.getX()][newCoordinate.getY()] = moveNumber;
				if (knightsTourBacktracking(board, boardSize, moveNumber, newCoordinate, validRelativeMoves)) {
					return true;
				}

				// Didn't work out... Backtrack
				// System.out.println(counter + " Move # " + moveNumber + ": backtracking from
				// co-ordinates (" + newCoordinate.getX() + ", " + newCoordinate.getY() + ")");
				board[newCoordinate.getX()][newCoordinate.getY()] = -1;
			}
		}

		return false;
	}

	private static boolean isValidKnightMoveForTour(int[][] board, int boardSize, Coordinate currentCoordinate,
													Coordinate newCoordinate) {
		if (newCoordinate.getX() >= 0 && newCoordinate.getX() < boardSize && newCoordinate.getY() >= 0
				&& newCoordinate.getY() < boardSize && board[newCoordinate.getX()][newCoordinate.getY()] == -1) {
			return true;
		}

		return false;
	}

	private static List<Coordinate> getValidRelativeMovesForKnightsTour() {
		// Moves that a knight can make relative to its current position
		List<Coordinate> validRelativeMoves = new ArrayList<>();
		validRelativeMoves.add(new Coordinate(2, 1));
		validRelativeMoves.add(new Coordinate(2, -1));
		validRelativeMoves.add(new Coordinate(1, 2));
		validRelativeMoves.add(new Coordinate(1, -2));
		validRelativeMoves.add(new Coordinate(-1, 2));
		validRelativeMoves.add(new Coordinate(-1, -2));
		validRelativeMoves.add(new Coordinate(-2, 1));
		validRelativeMoves.add(new Coordinate(-2, -1));

		return validRelativeMoves;
	}

	public static Map<Integer, Integer> colorGraph(int[][] graph, int numColors) {
		if (graph == null || graph.length == 0) {
			return null;
		}

		int numVertices = graph.length;
		for (var list : graph) {
			if (list == null || list.length != numVertices) {
				return null;
			}
		}

		Map<Integer, Integer> colorMappings = new TreeMap<>();
		for (int i = 0; i < numVertices; i++) {
			colorMappings.put(i, -1);
		}

//		// Set the first color.
//		colorMappings.put(0, 1);
		boolean colored = colorGraph(graph, numColors, numVertices, 0, colorMappings);
		if (!colored) {
			// Not possible to color the graph with m colors.
			return null;
		}

		return colorMappings;
	}

	private static boolean colorGraph(int[][] graph, int numColors, int numVertices, int currentVertex,
									  Map<Integer, Integer> colorMappings) {
		if (currentVertex == numVertices) {
			// All vertices colored
			return true;
		}

		for (int color = 1; color <= numColors; color++) {
			if (isAllowedForGraphColoring(graph, numVertices, currentVertex, color, colorMappings)) {
				colorMappings.put(currentVertex, color);
				if (colorGraph(graph, numColors, numVertices, currentVertex + 1, colorMappings)) {
					return true;
				}

				// Current color did not work. Backtrack.
				colorMappings.put(currentVertex, -1);
			}
		}

		return false;
	}

	private static boolean isAllowedForGraphColoring(int[][] graph, int numVertices, int vertex, int color,
													 Map<Integer, Integer> colorMappings) {
		if (vertex < 0 || vertex >= numVertices) {
			return false;
		}

		for (int i = 0; i < numVertices; i++) {
			if (graph[vertex][i] != 0 || graph[i][vertex] != 0) {
				if (colorMappings.get(i) == color) {
					return false;
				}
			}
		}

		return true;
	}

	public static int getMaxGoldFromMine(int[][] mine) {
		// The miner starts from any row in the first column
		// The miner can go right, right-up or right-down
		// Assumption: All cells have zero or positive values.
		if (mine == null || mine.length == 0 || mine[0] == null || mine[0].length == 0) {
			return -1;
		}

		int rows = mine.length;
		int columns = mine[0].length;
		for (int i = 1; i < rows; i++) {
			if (mine[i].length != columns) {
				return -1;
			}
		}

		int[][] goldMined = new int[rows][columns];
		for (int j = columns - 1; j >= 0; j--) {
			for (int i = 0; i < rows; i++) {
				if (j == columns - 1) {
					goldMined[i][j] = mine[i][j];
				} else {
					int rightUpGold = (i != 0 ? goldMined[i - 1][j + 1] : 0);
					int rightGold = goldMined[i][j + 1];
					int rightDownGold = (i != rows - 1 ? goldMined[i + 1][j + 1] : 0);

					goldMined[i][j] = mine[i][j] + Utilities.max(rightUpGold, rightGold, rightDownGold);
				}
			}
		}

		int maxGold = goldMined[0][0];
		for (int i = 1; i < rows; i++) {
			maxGold = Math.max(maxGold, goldMined[i][0]);
		}

		return maxGold;
	}

	public static int getMaxGoldFromMineRecursive(int[][] mine) {
		// The miner starts from any row in the first column
		// The miner can go right, right-up or right-down
		// Assumption: All cells have zero or positive values.
		if (mine == null || mine.length == 0 || mine[0] == null || mine[0].length == 0) {
			return -1;
		}

		int rows = mine.length;
		int columns = mine[0].length;
		for (int i = 1; i < rows; i++) {
			if (mine[i].length != columns) {
				return -1;
			}
		}

		int maxGold = 0;
		for (int i = 0; i < rows; i++) {
			int goldAvailable = getMaxGoldFromMineRecursive(mine, rows, columns, i, 0);
			maxGold = Math.max(maxGold, goldAvailable);
		}

		return maxGold;
	}

	private static int getMaxGoldFromMineRecursive(int[][] mine, int rows, int columns, int currentRow,
												   int currentColumn) {
		if (currentRow == rows || currentColumn == columns) {
			return 0;
		}

		if (currentRow < 0) {
			return 0;
		}

		return mine[currentRow][currentColumn]
				+ Utilities.max(getMaxGoldFromMineRecursive(mine, rows, columns, currentRow - 1, currentColumn + 1),
				getMaxGoldFromMineRecursive(mine, rows, columns, currentRow, currentColumn + 1),
				getMaxGoldFromMineRecursive(mine, rows, columns, currentRow + 1, currentColumn + 1));
	}

	public static char[][] solveMagnetPuzzle(char[][] boardConfiguration, int[] topSpecifications,
											 int[] leftSpecifications, int[] bottomSpecifications, int[] rightSpecifications) {
		if (boardConfiguration == null || boardConfiguration.length == 0 || topSpecifications == null
				|| leftSpecifications == null || bottomSpecifications == null || rightSpecifications == null) {
			return null;
		}

		int rows = boardConfiguration.length;
		int columns = boardConfiguration[0].length;

		for (var record : boardConfiguration) {
			if (record == null || record.length != columns) {
				return null;
			}
		}

		if (topSpecifications.length != columns || leftSpecifications.length != rows
				|| bottomSpecifications.length != columns || rightSpecifications.length != rows) {
			return null;
		}

		// Validate Board Configuration
		boolean boardConfigurationValid = true;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				char cell = boardConfiguration[i][j];
				if (cell == 'L' && j != columns - 1 && boardConfiguration[i][j + 1] != 'R') {
					boardConfigurationValid = false;
					break;
				}

				if (cell == 'T' && i != rows - 1 && boardConfiguration[i + 1][j] != 'B') {
					boardConfigurationValid = false;
					break;
				}
			}
		}

		if (!boardConfigurationValid) {
			return null;
		}

		char[][] board = new char[rows][columns];
		Utilities.fillArray(board, 'X');

		boolean solved = solveMagnetPuzzle(board, boardConfiguration, rows, columns, 0, 0, topSpecifications,
				leftSpecifications, bottomSpecifications, rightSpecifications);

		if (solved) {
			return board;
		}

		return null;
	}

	private static boolean solveMagnetPuzzle(char[][] board, char[][] boardConfiguration, int rows, int columns,
											 int currentRow, int currentColumn, int[] topSpecifications, int[] leftSpecifications,
											 int[] bottomSpecifications, int[] rightSpecifications) {
		if (currentRow >= rows - 1 && currentColumn >= columns) {
			// Finished the board. Validate.
			if (isMagnetPuzzleCompleteForBoard(board, boardConfiguration, rows, columns, topSpecifications,
					leftSpecifications, bottomSpecifications, rightSpecifications)) {
				return true;
			}

			return false;
		}

		if (currentColumn >= columns) {
			// Proceed with the next row
			currentRow++;
			currentColumn = 0;
		}

		char cell = boardConfiguration[currentRow][currentColumn];
//		if(cell == 'R' || cell == 'B') {
//			// Right or Bottom of a previous cell with Left or Top. Continue with next.
//			return solveMagnetPuzzle(board,
//					boardConfiguration,
//					rows, columns,
//					currentRow, currentColumn + 1,
//					topSpecifications, leftSpecifications,
//					bottomSpecifications, rightSpecifications);
//		}

		if (cell == 'L') {
			// Try (+, -), Left to Right
			if (isValidPoleForMagnetPuzzleCell(board, rows, columns, currentRow, currentColumn, '+', topSpecifications,
					leftSpecifications, bottomSpecifications, rightSpecifications)
					&& isValidPoleForMagnetPuzzleCell(board, rows, columns, currentRow, currentColumn + 1, '-',
					topSpecifications, leftSpecifications, bottomSpecifications, rightSpecifications)) {
				board[currentRow][currentColumn] = '+';
				board[currentRow][currentColumn + 1] = '-';
				if (solveMagnetPuzzle(board, boardConfiguration, rows, columns, currentRow, currentColumn + 2,
						topSpecifications, leftSpecifications, bottomSpecifications, rightSpecifications)) {
					return true;
				}

				// Backtrack.
				board[currentRow][currentColumn] = 'X';
				board[currentRow][currentColumn + 1] = 'X';
			}

			// Try (-, +), Left to Right
			if (isValidPoleForMagnetPuzzleCell(board, rows, columns, currentRow, currentColumn, '-', topSpecifications,
					leftSpecifications, bottomSpecifications, rightSpecifications)
					&& isValidPoleForMagnetPuzzleCell(board, rows, columns, currentRow, currentColumn + 1, '+',
					topSpecifications, leftSpecifications, bottomSpecifications, rightSpecifications)) {
				board[currentRow][currentColumn] = '-';
				board[currentRow][currentColumn + 1] = '+';
				if (solveMagnetPuzzle(board, boardConfiguration, rows, columns, currentRow, currentColumn + 2,
						topSpecifications, leftSpecifications, bottomSpecifications, rightSpecifications)) {
					return true;
				}

				// Backtrack.
				board[currentRow][currentColumn] = 'X';
				board[currentRow][currentColumn + 1] = 'X';
			}
		}

		if (cell == 'T') {
			// Try (+, -), Top to Bottom
			if (isValidPoleForMagnetPuzzleCell(board, rows, columns, currentRow, currentColumn, '+', topSpecifications,
					leftSpecifications, bottomSpecifications, rightSpecifications)
					&& isValidPoleForMagnetPuzzleCell(board, rows, columns, currentRow + 1, currentColumn, '-',
					topSpecifications, leftSpecifications, bottomSpecifications, rightSpecifications)) {
				board[currentRow][currentColumn] = '+';
				board[currentRow + 1][currentColumn] = '-';
				if (solveMagnetPuzzle(board, boardConfiguration, rows, columns, currentRow, currentColumn + 1,
						topSpecifications, leftSpecifications, bottomSpecifications, rightSpecifications)) {
					return true;
				}

				// Backtrack.
				board[currentRow][currentColumn] = 'X';
				board[currentRow + 1][currentColumn] = 'X';
			}

			// Try (-, +), Top to Bottom
			if (isValidPoleForMagnetPuzzleCell(board, rows, columns, currentRow, currentColumn, '-', topSpecifications,
					leftSpecifications, bottomSpecifications, rightSpecifications)
					&& isValidPoleForMagnetPuzzleCell(board, rows, columns, currentRow + 1, currentColumn, '+',
					topSpecifications, leftSpecifications, bottomSpecifications, rightSpecifications)) {
				board[currentRow][currentColumn] = '-';
				board[currentRow + 1][currentColumn] = '+';
				if (solveMagnetPuzzle(board, boardConfiguration, rows, columns, currentRow, currentColumn + 1,
						topSpecifications, leftSpecifications, bottomSpecifications, rightSpecifications)) {
					return true;
				}

				// Backtrack.
				board[currentRow][currentColumn] = 'X';
				board[currentRow + 1][currentColumn] = 'X';
			}
		}

		// Ignore current cell & proceed
		if (solveMagnetPuzzle(board, boardConfiguration, rows, columns, currentRow, currentColumn + 1,
				topSpecifications, leftSpecifications, bottomSpecifications, rightSpecifications)) {
			return true;
		}

		return false;
	}

	private static boolean isValidPoleForMagnetPuzzleCell(char[][] board, int rows, int columns, int currentRow,
														  int currentColumn, char pole, int[] topSpecifications, int[] leftSpecifications, int[] bottomSpecifications,
														  int[] rightSpecifications) {

		// If any adjacent cells (Top, Left, Bottom or Right) contains the same pole,
		// placement is invalid
		if ((currentRow > 0 && board[currentRow - 1][currentColumn] == pole)
				|| (currentColumn > 0 && board[currentRow][currentColumn - 1] == pole)
				|| (currentRow < rows - 1 && board[currentRow + 1][currentColumn] == pole)
				|| (currentColumn < columns - 1 && board[currentRow][currentColumn + 1] == pole)) {
			return false;
		}

		int poleCountInRow = 0;
		int poleCountInColumn = 0;

		for (int j = 0; j < columns; j++) {
			if (board[currentRow][j] == pole) {
				poleCountInRow++;
			}
		}

		for (int i = 0; i < rows; i++) {
			if (board[i][currentColumn] == pole) {
				poleCountInColumn++;
			}
		}

		if (pole == '+') {
			if ((topSpecifications[currentColumn] >= 0 && poleCountInColumn >= topSpecifications[currentColumn])
					|| (leftSpecifications[currentRow] >= 0 && poleCountInRow >= leftSpecifications[currentRow])) {
				return false;
			}
		} else {
			if ((bottomSpecifications[currentColumn] >= 0 && poleCountInColumn >= bottomSpecifications[currentColumn])
					|| (rightSpecifications[currentRow] >= 0 && poleCountInRow >= rightSpecifications[currentRow])) {
				return false;
			}
		}

		return true;
	}

	private static boolean isMagnetPuzzleCompleteForBoard(char[][] board, char[][] boardConfiguration, int rows,
														  int columns, int[] topSpecifications, int[] leftSpecifications, int[] bottomSpecifications,
														  int[] rightSpecifications) {

		// Check left & right specifications
		for (int i = 0; i < rows; i++) {
			int numPositives = 0;
			int numNegatives = 0;

			for (int j = 0; j < columns; j++) {
				char cell = board[i][j];
				if (cell == '+') {
					numPositives++;
				} else if (cell == '-') {
					numNegatives++;
				}
			}

			if ((leftSpecifications[i] >= 0 && numPositives != leftSpecifications[i])
					|| (rightSpecifications[i] >= 0 && numNegatives != rightSpecifications[i])) {
				return false;
			}
		}

		// Check top & bottom specifications
		for (int j = 0; j < columns; j++) {
			int numPositives = 0;
			int numNegatives = 0;

			for (int i = 0; i < rows; i++) {
				char cell = board[i][j];
				if (cell == '+') {
					numPositives++;
				} else if (cell == '-') {
					numNegatives++;
				}
			}

			if ((topSpecifications[j] >= 0 && numPositives != topSpecifications[j])
					|| (bottomSpecifications[j] >= 0 && numNegatives != bottomSpecifications[j])) {
				return false;
			}
		}

		return true;
	}

	public static int getMaxSurvivalTimeWithAreas(IntPair startingPowers, IntPair areaX, IntPair areaY, IntPair areaZ) {
		// Starting Powers -> (A, B)
		// 3 areas to choose from at start (X, Y, Z). from then each time interval,
		// choose one of the 2 remaining areas
		// Upon visiting any area, the powers gets incremented / decremented as denoted
		// by that area's specifications
		// Game is over once either of the powers (a / b) becomes zero
		// Find max survival time with the given configuration
		if (startingPowers == null || areaX == null || areaY == null || areaZ == null) {
			return -1;
		}

		Map<IntPair, Integer> memo = new HashMap<>();

		return Utilities.max(getMaxSurvivalTimeWithAreas(startingPowers, 0, areaX, areaX, areaY, areaZ, memo),
				getMaxSurvivalTimeWithAreas(startingPowers, 0, areaY, areaX, areaY, areaZ, memo),
				getMaxSurvivalTimeWithAreas(startingPowers, 0, areaZ, areaX, areaY, areaZ, memo));
	}

	private static int getMaxSurvivalTimeWithAreas(IntPair currentPowers, int survivalTime, IntPair newArea,
												   IntPair areaX, IntPair areaY, IntPair areaZ, Map<IntPair, Integer> memo) {
		IntPair newPowers = new IntPair(currentPowers.getA() + newArea.getA(), currentPowers.getB() + newArea.getB());

		if (newPowers.getA() < 0 || newPowers.getB() < 0) {
			return survivalTime;
		}

		if (memo.containsKey(newPowers)) {
			return memo.get(newPowers);
		}

		survivalTime++;

		int maxSurvivalTime = 0;
		if (newArea == areaX) {
			maxSurvivalTime = Math.max(
					getMaxSurvivalTimeWithAreas(newPowers, survivalTime, areaY, areaX, areaY, areaZ, memo),
					getMaxSurvivalTimeWithAreas(newPowers, survivalTime, areaZ, areaX, areaY, areaZ, memo));
		} else if (newArea == areaY) {
			maxSurvivalTime = Math.max(
					getMaxSurvivalTimeWithAreas(newPowers, survivalTime, areaX, areaX, areaY, areaZ, memo),
					getMaxSurvivalTimeWithAreas(newPowers, survivalTime, areaZ, areaX, areaY, areaZ, memo));
		} else if (newArea == areaZ) {
			maxSurvivalTime = Math.max(
					getMaxSurvivalTimeWithAreas(newPowers, survivalTime, areaX, areaX, areaY, areaZ, memo),
					getMaxSurvivalTimeWithAreas(newPowers, survivalTime, areaY, areaX, areaY, areaZ, memo));
		}

		memo.put(newArea, maxSurvivalTime);

		return maxSurvivalTime;
	}

	public static Pair<List<Integer>, List<Integer>> tugOfWar(int[] participantStrengths) {
		// Tug of war - divide participants into 2 teams, difference of strengths of
		// which is minimum
		// If # participants are odd, one team is of (n-1)/2 size & the other (n+1)/2
		// size
		// If # participants are even, both teams are of size n/2
		// Backtracking implementation
		// Alternate approach: use a boolean array to mark inclusion in team 1

		if (participantStrengths == null || participantStrengths.length == 0) {
			return null;
		}

		int numParticipants = participantStrengths.length;
		int totalStrength = 0;
		for (int strength : participantStrengths) {
			totalStrength += strength;
		}

		AtomicInteger minStrengthDifference = new AtomicInteger(Integer.MAX_VALUE);
		Set<Integer> selectedTeam1Participants = new HashSet<>();

		tugOfwar(participantStrengths, numParticipants, totalStrength, 0, new HashSet<>(), minStrengthDifference,
				selectedTeam1Participants);

		List<Integer> team1Strengths = new ArrayList<>();
		List<Integer> team2Strengths = new ArrayList<>();
		var teams = new Pair(team1Strengths, team2Strengths);

		for (int i = 0; i < numParticipants; i++) {
			if (selectedTeam1Participants.contains(i)) {
				team1Strengths.add(participantStrengths[i]);
			} else {
				team2Strengths.add(participantStrengths[i]);
			}
		}

		return teams;
	}

	private static void tugOfwar(int[] participantStrengths, int numParticipants, int totalStrength, int currentIndex,
								 Set<Integer> team1Participants, AtomicInteger minStrengthDifference,
								 Set<Integer> selectedTeam1Participants) {
		if (currentIndex >= numParticipants) {
			return;
		}

		if (team1Participants.size() >= numParticipants / 2) {
			int team1Strength = 0;
			for (int p : team1Participants) {
				team1Strength += participantStrengths[p];
			}
			int team2Strength = totalStrength - team1Strength;
			int strengthDifference = Math.abs(team1Strength - team2Strength);

			if (strengthDifference < minStrengthDifference.intValue()) {
				minStrengthDifference.set(strengthDifference);
				selectedTeam1Participants.clear();
				selectedTeam1Participants.addAll(team1Participants);
			}

			return;
		}

		// Choice 1: add current participant
		team1Participants.add(currentIndex);
		tugOfwar(participantStrengths, numParticipants, totalStrength, currentIndex + 1, team1Participants,
				minStrengthDifference, selectedTeam1Participants);

		// Choice 2: do not add current participant
		team1Participants.remove(currentIndex);
		tugOfwar(participantStrengths, numParticipants, totalStrength, currentIndex + 1, team1Participants,
				minStrengthDifference, selectedTeam1Participants);
	}

	public static Map<Character, Integer> solveCryptarithmeticSumPuzzle(String first, String second, String sum) {
		if (first == null || first.length() == 0 || second == null || second.length() == 0 || sum == null
				|| sum.length() == 0) {
			return null;
		}

		int[] counts = new int[26];
		Arrays.fill(counts, 0);
		for (int i = 0; i < first.length(); i++) {
			counts[first.charAt(i) - 'A']++;
		}

		for (int i = 0; i < second.length(); i++) {
			counts[second.charAt(i) - 'A']++;
		}

		for (int i = 0; i < sum.length(); i++) {
			counts[sum.charAt(i) - 'A']++;
		}

		int uniqueCharacterCount = 0;
		List<Character> uniqueCharacters = new ArrayList<>();
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] > 0) {
				uniqueCharacterCount++;
				uniqueCharacters.add((char) ('A' + i));
			}
		}

		if (uniqueCharacterCount > 10) {
			// No solution possible as there are more characters than digits
			return null;
		}

		Map<Character, Integer> solution = new HashMap<>();
		if (solveCryptarithmeticSumPuzzle(first, second, sum, uniqueCharacters, uniqueCharacterCount, 0, solution)) {
			return solution;
		}

		return null;
	}

	private static boolean solveCryptarithmeticSumPuzzle(String first, String second, String sum,
														 List<Character> uniqueCharacters, int uniqueCharacterCount, int currentIndex,
														 Map<Character, Integer> solution) {
		if (currentIndex == uniqueCharacterCount) {
			// Check if current solution satisfies the puzzle.
			if (isCryptarithmeticSumPuzzleSolved(first, second, sum, solution)) {
				return true;
			}

			return false;
		}

		Character character = uniqueCharacters.get(currentIndex);
		for (int digit = 0; digit < 10; digit++) {
			if (solution.containsValue(digit)) {
				// Digit already assigned.
				continue;
			}

			solution.put(character, digit);
			if (solveCryptarithmeticSumPuzzle(first, second, sum, uniqueCharacters, uniqueCharacterCount,
					currentIndex + 1, solution)) {
				return true;
			}

			// Backtrack
			solution.remove(character);
		}

		return false;
	}

	private static boolean isCryptarithmeticSumPuzzleSolved(String first, String second, String sum,
															Map<Character, Integer> solution) {
		int firstNumber = 0;
		int secondNumber = 0;
		int sumNumber = 0;

		for (int i = 0; i < first.length(); i++) {
			char c = first.charAt(i);
			int num = solution.get(c);
			firstNumber = (firstNumber * 10) + num;
		}

		for (int i = 0; i < second.length(); i++) {
			char c = second.charAt(i);
			int num = solution.get(c);
			secondNumber = (secondNumber * 10) + num;
		}

		for (int i = 0; i < sum.length(); i++) {
			char c = sum.charAt(i);
			int num = solution.get(c);
			sumNumber = (sumNumber * 10) + num;
		}

		return (firstNumber + secondNumber) == sumNumber;
	}

	public static Set<String> boggle(char[][] board, Set<String> dictionary) {
		// Search for words from the dictionary in the board & return the words found.
		// From any cell, all 8 adjacent cells are accessible for search.

		// Do DFS from each cell to its adjacent 9 cells & construct words.
		// Check if each of those words is in the dictionary

		if (board == null || board.length == 0 || board[0].length == 0 || dictionary == null
				|| dictionary.size() == 0) {
			return null;
		}

		int rows = board.length;
		int columns = board[0].length;
		for (int i = 1; i < rows; i++) {
			if (board[i] == null || board[i].length != columns) {
				return null;
			}
		}

		Trie trie = new Trie();
		for (var word : dictionary) {
			trie.insert(word);
		}

		boolean[][] visited = new boolean[rows][columns];
		TrieNode root = trie.getRoot();
		StringBuilder currentWord = new StringBuilder();
		Set<String> wordsInBoard = new HashSet<>();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Utilities.fillArray(visited, false);
				searchWordsForBoggle(board, rows, columns, i, j, root, visited, currentWord, wordsInBoard);
			}
		}

		return wordsInBoard;
	}

	private static void searchWordsForBoggle(char[][] board, int rows, int columns, int currentRow, int currentColumn,
											 TrieNode node, boolean[][] visited, StringBuilder currentWord, Set<String> wordsInBoard) {
		if (currentRow < 0 || currentRow >= rows || currentColumn < 0 || currentColumn >= columns
				|| visited[currentRow][currentColumn]) {
			return;
		}

		char c = board[currentRow][currentColumn];
		if (!node.getChildren().containsKey(c)) {
			return;
		}

		currentWord.append(c);
		visited[currentRow][currentColumn] = true;

		var childNode = node.getChildren().get(c);
		if (childNode.isEndOfWord()) {
			wordsInBoard.add(currentWord.toString());
		}

		for (int i = currentRow - 1; i <= currentRow + 1; i++) {
			for (int j = currentColumn - 1; j <= currentColumn + 1; j++) {
				if (!(currentRow == i && currentColumn == j)) {
					searchWordsForBoggle(board, rows, columns, i, j, childNode, visited, currentWord, wordsInBoard);
				}
			}
		}

		// Backtrack
		visited[currentRow][currentColumn] = false;
		currentWord.deleteCharAt(currentWord.length() - 1);
	}

	public static Set<String> boggleNaive(char[][] board, Set<String> dictionary) {
		// Search for words from the dictionary in the board & return the words found.
		// From any cell, all 8 adjacent cells are accessible for search.

		// Do DFS from each cell to its adjacent 9 cells & construct words.
		// Check if each of those words is in the dictionary

		if (board == null || board.length == 0 || board[0].length == 0 || dictionary == null
				|| dictionary.size() == 0) {
			return null;
		}

		int rows = board.length;
		int columns = board[0].length;
		for (int i = 1; i < rows; i++) {
			if (board[i] == null || board[i].length != columns) {
				return null;
			}
		}

		Set<IntPair> visitedCells = new HashSet<>();
		Set<String> wordsInBoard = new HashSet<>();
		StringBuilder currentWord = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				visitedCells.clear();
				boggleNaive(board, dictionary, rows, columns, i, j, visitedCells, currentWord, wordsInBoard);
			}
		}

		return wordsInBoard;
	}

	private static void boggleNaive(char[][] board, Set<String> dictionary, int rows, int columns, int currentRow,
									int currentColumn, Set<IntPair> visitedCells, StringBuilder currentWord, Set<String> wordsInBoard) {
		if (currentRow < 0 || currentRow >= rows || currentColumn < 0 || currentColumn >= columns) {
			return;
		}

		IntPair cell = new IntPair(currentRow, currentColumn);
		if (visitedCells.contains(cell)) {
			return;
		}

		visitedCells.add(cell);

		Character c = board[currentRow][currentColumn];
		currentWord.append(c);
		String word = currentWord.toString();
		if (dictionary.contains(word)) {
			wordsInBoard.add(word);
		}

		for (int i = currentRow - 1; i <= currentRow + 1; i++) {
			for (int j = currentColumn - 1; j <= currentColumn + 1; j++) {
				if (!(i == currentRow && j == currentColumn)) {
					boggleNaive(board, dictionary, rows, columns, i, j, visitedCells, currentWord, wordsInBoard);
				}
			}
		}

		// Backtrack
		visitedCells.remove(cell);
		currentWord.deleteCharAt(currentWord.length() - 1);
	}

	public static int[][] ratInAMaze(int[][] maze) {
		// The rat can move right or down in the maze. The rat has to start from (0,0) & reach (n-1, n-1) in the maze.
		// In the maze, a non-zero value implies that the path is accessible; zero implies a wall.

		if (maze == null || maze.length == 0 || maze[0].length == 0) {
			return null;
		}

		int rows = maze.length;
		int columns = maze[0].length;
		for (int i = 1; i < rows; i++) {
			if (maze[i] == null || maze[i].length != columns) {
				return null;
			}
		}

		int[][] solution = new int[rows][columns];
		Utilities.fillArray(solution, 0);

		if (ratInAMaze(maze, rows, columns, 0, 0, solution)) {
			return solution;
		}

		return null;
	}

	public static boolean ratInAMaze(int[][] maze, int rows, int columns,
									 int currentRow, int currentColumn, int[][] solution) {
		if (currentRow < 0 || currentRow >= rows || currentColumn < 0 || currentColumn >= columns) {
			return false;
		}

		if (maze[currentRow][currentColumn] == 0) {
			return false;
		}

		solution[currentRow][currentColumn] = 1;

		// Reached destination ?
		if (currentRow == rows - 1 && currentColumn == columns - 1) {
			return true;
		}

		// Try Right
		if (ratInAMaze(maze, rows, columns, currentRow, currentColumn + 1, solution)) {
			return true;
		}

		// Try Down
		if (ratInAMaze(maze, rows, columns, currentRow + 1, currentColumn, solution)) {
			return true;
		}

		solution[currentRow][currentColumn] = 0;
		return false;
	}
}
