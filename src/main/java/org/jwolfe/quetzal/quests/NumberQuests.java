package org.jwolfe.quetzal.quests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberQuests {
    public static List<Integer> getPrimeNumbersSmallerThanEqualToMaxUsingSieveOfEratosthenes(int max) {
        List<Integer> primeNumbers = new ArrayList<>();

        boolean[] isPrime = new boolean[max + 1];
        Arrays.fill(isPrime, true);

        for(int p = 2; p * p <= max; p++) {
            if(isPrime[p]) {
                for (int i = p*p; i <= max; i+=p) {
                    isPrime[i] = false;
                }
            }
        }

        for (int i = 2; i <= max; i++) {
            if(isPrime[i]) {
                primeNumbers.add(i);
            }
        }

        return primeNumbers;
    }
}
