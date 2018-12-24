package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.list.LinkedListNode;
import org.jwolfe.quetzal.library.list.AuxiliaryLinkedListNode;
import org.jwolfe.quetzal.library.list.RightDownLinkedListNode;
import org.jwolfe.quetzal.library.utilities.Utilities;
import org.jwolfe.quetzal.test.QuetzalAssertions;

import static org.junit.jupiter.api.Assertions.*;
import static org.jwolfe.quetzal.test.QuetzalAssertions.*;

class LinkedListQuestsTest {

    @Test
    void mergeSortedLinkedLists() {
        // 5->10->15
        // 2->3->20

        var head1 = new LinkedListNode(5);
        head1.setNext(new LinkedListNode(10));
        head1.getNext().setNext(new LinkedListNode(15));

        var head2 = new LinkedListNode(2);
        head2.setNext(new LinkedListNode(3));
        head2.getNext().setNext(new LinkedListNode(20));

        Utilities.printLinkedList(head1);
        Utilities.printLinkedList(head2);

        var mergedHead = LinkedListQuests.mergeSortedLinkedLists(head1, head2);
        Utilities.printLinkedList(mergedHead);
    }

    @Test
    void mergeSortedLinkedListsRecursive() {
        var head1 = Utilities.createLinkedList(5, 10, 15);
        var head2 = Utilities.createLinkedList(2, 3, 20);

        Utilities.printLinkedList(head1);
        Utilities.printLinkedList(head2);

        var mergedHead = LinkedListQuests.mergeSortedLinkedLists(head1, head2);
        Utilities.printLinkedList(mergedHead);
    }

    @Test
    void reverseLinkedList() {
        var head1 = Utilities.createLinkedList(5, 10, 15, 2, 3, 20);

        Utilities.printLinkedList(head1);
        var reversedHead1 = LinkedListQuests.reverseLinkedList(head1);
        Utilities.printLinkedList(reversedHead1);

        var head2 = Utilities.createLinkedList(1, 2, 3, 4, 5, 6);

        Utilities.printLinkedList(head2);
        var reversedHead2 = LinkedListQuests.reverseLinkedList(head2);
        Utilities.printLinkedList(reversedHead2);
    }

    @Test
    void deleteMiddle() {
        // 1->2->3->4->5->6
        var head1 = Utilities.createLinkedList(1, 2, 3, 4, 5, 6);

        Utilities.printLinkedList(head1);
        var modifiedHead = LinkedListQuests.deleteMiddle(head1);
        Utilities.printLinkedList(modifiedHead);
    }

    @Test
    void deleteNodeFromLinkedList() throws Exception {
        // 1->2->3->4->5->6
        var head1 = Utilities.createLinkedList(1, 2, 3, 4, 5, 6);

        Utilities.printLinkedList(head1);
        LinkedListQuests.deleteNodeFromLinkedList(head1.getNext().getNext().getNext());
        Utilities.printLinkedList(head1);
    }

    @Test
    void getLengthOfLoop() {
        var head1 = Utilities.createLinkedList(1, 2, 3, 4, 5, 6);
        Utilities.addLoopToLinkedList(head1, 2);

        int length1 = LinkedListQuests.getLengthOfLoop(head1);
        System.out.println(length1);
        assertEquals(4, length1);

        var head2 = Utilities.createLinkedList(1, 2, 3, 4, 5, 6);
        int length2 = LinkedListQuests.getLengthOfLoop(head2);
        System.out.println(length2);
        assertEquals(0, length2);
    }

    @Test
    void addOneToNumberAsLinkedList() {
        var head1 = Utilities.createLinkedList(1, 9, 9, 9);
        Utilities.printLinkedList(head1);
        LinkedListQuests.addOneToNumberAsLinkedList(head1);
        Utilities.printLinkedList(head1);
    }

