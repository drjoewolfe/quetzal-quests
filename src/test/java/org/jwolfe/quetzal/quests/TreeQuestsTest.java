package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.algorithms.TreeAlgorithms;
import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.tree.ConnectableBinaryTreeNode;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.ArrayList;
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

    @Test
    void maxPathSumBetweenTwoLeaves() {
        BinaryTreeNode tree;
        int maxPathSum;

        tree = Utilities.constructBinaryTree(-15, 5, 6, -8, 1, 3, 9, 2, 6, null, null, null, null, null, 0);
        tree.getRight().getRight().getRight().setLeft(new BinaryTreeNode(4));
        tree.getRight().getRight().getRight().setRight(new BinaryTreeNode(-1));
        tree.getRight().getRight().getRight().getRight().setLeft(new BinaryTreeNode(10));
        maxPathSum = TreeQuests.maxPathSum(tree);
        assertEquals(27, maxPathSum);
    }

    @Test
    void alternateLevelOrderTraversalForPerfectTree() {
        BinaryTreeNode tree;
        List<Integer> traversal;
        int[] expectedTraversal;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
        expectedTraversal = Utilities.constructArray(1, 2, 3, 4, 7, 5, 6, 8, 15, 9, 14, 10, 13, 11, 12, 16, 31, 17, 30, 18, 29, 19, 28, 20, 27, 21, 26,  22, 25, 23, 24);
        traversal = new ArrayList<>();
        TreeQuests.alternateLevelOrderTraversalForPerfectTree(tree, n->{
            System.out.print(n.getData() + " ");
            traversal.add(n.getData());
        });
        assertArrayEquals(expectedTraversal, traversal.stream().mapToInt(i->i).toArray());
        System.out.println();
    }

    @Test
    void alternateBottomsUpLevelOrderTraversalForPerfectTree() {
        BinaryTreeNode tree;
        List<Integer> traversal;
        int[] expectedTraversal;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
        expectedTraversal = Utilities.constructArray(16, 31, 17, 30, 18, 29, 19, 28, 20, 27, 21, 26, 22, 25, 23, 24, 8, 15, 9, 14, 10, 13, 11, 12, 4, 7, 5, 6, 2, 3, 1);
        traversal = new ArrayList<>();
        TreeQuests.alternateBottomsUpLevelOrderTraversalForPerfectTree(tree, n->{
            System.out.print(n.getData() + " ");
            traversal.add(n.getData());
        });
        assertArrayEquals(expectedTraversal, traversal.stream().mapToInt(i->i).toArray());
        System.out.println();
    }

    @Test
    void getExtremeNodesInAlternateOrder() {
        BinaryTreeNode tree;
        List<Integer> extremeNodes;
        int[] expectedExtremeNodes;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
        expectedExtremeNodes = Utilities.constructArray(1, 2, 7, 8, 31);
        extremeNodes = TreeQuests.getExtremeNodesInAlternateOrder(tree);
        assertArrayEquals(expectedExtremeNodes, extremeNodes.stream().mapToInt(i->i).toArray());
    }
    
    @Test
    void constructSpecialBinaryTreeFromInOrder() {
        BinaryTreeNode tree;
        int[] inOrder;

        inOrder = Utilities.constructArray(5, 10, 40, 30, 28);
        tree = TreeQuests.constructSpecialBinaryTreeFromInOrder(inOrder);
        assertEquals(40, tree.getData());
        assertEquals(10, tree.getLeft().getData());
        assertEquals(5, tree.getLeft().getLeft().getData());
        assertEquals(30, tree.getRight().getData());
        assertEquals(28, tree.getRight().getRight().getData());
        
        inOrder = Utilities.constructArray(1, 5, 10, 40, 30, 15, 28, 20);
        tree = TreeQuests.constructSpecialBinaryTreeFromInOrder(inOrder);
        assertEquals(40, tree.getData());
        assertEquals(10, tree.getLeft().getData());
        assertEquals(5, tree.getLeft().getLeft().getData());
        assertEquals(1, tree.getLeft().getLeft().getLeft().getData());
        assertEquals(30, tree.getRight().getData());
        assertEquals(28, tree.getRight().getRight().getData());
        assertEquals(15, tree.getRight().getRight().getLeft().getData());
        assertEquals(20, tree.getRight().getRight().getRight().getData());
    }
    
    @Test
    void connectNodesAtSameLevel() {
    	ConnectableBinaryTreeNode<Integer> tree;

    	tree = Utilities.constructConnectableBinaryTree(10, 8, 2, 3, null, null, 90);
    	TreeQuests.connectNodesAtSameLevel(tree);
    	assertNull(tree.getConnection());
    	assertEquals(2, (int) tree.getLeft().getConnection().getData());
    	assertNull(tree.getRight().getConnection());
    	assertEquals(90, (int) tree.getLeft().getLeft().getConnection().getData());
    	assertNull(tree.getRight().getRight().getConnection());    	
    }
    
    @Test
    void connectNodesAtSameLevelRecursive() {
    	ConnectableBinaryTreeNode<Integer> tree;

    	tree = Utilities.constructConnectableBinaryTree(10, 8, 2, 3, null, null, 90);
    	TreeQuests.connectNodesAtSameLevelRecursive(tree);
    	assertNull(tree.getConnection());
    	assertEquals(2, (int) tree.getLeft().getConnection().getData());
    	assertNull(tree.getRight().getConnection());
    	assertEquals(90, (int) tree.getLeft().getLeft().getConnection().getData());
    	assertNull(tree.getRight().getRight().getConnection());    	
    }
    
    @Test
    void connectNodesAtSameLevelIterative() {
    	ConnectableBinaryTreeNode<Integer> tree;

    	tree = Utilities.constructConnectableBinaryTree(10, 8, 2, 3, null, null, 90);
    	TreeQuests.connectNodesAtSameLevelIterative(tree);
    	assertNull(tree.getConnection());
    	assertEquals(2, (int) tree.getLeft().getConnection().getData());
    	assertNull(tree.getRight().getConnection());
    	assertEquals(90, (int) tree.getLeft().getLeft().getConnection().getData());
    	assertNull(tree.getRight().getRight().getConnection());    	
    }
}