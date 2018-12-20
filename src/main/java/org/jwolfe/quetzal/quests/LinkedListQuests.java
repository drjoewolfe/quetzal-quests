package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.library.list.LinkedListNode;
import org.jwolfe.quetzal.library.list.RightDownLinkedListNode;

import java.util.HashSet;
import java.util.Set;

public class LinkedListQuests {
    public static LinkedListNode mergeSortedLinkedLists(LinkedListNode head1, LinkedListNode head2) {
        var dummy = new LinkedListNode(0);
        var tail = dummy;

        while(head1 != null || head2 != null) {
            if(head1 == null) {
                tail.setNext(head2);
                break;
            }

            if(head2 == null) {
                tail.setNext(head1);
                break;
            }

            if(head1.getData() < head2.getData()) {
                tail.setNext(head1);
                head1 = head1.getNext();
            }
            else {
                tail.setNext(head2);
                head2 = head2.getNext();
            }

            tail = tail.getNext();
        }

        return dummy.getNext();
    }

    public static LinkedListNode mergeSortedLinkedListsRecursive(LinkedListNode head1, LinkedListNode head2) {
        LinkedListNode result;

        if(head1 == null) {
            return  head2;
        }

        if(head2 == null) {
            return  head1;
        }

        if(head1.getData() < head2.getData()) {
            result = head1;
            result.setNext( mergeSortedLinkedListsRecursive(head1.getNext(), head2));
        }
        else {
            result = head2;
            result.setNext( mergeSortedLinkedListsRecursive(head1, head2.getNext()));
        }

        return  result;
    }

