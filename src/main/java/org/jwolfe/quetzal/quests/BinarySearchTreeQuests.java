package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.algorithms.TreeAlgorithms;
import org.jwolfe.quetzal.library.tree.BinarySearchTree;
import org.jwolfe.quetzal.library.tree.BinaryTreeNode;

import java.util.concurrent.atomic.AtomicInteger;

public class BinarySearchTreeQuests {

    public static boolean checkIfAllInternalNodesHaveExactlyOneChild(int[] preOrder) {
        // In a pre-order for a BST with internal nodes having one child, all descendants of a node are either greater or lesser than the node.
        if(preOrder == null
                || preOrder.length < 2) {
            return true;
        }

        int lastDescendant = preOrder[preOrder.length - 1];
        for (int i = 0; i < preOrder.length -1; i++) {
            int node = preOrder[i];
            int nextDescendant = preOrder[i+1];

            if(((node - nextDescendant) * (node - lastDescendant)) < 0) {
                // Node is lesser & greater than the descendant nodes
                return false;
            }
        }

        return true;
    }

    public static boolean checkIfAllInternalNodesHaveExactlyOneChildApproach3(int[] preOrder) {
        // In a pre-order for a BST with internal nodes having one child, all descendants of a node are either greater or lesser than the node.
        if(preOrder == null
                || preOrder.length < 2) {
            return true;
        }

        int min;
        int max;

        if(preOrder[preOrder.length - 1] > preOrder[preOrder.length - 2]) {
            min = preOrder[preOrder.length - 2];
            max = preOrder[preOrder.length - 1];
        }
        else {
            min = preOrder[preOrder.length - 1];
            max = preOrder[preOrder.length - 2];
        }

        for (int i = preOrder.length - 3; i >= 0; i--) {
            int node = preOrder[i];

            if(node > max) {
                max  = node;
            }
            else if(node < min) {
                min = node;
            }
            else {
                return false;
            }
        }

        return true;
    }


    public static boolean checkIfAllInternalNodesHaveExactlyOneChildNaive(int[] preOrder) {
        // In a pre-order for a BST with internal nodes having one child, all descendants of a node are either greater or lesser than the node.
        if(preOrder == null
                || preOrder.length < 2) {
            return true;
        }

        for (int i = 0; i < preOrder.length - 1; i++) {
            boolean isDescending = preOrder[i] > preOrder[i+1] ? true : false;

            for (int j = i+2; j < preOrder.length; j++) {
                if(isDescending
                        && (preOrder[i] < preOrder[j])) {
                    return false;
                }
                else if(!isDescending
                        && preOrder[i] > preOrder[j]){
                    return false;
                }
            }
        }

        return true;
    }

    public static int largestNumberLessThanOrEqualToNInBST(BinaryTreeNode node, int n) {
        if(node == null) {
            return -1;
        }

        int largestNumber = -1;
        while(node != null) {
            if(node.getData() <= n) {
                largestNumber = node.getData();
                node = node.getRight();
            }
            else {
                node = node.getLeft();
            }
        }

        return largestNumber;
    }

    public static void convertBSTToTreeWithSumOfSmallerNodes(BinaryTreeNode root) {
        AtomicInteger runningSum = new AtomicInteger(0);
        convertBSTToTreeWithSumOfSmallerNodes(root, runningSum);
    }

    private static void convertBSTToTreeWithSumOfSmallerNodes(BinaryTreeNode root, AtomicInteger runningSum) {
        if(root == null) {
            return;
        }

        convertBSTToTreeWithSumOfSmallerNodes(root.getLeft(), runningSum);

        runningSum.set(runningSum.intValue() + root.getData());
        root.setData(runningSum.intValue());

        convertBSTToTreeWithSumOfSmallerNodes(root.getRight(), runningSum);
    }

    public static int getKthLargestElement(BinaryTreeNode root, int k) {
        // Approach 1: Inorder traversal & get kth from last
        // Approach 2: Augment data structure (node) to include the # of elements to its left
        // Approach 3: This. (Reverse Inorder Traversal

        AtomicInteger kthLargest = new AtomicInteger(Integer.MIN_VALUE);
        getKthLargestElement(root, k, new AtomicInteger(0), kthLargest);
        return kthLargest.intValue();
    }

    private static void getKthLargestElement(BinaryTreeNode root, int k, AtomicInteger currentRank, AtomicInteger kthLargest) {
        if(root == null
                || currentRank.intValue() >= k) {
            return;
        }

        getKthLargestElement(root.getRight(), k, currentRank, kthLargest);

        if(currentRank.incrementAndGet() == k) {
            kthLargest.set(root.getData());
            return;
        }

        getKthLargestElement(root.getLeft(), k, currentRank, kthLargest);
    }
}
