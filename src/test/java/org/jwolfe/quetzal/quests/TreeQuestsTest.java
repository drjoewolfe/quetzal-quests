package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.algorithms.TreeAlgorithms;
import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.utilities.Utilities;

import static org.junit.jupiter.api.Assertions.*;

class TreeQuestsTest {
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
}