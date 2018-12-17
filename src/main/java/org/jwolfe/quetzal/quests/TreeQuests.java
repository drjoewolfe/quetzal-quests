package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.library.tree.BinaryTreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class TreeQuests {
    private static BinaryTreeNode previous;
    private static BinaryTreeNode head;

    public static BinaryTreeNode convertTreeToDoublyLinkedList(BinaryTreeNode root) {
        if(root == null) {
            return null;
        }

        head = null;
        convertNodeToDoublyLinkedList(root);
        return head;
    }

    public static void convertNodeToDoublyLinkedList(BinaryTreeNode root) {
        if(root == null) {
            return;
        }

        convertNodeToDoublyLinkedList(root.getRight());

        root.setRight(head);
        if(head != null) {
            head.setLeft(root);
        }

        head = root;
        convertNodeToDoublyLinkedList(root.getLeft());
    }

    public static BinaryTreeNode convertTreeToDoublyLinkedListA3(BinaryTreeNode root) {
        if(root == null) {
            return null;
        }

        head = null;
        previous = null;
        convertNodeToDoublyLinkedListA3(root);
        return head;
    }

    private static void convertNodeToDoublyLinkedListA3(BinaryTreeNode root) {
        if(root == null) {
            return;
        }

        convertNodeToDoublyLinkedListA3(root.getLeft());

        if(previous == null) {
            head = root;
        }
        else {
            previous.setRight(root);
            root.setLeft(previous);
        }

        previous = root;

        convertNodeToDoublyLinkedListA3(root.getRight());
    }

    public static BinaryTreeNode convertTreeToDoublyLinkedListA2(BinaryTreeNode root) {
        if(root == null) {
            return null;
        }

        previous = null;
        fixPreviousPointer(root);
        return fixNextPointer(root);
    }

    private static void fixPreviousPointer(BinaryTreeNode root) {
        if(root == null) {
            return;
        }

        fixPreviousPointer(root.getLeft());

        if(previous != null) {
            root.setLeft(previous);
        }

        previous = root;

        fixPreviousPointer(root.getRight());
    }

    private static BinaryTreeNode fixNextPointer(BinaryTreeNode root) {
        if(root == null) {
            return null;
        }

        while(root.getRight() != null) {
            root = root.getRight();
        }

        while(root.getLeft() != null) {
            var left = root.getLeft();
            left.setRight(root);
            root = left;
        }

        return root;
    }

    public static BinaryTreeNode convertTreeToDoublyLinkedListA1(BinaryTreeNode root) {
        var head = convertNodeToDoublyLinkedListA1(root);
        while(head.getLeft() != null) {
            head = head.getLeft();
        }

        return head;
    }

    private static BinaryTreeNode convertNodeToDoublyLinkedListA1(BinaryTreeNode root) {
        if(root == null) {
            return null;
        }

        if(root.getLeft() != null) {
            var inOrderPredecessor = convertTreeToDoublyLinkedListA1(root.getLeft());

            while(inOrderPredecessor.getRight() != null) {
                inOrderPredecessor = inOrderPredecessor.getRight();
            }

            inOrderPredecessor.setRight(root);
            root.setLeft(inOrderPredecessor);
        }

        if(root.getRight() != null) {
            var inOrderSuccessor = convertTreeToDoublyLinkedListA1(root.getRight());

            while(inOrderSuccessor.getLeft() != null) {
                inOrderSuccessor = inOrderSuccessor.getLeft();
            }

            inOrderSuccessor.setLeft(root);
            root.setRight(inOrderSuccessor);
        }

        return root;
    }

    public static BinaryTreeNode convertTreeToDoublyLinkedListInSpiralFashion(BinaryTreeNode root) {
        if(root == null) {
            return null;
        }

        Deque<BinaryTreeNode> deque = new ArrayDeque<>();
        deque.addFirst(root);

        Stack<BinaryTreeNode> stack = new Stack<>();
        int level = 0;

        while(!deque.isEmpty()) {
            int n = deque.size();
            if (level % 2 == 1) {
                // Odd level
                for (int i = 0; i < n; i++) {
                    var node = deque.pollFirst();
                    stack.add(node);

                    if (node.getLeft() != null) {
                        deque.addLast(node.getLeft());
                    }

                    if (node.getRight() != null) {
                        deque.addLast(node.getRight());
                    }
                }
            } else {
                // Even Level
                for (int i = 0; i < n; i++) {
                    var node = deque.pollLast();
                    stack.add(node);

                    if (node.getRight() != null) {
                        deque.addFirst(node.getRight());
                    }

                    if (node.getLeft() != null) {
                        deque.addFirst(node.getLeft());
                    }
                }
            }

            level++;
        }

        BinaryTreeNode head = null;
        while(!stack.isEmpty()) {
            var node = stack.pop();

            node.setRight(head);
            node.setLeft(null);

            if(head != null) {
                head.setLeft(node);
            }

            head = node;
        }

        return head;
    }
}