    @Test
    void skipMDeleteN() {
        var head1 = Utilities.createLinkedList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Utilities.printLinkedList(head1);

        int m = 2;
        int n = 3;
        LinkedListQuests.skipMDeleteN(head1, m, n);
        System.out.println("Skip " + m + " delete " + n);
        Utilities.printLinkedList(head1);
    }

    @Test
    void detectAndRemoveLoop() {
        var head1 = Utilities.createLinkedList(50, 20, 15, 4, 10);
        Utilities.addLoopToLinkedList(head1, 2);

        var loopRemoved = LinkedListQuests.detectAndRemoveLoop(head1);
        if(loopRemoved) {
            System.out.println("Loop was found and removed");
            Utilities.printLinkedList(head1);
        }
        else {
            System.out.println("Loop was not found");
        }
    }

    @Test
    void getMiddle() {
        LinkedListNode head = new LinkedListNode(10);
        LinkedListNode middle;
        for(int i = 9; i>=0; i--) {
            Utilities.printLinkedList(head);
            middle = LinkedListQuests.getMiddle(head);
            System.out.println("\tMiddle: " + middle.getData());
            head = LinkedListQuests.push(head, i);
        }
    }

    @Test
    void rotate() {
        var head1 = Utilities.createLinkedList(10, 20, 30, 40, 50, 60);
        Utilities.printLinkedList(head1);
        head1 = LinkedListQuests.rotateCounterClockwise(head1, 4);
        Utilities.printLinkedList(head1);
    }

    @Test
    void rearrangeOddEvenNodesTogether() {
        LinkedListNode head;
        LinkedListNode expected;

        head = Utilities.createLinkedList();
        expected = Utilities.createLinkedList();
        Utilities.printLinkedList(head);
        head = LinkedListQuests.rearrangeOddEvenNodesTogether(head);
        Utilities.printLinkedList(head);
        assertLinkedLists(head, expected);

        head = Utilities.createLinkedList(1);
        expected = Utilities.createLinkedList(1);
        Utilities.printLinkedList(head);
        head = LinkedListQuests.rearrangeOddEvenNodesTogether(head);
        Utilities.printLinkedList(head);
        assertLinkedLists(head, expected);

        head = Utilities.createLinkedList(1, 2);
        expected = Utilities.createLinkedList(1, 2);
        Utilities.printLinkedList(head);
        head = LinkedListQuests.rearrangeOddEvenNodesTogether(head);
        Utilities.printLinkedList(head);
        assertLinkedLists(head, expected);

        head = Utilities.createLinkedList(1, 2, 3, 4);
        expected = Utilities.createLinkedList(1, 3, 2, 4);
        Utilities.printLinkedList(head);
        head = LinkedListQuests.rearrangeOddEvenNodesTogether(head);
        Utilities.printLinkedList(head);
        assertLinkedLists(head, expected);

        head = Utilities.createLinkedList(1, 2, 3, 4, 5);
        expected = Utilities.createLinkedList(1, 3, 5, 2, 4);
        Utilities.printLinkedList(head);
        head = LinkedListQuests.rearrangeOddEvenNodesTogether(head);
        Utilities.printLinkedList(head);
        assertLinkedLists(head, expected);

        head = Utilities.createLinkedList(10, 22, 30, 43, 56, 70);
        expected = Utilities.createLinkedList(10, 30, 56, 22, 43, 70);
        Utilities.printLinkedList(head);
        head = LinkedListQuests.rearrangeOddEvenNodesTogether(head);
        Utilities.printLinkedList(head);
        assertLinkedLists(head, expected);
    }

    @Test
    void printReverse() {
        LinkedListNode head;

        head = Utilities.createLinkedList(1, 2, 3, 4);
        Utilities.printLinkedList(head);
        LinkedListQuests.printReverse(head);
        System.out.println();
    }

