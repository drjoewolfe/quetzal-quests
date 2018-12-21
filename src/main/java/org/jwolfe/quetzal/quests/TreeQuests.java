package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.algorithms.TreeAlgorithms;
import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.tree.ConnectableBinaryTreeNode;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

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

    public static void sinkOddNodes(BinaryTreeNode root) {
        if(root == null) {
            return;
        }

        sinkOddNodes(root.getLeft());
        sinkOddNodes(root.getRight());

        if(root.getData() % 2 != 0) {
            // Odd node
            sinkNode(root);
        }
    }

    private static void sinkNode(BinaryTreeNode node) {
        if(node == null) {
            return;
        }

        if(TreeAlgorithms.isLeaf(node)) {
            return;
        }

        if(node.getData() % 2 == 0) {
            return;
        }

        if(node.getLeft() != null
            && node.getLeft().getData() % 2 == 0) {
            int temp = node.getData();
            node.setData(node.getLeft().getData());
            node.getLeft().setData(temp);

            sinkNode(node.getLeft());

        }
        else if(node.getRight() != null
                && node.getRight().getData() % 2 == 0) {
            int temp = node.getData();
            node.setData(node.getRight().getData());
            node.getRight().setData(temp);

            sinkNode(node.getRight());
        }
    }

    public static int maxPathSumBetweenTwoLeaves(BinaryTreeNode root) {
        AtomicInteger maxSum = new AtomicInteger(Integer.MIN_VALUE);
        maxPathSumBetweenTwoLeaves(root, maxSum);
        return maxSum.intValue();
    }

    private static int maxPathSumBetweenTwoLeaves(BinaryTreeNode root, AtomicInteger maxSum) {
        if(root == null) {
            return 0;
        }

        int maxLeftOnePathSum = maxPathSumBetweenTwoLeaves(root.getLeft(), maxSum);
        int maxRightOnePathSum = maxPathSumBetweenTwoLeaves(root.getRight(), maxSum);
        int nodeData = root.getData();

        int maxOnePathSum = Math.max(maxLeftOnePathSum, maxRightOnePathSum) + nodeData;
        int maxTwoPathSum = maxLeftOnePathSum + nodeData + maxRightOnePathSum;

        int maxPathSum = Math.max(maxOnePathSum, maxTwoPathSum);
        maxSum.set(Math.max(maxSum.intValue(), maxPathSum));

        return maxOnePathSum;
    }

    public static void alternateLevelOrderTraversalForPerfectTree(BinaryTreeNode root) {
        alternateLevelOrderTraversalForPerfectTree(root, n -> System.out.println(n.getData() + " "));
    }

    public static void alternateLevelOrderTraversalForPerfectTree(BinaryTreeNode root, Consumer<BinaryTreeNode> visit) {
        if(root == null) {
            return;
        }

        if(!TreeAlgorithms.isPerfect(root)) {
            return;
        }

        if(visit != null) {
            visit.accept(root);
        }

        if(root.getLeft() == null) {
            return;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root.getLeft());
        queue.offer(root.getRight());

        while(!queue.isEmpty()) {
            var left = queue.poll();
            var right = queue.poll();

            if(visit != null) {
                visit.accept(left);
                visit.accept(right);
            }

            if(left.getLeft() != null) {
                queue.offer(left.getLeft());
                queue.offer(right.getRight());
                queue.offer(left.getRight());
                queue.offer(right.getLeft());
            }
        }
    }

    public static void alternateBottomsUpLevelOrderTraversalForPerfectTree(BinaryTreeNode root) {
        alternateBottomsUpLevelOrderTraversalForPerfectTree(root, n -> System.out.println(n.getData() + " "));
    }

    public static void alternateBottomsUpLevelOrderTraversalForPerfectTree(BinaryTreeNode root, Consumer<BinaryTreeNode> visit) {
        if(root == null) {
            return;
        }

        if(!TreeAlgorithms.isPerfect(root)) {
            return;
        }


        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);


        if(root.getLeft() != null) {
            stack.push(root.getRight());
            stack.push(root.getLeft());

            Queue<BinaryTreeNode> queue = new LinkedList<>();
            queue.offer(root.getLeft());
            queue.offer(root.getRight());

            while (!queue.isEmpty()) {
                var left = queue.poll();
                var right = queue.poll();

                if (left.getLeft() != null) {
                    queue.offer(left.getRight());
                    queue.offer(right.getLeft());
                    queue.offer(left.getLeft());
                    queue.offer(right.getRight());

                    stack.push(right.getLeft());
                    stack.push(left.getRight());
                    stack.push(right.getRight());
                    stack.push(left.getLeft());
                }
            }
        }

        while(!stack.isEmpty()) {
            var node = stack.pop();
            if(visit != null) {
                visit.accept(node);
            }
        }
    }

    public static List<Integer> getExtremeNodesInAlternateOrder(BinaryTreeNode root) {
        List<Integer> alternateExtremeNodes = new ArrayList<>();

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean rightFlag = true;

        while(!queue.isEmpty()) {
            var n = queue.size();
            for (int i = 0; i < n; i++) {
                var node = queue.poll();

                if(node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }
                if(node.getRight() != null) {
                    queue.offer(node.getRight());
                }

                if((!rightFlag && i == 0)
                    || (rightFlag && i == n-1)) {
                    alternateExtremeNodes.add(node.getData());
                }
            }

            rightFlag = !rightFlag;
        }

        return alternateExtremeNodes;
    }
    
	public static BinaryTreeNode constructSpecialBinaryTreeFromInOrder(int[] inOrder) {
		// In-order traversal represents a Binary Tree where every node is greater than its children
		
		if(inOrder == null) {
			return null;
		}
		
		return constructSpecialBinaryTreeFromInOrder(inOrder, 0, inOrder.length - 1);
	}
	
	public static BinaryTreeNode constructSpecialBinaryTreeFromInOrder(int[] inOrder, int start, int end) {
		if(start > end) {
			return null;
		}
		
		int maxIndex = Utilities.maxIndex(inOrder, start, end);
		BinaryTreeNode node = new BinaryTreeNode(inOrder[maxIndex]);
		node.setLeft(constructSpecialBinaryTreeFromInOrder(inOrder, start, maxIndex - 1));
		node.setRight(constructSpecialBinaryTreeFromInOrder(inOrder, maxIndex + 1, end));
		
		return node;
	}
	
	public static void connectNodesAtSameLevelRecursive(ConnectableBinaryTreeNode<Integer> root) {
		if(root == null) {
			return;
		}
		
		root.setConnection(null);
		connectNodesAtSameLevelRecursiveHelper(root);
	}
	
	private static void connectNodesAtSameLevelRecursiveHelper(ConnectableBinaryTreeNode<Integer> node) {
		if(node == null) {
			return;
		}
		
		if(node.getConnection() != null) {
			connectNodesAtSameLevelRecursiveHelper(node.getConnection());
		}
		
		if(node.getLeft() != null) {
			if(node.getRight() != null) {
				node.getLeft().setConnection(node.getRight());
				node.getRight().setConnection(getNextLevelRightConnectable(node));
			}
			else {
				node.getLeft().setConnection(getNextLevelRightConnectable(node));
			}
			
			connectNodesAtSameLevelRecursiveHelper(node.getLeft());
		}
		else if(node.getRight() != null) {
			node.getRight().setConnection(getNextLevelRightConnectable(node));
			
			connectNodesAtSameLevelRecursiveHelper(node.getRight());
		}
		else {
			connectNodesAtSameLevelRecursiveHelper( getNextLevelRightConnectable(node));
		}
	}

	private static ConnectableBinaryTreeNode<Integer> getNextLevelRightConnectable(ConnectableBinaryTreeNode<Integer> node) {
		var current = node.getConnection();
		while(current != null) {
			if(current.getLeft() != null) {
				return current.getLeft();
			}
			
			if(current.getRight() != null) {
				return current.getRight();
			}
			current = current.getConnection();
		}
		
		return null;
	}

	public static void connectNodesAtSameLevelIterative(ConnectableBinaryTreeNode<Integer> root) {
		if(root == null) {
			return;
		}
		
		Queue<ConnectableBinaryTreeNode<Integer>> queue = new LinkedList<>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			int n = queue.size();
			for (int i = 0; i < n; i++) {
				var node = queue.poll();
				
				// Connect
				if(i != n-1) {
					node.setConnection(queue.peek());
				}
				else {
					node.setConnection(null);
				}
				
				if(node.getLeft() != null) {
					queue.offer(node.getLeft());
				}
				
				if(node.getRight() != null) {
					queue.offer(node.getRight());
				}
			}
		}
	}
}
