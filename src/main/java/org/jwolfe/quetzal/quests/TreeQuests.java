package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.library.tree.BinaryTreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class TreeQuests {
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
