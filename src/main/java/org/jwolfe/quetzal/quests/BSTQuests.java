package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.library.tree.BinaryTreeNode;

import java.util.Stack;

public class BSTQuests {
    static BinaryTreeNode first;
    static BinaryTreeNode middle;
    static BinaryTreeNode last;
    static BinaryTreeNode prev;

    public static void correctBSTForSwapped2Nodes(BinaryTreeNode root) {
        first = null;
        middle = null;
        last = null;
        prev = null;

        correctBSTForSwapped2NodesUtil(root);

        if(first != null && last != null) {
            var temp = first.getData();
            first.setData(last.getData());
            last.setData(temp);
        }
        else if(first != null) {
            var temp = first.getData();
            first.setData(middle.getData());
            middle.setData(temp);
        }
    }

    private static void correctBSTForSwapped2NodesUtil(BinaryTreeNode root) {
        if(root == null)
            return;

        correctBSTForSwapped2NodesUtil(root.getLeft());

        if(prev != null
                && prev.getData() > root.getData()) {
            if(first == null) {
                first = prev;
                middle = root;
            }
            else {
                last = root;
            }
        }

        prev = root;
        correctBSTForSwapped2NodesUtil(root.getRight());
    }

    public static boolean doesPreOrderRepresentBST1(int[] pre) {
        Stack<Integer> preOrderStack = new Stack<>();
        int lastInOrderElement = 0;

        for (int i = 0; i < pre.length; i++) {
            if (preOrderStack.isEmpty()
                    || preOrderStack.peek() > pre[i]) {
                preOrderStack.push(pre[i]);
            } else {
                while (!preOrderStack.isEmpty()
                        && preOrderStack.peek() < pre[i]) {
                    int element = preOrderStack.pop();

                    if (lastInOrderElement > element) {
                        return false;
                    }

                    lastInOrderElement = element;
                }

                preOrderStack.push(pre[i]);
            }
        }

        while (!preOrderStack.isEmpty()) {
            int element = preOrderStack.pop();

            if (lastInOrderElement > element) {
                return false;
            }

            lastInOrderElement = element;
        }

        return true;
    }

    public static boolean doesPreOrderRepresentBST2(int[] pre) {
        Stack<Integer> preOrderStack = new Stack<>();
        Stack<Integer> inOrderStack = new Stack<>();

        for (int i = 0; i < pre.length; i++) {
            if (preOrderStack.isEmpty()
                    || preOrderStack.peek() > pre[i]) {
                preOrderStack.push(pre[i]);
            } else {
                while (!preOrderStack.isEmpty()
                        && preOrderStack.peek() < pre[i]) {
                    int element = preOrderStack.pop();

                    if (!inOrderStack.isEmpty()
                            && inOrderStack.peek() > element) {
                        return false;
                    }

                    inOrderStack.push(element);
                }

                preOrderStack.push(pre[i]);
            }
        }

        while (!preOrderStack.isEmpty()) {
            int element = preOrderStack.pop();

            if (!inOrderStack.isEmpty()
                    && inOrderStack.peek() > element) {
                return false;
            }

            inOrderStack.push(element);
        }

        return true;
    }

    public static boolean doesPreOrderRepresentBST3(int[] pre) {
        return doesPreOrderRepresentBST3(pre, 0, pre.length - 1);
    }

    public static boolean doesPreOrderRepresentBST3(int[] pre, int start, int end) {
        if (start >= end) {
            return true;
        }

        // Find first element after start that is greater than start.
        int rightStart = start;
        while (pre[start] >= pre[rightStart]) {
            rightStart++;
        }

        for (int i = rightStart; i <= end; i++) {
            if (pre[start] >= pre[i]) {
                return false;
            }
        }

        return doesPreOrderRepresentBST3(pre, start + 1, rightStart - 1)
                && doesPreOrderRepresentBST3(pre, rightStart + 1, end);
    }

    public static boolean iterativeSearch(BinaryTreeNode root, int key) {
        while (root != null) {
            if (root.getData() == key) {
                return true;
            }

            if (root.getData() > key) {
                root = root.getLeft();
            } else {
                root = root.getRight();
            }
        }

        return false;
    }

    public static void transformToGreaterSumTree(BinaryTreeNode root) {
        transformToGreaterSumTree(root, 0);
    }

    private static int transformToGreaterSumTree(BinaryTreeNode node, int runningSum) {
        if(node == null) {
            return runningSum;
        }

        int sum = transformToGreaterSumTree(node.getRight(), runningSum);

        sum += node.getData();
        node.setData(sum -  node.getData());

        return transformToGreaterSumTree(node.getLeft(), sum);
    }

    public static int findFloor(BinaryTreeNode root, int key) {
        BinaryTreeNode current = root;
        BinaryTreeNode answer = null;

        while(current != null) {
            if(current.getData() <= key) {
                answer = current;
                current = current.getRight();
            }
            else {
                current = current.getLeft();
            }
        }

        if(answer != null) {
            return answer.getData();
        }

        return -1;
    }

    public static void removeOutsideRange(BinaryTreeNode root, int min, int max) {
        removeOutsideRangeRecursive(root, min, max);
    }

    private static BinaryTreeNode removeOutsideRangeRecursive(BinaryTreeNode node, int min, int max) {
        if(node == null) {
            return  null;
        }

        node.setLeft(removeOutsideRangeRecursive(node.getLeft(), min, max));
        node.setRight(removeOutsideRangeRecursive(node.getRight(), min, max));

        if(node.getData() < min) {
            return node.getRight();
        }
        else if(node.getData() > max) {
            return node.getLeft();
        }

        return node;
    }
}