    @Test
    void addLists() {
        LinkedListNode head1;
        LinkedListNode head2;
        LinkedListNode sumList;
        LinkedListNode expectedList;

        head1 = Utilities.createLinkedList(7, 5, 9, 4, 6);
        head2 = Utilities.createLinkedList(8, 4);
        expectedList = Utilities.createLinkedList(5, 0, 0, 5, 6);
        sumList = LinkedListQuests.addLists(head1, head2);
        Utilities.printLinkedList(head1);
        Utilities.printLinkedList(head2);
        Utilities.printLinkedList(sumList);
        assertLinkedLists(expectedList, sumList);

        head1 = Utilities.createLinkedList(5, 6, 3);
        head2 = Utilities.createLinkedList(8, 4, 2);
        expectedList = Utilities.createLinkedList(3, 1, 6);
        sumList = LinkedListQuests.addLists(head1, head2);
        Utilities.printLinkedList(head1);
        Utilities.printLinkedList(head2);
        Utilities.printLinkedList(sumList);
        assertLinkedLists(expectedList, sumList);
    }

    @Test
    void flatten() {
        RightDownLinkedListNode head;

        head = Utilities.createRightDownLinkedList(5, 10, 19, 28);
        Utilities.attachDownListToRightDownLinkedList(head, 5, 7, 8, 30);
        Utilities.attachDownListToRightDownLinkedList(head, 10, 20);
        Utilities.attachDownListToRightDownLinkedList(head, 19, 22, 50);
        Utilities.attachDownListToRightDownLinkedList(head, 28, 35, 40, 45);
        Utilities.printRightDownLinkedList(head);

        LinkedListQuests.flatten(head);

        System.out.println();
        Utilities.printRightDownLinkedList(head);
    }

    @Test
    void detectLoop() {
        LinkedListNode head1;
        boolean hasLoop;

        head1 = Utilities.createLinkedList(20, 4, 15, 10);
        hasLoop = LinkedListQuests.detectLoop(head1);
        assertEquals(false, hasLoop);

        head1 = Utilities.createLinkedList(20, 4, 15, 10, 11, 12);
        Utilities.addLoopToLinkedList(head1, 3);
        hasLoop = LinkedListQuests.detectLoop(head1);
        assertEquals(true, hasLoop);

        head1 = Utilities.createLinkedList(20, 4);
        Utilities.addLoopToLinkedList(head1, 0);
        hasLoop = LinkedListQuests.detectLoop(head1);
        assertEquals(true, hasLoop);

        head1 = Utilities.createLinkedList(20);
        Utilities.addLoopToLinkedList(head1, 0);
        hasLoop = LinkedListQuests.detectLoop(head1);
        assertEquals(true, hasLoop);

        head1 = Utilities.createLinkedList(20);
        hasLoop = LinkedListQuests.detectLoop(head1);
        assertEquals(false, hasLoop);
    }

    @Test
    void detectLoopHashing() {
        LinkedListNode head1;
        boolean hasLoop;

        head1 = Utilities.createLinkedList(20, 4, 15, 10);
        hasLoop = LinkedListQuests.detectLoopHashing(head1);
        assertEquals(false, hasLoop);

        head1 = Utilities.createLinkedList(20, 4, 15, 10, 11, 12);
        Utilities.addLoopToLinkedList(head1, 3);
        hasLoop = LinkedListQuests.detectLoopHashing(head1);
        assertEquals(true, hasLoop);

        head1 = Utilities.createLinkedList(20, 4);
        Utilities.addLoopToLinkedList(head1, 0);
        hasLoop = LinkedListQuests.detectLoopHashing(head1);
        assertEquals(true, hasLoop);

        head1 = Utilities.createLinkedList(20);
        Utilities.addLoopToLinkedList(head1, 0);
        hasLoop = LinkedListQuests.detectLoopHashing(head1);
        assertEquals(true, hasLoop);

        head1 = Utilities.createLinkedList(20);
        hasLoop = LinkedListQuests.detectLoopHashing(head1);
        assertEquals(false, hasLoop);
    }

