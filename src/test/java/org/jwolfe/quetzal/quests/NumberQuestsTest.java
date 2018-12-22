package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumberQuestsTest {

	@Test
	void getPrimeNumbersSmallerThanEqualToMaxUsingSieveOfEratosthenes() {
		List<Integer> primeNumbers;
		int[] expectedPrimeNumbers;

		primeNumbers = NumberQuests.getPrimeNumbersSmallerThanEqualToMaxUsingSieveOfEratosthenes(10);
		expectedPrimeNumbers = Utilities.constructArray(2, 3, 5, 7);
		assertArrayEquals(expectedPrimeNumbers, primeNumbers.stream().mapToInt(i -> i).toArray());

		primeNumbers = NumberQuests.getPrimeNumbersSmallerThanEqualToMaxUsingSieveOfEratosthenes(20);
		expectedPrimeNumbers = Utilities.constructArray(2, 3, 5, 7, 11, 13, 17, 19);
		assertArrayEquals(expectedPrimeNumbers, primeNumbers.stream().mapToInt(i -> i).toArray());

		primeNumbers = NumberQuests.getPrimeNumbersSmallerThanEqualToMaxUsingSieveOfEratosthenes(30);
		expectedPrimeNumbers = Utilities.constructArray(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		assertArrayEquals(expectedPrimeNumbers, primeNumbers.stream().mapToInt(i -> i).toArray());
	}

	@Test
	void minStepsRequiredToFindNumberInNumberLineForNStepsPerIteration() {
		assertEquals(4, NumberQuests.minStepsRequiredToFindNumberInNumberLineForNStepsPerIteration(10));
		assertEquals(5, NumberQuests.minStepsRequiredToFindNumberInNumberLineForNStepsPerIteration(13));
		assertEquals(7, NumberQuests.minStepsRequiredToFindNumberInNumberLineForNStepsPerIteration(20));
	}
}