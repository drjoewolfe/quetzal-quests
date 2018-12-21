package org.jwolfe.quetzal.quests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.jwolfe.quetzal.test.QuetzalAssertions.*;

class GraphQuestsTest {

	@Test
	void testRemoveAllOutgoingEdgesExceptForMinimum() {
		int[][] graph = new int[][]{
								{0, 3, 2, 5},
								{0, 2, 4, 7},
								{1, 2, 0, 3},
								{5, 2, 1, 3}};
		int[][] expectedMinimizedGraph = new int[][]{
								{0, 0, 2, 0},
								{0, 2, 0, 0},
								{1, 0, 0, 0},
								{0, 0, 1, 0}};
								
		int[][] minimizedGraph = GraphQuests.removeAllOutgoingEdgesExceptForMinimum(graph);
		assertTwoDimensionalArrayEquals(expectedMinimizedGraph, minimizedGraph);
	}
}
