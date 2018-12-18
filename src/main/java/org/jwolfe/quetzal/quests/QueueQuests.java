package org.jwolfe.quetzal.quests;

import java.util.Queue;
import java.util.Stack;

public class QueueQuests {
    public static void interleaveFirstAndSecondHalves(Queue<Integer> queue) {
        if(queue == null
            || queue.size() == 0
            || queue.size() % 2 > 0) {
            return;
        }

        int halfSize = queue.size() / 2;
        Stack<Integer> stack = new Stack<>();

        // Q: H1 H2  S: <empty>
        // Q: H2     S: H1r
        for (int i = 0; i < halfSize; i++) {
            stack.push(queue.poll());
        }

        // Q: H2 H1r S: <empty>
        for (int i = 0; i < halfSize; i++) {
            queue.offer(stack.pop());
        }

        // Q: H1r H2 S: <empty>
        for (int i = 0; i < halfSize; i++) {
            queue.offer(queue.poll());
        }

        // Q: H2     S: H1
        for (int i = 0; i < halfSize; i++) {
            stack.push(queue.poll());
        }

        // Interleave
        for (int i = 0; i < halfSize; i++) {
            queue.offer(stack.pop());
            queue.offer(queue.poll());
        }

    }
}
