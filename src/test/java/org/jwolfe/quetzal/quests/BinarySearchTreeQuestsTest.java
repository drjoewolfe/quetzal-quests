package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Test;
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
}