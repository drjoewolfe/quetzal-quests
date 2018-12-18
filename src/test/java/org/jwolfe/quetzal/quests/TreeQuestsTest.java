package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.algorithms.TreeAlgorithms;
import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreeQuestsTest {
    @Test
    void convertTreeToDoublyLinkedList() {
        BinaryTreeNode tree;
        BinaryTreeNode head;
        int[] traversal;
        int[] expectedTraversal;

        tree = Utilities.constructBinaryTree(10, 12, 15, 25, 30, 36);
        TreeAlgorithms.visitInOrder(tree);
        head = TreeQuests.convertTreeToDoublyLinkedList(tree);

        System.out.println();
        expectedTraversal = Utilities.constructArray(25, 12, 30, 10, 36, 15);
        traversal = Utilities.getLeftToRightTraversal(head);
        Utilities.printArray(expectedTraversal);
        Utilities.printArray(traversal);
        assertArrayEquals(expectedTraversal, traversal);

        expectedTraversal = Utilities.constructArray(15, 36, 10, 30, 12, 25);
        traversal = Utilities.getRightToLeftTraversal(head);
        Utilities.printArray(expectedTraversal);
        Utilities.printArray(traversal);
        assertArrayEquals(expectedTraversal, traversal);

        System.out.println();

        tree = Utilities.constructBinaryTree(5, 3, 6, 1, 4, null, 8, 0, 2, null, null, null, null, 7, 9);
        TreeAlgorithms.visitInOrder(tree);
        head = TreeQuests.convertTreeToDoublyLinkedList(tree);

        System.out.println();
        expectedTraversal = Utilities.constructArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        traversal = Utilities.getLeftToRightTraversal(head);
        Utilities.printArray(expectedTraversal);
        Utilities.printArray(traversal);
        assertArrayEquals(expectedTraversal, traversal);

        expectedTraversal = Utilities.constructArray(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        traversal = Utilities.getRightToLeftTraversal(head);
        Utilities.printArray(expectedTraversal);
        Utilities.printArray(traversal);
        assertArrayEquals(expectedTraversal, traversal);
    }

    @Test
    void convertTreeToDoublyLinkedListA3() {
        BinaryTreeNode tree;
        BinaryTreeNode head;
        int[] traversal;
        int[] expectedTraversal;

        tree = Utilities.constructBinaryTree(10, 12, 15, 25, 30, 36);
        TreeAlgorithms.visitInOrder(tree);
        head = TreeQuests.convertTreeToDoublyLinkedListA3(tree);

        System.out.println();
        expectedTraversal = Utilities.constructArray(25, 12, 30, 10, 36, 15);
        traversal = Utilities.getLeftToRightTraversal(head);
        Utilities.printArray(expectedTraversal);
        Utilities.printArray(traversal);
        assertArrayEquals(expectedTraversal, traversal);

        expectedTraversal = Utilities.constructArray(15, 36, 10, 30, 12, 25);
        traversal = Utilities.getRightToLeftTraversal(head);
        Utilities.printArray(expectedTraversal);
        Utilities.printArray(traversal);
        assertArrayEquals(expectedTraversal, traversal);
    }

    @Test
    void convertTreeToDoublyLinkedListA2() {
        BinaryTreeNode tree;
        BinaryTreeNode head;
        int[] traversal;
        int[] expectedTraversal;

        tree = Utilities.constructBinaryTree(10, 12, 15, 25, 30, 36);
        TreeAlgorithms.visitInOrder(tree);
        head = TreeQuests.convertTreeToDoublyLinkedListA2(tree);

        System.out.println();
        expectedTraversal = Utilities.constructArray(25, 12, 30, 10, 36, 15);
        traversal = Utilities.getLeftToRightTraversal(head);
        Utilities.printArray(expectedTraversal);
        Utilities.printArray(traversal);
        assertArrayEquals(expectedTraversal, traversal);

        expectedTraversal = Utilities.constructArray(15, 36, 10, 30, 12, 25);
        traversal = Utilities.getRightToLeftTraversal(head);
        Utilities.printArray(expectedTraversal);
        Utilities.printArray(traversal);
        assertArrayEquals(expectedTraversal, traversal);
    }

    @Test
    void convertTreeToDoublyLinkedListA1() {
        BinaryTreeNode tree;
        BinaryTreeNode head;
        int[] traversal;
        int[] expectedTraversal;

        tree = Utilities.constructBinaryTree(10, 12, 15, 25, 30, 36);
        TreeAlgorithms.visitInOrder(tree);
        head = TreeQuests.convertTreeToDoublyLinkedListA1(tree);

        System.out.println();
        expectedTraversal = Utilities.constructArray(25, 12, 30, 10, 36, 15);
        traversal = Utilities.getLeftToRightTraversal(head);
        Utilities.printArray(expectedTraversal);
        Utilities.printArray(traversal);
        assertArrayEquals(expectedTraversal, traversal);

        expectedTraversal = Utilities.constructArray(15, 36, 10, 30, 12, 25);
        traversal = Utilities.getRightToLeftTraversal(head);
        Utilities.printArray(expectedTraversal);
        Utilities.printArray(traversal);
        assertArrayEquals(expectedTraversal, traversal);
    }

    @Test
    void convertTreeToDoublyLinkedListInSpiralFashion() {
        BinaryTreeNode tree;
        BinaryTreeNode head;

        int[] traversal;
        int[] expectedTraversal;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, null, 13, 14);
        TreeAlgorithms.visitInOrder(tree);
        head = TreeQuests.convertTreeToDoublyLinkedListInSpiralFashion(tree);

        System.out.println();
        expectedTraversal = Utilities.constructArray(1, 2, 3, 7, 6, 5, 4, 8, 9, 10, 11, 13, 14);
        traversal = Utilities.getLeftToRightTraversal(head);
        Utilities.printArray(expectedTraversal);
        Utilities.printArray(traversal);
        assertArrayEquals(expectedTraversal, traversal);

        expectedTraversal = Utilities.constructArray(14, 13, 11, 10, 9, 8, 4, 5, 6, 7, 3, 2, 1);
        traversal = Utilities.getRightToLeftTraversal(head);
        Utilities.printArray(expectedTraversal);
        Utilities.printArray(traversal);
        assertArrayEquals(expectedTraversal, traversal);
    }

    @Test
    void getDepthOfDeepestOddLevelLeafNode() {
        BinaryTreeNode tree;
        int deepestLevel;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, null, 5, 6, null, null, null, null, null, 7, null, 8);
        tree.getRight().getLeft().getRight().setLeft(new BinaryTreeNode(9));
        tree.getRight().getRight().getRight().setRight(new BinaryTreeNode(10));
        tree.getRight().getRight().getRight().getRight().setLeft(new BinaryTreeNode(11));

        deepestLevel = TreeQuests.getDepthOfDeepestOddLevelLeafNode(tree);
        assertEquals(5, deepestLevel);
    }

    @Test
    void getDepthOfDeepestOddLevelLeafNodeIterative() {
        BinaryTreeNode tree;
        int deepestLevel;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, null, 5, 6, null, null, null, null, null, 7, null, 8);
        tree.getRight().getLeft().getRight().setLeft(new BinaryTreeNode(9));
        tree.getRight().getRight().getRight().setRight(new BinaryTreeNode(10));
        tree.getRight().getRight().getRight().getRight().setLeft(new BinaryTreeNode(11));

        deepestLevel = TreeQuests.getDepthOfDeepestOddLevelLeafNodeIterative(tree);
        assertEquals(5, deepestLevel);
    }

    @Test
    void getLevelOrderLines() {
        BinaryTreeNode tree;
        List<List<Integer>> levelOrderLines;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5);
        levelOrderLines = TreeQuests.getLevelOrderLines(tree);
        assertEquals(3, levelOrderLines.size());
        assertArrayEquals(new int[] {1}, levelOrderLines.get(0).stream().mapToInt(i->i).toArray());
        assertArrayEquals(new int[] {2, 3}, levelOrderLines.get(1).stream().mapToInt(i->i).toArray());
        assertArrayEquals(new int[] {4, 5}, levelOrderLines.get(2).stream().mapToInt(i->i).toArray());
    }

    @Test
    void maxPathSum() {
        BinaryTreeNode tree;
        int maxPathSum;

        tree = Utilities.constructBinaryTree(1, 2, 3);
        maxPathSum = TreeQuests.maxPathSum(tree);
        assertEquals(6, maxPathSum);

        tree = Utilities.constructBinaryTree(10, 2, 10, 20, 1, null, -25, null, null, null, null, null, null, 3, 4);
        maxPathSum = TreeQuests.maxPathSum(tree);
        assertEquals(42, maxPathSum);
    }

    @Test
    void sinkOddNodes() {
        BinaryTreeNode tree;
        int[] levelOrder;
        int[] expectedLevelOrder;

        tree = Utilities.constructBinaryTree(1, 2, 3);
        expectedLevelOrder = Utilities.constructArray(2, 1, 3);
        TreeQuests.sinkOddNodes(tree);
        levelOrder = TreeAlgorithms.getLevelOrder(tree);
        assertArrayEquals(expectedLevelOrder, levelOrder);

        tree = Utilities.constructBinaryTree(1, 5, 8, 2, 4, 9, 10);
        expectedLevelOrder = Utilities.constructArray(2, 4, 8, 5, 1, 9, 10);
        TreeQuests.sinkOddNodes(tree);
        levelOrder = TreeAlgorithms.getLevelOrder(tree);
        assertArrayEquals(expectedLevelOrder, levelOrder);
    }
}