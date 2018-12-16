package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.tree.BinarySearchTree;
import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.utilities.Utilities;

import static org.junit.jupiter.api.Assertions.*;

class BSTQuestsTest {

    @Test
    void correctBSTForSwapped2Nodes() {
       /*    6
            / \
           10  2
          / \ / \
         1  3 7 12

        10 and 2 are swapped
        */

        BinaryTreeNode root = new BinaryTreeNode(6);
        root.setLeft(new BinaryTreeNode(10));
        root.setRight( new BinaryTreeNode(2));
        root.getLeft().setLeft( new BinaryTreeNode(1));
        root.getLeft().setRight( new BinaryTreeNode(3));
        root.getRight().setLeft( new BinaryTreeNode(7));
        root.getRight().setRight( new BinaryTreeNode(12));

        BinarySearchTree tree = new BinarySearchTree();
        tree.setRoot(root);
        tree.visitInorder();

        BSTQuests.correctBSTForSwapped2Nodes(root);

        System.out.println();
        tree.visitInorder();
    }

    @Test
    void doesPreOrderRepresentBST1() {
        int[] arr;

        arr = Utilities.constructArray(2, 4, 3);
        assertEquals(BSTQuests.doesPreOrderRepresentBST1(arr), true);

        arr = Utilities.constructArray(2, 4, 1);
        assertEquals(BSTQuests.doesPreOrderRepresentBST1(arr), false);

        arr = Utilities.constructArray(40, 30, 35, 80, 100);
        assertEquals(BSTQuests.doesPreOrderRepresentBST1(arr), true);

        arr = Utilities.constructArray(40, 30, 35, 20, 80, 100);
        assertEquals(BSTQuests.doesPreOrderRepresentBST1(arr), false);
    }

    @Test
    void doesPreOrderRepresentBST2() {
        int[] arr;

        arr = Utilities.constructArray(2, 4, 3);
        assertEquals(BSTQuests.doesPreOrderRepresentBST2(arr), true);

        arr = Utilities.constructArray(2, 4, 1);
        assertEquals(BSTQuests.doesPreOrderRepresentBST2(arr), false);

        arr = Utilities.constructArray(40, 30, 35, 80, 100);
        assertEquals(BSTQuests.doesPreOrderRepresentBST2(arr), true);

        arr = Utilities.constructArray(40, 30, 35, 20, 80, 100);
        assertEquals(BSTQuests.doesPreOrderRepresentBST2(arr), false);
    }

    @Test
    void doesPreOrderRepresentBST3() {
        int[] arr;

        arr = Utilities.constructArray(2, 4, 3);
        assertEquals(BSTQuests.doesPreOrderRepresentBST3(arr), true);

        arr = Utilities.constructArray(2, 4, 1);
        assertEquals(BSTQuests.doesPreOrderRepresentBST3(arr), false);

        arr = Utilities.constructArray(40, 30, 35, 80, 100);
        assertEquals(BSTQuests.doesPreOrderRepresentBST3(arr), true);

        arr = Utilities.constructArray(40, 30, 35, 20, 80, 100);
        assertEquals(BSTQuests.doesPreOrderRepresentBST3(arr), false);
    }

    @Test
    void iterativeSearch() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insertList(50, 30, 20, 40, 70, 60, 80);

        assertEquals(BSTQuests.iterativeSearch(tree.getRoot(), 15), false);
        assertEquals(BSTQuests.iterativeSearch(tree.getRoot(), 70), true);
    }

    @Test
    void transformToGreaterSumTree() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insertList(11, 2, 29, 1, 7, 15, 40, 35);
        tree.balanceTree();

        tree.visitInorder();
        BSTQuests.transformToGreaterSumTree(tree.getRoot());
        System.out.println();
        tree.visitInorder();
    }

    @Test
    void findFloor() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insertList(19, 2, 1, 3, 12, 9, 21, 19, 25);

        assertEquals(BSTQuests.findFloor(tree.getRoot(), 25), 25);
        assertEquals(BSTQuests.findFloor(tree.getRoot(), 24), 21);
        assertEquals(BSTQuests.findFloor(tree.getRoot(), 4), 3);
    }

    @Test
    void removeOutsideRange() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insertList(6, -13, 14, -8, 15, 13, 7);

        tree.visitInorder();
        BSTQuests.removeOutsideRange(tree.getRoot(), -10, 13);
        System.out.println();
        tree.visitInorder();
    }
}