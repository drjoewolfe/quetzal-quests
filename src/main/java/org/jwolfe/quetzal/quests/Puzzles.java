package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.algorithms.ListAlgorithms;
import org.jwolfe.quetzal.library.general.Coordinate;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.general.Rod;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.*;

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
			var newCoordinate = new Coordinate(currentCoordinate.getX() + relativeCoordinate.getX(), currentCoordinate.getY() + relativeCoordinate.getY());
			
			if(isValidKnightMoveForTour(board, boardSize, currentCoordinate, newCoordinate)) {
				int degreeOfAccessibility = getDegreeOfAccessibilityForKnightMove(board, boardSize, newCoordinate, validRelativeMoves);
				if(degreeOfAccessibility < minDegreeOfAccessibility) {
					minDegreeOfAccessibility = degreeOfAccessibility;
					minAccessibilityCoordinate = newCoordinate;
				}
			}
		}

		if(minAccessibilityCoordinate == null) {
			return false;
		}
		
		currentCoordinate.set(minAccessibilityCoordinate);
		board[currentCoordinate.getX()][currentCoordinate.getY()] = moveNumber;
		return true;
	}

	private static int getDegreeOfAccessibilityForKnightMove(int[][] board, int boardSize, Coordinate coordinate,
			List<Coordinate> validRelativeMoves) {
		int degree = 0;
		for(var relativeCoordinate : validRelativeMoves) {
			var newCoordinate = new Coordinate(coordinate.getX() + relativeCoordinate.getX(), coordinate.getY() + relativeCoordinate.getY());
			if(newCoordinate.getX() >= 0 && newCoordinate.getX() <boardSize
					&& newCoordinate.getY() >= 0 && newCoordinate.getY() < boardSize
					&& board[newCoordinate.getX()][newCoordinate.getY()] == -1) {
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
		if (mine == null
				|| mine.length == 0
				|| mine[0] == null
				|| mine[0].length == 0) {
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

					goldMined[i][j] = mine[i][j]
							+ Utilities.max(rightUpGold, rightGold, rightDownGold);
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
		if(mine == null
			|| mine.length == 0
			|| mine[0] == null
			|| mine[0].length == 0) {
			return -1;
		}

		int rows = mine.length;
		int columns = mine[0].length;
		for (int i = 1; i < rows; i++) {
			if(mine[i].length != columns) {
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

	private static int getMaxGoldFromMineRecursive(int[][] mine, int rows, int columns, int currentRow, int currentColumn) {
		if(currentRow == rows || currentColumn == columns) {
			return 0;
		}

		if(currentRow < 0) {
			return 0;
		}

		return mine[currentRow][currentColumn]
				+ Utilities.max(
					getMaxGoldFromMineRecursive(mine, rows, columns, currentRow - 1, currentColumn + 1),
					getMaxGoldFromMineRecursive(mine, rows, columns, currentRow, currentColumn + 1),
					getMaxGoldFromMineRecursive(mine, rows, columns, currentRow + 1, currentColumn + 1)
				);
	}
}