    @Test
    void makeMiddleNodeHead() {
        LinkedListNode head;
        LinkedListNode newHead;

        head = Utilities.createLinkedList(1, 2, 3, 4, 5);
        Utilities.printLinkedList(head);
        newHead = LinkedListQuests.makeMiddleNodeHead(head);
        Utilities.printLinkedList(newHead);
        assertEquals(3, newHead.getData());

        head = null;
        Utilities.printLinkedList(head);
        newHead = LinkedListQuests.makeMiddleNodeHead(head);
        Utilities.printLinkedList(newHead);
        assertEquals(null, newHead);

        head = Utilities.createLinkedList(1);
        Utilities.printLinkedList(head);
        newHead = LinkedListQuests.makeMiddleNodeHead(head);
        Utilities.printLinkedList(newHead);
        assertEquals(1, newHead.getData());
    }

    @Test
    void removeDuplicatesFromSortedLinkedList() {
        LinkedListNode head;

        head = Utilities.createLinkedList(11, 11, 11, 13, 13, 20);
        Utilities.printLinkedList(head);
        LinkedListQuests.removeDuplicatesFromSortedLinkedList(head);
        Utilities.printLinkedList(head);

        head = Utilities.createLinkedList(11, 11, 11, 21, 43, 43, 60);
        Utilities.printLinkedList(head);
        LinkedListQuests.removeDuplicatesFromSortedLinkedList(head);
        Utilities.printLinkedList(head);
    }

    @Test
    void pairwiseSwap() {
        LinkedListNode head;

        head = Utilities.createLinkedList(1, 2, 3, 4, 5);
        Utilities.printLinkedList(head);
        LinkedListQuests.pairwiseSwap(head);
        Utilities.printLinkedList(head);

        head = Utilities.createLinkedList(1, 2, 3, 4, 5, 6);
        Utilities.printLinkedList(head);
        LinkedListQuests.pairwiseSwap(head);
        Utilities.printLinkedList(head);
    }

    @Test
    void swapNodesUsingLinks() {
        LinkedListNode head;
        LinkedListNode newHead;

        head = Utilities.createLinkedList(1, 2, 3, 4, 5, 6, 7);
        Utilities.printLinkedList(head);
        System.out.println("Swap 4 & 3");
        newHead = LinkedListQuests.swapNodesUsingLinks(head, 4, 3);
        Utilities.printLinkedList(newHead);
        System.out.println();

        head = Utilities.createLinkedList(10, 15, 12, 13, 20, 14);
        Utilities.printLinkedList(head);
        System.out.println("Swap 12 & 20");
        newHead = LinkedListQuests.swapNodesUsingLinks(head, 12, 20);
        Utilities.printLinkedList(newHead);
        System.out.println();

        head = Utilities.createLinkedList(10, 15, 12, 13, 20, 14);
        Utilities.printLinkedList(head);
        System.out.println("Swap 10  & 20");
        newHead = LinkedListQuests.swapNodesUsingLinks(head, 10, 20);
        Utilities.printLinkedList(newHead);
        System.out.println();

        head = Utilities.createLinkedList(10, 15, 12, 13, 20, 14);
        Utilities.printLinkedList(head);
        System.out.println("Swap 12 & 13");
        newHead = LinkedListQuests.swapNodesUsingLinks(head, 12, 13);
        Utilities.printLinkedList(newHead);
        System.out.println();
    }

    @Test
    void sortLinkedListOfZerosOnesAndTwosUsingLinks() {
        LinkedListNode head;
        LinkedListNode newHead;
        LinkedListNode expected;

        head = Utilities.createLinkedList(2, 1, 2, 1, 1, 2, 0, 1, 0);
        expected = Utilities.createLinkedList(0, 0, 1, 1, 1, 1, 2, 2, 2);
        Utilities.printLinkedList(head);
        newHead = LinkedListQuests.sortLinkedListOfZerosOnesAndTwosUsingLinks(head);
        Utilities.printLinkedList(newHead);
        assertLinkedListEquals(expected, newHead);
        System.out.println();

        head = Utilities.createLinkedList(1, 2, 0, 1);
        expected = Utilities.createLinkedList(0, 1, 1, 2);
        Utilities.printLinkedList(head);
        newHead = LinkedListQuests.sortLinkedListOfZerosOnesAndTwosUsingLinks(head);
        Utilities.printLinkedList(newHead);
        assertLinkedListEquals(expected, newHead);
        System.out.println();
    }

