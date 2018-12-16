package org.jwolfe.quetzal.quests;

import jdk.nashorn.api.tree.Tree;
import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.algorithms.TreeAlgorithms;
import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.utilities.Utilities;

import static org.junit.jupiter.api.Assertions.*;

class TreeQuestsTest {

    @Test
    void convertTreeToDoublyLinkedListInSpiralFashion() {
        BinaryTreeNode tree;
        BinaryTreeNode head;
        BinaryTreeNode current;
        int[] traversal;
        int[] expectedTraversal;
        int index;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, null, 13, 14);

        TreeAlgorithms.visitInOrder(tree);
        head = TreeQuests.convertTreeToDoublyLinkedListInSpiralFashion(tree);

        current = head;
        expectedTraversal = Utilities.constructArray(1, 2, 3, 7, 6, 5, 4, 8, 9, 10, 11, 13, 14);
        traversal = new int[13];
        index = 0;
        System.out.println();
        while(current != null) {
            System.out.print(current.getData() + " ");
            traversal[index++] = current.getData();

            current = current.getRight();
        }
        System.out.println();
        assertArrayEquals(expectedTraversal, traversal);

        current = head;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        expectedTraversal = Utilities.constructArray(1, 2, 3, 7, 6, 5, 4, 8, 9, 10, 11, 13, 14);
        expectedTraversal = Utilities.constructArray(14, 13, 11, 10, 9, 8, 4, 5, 6, 7, 3, 2, 1);
        traversal = new int[13];
        index = 0;
        System.out.println();
        while(current != null) {
            System.out.print(current.getData() + " ");
            traversal[index++] = current.getData();

            current = current.getLeft();
        }
        System.out.println();
        assertArrayEquals(expectedTraversal, traversal);
    }
}