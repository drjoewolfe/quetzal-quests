package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
import static org.jwolfe.quetzal.test.QuetzalAssertions.*;

class QueueQuestsTest {

    @Test
    void interleaveFirstAndSecondHalves() {
        Queue<Integer> queue;
        Queue<Integer> expectedQueue;

        queue = Utilities.constructQueue(11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        expectedQueue = Utilities.constructQueue(11, 16, 12, 17, 13, 18, 14, 19, 15, 20);
        QueueQuests.interleaveFirstAndSecondHalves(queue);
        assertQueueEquals(expectedQueue, queue);
    }
}