    public static LinkedListNode reverseLinkedList(LinkedListNode head) {
        LinkedListNode prev = null;
        LinkedListNode curr = head;
        LinkedListNode next = null;

        while(curr != null) {
            next = curr.getNext();
            curr.setNext(prev);

            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static LinkedListNode deleteMiddle(LinkedListNode head) {
        if (head == null)
            return null;

        if (head.getNext() == null)
            return null;

        LinkedListNode slow = head;
        LinkedListNode fast = head;
        LinkedListNode prev = null;

        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            prev = slow;
            slow = slow.getNext();
        }

        prev.setNext(slow.getNext());

        return head;
    }

    public static void deleteNodeFromLinkedList(LinkedListNode node) throws Exception {
        var nextNode = node.getNext();

        if(nextNode == null){
            throw new Exception("Last node cannot be deleted from its reference.");
        }

        node.setData(nextNode.getData());
        node.setNext(nextNode.getNext());
    }

    public static int getLengthOfLoop(LinkedListNode node){
        LinkedListNode fast = node;
        LinkedListNode slow = node;

        boolean loopFound = false;
        while(fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();

            if(fast == slow) {
                loopFound = true;
                break;
            }
        }

        if(loopFound) {
            System.out.println("Loop found in linked list.");

            LinkedListNode runner = slow;
            int length = 1;
            while(runner.getNext() != slow) {
                runner = runner.getNext();
                length++;
            }

            return length;
        }
        else {
            System.out.println("The linked list has no loops.");
        }

        return 0;
    }

    public static LinkedListNode addOneToNumberAsLinkedList(LinkedListNode head) {
        // Recursive implementation also viable. Recursive does not require reveral of the linked lists as in this implementation

        if(head == null) {
            return null;
        }

        head = reverseLinkedList(head);

        int sum;
        int carry = 1; // Starting with 1, as it is add one.
        LinkedListNode runner = head;
        LinkedListNode temp = null;

        while(runner != null) {
            sum = carry + runner.getData();

            carry = (sum >= 10) ? 1 : 0;
            sum %= 10;

            runner.setData(sum);

            temp = runner;
            runner = runner.getNext();
        }

        if(carry > 0) {
            temp.setNext(new LinkedListNode(carry));
        }

        head = reverseLinkedList(head);
        return head;
    }

    public static LinkedListNode skipMDeleteN(LinkedListNode head, int m, int n) {
        LinkedListNode runner = head;
        int counter;
        while (runner != null) {
            // Skip m
            for (counter = 1; counter < m && runner != null; counter++) {
                runner = runner.getNext();
            }

            if(runner == null) {
                // Finished while skipping m
                return head;
            }

            // Delete n
            var node = runner;
            for(counter = 0; counter < n && runner != null; counter++) {
                runner = runner.getNext();
            }

            if(runner == null) {
                // Exhausted before reaching n
                node.setNext(null);
                break;
            }
            else {
                node.setNext(runner.getNext());
            }

            runner = runner.getNext();
        }

        return head;
    }

    public static boolean detectAndRemoveLoop(LinkedListNode head) {
        // 4 ways to remove loop after detection
        //  1. Count 1-by-1
        //  2. Re-traverse from head & loop length positions
        //  3. Re-traverse from head & meeting point
        //  4. Hashing
        //
        // This implementation is based on (2)

        LinkedListNode fast = head;
        LinkedListNode slow = head;
        boolean loopFound = false;

        while(fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();

            if(fast == slow) {
                // Loop found.
                loopFound = true;
                removeLoop(head, slow);
            }
        }

        return loopFound;
    }

    private static void removeLoop(LinkedListNode head, LinkedListNode loopNode) {
        // Find length of loop
        int n = 1;
        LinkedListNode runner1 = loopNode;
        LinkedListNode runner2 = null;
        while(runner1.getNext() != loopNode) {
            runner1 = runner1.getNext();
            n++;
        }

        // Traverse from start & start + n
        runner1 = head;
        runner2 = head;
        for(int i=0; i<n; i++) {
            runner1 = runner1.getNext();
        }

        while(runner1.getNext() != runner2.getNext()) {
            runner1 = runner1.getNext();
            runner2 = runner2.getNext();
        }

        runner1.setNext(null);
    }

    public static boolean detectLoop(LinkedListNode head) {
        if(head == null) {
            return false;
        }

        LinkedListNode fast = head;
        LinkedListNode slow = head;

        while(fast.getNext() != null
                && fast.getNext().getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();

            if(fast == slow) {
                return true;
            }
        }

        return false;
    }

    public static boolean detectLoopHashing(LinkedListNode head) {
        Set set = new HashSet<LinkedListNode>();
        boolean hasLoop = false;
        while(head != null) {
            if(set.contains(head)) {
                hasLoop = true;
                break;
            }

            set.add(head);
            head = head.getNext();
        }

        return hasLoop;
    }

    public static LinkedListNode getMiddle(LinkedListNode head) {
        // This can also be done using a counter. Have a mid pointer, which is set to next only when the counter is at an odd value.

        LinkedListNode fast = head;
        LinkedListNode slow = head;

        while(fast != null
                && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }

        return slow;
    }

    public static LinkedListNode rotateCounterClockwise(LinkedListNode head, int k) {
        // Get kth, k+1 & last nodes; Then adjust pointers
        if(k==0) {
            return head;
        }

        LinkedListNode runner = head;
        int counter = 1;
        while(counter < k
                && runner != null) {
            counter++;
            runner = runner.getNext();
        }

        if(runner == null) {
            // => k is greater than the length of the linked list
            return head;
        }

        LinkedListNode nodeK = runner;
        LinkedListNode nodeKPlus = runner.getNext();
        while(runner.getNext() != null) {
            runner = runner.getNext();
        }

        LinkedListNode last = runner;

        last.setNext(head);
        nodeK.setNext(null);
        return nodeKPlus;
    }

    public static LinkedListNode push(LinkedListNode head, int value) {
        LinkedListNode node = new LinkedListNode(value);
        node.setNext(head);
        head = node;

        return head;
    }

    public static LinkedListNode rearrangeOddEvenNodesTogether(LinkedListNode head) {
        if(head == null) {
            return null;
        }

        LinkedListNode even = head;
        LinkedListNode odd = head.getNext();

        LinkedListNode first = head;
        LinkedListNode oddFirst = odd;

        while(true) {
            if(odd == null
                    || odd.getNext() == null) {
                even.setNext(oddFirst);
                break;
            }

            even.setNext(odd.getNext());
            even = even.getNext();

            odd.setNext(even.getNext());
            odd = odd.getNext();
        }

        return first;
    }

    public static void printReverse(LinkedListNode head) {
        if(head == null) {
            return;
        }

        printReverse(head.getNext());
        System.out.print(head.getData() + " ");
    }

    public static LinkedListNode addLists(LinkedListNode head1, LinkedListNode head2) {
        int sum;
        int carry = 0;

        LinkedListNode sumHead = new LinkedListNode(0);
        LinkedListNode sumNode = sumHead;

        while(head1 != null || head2 != null) {
            sum = carry;
            if(head1 != null) {
                sum += head1.getData();
                head1 = head1.getNext();
            }

            if(head2 != null) {
                sum += head2.getData();
                head2 = head2.getNext();
            }

            carry = sum / 10;
            sum = sum % 10;

            LinkedListNode node = new LinkedListNode(sum);
            sumNode.setNext(node);
            sumNode = sumNode.getNext();
        }

        if(carry > 0) {
            LinkedListNode node = new LinkedListNode(carry);
            sumNode.setNext(node);
        }

        return sumHead.getNext();
    }

    public static RightDownLinkedListNode flatten(RightDownLinkedListNode head) {
        if(head == null
                || head.getNext() == null) {
            return head;
        }

        var right = flatten(head.getNext());
        head = merge(head, right);
        head.setNext(null);

        return head;
    }

    private static RightDownLinkedListNode merge(RightDownLinkedListNode head1, RightDownLinkedListNode head2) {
        if(head1 == null) {
            return head2;
        }

        if(head2 == null) {
            return head1;
        }

        RightDownLinkedListNode mergedHead;
        if(head1.getData() < head2.getData()) {
            mergedHead = head1;
            mergedHead.setDown(merge(head1.getDown(), head2));
        }
        else {
            mergedHead = head2;
            mergedHead.setDown(merge(head1, head2.getDown()));
        }

        return mergedHead;
    }

    public static LinkedListNode makeMiddleNodeHead(LinkedListNode head) {
        if(head == null) {
            return null;
        }

        LinkedListNode fast = head;
        LinkedListNode slow = head;
        LinkedListNode previous = null;

        while(fast.getNext() != null
                && fast.getNext().getNext() != null) {
            previous = slow;
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }

        if(previous != null) {
            previous.setNext(slow.getNext());
            slow.setNext(head);
        }

        head = slow;

        return head;
    }

    public static void removeDuplicatesFromSortedLinkedList(LinkedListNode head) {
        if(head == null) {
            return;
        }

        LinkedListNode current = head;

        while(current.getNext() != null) {
            if(current.getData() == current.getNext().getData()) {
                current.setNext(current.getNext().getNext());
            }
            else {
                current = current.getNext();
            }
        }
    }

    public static void pairwiseSwap(LinkedListNode head) {
        LinkedListNode current = head;

        while(current != null
                && current.getNext() != null) {
            swapData(current, current.getNext());
            current = current.getNext().getNext();
        }
    }

    private static void swapData(LinkedListNode node1, LinkedListNode node2) {
        int temp = node1.getData();
        node1.setData(node2.getData());
        node2.setData(temp);
    }

    public static LinkedListNode swapNodesUsingLinks(LinkedListNode head, int x, int y) {
        if(x == y) {
            return head;
        }

        LinkedListNode currentX = head;
        LinkedListNode previousX = null;

        while(currentX != null
                && currentX.getData() != x) {
            previousX = currentX;
            currentX = currentX.getNext();
        }

        LinkedListNode currentY = head;
        LinkedListNode previousY = null;

        while(currentY != null
                && currentY.getData() != y) {
            previousY = currentY;
            currentY = currentY.getNext();
        }

        if(currentX == null
                || currentY == null) {
            return head;
        }

        if(previousX != null) {
            previousX.setNext(currentY);
        }
        else {
            head = currentY;
        }

        if(previousY != null) {
            previousY.setNext(currentX);
        }
        else {
            head = currentX;
        }

        var temp = currentX.getNext();
        currentX.setNext(currentY.getNext());
        currentY.setNext(temp);

        return head;
    }

    public static LinkedListNode sortLinkedListOfZerosOnesAndTwosUsingLinks(LinkedListNode head) {
        if(head == null) {
            return null;
        }

        LinkedListNode zeroD = new LinkedListNode(0);
        LinkedListNode oneD = new LinkedListNode(0);
        LinkedListNode twoD = new LinkedListNode(0);

        LinkedListNode zeroN = zeroD;
        LinkedListNode oneN = oneD;
        LinkedListNode twoN = twoD;

        LinkedListNode current = head;
        while(current != null) {
            if(current.getData() == 0) {
                zeroN.setNext(current);
                zeroN = zeroN.getNext();
            }
            else if(current.getData() == 1) {
                oneN.setNext(current);
                oneN = oneN.getNext();
            }
            else {
                twoN.setNext(current);
                twoN = twoN.getNext();
            }

            current = current.getNext();
        }

        zeroN.setNext( oneD.getNext() != null ? oneD.getNext() : twoD.getNext() );
        oneN.setNext(twoD.getNext());
        twoN.setNext(null);

        return zeroD.getNext();
    }

    public static LinkedListNode deleteAlternateNodes(LinkedListNode head) {
        if(head == null) {
            return null;
        }

        LinkedListNode previous = head;
        LinkedListNode current = head.getNext();

        while(previous != null
                && current != null) {
            previous.setNext(current.getNext());

            previous = previous.getNext();
            if(previous != null) {
                current = previous.getNext();
            }
        }

        return head;
    }

    public static LinkedListNode insertIntoSortedCircularLinkedList(LinkedListNode head, int value) {
        LinkedListNode node = new LinkedListNode(value);

        // Case 1: Head is null
        if(head == null) {
            node.setNext(node);
            return node;
        }

        // Case 2: Node is to be inserted before Head
        if(head.getData() > value) {
            var lastElement = head;
            while (lastElement.getNext() != head) {
                lastElement = lastElement.getNext();
            }

            lastElement.setNext(node);
            node.setNext(head);
            return node;
        }

        // Case 3: Node is to be inserted after head
        var current = head;
        while (current.getNext() != head
                && current.getNext().getData() < value) {
            current = current.getNext();
        }

        node.setNext(current.getNext());
        current.setNext(node);

        return head;
    }
}
