package org.jwolfe.quetzal.quests;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.general.Point;
import org.jwolfe.quetzal.library.general.Triplet;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQuestsTest {

    @Test
    void findTripletsWithZeroSumBrute() {
        //        Input : arr[] = {0, -1, 2, -3, 1}
        //        Output : 0 -1 1
        //        2 -3 1
        //
        //        Input : arr[] = {1, -2, 1, 0, 5}
        //        Output : 1 -2  1

        int[] array1 = {0, -1, 2, -3, 1};
        ArrayQuests.findTripletsWithZeroSumBrute(array1);

        int[] array2 = {1, -2, 1, 0, 5};
        ArrayQuests.findTripletsWithZeroSumBrute(array2);
    }

    @Test
    void findTripletsWithZeroSumHashing() {
        //        Input : arr[] = {0, -1, 2, -3, 1}
        //        Output : 0 -1 1
        //        2 -3 1
        //
        //        Input : arr[] = {1, -2, 1, 0, 5}
        //        Output : 1 -2  1

        int[] array1 = {0, -1, 2, -3, 1};
        ArrayQuests.findTripletsWithZeroSumHashing(array1);

        int[] array2 = {1, -2, 1, 0, 5};
        ArrayQuests.findTripletsWithZeroSumHashing(array2);
    }

    @Test
    void findTripletsWithZeroSumSorting() {
        //        Input : arr[] = {0, -1, 2, -3, 1}
        //        Output : 0 -1 1
        //        2 -3 1
        //
        //        Input : arr[] = {1, -2, 1, 0, 5}
        //        Output : 1 -2  1

        int[] array1 = {0, -1, 2, -3, 1};
        ArrayQuests.findTripletsWithZeroSumSorting(array1);

//        int[] array2 = {1, -2, 1, 0, 5};
//        ArrayQuests.findTripletsWithZeroSumSorting(array2);
    }

    @Test
    void findTripletsWithZeroSum() {
        findTripletsWithZeroSumBrute();
        findTripletsWithZeroSumHashing();
        findTripletsWithZeroSumSorting();
    }

    @Test
    void rotateArray() {
        int[] arr;
        int rotationCount;
        int[] expectedArray;

        arr = Utilities.constructArray(1, 2, 3, 4, 5, 6, 7);
        rotationCount = 2;
        expectedArray = Utilities.constructArray(3, 4, 5, 6, 7, 1, 2);
        Utilities.printArray(arr);
        ArrayQuests.rotateArray(arr, rotationCount);
        Utilities.printArray(arr);
        assertArrayEquals(expectedArray, arr);
        System.out.println();

        arr = Utilities.constructArray(1, 2, 3, 4, 5, 6, 7);
        rotationCount = 0;
        expectedArray = Utilities.constructArray(1, 2, 3, 4, 5, 6, 7);
        Utilities.printArray(arr);
        ArrayQuests.rotateArray(arr, rotationCount);
        Utilities.printArray(arr);
        assertArrayEquals(expectedArray, arr);
        System.out.println();

        arr = Utilities.constructArray(1, 2, 3, 4, 5, 6, 7);
        rotationCount = 7;
        expectedArray = Utilities.constructArray(1, 2, 3, 4, 5, 6, 7);
        Utilities.printArray(arr);
        ArrayQuests.rotateArray(arr, rotationCount);
        Utilities.printArray(arr);
        assertArrayEquals(expectedArray, arr);
        System.out.println();

        arr = Utilities.constructArray(1, 2, 3, 4, 5, 6, 7);
        rotationCount = 10;
        expectedArray = Utilities.constructArray(4, 5, 6, 7, 1, 2, 3);
        Utilities.printArray(arr);
        ArrayQuests.rotateArray(arr, rotationCount);
        Utilities.printArray(arr);
        assertArrayEquals(expectedArray, arr);
        System.out.println();
    }

    @Test
    void rotateArrayApproach4() {
        int[] arr = {1, 3, 5, 7, 9};

        Utilities.printArray(arr);
        ArrayQuests.rotateArrayEfficient(arr,2);
        ArrayQuests.rotateArrayEfficient(arr,3);
        ArrayQuests.rotateArrayEfficient(arr,4);
    }

    @Test
    void rotationCountInRotatedSortedArray() {
        int[] arr1 = {15, 18, 2, 3, 6, 12};
        int index1 = ArrayQuests.rotationCountInRotatedSortedArray(arr1);
        System.out.println(index1);
        assertEquals(2, index1);

        int[] arr2 = {7, 9, 11, 12, 5};
        int index2 = ArrayQuests.rotationCountInRotatedSortedArray(arr2);
        System.out.println(index2);
        assertEquals(4, index2);

        int[] arr3 = {7, 9, 11, 12, 15};
        int index3 = ArrayQuests.rotationCountInRotatedSortedArray(arr3);
        System.out.println(index3);
        assertEquals(0, index3);
    }

    @Test
    void rotationCountInRotatedSortedArrayLinearSearch() {
        int[] arr1 = {15, 18, 2, 3, 6, 12};
        int index1 = ArrayQuests.rotationCountInRotatedSortedArrayLinearSearch(arr1);
        System.out.println(index1);
        assertEquals(2, index1);

        int[] arr2 = {7, 9, 11, 12, 5};
        int index2 = ArrayQuests.rotationCountInRotatedSortedArrayLinearSearch(arr2);
        System.out.println(index2);
        assertEquals(4, index2);

        int[] arr3 = {7, 9, 11, 12, 15};
        int index3 = ArrayQuests.rotationCountInRotatedSortedArrayLinearSearch(arr3);
        System.out.println(index3);
        assertEquals(0, index3);
    }

    @Test
    void rotationCountInRotatedSortedArrayBinarySearch() {
        int[] arr1 = {15, 18, 2, 3, 6, 12};
        int index1 = ArrayQuests.rotationCountInRotatedSortedArrayBinarySearch(arr1);
        System.out.println(index1);
        assertEquals(2, index1);

        int[] arr2 = {7, 9, 11, 12, 5};
        int index2 = ArrayQuests.rotationCountInRotatedSortedArrayBinarySearch(arr2);
        System.out.println(index2);
        assertEquals(4, index2);

        int[] arr3 = {7, 9, 11, 12, 15};
        int index3 = ArrayQuests.rotationCountInRotatedSortedArrayBinarySearch(arr3);
        System.out.println(index3);
        assertEquals(0, index3);
    }

    @Test
    void medianOfSortedArraysOfSameSize() {
        int[] arr1 = {1, 12, 15, 26, 38};
        int[] arr2 = {2, 13, 17, 30, 45};
        int median = ArrayQuests.medianOfSortedArraysOfSameSize(arr1, arr2);
        System.out.println(median);
        assertEquals(16, median);

        int[] arr3 = {2, 6, 9};
        int[] arr4 = {1, 5, 7};
        median = ArrayQuests.medianOfSortedArraysOfSameSize(arr3, arr4);
        System.out.println(median);
        assertEquals(5, median);
    }

    @Test
    void medianOfSortedArraysOfSameSizeMergeCount() {
        int[] arr1 = {1, 12, 15, 26, 38};
        int[] arr2 = {2, 13, 17, 30, 45};
        int median = ArrayQuests.medianOfSortedArraysOfSameSizeMergeCount(arr1, arr2);
        System.out.println(median);
        assertEquals(16, median);

        int[] arr3 = {2, 6, 9};
        int[] arr4 = {1, 5, 7};
        median = ArrayQuests.medianOfSortedArraysOfSameSizeMergeCount(arr3, arr4);
        System.out.println(median);
        assertEquals(5, median);
    }

    @Test
    void medianOfSortedArraysOfSameSizeDC() {
        int[] arr1 = {1, 12, 15, 26, 38};
        int[] arr2 = {2, 13, 17, 30, 45};
        int median = ArrayQuests.medianOfSortedArraysOfSameSizeDC(arr1, arr2);
        System.out.println(median);
        assertEquals(16, median);

        int[] arr3 = {2, 6, 9};
        int[] arr4 = {1, 5, 7};
        median = ArrayQuests.medianOfSortedArraysOfSameSizeDC(arr3, arr4);
        System.out.println(median);
        assertEquals(5, median);
    }

    @Test
    void largestSumContinuousSubArray() {
        int[] arr1 =  {-2, -3, 4, -1, -2, 1, 5, -3};
        int sum1 = ArrayQuests.largestSumContinuousSubArray(arr1);
        System.out.println(sum1);
        assertEquals(7, sum1);
    }

    @Test
    void longestCommonSpanWithSameSumInBinaryArrays1() {
        boolean[] arr1, arr2;

        arr1 = Utilities.constructBinaryArray(0, 1, 0, 0, 0, 0);
        arr2 = Utilities.constructBinaryArray(1, 0, 1, 0, 0, 1);
        assertEquals(ArrayQuests.longestCommonSpanWithSameSumInBinaryArrays1(arr1, arr2), 4);

        arr1 = Utilities.constructBinaryArray(0, 1, 0, 1, 1, 1, 1);
        arr2 = Utilities.constructBinaryArray(1, 1, 1, 1, 1, 0, 1);
        assertEquals(ArrayQuests.longestCommonSpanWithSameSumInBinaryArrays1(arr1, arr2), 6);

        arr1 = Utilities.constructBinaryArray(0, 0, 0);
        arr2 = Utilities.constructBinaryArray(1, 1, 1);
        assertEquals(ArrayQuests.longestCommonSpanWithSameSumInBinaryArrays1(arr1, arr2), 0);

        arr1 = Utilities.constructBinaryArray(0, 0, 1, 0);
        arr2 = Utilities.constructBinaryArray(1, 1, 1, 1);
        assertEquals(ArrayQuests.longestCommonSpanWithSameSumInBinaryArrays1(arr1, arr2), 1);
    }

    @Test
    void longestCommonSpanWithSameSumInBinaryArrays2() {
        boolean[] arr1, arr2;

        arr1 = Utilities.constructBinaryArray(0, 1, 0, 0, 0, 0);
        arr2 = Utilities.constructBinaryArray(1, 0, 1, 0, 0, 1);
        assertEquals(ArrayQuests.longestCommonSpanWithSameSumInBinaryArrays2(arr1, arr2), 4);

        arr1 = Utilities.constructBinaryArray(0, 1, 0, 1, 1, 1, 1);
        arr2 = Utilities.constructBinaryArray(1, 1, 1, 1, 1, 0, 1);
        assertEquals(ArrayQuests.longestCommonSpanWithSameSumInBinaryArrays2(arr1, arr2), 6);

        arr1 = Utilities.constructBinaryArray(0, 0, 0);
        arr2 = Utilities.constructBinaryArray(1, 1, 1);
        assertEquals(ArrayQuests.longestCommonSpanWithSameSumInBinaryArrays2(arr1, arr2), 0);

        arr1 = Utilities.constructBinaryArray(0, 0, 1, 0);
        arr2 = Utilities.constructBinaryArray(1, 1, 1, 1);
        assertEquals(ArrayQuests.longestCommonSpanWithSameSumInBinaryArrays2(arr1, arr2), 1);
    }

    @Test
    void longestCommonSpanWithSameSumInBinaryArrays3() {
        boolean[] arr1, arr2;

        arr1 = Utilities.constructBinaryArray(0, 1, 0, 0, 0, 0);
        arr2 = Utilities.constructBinaryArray(1, 0, 1, 0, 0, 1);
        assertEquals(ArrayQuests.longestCommonSpanWithSameSumInBinaryArrays3(arr1, arr2), 4);

        arr1 = Utilities.constructBinaryArray(0, 1, 0, 1, 1, 1, 1);
        arr2 = Utilities.constructBinaryArray(1, 1, 1, 1, 1, 0, 1);
        assertEquals(ArrayQuests.longestCommonSpanWithSameSumInBinaryArrays3(arr1, arr2), 6);

        arr1 = Utilities.constructBinaryArray(0, 0, 0);
        arr2 = Utilities.constructBinaryArray(1, 1, 1);
        assertEquals(ArrayQuests.longestCommonSpanWithSameSumInBinaryArrays3(arr1, arr2), 0);

        arr1 = Utilities.constructBinaryArray(0, 0, 1, 0);
        arr2 = Utilities.constructBinaryArray(1, 1, 1, 1);
        assertEquals(ArrayQuests.longestCommonSpanWithSameSumInBinaryArrays3(arr1, arr2), 1);
    }

    @Test
    void minimumDistanceBetweenTwoNumbers() {
        int[] arr;

        arr = Utilities.constructArray(1, 2);
        assertEquals(ArrayQuests.minimumDistanceBetweenTwoNumbers(arr, 1, 2), 1);

        arr = Utilities.constructArray(3, 4, 5);
        assertEquals(ArrayQuests.minimumDistanceBetweenTwoNumbers(arr, 3, 5), 2);

        arr = Utilities.constructArray(3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3);
        assertEquals(ArrayQuests.minimumDistanceBetweenTwoNumbers(arr, 3, 6), 4);

        arr = Utilities.constructArray(2, 5, 3, 5, 4, 4, 2, 3);
        assertEquals(ArrayQuests.minimumDistanceBetweenTwoNumbers(arr, 3, 2), 1);
    }

    @Test
    void majorityElement1() {
        int[] arr;

        arr = Utilities.constructArray(3, 3, 4, 2, 4, 4, 2, 4, 4);
        assertEquals(ArrayQuests.majorityElement1(arr), 4);

        arr = Utilities.constructArray(3, 3, 4, 2, 4, 4, 2, 4);
        assertEquals(ArrayQuests.majorityElement1(arr), -1);
    }

    @Test
    void majorityElement2() {
        int[] arr;

        arr = Utilities.constructArray(3, 3, 4, 2, 4, 4, 2, 4, 4);
        assertEquals(ArrayQuests.majorityElement2(arr), 4);

        arr = Utilities.constructArray(3, 3, 4, 2, 4, 4, 2, 4);
        assertEquals(ArrayQuests.majorityElement2(arr), -1);
    }

    @Test
    void countStrictlyIncreasingSubArrays1() {
        int[] arr;

        arr = Utilities.constructArray(1, 4, 3);
        assertEquals(ArrayQuests.countStrictlyIncreasingSubArrays1(arr), 1);

        arr = Utilities.constructArray(1, 2, 3, 4);
        assertEquals(ArrayQuests.countStrictlyIncreasingSubArrays1(arr), 6);

        arr = Utilities.constructArray(1, 2, 2, 4);
        assertEquals(ArrayQuests.countStrictlyIncreasingSubArrays1(arr), 2);
    }

    @Test
    void countStrictlyIncreasingSubArrays2() {
        int[] arr;

        arr = Utilities.constructArray(1, 4, 3);
        assertEquals(ArrayQuests.countStrictlyIncreasingSubArrays2(arr), 1);

        arr = Utilities.constructArray(1, 2, 3, 4);
        assertEquals(ArrayQuests.countStrictlyIncreasingSubArrays2(arr), 6);

        arr = Utilities.constructArray(1, 2, 2, 4);
        assertEquals(ArrayQuests.countStrictlyIncreasingSubArrays2(arr), 2);
    }

    @Test
    void segregate1sAnd0s() {
        int[] arr;
        System.out.println();
        arr = Utilities.constructArray(0, 1, 0, 1, 0, 0, 1, 1, 1, 0);
        System.out.println(Arrays.toString(arr));
        ArrayQuests.segregate1sAnd0s(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println();
        arr = Utilities.constructArray(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        System.out.println(Arrays.toString(arr));
        ArrayQuests.segregate1sAnd0s(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println();
        arr = Utilities.constructArray(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        System.out.println(Arrays.toString(arr));
        ArrayQuests.segregate1sAnd0s(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println();
        arr = Utilities.constructArray(1, 1, 1, 1, 1, 1, 1, 1, 1, 0);
        System.out.println(Arrays.toString(arr));
        ArrayQuests.segregate1sAnd0s(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println();
        arr = Utilities.constructArray(1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        System.out.println(Arrays.toString(arr));
        ArrayQuests.segregate1sAnd0s(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void mergeIntervals() {
        List<Pair<Integer, Integer>> intervals = new LinkedList<>();
        intervals.add(new Pair<>(6, 8));
        intervals.add(new Pair<>(1, 9));
        intervals.add(new Pair<>(2, 4));
        intervals.add(new Pair<>(4, 7));

        var mergedIntervals = ArrayQuests.mergeIntervals(intervals);
        for(var interval : mergedIntervals) {
            System.out.println("{" + interval.getFirst() + ", " + interval.getSecond() + "}");
        }
    }

    @Test
    void searchInRotatedSortedArray() {
        int arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int key;
        int index;

        key = 3;
        index = ArrayQuests.searchInRotatedSortedArray(arr, key);
        System.out.println(key + " -> " + index);
        assertEquals(8, index);

        key = 5;
        index = ArrayQuests.searchInRotatedSortedArray(arr, key);
        System.out.println(key + " -> " + index);
        assertEquals(0, index);

        key = 9;
        index = ArrayQuests.searchInRotatedSortedArray(arr, key);
        System.out.println(key + " -> " + index);
        assertEquals(4, index);

        key = 1;
        index = ArrayQuests.searchInRotatedSortedArray(arr, key);
        System.out.println(key + " -> " + index);
        assertEquals(6, index);
    }

    @Test
    void countTriplets() {
        int[] arr;
        int sum;

        arr = Utilities.constructArray(5, 1, 3, 4, 7);
        sum = 12;
        assertEquals(4, ArrayQuests.countTriplets(arr, sum));

        arr = Utilities.constructArray(-2, 0, 1, 3);
        sum = 2;
        assertEquals(2, ArrayQuests.countTriplets(arr, sum));
    }

    @Test
    void countTripletsNaive() {
        int[] arr;
        int sum;

        arr = Utilities.constructArray(5, 1, 3, 4, 7);
        sum = 12;
        assertEquals(4, ArrayQuests.countTripletsNaive(arr, sum));

        arr = Utilities.constructArray(-2, 0, 1, 3);
        sum = 2;
        assertEquals(2, ArrayQuests.countTripletsNaive(arr, sum));
    }


    @Test
    void findTripletForSum() {
        int[] arr;
        int sum;

        arr = Utilities.constructArray(1, 4, 45, 6, 10, 8);
        sum = 22;
        Triplet<Integer, Integer, Integer> triplet = ArrayQuests.findTripletForSum(arr, sum);
        assertEquals(4, (int) triplet.getFirst());
        assertEquals(8, (int) triplet.getSecond());
        assertEquals(10, (int) triplet.getThird());
    }

    @Test
    void findTripletForSumHashing() {
        int[] arr;
        int sum;

        arr = Utilities.constructArray(1, 4, 45, 6, 10, 8);
        sum = 22;
        Triplet<Integer, Integer, Integer> triplet = ArrayQuests.findTripletForSumHashing(arr, sum);
        assertEquals(4, (int) triplet.getFirst());
        assertEquals(10, (int) triplet.getSecond());
        assertEquals(8, (int) triplet.getThird());
    }

    @Test
    void findTripletForSumNaive() {
        int[] arr;
        int sum;

        arr = Utilities.constructArray(1, 4, 45, 6, 10, 8);
        sum = 22;
        Triplet<Integer, Integer, Integer> triplet = ArrayQuests.findTripletForSumNaive(arr, sum);
        assertEquals(4, (int) triplet.getFirst());
        assertEquals(10, (int) triplet.getSecond());
        assertEquals(8, (int) triplet.getThird());
    }

    @Test
    void twoSum() {
        int[] arr;
        int sum;
        boolean pairExists;

        arr = Utilities.constructArray(1, 4, 45, 6, 10, -8);
        sum = 16;
        pairExists = ArrayQuests.twoSum(arr, sum);
        System.out.println(pairExists);
        assertEquals(true, pairExists);

        arr = Utilities.constructArray(1, 4, 45, 6, 10, -8);
        sum = 50;
        pairExists = ArrayQuests.twoSum(arr, sum);
        System.out.println(pairExists);
        assertEquals(false, pairExists);
    }

    @Test
    void twoSumHashing() {
        int[] arr;
        int sum;
        boolean pairExists;

        arr = Utilities.constructArray(1, 4, 45, 6, 10, -8);
        sum = 16;
        pairExists = ArrayQuests.twoSumHashing(arr, sum);
        System.out.println(pairExists);
        assertEquals(true, pairExists);

        arr = Utilities.constructArray(1, 4, 45, 6, 10, -8);
        sum = 50;
        pairExists = ArrayQuests.twoSumHashing(arr, sum);
        System.out.println(pairExists);
        assertEquals(false, pairExists);
    }

    @Test
    void countPairsForSum() {
        int[] arr;
        int sum;
        int pairCount;

        arr = Utilities.constructArray(1, 5, 7, -1);
        sum = 6;
        pairCount = ArrayQuests.countPairsForSum(arr, sum);
        System.out.println("Pair Count: " + pairCount);
        assertEquals(2, pairCount);

        arr = Utilities.constructArray(1, 5, 7, -1, 5);
        sum = 6;
        pairCount = ArrayQuests.countPairsForSum(arr, sum);
        System.out.println("Pair Count: " + pairCount);
        assertEquals(3, pairCount);

        arr = Utilities.constructArray(1, 1, 1, 1);
        sum = 2;
        pairCount = ArrayQuests.countPairsForSum(arr, sum);
        System.out.println("Pair Count: " + pairCount);
        assertEquals(6, pairCount);

        arr = Utilities.constructArray(10, 12, 10, 15, -1, 7, 6, 5, 4, 2, 1, 1, 1);
        sum = 11;
        pairCount = ArrayQuests.countPairsForSum(arr, sum);
        System.out.println("Pair Count: " + pairCount);
        assertEquals(9, pairCount);
    }

    @Test
    void countPairsForSumNaive() {
        int[] arr;
        int sum;
        int pairCount;

        arr = Utilities.constructArray(1, 5, 7, -1);
        sum = 6;
        pairCount = ArrayQuests.countPairsForSumNaive(arr, sum);
        System.out.println("Pair Count: " + pairCount);
        assertEquals(2, pairCount);

        arr = Utilities.constructArray(1, 5, 7, -1, 5);
        sum = 6;
        pairCount = ArrayQuests.countPairsForSumNaive(arr, sum);
        System.out.println("Pair Count: " + pairCount);
        assertEquals(3, pairCount);

        arr = Utilities.constructArray(1, 1, 1, 1);
        sum = 2;
        pairCount = ArrayQuests.countPairsForSumNaive(arr, sum);
        System.out.println("Pair Count: " + pairCount);
        assertEquals(6, pairCount);

        arr = Utilities.constructArray(10, 12, 10, 15, -1, 7, 6, 5, 4, 2, 1, 1, 1);
        sum = 11;
        pairCount = ArrayQuests.countPairsForSumNaive(arr, sum);
        System.out.println("Pair Count: " + pairCount);
        assertEquals(9, pairCount);
    }

    @Test
    void kthAbsoluteDifferenceBetweenElements() {
        int[] arr;
        int k;
        int difference;

        arr = Utilities.constructArray(1, 2, 3, 4);
        k = 3;
        difference = ArrayQuests.kthAbsoluteDifferenceBetweenElements(arr, k);
        System.out.println(difference);
        assertEquals(1, difference);

        arr = Utilities.constructArray(10, 10);
        k = 1;
        difference = ArrayQuests.kthAbsoluteDifferenceBetweenElements(arr, k);
        System.out.println(difference);
        assertEquals(0, difference);
    }

    @Test
    void moveZerosToEnd() {
        int[] arr;
        int i;

        arr = Utilities.constructArray(1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9);
        System.out.println(Arrays.toString(arr));
        ArrayQuests.moveZerosToEnd(arr);
        System.out.println(Arrays.toString(arr));
        i = 1;
        while(i < 5) {
            assertEquals(0, arr[arr.length - i]);
            i++;
        }

        arr = Utilities.constructArray(1, 2, 0, 0, 0, 3, 6);
        System.out.println(Arrays.toString(arr));
        ArrayQuests.moveZerosToEnd(arr);
        System.out.println(Arrays.toString(arr));
        i = 1;
        while(i < 4) {
            assertEquals(0, arr[arr.length - i]);
            i++;
        }

        arr = Utilities.constructArray(1, 2, 0, 4, 3, 0, 5, 0);
        System.out.println(Arrays.toString(arr));
        ArrayQuests.moveZerosToEnd(arr);
        System.out.println(Arrays.toString(arr));
        i = 1;
        while(i < 3) {
            assertEquals(0, arr[arr.length - i]);
            i++;
        }
    }

    @Test
    void maxDifferenceBetweenIncreasingElementsInArrayApproach4() {
        int[] arr;
        int difference;

        arr = Utilities.constructArray(2, 3, 10, 6, 4, 8, 1);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayApproach4(arr);
        System.out.println(difference);
        assertEquals(8, difference);

        arr = Utilities.constructArray(7, 9, 5, 6, 3, 2);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayApproach4(arr);
        System.out.println(difference);
        assertEquals(2, difference);

        arr = Utilities.constructArray(1, 2, 90, 10, 110);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayApproach4(arr);
        System.out.println(difference);
        assertEquals(109, difference);

        arr = Utilities.constructArray(80, 2, 6, 3, 100);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayApproach4(arr);
        System.out.println(difference);
        assertEquals(98, difference);
    }

    @Test
    void maxDifferenceBetweenIncreasingElementsInArrayApproach3() {
        int[] arr;
        int difference;

        arr = Utilities.constructArray(2, 3, 10, 6, 4, 8, 1);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayApproach3(arr);
        System.out.println(difference);
        assertEquals(8, difference);

        arr = Utilities.constructArray(7, 9, 5, 6, 3, 2);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayApproach3(arr);
        System.out.println(difference);
        assertEquals(2, difference);

        arr = Utilities.constructArray(1, 2, 90, 10, 110);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayApproach3(arr);
        System.out.println(difference);
        assertEquals(109, difference);

        arr = Utilities.constructArray(80, 2, 6, 3, 100);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayApproach3(arr);
        System.out.println(difference);
        assertEquals(98, difference);
    }

    @Test
    void maxDifferenceBetweenIncreasingElementsInArrayApproach2() {
        int[] arr;
        int difference;

        arr = Utilities.constructArray(2, 3, 10, 6, 4, 8, 1);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayApproach2(arr);
        System.out.println(difference);
        assertEquals(8, difference);

        arr = Utilities.constructArray(7, 9, 5, 6, 3, 2);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayApproach2(arr);
        System.out.println(difference);
        assertEquals(2, difference);

        arr = Utilities.constructArray(1, 2, 90, 10, 110);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayApproach2(arr);
        System.out.println(difference);
        assertEquals(109, difference);

        arr = Utilities.constructArray(80, 2, 6, 3, 100);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayApproach2(arr);
        System.out.println(difference);
        assertEquals(98, difference);
    }

    @Test
    void maxDifferenceBetweenIncreasingElementsInArrayNaive() {
        int[] arr;
        int difference;

        arr = Utilities.constructArray(2, 3, 10, 6, 4, 8, 1);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayNaive(arr);
        System.out.println(difference);
        assertEquals(8, difference);

        arr = Utilities.constructArray(7, 9, 5, 6, 3, 2);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayNaive(arr);
        System.out.println(difference);
        assertEquals(2, difference);

        arr = Utilities.constructArray(1, 2, 90, 10, 110);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayNaive(arr);
        System.out.println(difference);
        assertEquals(109, difference);

        arr = Utilities.constructArray(80, 2, 6, 3, 100);
        System.out.println(Arrays.toString(arr));
        difference = ArrayQuests.maxDifferenceBetweenIncreasingElementsInArrayNaive(arr);
        System.out.println(difference);
        assertEquals(98, difference);
    }

    @Test
    void convertToZigZagApproach2() {
        int[] arr;

        arr = Utilities.constructArray(4, 3, 7, 8, 6, 2, 1);
        System.out.println(Arrays.toString(arr));
        ArrayQuests.convertToZigZagApproach2(arr);
        System.out.println(Arrays.toString(arr));

        arr = Utilities.constructArray(1, 4, 3, 2);
        System.out.println(Arrays.toString(arr));
        ArrayQuests.convertToZigZagApproach2(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void convertToZigZagApproach1() {
        int[] arr;

        arr = Utilities.constructArray(4, 3, 7, 8, 6, 2, 1);
        System.out.println(Arrays.toString(arr));
        ArrayQuests.convertToZigZagApproach1(arr);
        System.out.println(Arrays.toString(arr));

        arr = Utilities.constructArray(1, 4, 3, 2);
        System.out.println(Arrays.toString(arr));
        ArrayQuests.convertToZigZagApproach1(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void lengthOfLargestContiguousSubArrayForDistinctElementArray() {
        int[] arr;
        int length;

        arr = Utilities.constructArray(10, 12, 11);
        System.out.println(Arrays.toString(arr));
        length = ArrayQuests.lengthOfLargestContiguousSubArrayForDistinctElementArray(arr);
        System.out.println(length);
        assertEquals(3, length);

        arr = Utilities.constructArray(14, 12, 11, 20);
        System.out.println(Arrays.toString(arr));
        length = ArrayQuests.lengthOfLargestContiguousSubArrayForDistinctElementArray(arr);
        System.out.println(length);
        assertEquals(2, length);

        arr = Utilities.constructArray(1, 56, 58, 57, 90, 92, 94, 93, 91, 45);
        System.out.println(Arrays.toString(arr));
        length = ArrayQuests.lengthOfLargestContiguousSubArrayForDistinctElementArray(arr);
        System.out.println(length);
        assertEquals(5, length);
    }

    @Test
    void lengthOfLargestContiguousSubArray() {
        int[] arr;
        int length;

        arr = Utilities.constructArray(10, 12, 11);
        System.out.println(Arrays.toString(arr));
        length = ArrayQuests.lengthOfLargestContiguousSubArray(arr);
        System.out.println(length);
        assertEquals(3, length);

        arr = Utilities.constructArray(10, 12, 12, 10, 10, 11, 10);
        System.out.println(Arrays.toString(arr));
        length = ArrayQuests.lengthOfLargestContiguousSubArray(arr);
        System.out.println(length);
        assertEquals(2, length);
    }

    @Test
    void countInversions() {
        int[] arr;
        int inversionCount;

        arr = Utilities.constructArray(1, 20, 6, 4, 5);
        System.out.println(Arrays.toString(arr));
        inversionCount = ArrayQuests.countInversions(arr);
        System.out.println(inversionCount);
        System.out.println(Arrays.toString(arr));
        assertEquals(5, inversionCount);

        arr = Utilities.constructArray(2, 4, 1, 3, 5);
        System.out.println(Arrays.toString(arr));
        inversionCount = ArrayQuests.countInversions(arr);
        System.out.println(inversionCount);
        assertEquals(3, inversionCount);

        arr = Utilities.constructArray(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println(Arrays.toString(arr));
        inversionCount = ArrayQuests.countInversions(arr);
        System.out.println(inversionCount);
        assertEquals(0, inversionCount);
    }

    @Test
    void countInversionsNaive() {
        int[] arr;
        int inversionCount;

        arr = Utilities.constructArray(1, 20, 6, 4, 5);
        System.out.println(Arrays.toString(arr));
        inversionCount = ArrayQuests.countInversionsNaive(arr);
        System.out.println(inversionCount);
        assertEquals(5, inversionCount);

        arr = Utilities.constructArray(2, 4, 1, 3, 5);
        System.out.println(Arrays.toString(arr));
        inversionCount = ArrayQuests.countInversionsNaive(arr);
        System.out.println(inversionCount);
        assertEquals(3, inversionCount);

        arr = Utilities.constructArray(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println(Arrays.toString(arr));
        inversionCount = ArrayQuests.countInversionsNaive(arr);
        System.out.println(inversionCount);
        assertEquals(0, inversionCount);
    }

    @Test
    void lengthOfSmallestSubarrayWithGreaterSum() {
        int[] arr;
        int sum;
        int length;
        int expectedLength;

        arr = Utilities.constructArray(1, 4, 45, 6, 10, 19);
        sum = 51;
        expectedLength = 3;
        length = ArrayQuests.lengthOfSmallestSubarrayWithGreaterSum(arr, sum);
        assertEquals(expectedLength, length);

        arr = Utilities.constructArray(1, 10, 5, 2, 7);
        sum = 9;
        expectedLength = 1;
        length = ArrayQuests.lengthOfSmallestSubarrayWithGreaterSum(arr, sum);
        assertEquals(expectedLength, length);

        arr = Utilities.constructArray(1, 11, 100, 1, 0, 200, 3, 2, 1, 250);
        sum = 280;
        expectedLength = 4;
        length = ArrayQuests.lengthOfSmallestSubarrayWithGreaterSum(arr, sum);
        assertEquals(expectedLength, length);

        arr = Utilities.constructArray(1, 2, 4);
        sum = 8;
        expectedLength = 0;
        length = ArrayQuests.lengthOfSmallestSubarrayWithGreaterSum(arr, sum);
        assertEquals(expectedLength, length);
    }

    @Test
    void lengthOfSmallestSubarrayWithGreaterSumNaive() {
        int[] arr;
        int sum;
        int length;
        int expectedLength;

        arr = Utilities.constructArray(1, 4, 45, 6, 10, 19);
        sum = 51;
        expectedLength = 3;
        length = ArrayQuests.lengthOfSmallestSubarrayWithGreaterSumNaive(arr, sum);
        assertEquals(expectedLength, length);

        arr = Utilities.constructArray(1, 10, 5, 2, 7);
        sum = 9;
        expectedLength = 1;
        length = ArrayQuests.lengthOfSmallestSubarrayWithGreaterSumNaive(arr, sum);
        assertEquals(expectedLength, length);

        arr = Utilities.constructArray(1, 11, 100, 1, 0, 200, 3, 2, 1, 250);
        sum = 280;
        expectedLength = 4;
        length = ArrayQuests.lengthOfSmallestSubarrayWithGreaterSumNaive(arr, sum);
        assertEquals(expectedLength, length);

        arr = Utilities.constructArray(1, 2, 4);
        sum = 8;
        expectedLength = 0;
        length = ArrayQuests.lengthOfSmallestSubarrayWithGreaterSumNaive(arr, sum);
        assertEquals(expectedLength, length);
    }

    @Test
    void maxSubsequenceSumWithNoAdjacentElements() {
        int[] arr;
        int sum;
        int expectedSum;

        arr = Utilities.constructArray(5, 5, 10, 100, 10, 5);
        expectedSum = 110;
        sum = ArrayQuests.maxSubsequenceSumWithNoAdjacentElements(arr);
        assertEquals(expectedSum, sum);

        arr = Utilities.constructArray(1, 2, 3);
        expectedSum = 4;
        sum = ArrayQuests.maxSubsequenceSumWithNoAdjacentElements(arr);
        assertEquals(expectedSum, sum);

        arr = Utilities.constructArray(1, 20, 3);
        expectedSum = 20;
        sum = ArrayQuests.maxSubsequenceSumWithNoAdjacentElements(arr);
        assertEquals(expectedSum, sum);
    }

    @Test
    void closestDistanceBetweenTwoPoints() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(2, 3));
        points.add(new Point(12, 30));
        points.add(new Point(40, 50));
        points.add(new Point(5, 1));
        points.add(new Point(12, 10));
        points.add(new Point(3, 4));

        double closestDistance = ArrayQuests.closestDistanceBetweenTwoPoints(points);
        System.out.println("Closest distance: " + closestDistance);
        assertEquals(1.414214, Math.round(closestDistance * 1000000.0) / 1000000.0);
    }

    @Test
    void countOccurancesInSortedArray() {
        int[] arr;
        int value;
        int occurances;
        int expectedOccurances;

        arr = Utilities.constructArray(1, 1, 2, 2, 2, 2, 3);
        value = 2;
        expectedOccurances = 4;
        occurances = ArrayQuests.countOccurancesInSortedArray(arr, value);
        assertEquals(expectedOccurances, occurances);

        arr = Utilities.constructArray(1, 1, 2, 2, 2, 2, 3);
        value = 3;
        expectedOccurances = 1;
        occurances = ArrayQuests.countOccurancesInSortedArray(arr, value);
        assertEquals(expectedOccurances, occurances);

        arr = Utilities.constructArray(1, 1, 2, 2, 2, 2, 3);
        value = 1;
        expectedOccurances = 2;
        occurances = ArrayQuests.countOccurancesInSortedArray(arr, value);
        assertEquals(expectedOccurances, occurances);

        arr = Utilities.constructArray(1, 1, 2, 2, 2, 2, 3);
        value = 4;
        expectedOccurances = 0;
        occurances = ArrayQuests.countOccurancesInSortedArray(arr, value);
        assertEquals(expectedOccurances, occurances);
    }

    @Test
    void sortInWaveForm() {
        int[] arr;

        arr = Utilities.constructArray(10, 90, 49, 2, 1, 5, 23);
        Utilities.printArray(arr);
        ArrayQuests.sortInWaveForm(arr);
        Utilities.printArray(arr);

        arr = Utilities.constructArray(10, 5, 6, 3, 2, 20, 100, 80);
        Utilities.printArray(arr);
        ArrayQuests.sortInWaveForm(arr);
        Utilities.printArray(arr);

        arr = Utilities.constructArray(20, 10, 8, 6, 4, 2);
        Utilities.printArray(arr);
        ArrayQuests.sortInWaveForm(arr);
        Utilities.printArray(arr);

        arr = Utilities.constructArray(2, 4, 6, 8, 10, 20);
        Utilities.printArray(arr);
        ArrayQuests.sortInWaveForm(arr);
        Utilities.printArray(arr);

        arr = Utilities.constructArray(3, 6, 5, 10, 7, 20);
        Utilities.printArray(arr);
        ArrayQuests.sortInWaveForm(arr);
        Utilities.printArray(arr);
    }

    @Test
    void sortInWaveFormNaive() {
        int[] arr;

        arr = Utilities.constructArray(10, 90, 49, 2, 1, 5, 23);
        Utilities.printArray(arr);
        ArrayQuests.sortInWaveFormNaive(arr);
        Utilities.printArray(arr);

        arr = Utilities.constructArray(10, 5, 6, 3, 2, 20, 100, 80);
        Utilities.printArray(arr);
        ArrayQuests.sortInWaveFormNaive(arr);
        Utilities.printArray(arr);

        arr = Utilities.constructArray(20, 10, 8, 6, 4, 2);
        Utilities.printArray(arr);
        ArrayQuests.sortInWaveFormNaive(arr);
        Utilities.printArray(arr);

        arr = Utilities.constructArray(2, 4, 6, 8, 10, 20);
        Utilities.printArray(arr);
        ArrayQuests.sortInWaveFormNaive(arr);
        Utilities.printArray(arr);

        arr = Utilities.constructArray(3, 6, 5, 10, 7, 20);
        Utilities.printArray(arr);
        ArrayQuests.sortInWaveFormNaive(arr);
        Utilities.printArray(arr);
    }

    @Test
    void getSumTriangle() {
        int[] arr;
        int[][] sumTriangle;

        arr = Utilities.constructArray(1, 2, 3, 4, 5);
        Utilities.printArray(arr);
        sumTriangle = ArrayQuests.getSumTriangle(arr);
        for(var list : sumTriangle) {
            Utilities.printArray(list);
        }
    }

    @Test
    void convertToReducedForm() {
        int[] arr;
        int[] expectedArray;

        arr = Utilities.constructArray(10, 20, 15, 12, 11, 50);
        expectedArray = Utilities.constructArray(0, 4, 3, 2, 1, 5);
        Utilities.printArray(arr);
        ArrayQuests.convertToReducedForm(arr);
        Utilities.printArray(arr);
        assertArrayEquals(expectedArray, arr);
    }

    @Test
    void getThirdLargestElement() {
        int[] arr;
        int third;

        arr = Utilities.constructArray(1, 14, 2, 16, 10, 20);
        Utilities.printArray(arr);
        third = ArrayQuests.getThirdLargestElement(arr);
        assertEquals(14, third);

        arr = Utilities.constructArray(19, -10, 20, 14, 2, 16, 10);
        Utilities.printArray(arr);
        third = ArrayQuests.getThirdLargestElement(arr);
        assertEquals(16, third);

        arr = Utilities.constructArray(12, 13, 1, 10, 34, 16);
        Utilities.printArray(arr);
        third = ArrayQuests.getThirdLargestElement(arr);
        assertEquals(13, third);
    }

    @Test
    void zeroSumSubarrayExists() {
        int[] arr;
        boolean subArrayExists;

        arr = Utilities.constructArray(-3, 2, 3, 1, 6);
        Utilities.printArray(arr);
        subArrayExists = ArrayQuests.zeroSumSubarrayExists(arr);
        assertEquals(false, subArrayExists);

        arr = Utilities.constructArray(4, 2, -3, 1, 6);
        Utilities.printArray(arr);
        subArrayExists = ArrayQuests.zeroSumSubarrayExists(arr);
        assertEquals(true, subArrayExists);

        arr = Utilities.constructArray(4, 2, 0, 1, 6);
        Utilities.printArray(arr);
        subArrayExists = ArrayQuests.zeroSumSubarrayExists(arr);
        assertEquals(true, subArrayExists);
    }

    @Test
    void queryCountGreaterThanAndNotLessThan() {
        int[] arr;
        List<Pair<Integer, Integer>> queries;
        int[] expectedResult;
        int[] actualResult;

        arr = Utilities.constructArray(1, 2, 3, 4);
        queries = new LinkedList<>();
        queries.add(new Pair<>(0, 5));
        queries.add(new Pair<>(1, 3));
        queries.add(new Pair<>(0, 3));
        expectedResult = Utilities.constructArray(0, 1, 2);
        var resuts = ArrayQuests.queryCountGreaterThanAndNotLessThan(arr, queries);
        actualResult = resuts.stream().mapToInt(i -> i).toArray();
        Utilities.printArray(actualResult);
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void getSmallestMissingNumberInSortedArray() {
        int[] arr;
        int index;

        arr = Utilities.constructArray(0, 1, 2, 6, 9);
        index = ArrayQuests.getSmallestMissingNumberInSortedArray(arr);
        assertEquals(3, index);

        arr = Utilities.constructArray(4, 5, 10, 11);
        index = ArrayQuests.getSmallestMissingNumberInSortedArray(arr);
        assertEquals(0, index);

        arr = Utilities.constructArray(0, 1, 2, 3);
        index = ArrayQuests.getSmallestMissingNumberInSortedArray(arr);
        assertEquals(4, index);

        arr = Utilities.constructArray(0, 1, 2, 3, 4, 5, 6, 7, 10);
        index = ArrayQuests.getSmallestMissingNumberInSortedArray(arr);
        assertEquals(8, index);
    }

    @Test
    void getIncreasingSubsequenceOfLengthThreeWithMaxProduct() {
        int[] arr;
        Triplet<Integer, Integer, Integer> subsequence;

        arr = Utilities.constructArray(6, 7, 8, 1, 2, 3, 9, 10);
        subsequence = ArrayQuests.getIncreasingSubsequenceOfLengthThreeWithMaxProduct(arr);
        assertEquals(8, (int) subsequence.getFirst());
        assertEquals(9, (int) subsequence.getSecond());
        assertEquals(10, (int) subsequence.getThird());

        arr = Utilities.constructArray(1, 5, 10, 8, 9);
        subsequence = ArrayQuests.getIncreasingSubsequenceOfLengthThreeWithMaxProduct(arr);
        assertEquals(5, (int) subsequence.getFirst());
        assertEquals(8, (int) subsequence.getSecond());
        assertEquals(9, (int) subsequence.getThird());

        arr = Utilities.constructArray(5, 4, 3, 2, 1);
        subsequence = ArrayQuests.getIncreasingSubsequenceOfLengthThreeWithMaxProduct(arr);
        assertEquals(null, subsequence);
    }

    @Test
    void getIncreasingSubsequenceOfLengthThreeWithMaxProductApproach2() {
        int[] arr;
        Triplet<Integer, Integer, Integer> subsequence;

        arr = Utilities.constructArray(6, 7, 8, 1, 2, 3, 9, 10);
        subsequence = ArrayQuests.getIncreasingSubsequenceOfLengthThreeWithMaxProductApproach2(arr);
        assertEquals(8, (int) subsequence.getFirst());
        assertEquals(9, (int) subsequence.getSecond());
        assertEquals(10, (int) subsequence.getThird());

        arr = Utilities.constructArray(1, 5, 10, 8, 9);
        subsequence = ArrayQuests.getIncreasingSubsequenceOfLengthThreeWithMaxProductApproach2(arr);
        assertEquals(5, (int) subsequence.getFirst());
        assertEquals(8, (int) subsequence.getSecond());
        assertEquals(9, (int) subsequence.getThird());

        arr = Utilities.constructArray(5, 4, 3, 2, 1);
        subsequence = ArrayQuests.getIncreasingSubsequenceOfLengthThreeWithMaxProductApproach2(arr);
        assertEquals(null, subsequence);
    }

    @Test
    void getIncreasingSubsequenceOfLengthThreeWithMaxProductNaive() {
        int[] arr;
        Triplet<Integer, Integer, Integer> subsequence;

        arr = Utilities.constructArray(6, 7, 8, 1, 2, 3, 9, 10);
        subsequence = ArrayQuests.getIncreasingSubsequenceOfLengthThreeWithMaxProductNaive(arr);
        assertEquals(8, (int) subsequence.getFirst());
        assertEquals(9, (int) subsequence.getSecond());
        assertEquals(10, (int) subsequence.getThird());

        arr = Utilities.constructArray(1, 5, 10, 8, 9);
        subsequence = ArrayQuests.getIncreasingSubsequenceOfLengthThreeWithMaxProductNaive(arr);
        assertEquals(5, (int) subsequence.getFirst());
        assertEquals(8, (int) subsequence.getSecond());
        assertEquals(9, (int) subsequence.getThird());

        arr = Utilities.constructArray(5, 4, 3, 2, 1);
        subsequence = ArrayQuests.getIncreasingSubsequenceOfLengthThreeWithMaxProductNaive(arr);
        assertEquals(null, subsequence);
    }

    @Test
    void minimumIncrementByKOperationsToMakeArrayElementsEqual() {
        int[] arr;
        int operationCount;

        arr = Utilities.constructArray(4, 7, 19, 16);
        operationCount = ArrayQuests.minimumIncrementByKOperationsToMakeArrayElementsEqual(arr, 3);
        assertEquals(10, operationCount);

        arr = Utilities.constructArray(4, 4, 4, 4);
        operationCount = ArrayQuests.minimumIncrementByKOperationsToMakeArrayElementsEqual(arr, 3);
        assertEquals(0, operationCount);

        arr = Utilities.constructArray(4, 2, 6, 8);
        operationCount = ArrayQuests.minimumIncrementByKOperationsToMakeArrayElementsEqual(arr, 3);
        assertEquals(-1, operationCount);

        arr = Utilities.constructArray(21, 33, 9, 45, 63);
        operationCount = ArrayQuests.minimumIncrementByKOperationsToMakeArrayElementsEqual(arr, 6);
        assertEquals(24, operationCount);
    }

    @Test
    void subArrayOfPositiveIntegersWithGivenSum() {
        int[] arr;
        int[] subArray;
        int[] expectedArray;

        arr = Utilities.constructArray(1, 4, 20, 3, 10, 5);
        expectedArray = Utilities.constructArray(20, 3, 10);
        subArray = ArrayQuests.subArrayOfPositiveIntegersWithGivenSum(arr, 33);
        Utilities.printArray(subArray);
        assertArrayEquals(expectedArray, subArray);

        arr = Utilities.constructArray(1, 4, 0, 0, 3, 10, 5);
        expectedArray = Utilities.constructArray(4, 0, 0, 3);
        subArray = ArrayQuests.subArrayOfPositiveIntegersWithGivenSum(arr, 7);
        Utilities.printArray(subArray);
        assertArrayEquals(expectedArray, subArray);

        arr = Utilities.constructArray(1, 4);
        expectedArray = null;
        subArray = ArrayQuests.subArrayOfPositiveIntegersWithGivenSum(arr, 0);
        Utilities.printArray(subArray);
        assertArrayEquals(expectedArray, subArray);
    }
}