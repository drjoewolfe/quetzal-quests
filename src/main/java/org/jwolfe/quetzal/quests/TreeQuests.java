package org.jwolfe.quetzal.quests;

import jdk.nashorn.api.tree.Tree;
import org.jwolfe.quetzal.algorithms.TreeAlgorithms;
import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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

    public static int getDepthOfDeepestOddLevelLeafNode(BinaryTreeNode root) {
        return getDepthOfDeepestOddLevelLeafNode(root, 1);
    }

    private static int getDepthOfDeepestOddLevelLeafNode(BinaryTreeNode root, int level) {
        if (root == null) {
            return 0;
        }

        if (TreeAlgorithms.isLeaf(root)) {
            if (level % 2 != 0) {
                // Odd level
                return level;
            }

            return 0;
        }

        return Math.max(
                getDepthOfDeepestOddLevelLeafNode(root.getLeft(), level + 1),
                getDepthOfDeepestOddLevelLeafNode(root.getRight(), level + 1));
    }

    public static int getDepthOfDeepestOddLevelLeafNodeIterative(BinaryTreeNode root) {
        if(root == null) {
            return 0;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        int maxDepth = 0;

        while(!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                var node = queue.poll();

                if (TreeAlgorithms.isLeaf(node)
                        && level % 2 != 0) {
                    maxDepth = Math.max(maxDepth, level);
                }

                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }

                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }
            }

            level++;
        }

        return maxDepth;
    }

    public static List<List<Integer>> getLevelOrderLines(BinaryTreeNode root) {
        List<List<Integer>> levelOrderLines = new LinkedList<>();

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);

        LinkedList<Integer> line = new LinkedList<>();
        levelOrderLines.add(line);

        while(!queue.isEmpty()) {
            var node = queue.poll();

            if(node == null) {
                if(!queue.isEmpty()) {
                    line = new LinkedList<>();
                    levelOrderLines.add(line);
                    queue.offer(null);
                }

                continue;
            }

            line.add(node.getData());

            if(node.getLeft() != null) {
                queue.offer(node.getLeft());
            }

            if(node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }

        return levelOrderLines;
    }

    public static int maxPathSum(BinaryTreeNode root) {
        AtomicInteger maxSum = new AtomicInteger(Integer.MIN_VALUE);
        maxPathSum(root, maxSum);
        return maxSum.intValue();
    }

    private static int maxPathSum(BinaryTreeNode root, AtomicInteger maxSum) {
        if(root == null) {
            return 0;
        }

        int maxLeftOnePathSum = maxPathSum(root.getLeft(), maxSum);
        int maxRightOnePathSum = maxPathSum(root.getRight(), maxSum);
        int nodeData = root.getData();

        int maxOnePathSum = Math.max(
                                Math.max(maxLeftOnePathSum, maxRightOnePathSum) + nodeData,
                                nodeData);
        int maxTwoPathSum = maxLeftOnePathSum + nodeData + maxRightOnePathSum;

        int maxPathSum = Math.max(maxOnePathSum, maxTwoPathSum);
        maxSum.set(Math.max(maxSum.intValue(), maxPathSum));
        return maxOnePathSum;
    }
}