    @Test
    void deleteAlternateNodes() {
        LinkedListNode head;
        LinkedListNode newHead;
        LinkedListNode expected;

        head = Utilities.createLinkedList(1, 2, 3, 4, 5);
        expected = Utilities.createLinkedList(1, 3, 5);
        Utilities.printLinkedList(head);
        newHead = LinkedListQuests.deleteAlternateNodes(head);
        Utilities.printLinkedList(newHead);
        assertLinkedListEquals(expected, newHead);
        System.out.println();

        head = Utilities.createLinkedList(1, 2, 3, 4);
        expected = Utilities.createLinkedList(1, 3);
        Utilities.printLinkedList(head);
        newHead = LinkedListQuests.deleteAlternateNodes(head);
        Utilities.printLinkedList(newHead);
        assertLinkedListEquals(expected, newHead);
        System.out.println();

        head = Utilities.createLinkedList(1);
        expected = Utilities.createLinkedList(1);
        Utilities.printLinkedList(head);
        newHead = LinkedListQuests.deleteAlternateNodes(head);
        Utilities.printLinkedList(newHead);
        assertLinkedListEquals(expected, newHead);
        System.out.println();

        head = Utilities.createLinkedList(1, 2);
        expected = Utilities.createLinkedList(1);
        Utilities.printLinkedList(head);
        newHead = LinkedListQuests.deleteAlternateNodes(head);
        Utilities.printLinkedList(newHead);
        assertLinkedListEquals(expected, newHead);
        System.out.println();


        head = Utilities.createLinkedList(1, 2, 3);
        expected = Utilities.createLinkedList(1, 3);
        Utilities.printLinkedList(head);
        newHead = LinkedListQuests.deleteAlternateNodes(head);
        Utilities.printLinkedList(newHead);
        assertLinkedListEquals(expected, newHead);
        System.out.println();
    }

    @Test
    void insertIntoSortedCircularLinkedList() {
        LinkedListNode head;

        head = null;
        head = LinkedListQuests.insertIntoSortedCircularLinkedList(head, 11);
        head = LinkedListQuests.insertIntoSortedCircularLinkedList(head, 12);
        head = LinkedListQuests.insertIntoSortedCircularLinkedList(head, 90);
        head = LinkedListQuests.insertIntoSortedCircularLinkedList(head, 2);
        head = LinkedListQuests.insertIntoSortedCircularLinkedList(head, 1);
        head = LinkedListQuests.insertIntoSortedCircularLinkedList(head, 56);

        assertEquals(1, head.getData());
        assertEquals(2, head.getNext().getData());
        assertEquals(11, head.getNext().getNext().getData());
        assertEquals(12, head.getNext().getNext().getNext().getData());
        assertEquals(56, head.getNext().getNext().getNext().getNext().getData());
        assertEquals(90, head.getNext().getNext().getNext().getNext().getNext().getData());
        assertEquals(1, head.getNext().getNext().getNext().getNext().getNext().getNext().getData());
    }
    
    @Test
    void cloneDoublyLinkedListWithArbitraryPointer() {
        AuxiliaryLinkedListNode head;
        AuxiliaryLinkedListNode clone;

        head = Utilities.createAuxiliaryLinkedList(1, 2, 3, 4);
        head.setAuxiliary(head.getNext().getNext());
        head.getNext().setAuxiliary(head.getNext().getNext().getNext());
        head.getNext().getNext().setAuxiliary(head);
        head.getNext().getNext().getNext().setAuxiliary(head.getNext());
        clone = LinkedListQuests.cloneDoublyLinkedListWithArbitraryPointer(head);
        
        assertAuxiliaryLinkedListEquals(clone, head);
    }
}