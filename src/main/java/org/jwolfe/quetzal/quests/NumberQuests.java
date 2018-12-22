package org.jwolfe.quetzal.quests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumberQuests {
	public static List<Integer> getPrimeNumbersSmallerThanEqualToMaxUsingSieveOfEratosthenes(int max) {
		List<Integer> primeNumbers = new ArrayList<>();

		boolean[] isPrime = new boolean[max + 1];
		Arrays.fill(isPrime, true);

		for (int p = 2; p * p <= max; p++) {
			if (isPrime[p]) {
				for (int i = p * p; i <= max; i += p) {
					isPrime[i] = false;
				}
			}
		}

		for (int i = 2; i <= max; i++) {
			if (isPrime[i]) {
				primeNumbers.add(i);
			}
		}

		return primeNumbers;
	}

	public static int minStepsRequiredToFindNumberInNumberLineForNStepsPerIteration(int number) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);

		int iteration = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Integer n = queue.poll();
				if (n == number) {
					return iteration - 1;
				}

				queue.offer(n + iteration);
				queue.offer(n - iteration);
			}

			iteration++;
		}

		return -1;
	}
}
