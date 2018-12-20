package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.utilities.Utilities;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeQuestsTest {
    @Test
    void checkIfAllInternalNodesHaveExactlyOneChild() {
        int[] preorder;
        boolean rv;

        preorder = Utilities.constructArray(8, 3, 5, 7, 6);
        rv = BinarySearchTreeQuests.checkIfAllInternalNodesHaveExactlyOneChild(preorder);
        assertEquals(true, rv);

        preorder = Utilities.constructArray(20, 10, 11, 13, 12);
        rv = BinarySearchTreeQuests.checkIfAllInternalNodesHaveExactlyOneChild(preorder);
        assertEquals(true, rv);

        preorder = Utilities.constructArray(100, 25, 75, 15, 25, 50, 85);
        rv = BinarySearchTreeQuests.checkIfAllInternalNodesHaveExactlyOneChild(preorder);
        assertEquals(false, rv);
    }

    @Test
    void checkIfAllInternalNodesHaveExactlyOneChildApproach3() {
        int[] preorder;
        boolean rv;

        preorder = Utilities.constructArray(8, 3, 5, 7, 6);
        rv = BinarySearchTreeQuests.checkIfAllInternalNodesHaveExactlyOneChildApproach3(preorder);
        assertEquals(true, rv);

        preorder = Utilities.constructArray(20, 10, 11, 13, 12);
        rv = BinarySearchTreeQuests.checkIfAllInternalNodesHaveExactlyOneChildApproach3(preorder);
        assertEquals(true, rv);

        preorder = Utilities.constructArray(100, 25, 75, 15, 25, 50, 85);
        rv = BinarySearchTreeQuests.checkIfAllInternalNodesHaveExactlyOneChildApproach3(preorder);
        assertEquals(false, rv);
    }

    @Test
    void checkIfAllInternalNodesHaveExactlyOneChildNaive() {
        int[] preorder;
        boolean rv;

        preorder = Utilities.constructArray(8, 3, 5, 7, 6);
        rv = BinarySearchTreeQuests.checkIfAllInternalNodesHaveExactlyOneChildNaive(preorder);
        assertEquals(true, rv);

        preorder = Utilities.constructArray(20, 10, 11, 13, 12);
        rv = BinarySearchTreeQuests.checkIfAllInternalNodesHaveExactlyOneChildNaive(preorder);
        assertEquals(true, rv);

        preorder = Utilities.constructArray(100, 25, 75, 15, 25, 50, 85);
        rv = BinarySearchTreeQuests.checkIfAllInternalNodesHaveExactlyOneChildNaive(preorder);
        assertEquals(false, rv);
    }

    @Test
    void largestNumberLessThanOrEqualToNInBST() {
        BinaryTreeNode tree;
        int largestNumber;

        tree = Utilities.constructBinaryTree(5 ,2, 12, 1, 3, 9, 21, null, null, null, null, null, null, 19, 25);
        largestNumber = BinarySearchTreeQuests.largestNumberLessThanOrEqualToNInBST(tree, 24);
        assertEquals(21, largestNumber);
        largestNumber = BinarySearchTreeQuests.largestNumberLessThanOrEqualToNInBST(tree, 4);
        assertEquals(3, largestNumber);
    }

    @Test
    void convertBSTToTreeWithSumOfSmallerNodes() {
        BinaryTreeNode tree;

        tree = Utilities.constructBinaryTree(9, 6, 15, 3, null, null, 21);
        BinarySearchTreeQuests.convertBSTToTreeWithSumOfSmallerNodes(tree);
        assertEquals(18, tree.getData());
        assertEquals(9, tree.getLeft().getData());
        assertEquals(3, tree.getLeft().getLeft().getData());
        assertEquals(33, tree.getRight().getData());
        assertEquals(54, tree.getRight().getRight().getData());
    }

    @Test
    void getKthLargestElement() {
        BinaryTreeNode tree;
        int kthLargest;

        tree = Utilities.constructBinaryTree(20, 8, 22, 4, 12, null, null, null, null, 10, 14);
        kthLargest = BinarySearchTreeQuests.getKthLargestElement(tree, 3);
        assertEquals(14, kthLargest);
        kthLargest = BinarySearchTreeQuests.getKthLargestElement(tree, 5);
        assertEquals(10, kthLargest);

        tree = Utilities.constructBinaryTree(50, 30, 70, 20, 40, 60, 80);
        for (int i = 1; i < 8; i++) {
            kthLargest = BinarySearchTreeQuests.getKthLargestElement(tree, i);
            assertEquals(80 - 10 * (i - 1), kthLargest);
        }
    }
}