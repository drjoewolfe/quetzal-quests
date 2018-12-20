package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.library.tree.BinaryTreeNode;

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
}
