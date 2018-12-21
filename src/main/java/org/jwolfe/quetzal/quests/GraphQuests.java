package org.jwolfe.quetzal.quests;

public class GraphQuests {
	public static int[][] removeAllOutgoingEdgesExceptForMinimum(int[][] graph) {
		if (graph == null || graph.length == 1) {
			return null;
		}

		int n = graph.length;
		for (int i = 0; i < n; i++) {
			if (n != graph[i].length) {
				return null;
			}
		}

		int[][] minimizedGraph = new int[n][n];
		for (int i = 0; i < n; i++) {
			int min = Integer.MAX_VALUE;

			for (int j = 0; j < n; j++) {
				int weight = graph[i][j];
				if (weight != 0 && weight < min) {
					min = weight;
				}
			}

			for (int j = 0; j < n; j++) {
				if (graph[i][j] == min) {
					minimizedGraph[i][j] = min;
				} else {
					minimizedGraph[i][j] = 0;
				}
			}
		}

		return minimizedGraph;
	}
}
