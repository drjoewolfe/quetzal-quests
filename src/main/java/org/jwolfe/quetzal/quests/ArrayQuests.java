package org.jwolfe.quetzal.quests;

import org.jwolfe.quetzal.algorithms.ArrayAlgorithms;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.general.Point;
import org.jwolfe.quetzal.library.general.Triplet;
import org.jwolfe.quetzal.library.tree.AvlTree;
import org.jwolfe.quetzal.library.utilities.PairFirstSorter;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.*;

public class ArrayQuests {
    public static void findTripletsWithZeroSum(int[] arr) {
        findTripletsWithZeroSumBrute(arr);
        findTripletsWithZeroSumHashing(arr);
        findTripletsWithZeroSumSorting(arr);
    }

    public static void findTripletsWithZeroSumBrute(int[] arr) {
        int n = arr.length;

        if (n < 3)
            return;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = i + 2; k < n; k++) {
                    if (arr[i] + arr[j] + arr[k] == 0) {
                        System.out.println("Triplets with zero sum found at indexes - " + i + ", " + j + ", " + k + " : with values - " + arr[i] + ", " + arr[j] + ", " + arr[k]);
                    }
                }
            }
        }
    }

    public static void findTripletsWithZeroSumHashing(int[] arr) {
        int n = arr.length;

        if (n < 3)
            return;

        for (int i = 0; i < n - 1; i++) {
            HashSet<Integer> map = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                int key = -(arr[i] + arr[j]);
                if (map.contains(key)) {
                    System.out.println("Triplets with zero sum found with values - " + arr[i] + ", " + key + ", " + arr[j]);
                } else {
                    map.add(arr[j]);
                }
            }
        }
    }

    public static void findTripletsWithZeroSumSorting(int[] arr) {
        int n = arr.length;

        if (n < 3)
            return;

        Arrays.sort(arr);
        for (int i = 0; i < n - 1; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == 0) {
                    System.out.println("Triplets with zero sum found at indexes - " + i + ", " + left + ", " + right + " : with values - " + arr[i] + ", " + arr[left] + ", " + arr[right]);
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
    }

    public static void rotateArray(int[] arr, int rotationCount) {
        // Other approaches
        //  1) Auxiliary array
        //  2) Modified Bubble Sort

        if (rotationCount > arr.length) {
            rotationCount %= arr.length;
        }

        reverseArray(arr, 0, rotationCount - 1);
        reverseArray(arr, rotationCount, arr.length - 1);
        reverseArray(arr, 0, arr.length - 1);
    }

    public static void rotateArrayEfficient(int[] arr, int rotationCount) {
        if (arr == null
                || arr.length == 0) {
            return;
        }

        for (int i = rotationCount; i < rotationCount + arr.length; i++) {
            System.out.print(arr[i % arr.length] + " ");
        }

        System.out.println();
    }

    public static void reverseArray(int[] arr, int start, int end) {
        if (arr == null
                || arr.length == 0
                || start > end) {
            return;
        }

        while (start < end) {
            Utilities.swap(arr, start, end);
            start++;
            end--;
        }
    }

    public static int rotationCountInRotatedSortedArray(int[] arr) {
        return rotationCountInRotatedSortedArrayBinarySearch(arr);
    }

    public static int rotationCountInRotatedSortedArrayLinearSearch(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return i + 1;
            }
        }

        return 0;
    }

    public static int rotationCountInRotatedSortedArrayBinarySearch(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        return rotationCountInRotatedSortedArrayBinarySearch(arr, left, right);
    }

    private static int rotationCountInRotatedSortedArrayBinarySearch(int[] arr, int left, int right) {
        if (left > right) {
            return 0;
        }

        if (left == right) {
            return left;
        }

        int mid = (left + right) / 2;

        if (mid < right && arr[mid + 1] < arr[mid]) {
            return mid + 1;
        }

        if (mid > left && arr[mid] < arr[mid - 1]) {
            return mid;
        }


        if (arr[right] > arr[mid]) {
            return rotationCountInRotatedSortedArrayBinarySearch(arr, left, mid - 1);
        } else {
            return rotationCountInRotatedSortedArrayBinarySearch(arr, mid + 1, right);
        }
    }

    public static int medianOfSortedArraysOfSameSize(int[] arr1, int[] arr2) {
        medianOfSortedArraysOfSameSizeMergeCount(arr1, arr2);
        return medianOfSortedArraysOfSameSizeDC(arr1, arr2);
    }

    public static int medianOfSortedArraysOfSameSizeMergeCount(int[] arr1, int[] arr2) {
        int n = arr1.length;
        if (n != arr2.length)
            return -1;

        int i = 0;
        int j = 0;

        int m1 = -1;
        int m2 = -1;

        for (int count = 0; count <= n; count++) {
            if (i == n) {
                m1 = m2;
                m2 = arr2[0];
            } else if (j == n) {
                m1 = m2;
                m2 = arr1[0];
            }

            if (arr1[i] < arr2[j]) {
                m1 = m2;
                m2 = arr1[i];
                i++;
            } else {
                m1 = m2;
                m2 = arr2[j];
                j++;
            }
        }

        return (m1 + m2) / 2;
    }

    public static int medianOfSortedArraysOfSameSizeDC(int[] arr1, int[] arr2) {
        int n = arr1.length;
        if (n != arr2.length)
            return -1;

        return medianOfSortedArraysOfSameSizeDC(arr1, arr2, 0, n, 0, n);
    }

    private static int medianOfSortedArraysOfSameSizeDC(int[] arr1, int[] arr2, int arr1Start, int arr1End, int arr2Start, int arr2End) {
        int n = arr1End - arr1Start;

        if (n <= 0) {
            return -1;
        }

        if (n == 1) {
            return (arr1[arr1Start] + arr2[arr2Start]) / 2;
        }

        if (n == 2) {
            return (Math.max(arr1[arr1Start], arr2[arr2Start]) + Math.min(arr1[arr1Start + 1], arr2[arr2Start + 1])) / 2;
        }

        int m1 = getMedian(arr1, arr1Start, arr1End);
        int m2 = getMedian(arr2, arr2Start, arr2End);

        if (m1 == m2) {
            return (m1 + m2) / 2;
        } else if (m1 > m2) {
            if (n % 2 == 0)
                return medianOfSortedArraysOfSameSizeDC(arr1, arr2, arr1Start, arr1Start + n / 2 + 1, arr2Start + n / 2 - 1, arr2End);
            else
                return medianOfSortedArraysOfSameSizeDC(arr1, arr2, arr1Start, arr1Start + n / 2 + 1, arr2Start + n / 2, arr2End);
        } else {
            if (n % 2 == 0)
                return medianOfSortedArraysOfSameSizeDC(arr1, arr2, arr1Start + n / 2 + 1, arr1End, arr2Start, arr2Start - n / 2 + 1);
            else
                return medianOfSortedArraysOfSameSizeDC(arr1, arr2, arr1Start + n / 2, arr1End, arr2Start, arr2Start + n / 2 + 1);
        }
    }

    private static int getMedian(int[] arr, int start, int end) {
        int n = end - start;
        if (n % 2 == 0) {
            return (arr[start + n / 2] + arr[start + n / 2 - 1]) / 2;
        } else {
            return arr[start + n / 2];
        }
    }

    public static int largestSumContinuousSubArray(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }

        int currentMax = arr[0];
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            currentMax = Math.max(arr[i], currentMax + arr[i]);
            max = Math.max(currentMax, max);
        }

        return max;
    }

    public static int longestCommonSpanWithSameSumInBinaryArrays1(boolean[] arr1, boolean[] arr2) {
        int n = arr1.length;
        if (n != arr2.length) {
            return -1;
        }

        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            int sum1 = 0;
            int sum2 = 0;
            for (int j = i; j < n; j++) {
                sum1 += arr1[j] ? 1 : 0;
                sum2 += arr2[j] ? 1 : 0;

                if (sum1 == sum2) {
                    int len = j - i + 1;
                    if (len > maxLength) {
                        maxLength = len;
                    }
                }
            }
        }

        return maxLength;
    }

    public static int longestCommonSpanWithSameSumInBinaryArrays2(boolean[] arr1, boolean[] arr2) {
        int n = arr1.length;
        if (n != arr2.length) {
            return -1;
        }

        int preSum1 = 0;
        int preSum2 = 0;

        int maxLength = 0;

        int[] diff = new int[2 * n + 1];
        Arrays.fill(diff, -1);

        for (int i = 0; i < n; i++) {
            preSum1 += arr1[i] ? 1 : 0;
            preSum2 += arr2[i] ? 1 : 0;

            int currentDiff = preSum1 - preSum2;
            int diffIndex = n + currentDiff;

            if (currentDiff == 0) {
                maxLength = i + 1;
            } else if (diff[diffIndex] == -1) {
                diff[diffIndex] = i;
            } else {
                int len = i - diff[diffIndex];
                if (len > maxLength) {
                    maxLength = len;
                }
            }
        }

        return maxLength;
    }

    public static int longestCommonSpanWithSameSumInBinaryArrays3(boolean[] arr1, boolean[] arr2) {
        int n = arr1.length;
        if (n != arr2.length) {
            return -1;
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (arr1[i] ? 1 : 0) - (arr2[i] ? 1 : 0);
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (sum == 0) {
                maxLength = i + 1;
            }

            if (map.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }

        return maxLength;
    }

    public static int minimumDistanceBetweenTwoNumbers(int[] arr, int x, int y) {
        int distance = Integer.MAX_VALUE;
        int i = 0;
        int prev = -1;
        for (; i < arr.length; i++) {
            if (arr[i] == x
                    || arr[i] == y) {
                prev = i;
                break;
            }
        }

        for (; i < arr.length; i++) {
            if (arr[i] == x
                    || arr[i] == y) {
                if (arr[prev] != arr[i]
                        && (i - prev) < distance) {
                    distance = i - prev;
                    prev = i;
                } else {
                    prev = i;
                }
            }
        }

        return distance;
    }

    public static int majorityElement1(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            if (map.containsKey(key)) {
                int val = map.get(key) + 1;
                map.put(key, val);
            } else {
                map.put(key, 1);
            }
        }

        int majority = n / 2 + 1;
        for (var e : map.entrySet()) {
            if (e.getValue() >= majority) {
                return e.getKey();
            }
        }

        return -1;
    }

    public static int majorityElement2(int[] arr) {
        // Moore's majority element method
        int n = arr.length;
        int candidate = findCandidate(arr);
        if (isMajority(arr, candidate))
            return candidate;

        return -1;
    }

    private static int findCandidate(int[] arr) {
        int n = arr.length;

        int majorityIndex = 0;
        int count = 1;

        for (int i = 0; i < n; i++) {
            if (arr[majorityIndex] == arr[i]) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                majorityIndex = i;
                count = 1;
            }
        }

        return arr[majorityIndex];
    }

    private static boolean isMajority(int[] arr, int candidate) {
        int n = arr.length;

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == candidate) {
                count++;
            }
        }

        if (count > n / 2) {
            return true;
        }

        return false;
    }

    public static int countStrictlyIncreasingSubArrays1(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j - 1] < arr[j]) {
                    count++;
                } else {
                    break;
                }

            }
        }

        return count;
    }

    public static int countStrictlyIncreasingSubArrays2(int[] arr) {
        //  Sorted subarray of length ‘len’ adds len*(len-1)/2 to result.
        int count = 0;
        int len = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i]) {
                len++;
            } else {
                count += (len * (len - 1) / 2);
                len = 1;
            }
        }

        if (len > 1) {
            count += (len * (len - 1) / 2);
        }

        return count;
    }

    public static void segregate1sAnd0s(int[] arr) {
        if (arr == null
                || arr.length == 0)
            return;

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            while (arr[left] == 0 && left < right) {
                left++;
            }

            while (arr[right] == 1 && left < right) {
                right--;
            }

            if (left < right) {
                Utilities.swap(arr, left, right);
                left++;
                right--;
            }
        }
    }

    public static List<Pair<Integer, Integer>> mergeIntervals(List<Pair<Integer, Integer>> intervals) {
        // Below is a stack based implementation (O(n) space).
        // This can also be done in place, thus saving the extra space.

        if (intervals.size() == 0) {
            return null;
        }

        Collections.sort(intervals, new PairFirstSorter());

        Stack<Pair<Integer, Integer>> mergedIntervals = new Stack<>();
        mergedIntervals.push(intervals.get(0));

        for (int i = 1; i < intervals.size(); i++) {
            var interval = intervals.get(i);
            var topInterval = mergedIntervals.peek();

            if (topInterval.getSecond() > interval.getFirst()) {
                // Overlap
                if (topInterval.getSecond() < interval.getSecond()) {
                    topInterval.setSecond(interval.getSecond());
                }
            } else {
                mergedIntervals.push(interval);
            }
        }

        List<Pair<Integer, Integer>> result = new LinkedList<>();
        while (!mergedIntervals.isEmpty()) {
            var interval = mergedIntervals.pop();
            result.add(interval);
            Collections.sort(result, new PairFirstSorter());
        }

        return result;
    }

    public static int searchInRotatedSortedArray(int[] arr, int key) {
        if (arr.length == 0) {
            return -1;
        }

        int pivot = findPivotInRotatedSortedArray(arr, 0, arr.length - 1);

        if (pivot == -1) {
            // Array is not rotated
            return ArrayAlgorithms.binarySearch(arr, key, 0, arr.length - 1);
        }

        if (key >= arr[0]) {
            return ArrayAlgorithms.binarySearch(arr, key, 0, pivot);
        } else {
            return ArrayAlgorithms.binarySearch(arr, key, pivot + 1, arr.length - 1);
        }
    }

    private static int findPivotInRotatedSortedArray(int[] arr, int start, int end) {
        if (start > end) {
            return -1;
        }

        if (start == end) {
            return start;
        }

        int mid = (start + end) / 2;

        // Check arr[mid-1], arr[mid], arr[mid+1]
        if (end > mid
                && arr[mid] > arr[mid + 1]) {
            return mid;
        }

        if (start < mid
                && arr[mid - 1] > arr[mid]) {
            return mid - 1;
        }

        if (arr[mid] > arr[end]) {
            return findPivotInRotatedSortedArray(arr, mid + 1, end);
        } else {
            return findPivotInRotatedSortedArray(arr, start, mid - 1);
        }
    }

    public static int countTriplets(int[] array, int sum) {
        int count = 0;
        Arrays.sort(array);

        for (int i = 0; i < array.length - 2; i++) {
            int left = i + 1;
            int right = array.length - 1;

            while (left < right) {
                int currentSum = array[i] + array[left] + array[right];
                if (currentSum < sum) {
                    System.out.println(currentSum + " -> " + array[i] + ", " + array[left] + " " + array[right]);
                    count += (right - left);

                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println("Count: " + count);
        System.out.println();
        return count;
    }

    public static int countTripletsNaive(int[] array, int sum) {
        int count = 0;
        for (int i = 0; i < array.length - 2; i++) {
            for (int j = i + 1; j < array.length - 1; j++) {
                for (int k = j + 1; k < array.length; k++) {
                    int currentSum = array[i] + array[j] + array[k];
                    if (currentSum < sum) {
                        System.out.println(currentSum + " -> " + array[i] + ", " + array[j] + " " + array[k]);
                        count++;
                    }
                }
            }
        }

        System.out.println("Count: " + count);
        System.out.println();
        return count;
    }

    public static Triplet<Integer, Integer, Integer> findTripletForSum(int[] arr, int sum) {
        if (arr == null
                || arr.length < 3) {
            return null;
        }

        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int loopSum = arr[i] + arr[left] + arr[right];
                if (loopSum == sum) {
                    return new Triplet<>(arr[i], arr[left], arr[right]);
                } else if (loopSum < sum) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return null;
    }

    public static Triplet<Integer, Integer, Integer> findTripletForSumHashing(int[] arr, int sum) {
        if (arr == null
                || arr.length < 3) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < arr.length - 2; i++) {
            map.clear();
            int tempSum = sum - arr[i];
            for (int j = 0; j < arr.length; j++) {
                int key = tempSum - arr[j];
                if (map.containsKey(key)) {
                    return new Triplet<Integer, Integer, Integer>(arr[i], key, arr[j]);
                }

                map.put(arr[j], j);
            }
        }

        return null;
    }

    public static Triplet<Integer, Integer, Integer> findTripletForSumNaive(int[] arr, int sum) {
        if (arr == null
                || arr.length < 3) {
            return null;
        }

        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                for (int k = 0; k < arr.length; k++) {
                    if (arr[i] + arr[j] + arr[k] == sum) {
                        return new Triplet<>(arr[i], arr[j], arr[k]);
                    }
                }
            }
        }

        return null;
    }

    public static boolean twoSum(int[] array, int sum) {
        if (array.length < 2) {
            return false;
        }

        Arrays.sort(array);

        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int currentSum = array[left] + array[right];
            if (currentSum == sum) {
                return true;
            }

            if (currentSum < sum) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    public static boolean twoSumHashing(int[] array, int sum) {
        if (array.length < 2) {
            return false;
        }

        HashSet<Integer> hash = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            int key = sum - array[i];
            if (hash.contains(key)) {
                System.out.println(array[i] + ", " + key);
                return true;
            }

            hash.add(array[i]);
        }

        return false;
    }

    public static int countPairsForSum(int[] arr, int sum) {
        if (arr == null
                || arr.length < 2) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            if (map.containsKey(key)) {
                int value = map.get(key) + 1;
                map.put(key, value);
            } else {
                map.put(key, 1);
            }
        }

        int twicePairCount = 0;
        for (int i = 0; i < arr.length; i++) {
            int key = sum - arr[i];
            if (map.containsKey(key)) {
                twicePairCount += map.get(key);
            }

            if (key == arr[i]) {
                twicePairCount--;
            }
        }

        return twicePairCount / 2;
    }

    public static int countPairsForSumNaive(int[] arr, int sum) {
        if (arr == null
                || arr.length < 2) {
            return 0;
        }

        int pairCount = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == sum) {
                    pairCount++;
                }
            }
        }

        return pairCount;
    }

    public static int kthAbsoluteDifferenceBetweenElements(int[] arr, int k) {
        Arrays.sort(arr);
        int length = arr.length;
        if (length < 2) {
            return -1;
        }

        int high = arr[length - 1] - arr[0];
        int low = arr[1] - arr[0];
        for (int i = 0; i < length - 1; i++) {
            low = Math.min(low, arr[i + 1] - arr[i]);
        }

        while (low < high) {
            int mid = (low + high) / 2;
            int pairCount = countPairsLessOrEqualDifference(arr, mid);
            if (pairCount < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private static int countPairsLessOrEqualDifference(int[] arr, int difference) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int pairCeil = arr[i] + difference;
            int upperBound = upperBound(arr, i, arr.length - 1, pairCeil);
            if (pairCeil >= arr[arr.length - 1]) {
                count += (upperBound - i);
            } else {
                count += (upperBound - (i + 1));
            }
        }

        return count;
    }

    private static int upperBound(int[] arr, int low, int high, int value) {
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    public static void moveZerosToEnd(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[count++] = arr[i];
            }
        }

        while (count < arr.length) {
            arr[count++] = 0;
        }
    }

    public static int maxDifferenceBetweenIncreasingElementsInArrayApproach4(int[] arr) {
        if (arr.length < 2) {
            return 0;
        }


        int difference = arr[1] - arr[0];
        int runningSum = difference;
        int maxDifference = runningSum;

        for (int i = 1; i < arr.length - 1; i++) {
            difference = arr[i + 1] - arr[i];
            if (runningSum > 0) {
                runningSum += difference;
            } else {
                runningSum = difference;
            }

            if (maxDifference < runningSum) {
                maxDifference = runningSum;
            }
        }

        if (maxDifference < 0) {
            return 0;
        }

        return maxDifference;
    }

    public static int maxDifferenceBetweenIncreasingElementsInArrayApproach3(int[] arr) {
        if (arr.length < 2) {
            return 0;
        }

        int[] differences = new int[arr.length - 1];
        for (int i = 0; i < arr.length - 1; i++) {
            differences[i] = arr[i + 1] - arr[i];
        }

        int maxDifference = differences[0];
        for (int i = 1; i < differences.length; i++) {
            if (differences[i - 1] > 0) {
                differences[i] += differences[i - 1];
            }

            if (maxDifference < differences[i]) {
                maxDifference = differences[i];
            }
        }

        if (maxDifference < 0) {
            return 0;
        }

        return maxDifference;
    }

    public static int maxDifferenceBetweenIncreasingElementsInArrayApproach2(int[] arr) {
        if (arr.length < 2) {
            return 0;
        }

        int maxDifference = 0;
        int minElement = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (maxDifference < (arr[i] - minElement)) {
                maxDifference = arr[i] - minElement;
            }

            if (arr[i] < minElement) {
                minElement = arr[i];
            }
        }

        return maxDifference;
    }

    public static int maxDifferenceBetweenIncreasingElementsInArrayNaive(int[] arr) {
        if (arr.length < 2) {
            return 0;
        }

        int maxDifference = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int difference = arr[j] - arr[i];
                if (maxDifference < difference) {
                    maxDifference = difference;
                }
            }
        }

        return maxDifference;
    }

    public static void convertToZigZagApproach2(int[] arr) {
        boolean greaterThanFlag = false;

        for (int i = 0; i < arr.length - 1; i++) {
            if (greaterThanFlag
                    && arr[i] < arr[i + 1]) {
                Utilities.swap(arr, i, i + 1);
            } else if (!greaterThanFlag
                    && arr[i] > arr[i + 1]) {
                Utilities.swap(arr, i, i + 1);
            }

            greaterThanFlag = !greaterThanFlag;
        }
    }

    public static void convertToZigZagApproach1(int[] arr) {
        Arrays.sort(arr);

        for (int i = 1; i < arr.length - 1; i += 2) {
            Utilities.swap(arr, i, i + 1);
        }
    }

    public static int lengthOfLargestContiguousSubArrayForDistinctElementArray(int[] arr) {
        int maxLength = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int max = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);

                int length = max - min + 1;
                if (length == (j - i + 1)) {
                    // Contiguous subarray
                    maxLength = Math.max(maxLength, length);
                }
            }
        }

        return maxLength;
    }

    public static int lengthOfLargestContiguousSubArray(int[] arr) {
        // General case - allowing for duplicates
        int maxLength = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            HashSet<Integer> set = new HashSet<>();
            set.add(arr[i]);

            int min = arr[i];
            int max = arr[i];

            for (int j = i + 1; j < arr.length; j++) {
                if (set.contains(arr[j])) {
                    break;
                }

                set.add(arr[j]);

                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);

                int length = max - min + 1;
                if (length == (j - i + 1)) {
                    // Contiguous subarray
                    maxLength = Math.max(maxLength, length);
                }
            }
        }

        return maxLength;
    }

    public static int countInversions(int[] arr) {
        return mergeSortForInversionCount(arr, 0, arr.length - 1);
    }

    private static int mergeSortForInversionCount(int[] arr, int left, int right) {
        int inversionCount = 0;
        if (left < right) {
            int mid = (left + right) / 2;

            inversionCount += mergeSortForInversionCount(arr, left, mid);
            inversionCount += mergeSortForInversionCount(arr, mid + 1, right);
            inversionCount += mergeForInversionCount(arr, left, mid, right);
        }

        return inversionCount;
    }

    private static int mergeForInversionCount(int[] arr, int left, int mid, int right) {
        int inversionCount = 0;

        int l = left;
        int r = mid + 1;

        int[] temp = new int[right - left + 1];
        int t = 0;

        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) {
                temp[t++] = arr[l++];
            } else {
                temp[t++] = arr[r++];
                inversionCount += (mid - l + 1);
            }
        }

        while (l <= mid) {
            temp[t++] = arr[l++];
        }

        while (r <= right) {
            temp[t++] = arr[r++];
        }

        for (t = 0; t < temp.length; t++) {
            arr[left + t] = temp[t];
        }

        return inversionCount;
    }

    public static int countInversionsNaive(int[] arr) {
        int inversionCount = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    inversionCount++;
                }
            }
        }

        return inversionCount;
    }

    public static int lengthOfSmallestSubarrayWithGreaterSum(int[] arr, int sum) {
        if (arr == null
                || arr.length == 0) {
            return 0;
        }

        int minLength = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int runningSum = 0;

        System.out.println(Arrays.toString(arr) + ", " + sum);
        while (end < arr.length) {
            while (runningSum <= sum
                    && end < arr.length) {
                runningSum += arr[end++];
            }

            while (runningSum > sum
                    && start < arr.length) {
                int length = end - start;
                if (minLength > length) {
                    System.out.println("Found " + start + ", " + end + " -> " + length);
                    minLength = length;
                }

                runningSum -= arr[start++];
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static int lengthOfSmallestSubarrayWithGreaterSumNaive(int[] arr, int sum) {
        if (arr == null) {
            return -1;
        }

        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int runningSum = 0;
            for (int j = i; j < arr.length; j++) {
                int length = j - i + 1;
                runningSum += arr[j];

                if (runningSum > sum
                        && minLength > length) {
                    minLength = length;

                    continue;
                }
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static int maxSubsequenceSumWithNoAdjacentElements(int[] arr) {
        if (arr == null
                || arr.length == 0) {
            return 0;
        }

        int inclusiveSum = arr[0];
        int exclusiveSum = 0;
        int maxSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int temp = inclusiveSum;
            inclusiveSum = exclusiveSum + arr[i];
            exclusiveSum = temp;

            maxSum = Utilities.max(maxSum, inclusiveSum, exclusiveSum);
        }

        return maxSum;
    }

    public static double closestDistanceBetweenTwoPoints(List<Point> points) {
        Collections.sort(points, (o1, o2) -> o1.getX() - o2.getX());
        return closestDistanceBetweenTwoPoints(points, 0, points.size() - 1);
    }

    private static double closestDistanceBetweenTwoPoints(List<Point> points, int start, int end) {
        int length = end - start + 1;
        if (length <= 3) {
            return closestDistanceBetweenTwoPointsBrute(points, start, end);
        }

        int mid = (start + end) / 2;
        double leftResult = closestDistanceBetweenTwoPoints(points, start, mid);
        double rightResult = closestDistanceBetweenTwoPoints(points, mid + 1, end);

        double ceilingDistance = Math.min(leftResult, rightResult);
        List<Point> strip = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            Point p = points.get(i);
            if (Math.abs(p.getX() - mid) < ceilingDistance) {
                strip.add(p);
            }
        }

        return closestDistanceBetweenTwoPointsInStrip(strip, ceilingDistance);
    }

    private static double closestDistanceBetweenTwoPointsBrute(List<Point> points, int start, int end) {
        int length = end - start + 1;
        if (length == 1) {
            return 0;
        }

        double closestDistance = Double.MAX_VALUE;
        for (int i = start + 1; i <= end; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i - 1);

            double distance = distanceBetweenPoints(p1, p2);
            closestDistance = Math.min(closestDistance, distance);
        }

        return closestDistance == Double.MAX_VALUE ? 0 : closestDistance;
    }

    private static double closestDistanceBetweenTwoPointsInStrip(List<Point> strip, double ceilingDistance) {
        if (strip == null
                || strip.size() <= 1) {
            return ceilingDistance;
        }

        Collections.sort(strip, (o1, o2) -> o1.getY() - o2.getY());

        double closestDistance = ceilingDistance;
        for (int i = 0; i < strip.size(); i++) {
            Point p1 = strip.get(i);

            for (int j = i + 1; j < strip.size(); j++) {
                Point p2 = strip.get(j);
                double distance = distanceBetweenPoints(p1, p2);
                closestDistance = Math.min(closestDistance, distance);
            }
        }

        return closestDistance;
    }

    private static double distanceBetweenPoints(Point p1, Point p2) {
        double distance = Math.sqrt(
                Math.pow(p1.getX() - p2.getX(), 2)
                        + Math.pow(p1.getY() - p2.getY(), 2));
        return distance;
    }

    public static int countOccurancesInSortedArray(int[] arr, int value) {
        if (arr == null
                || arr.length == 0) {
            return 0;
        }

        int leftIndex = getLeftIndex(arr, value);
        if (leftIndex == -1) {
            return 0;
        }

        int rightIndex = getRightIndex(arr, value);
        return rightIndex - leftIndex + 1;
    }

    private static int getLeftIndex(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if ((mid == 0 || arr[mid - 1] < value) && arr[mid] == value) {
                return mid;
            } else if (arr[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    private static int getRightIndex(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if ((mid == arr.length - 1 || value < arr[mid + 1]) && arr[mid] == value) {
                return mid;
            } else if (value < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    public static void sortInWaveForm(int[] arr) {
        if (arr == null
                || arr.length <= 1) {
            return;
        }

        for (int i = 1; i < arr.length; i += 2) {
            if (arr[i] > arr[i - 1]) {
                Utilities.swap(arr, i, i - 1);
            }

            if (i + 1 != arr.length
                    && arr[i] > arr[i + 1]) {
                Utilities.swap(arr, i, i + 1);
            }
        }
    }

    public static void sortInWaveFormNaive(int[] arr) {
        Arrays.sort(arr);

        for (int i = 1; i < arr.length; i += 2) {
            Utilities.swap(arr, i, i - 1);
        }
    }

    public static int[][] getSumTriangle(int[] arr) {
        int length = arr.length;
        int[][] sumTriangle = new int[length][];
        sumTriangle[length - 1] = arr;
        computeSumTriangle(arr, arr.length - 1, sumTriangle);

        return sumTriangle;
    }

    private static void computeSumTriangle(int[] arr, int length, int[][] sumTriangle) {
        if (length < 1) {
            return;
        }

        if (length == 1) {
            sumTriangle[0] = new int[]{arr[0] + arr[1]};
            return;
        }

        int[] rowArray = new int[length];
        for (int i = 0; i < length; i++) {
            rowArray[i] = arr[i] + arr[i + 1];
        }

        computeSumTriangle(rowArray, length - 1, sumTriangle);
        sumTriangle[length - 1] = rowArray;
    }

    public static void convertToReducedForm(int[] arr) {
        if (arr == null
                || arr.length == 0) {
            return;
        }

        int[] temp = Arrays.copyOf(arr, arr.length);
        Arrays.sort(temp);

        Map<Integer, Integer> map = new HashMap<>();
        int val = 0;
        for (int i = 0; i < temp.length; i++) {
            int key = temp[i];
            if (!map.containsKey(key)) {
                map.put(key, val);
                val++;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            arr[i] = map.get(key);
        }
    }

    public static int getThirdLargestElement(int[] arr) {
        if (arr == null
                || arr.length < 3) {
            return Integer.MIN_VALUE;
        }

        int first = arr[0];
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > first) {
                third = second;
                second = first;
                first = arr[i];
            } else if (arr[i] > second) {
                third = second;
                second = arr[i];
            } else if (arr[i] > third) {
                third = arr[i];
            }
        }

        return third;
    }

    public static boolean zeroSumSubarrayExists(int[] arr) {
        int prefixSum = 0;
        Set<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            if (prefixSum == 0
                    || set.contains(prefixSum)) {
                return true;
            }

            set.add(prefixSum);
        }

        return false;
    }

    public static List<Integer> queryCountGreaterThanAndNotLessThan(int[] arr, List<Pair<Integer, Integer>> queries) {
        // Query Pairs ar of the form (q, x)
        //      q: 0 => Greater Than or Equal To x
        //      q: 1 => Greater Than x

        if (arr == null) {
            return null;
        }

        List<Integer> results = new LinkedList<>();

        Arrays.sort(arr);
        for (var query : queries) {
            int q = query.getFirst();
            int x = query.getSecond();

            if (q == 0) {
                int lowerBound = lowerBound(arr, 0, arr.length, x);
                results.add(arr.length - lowerBound);
            } else if (q == 1) {
                int upperBound = upperBound(arr, 0, arr.length, x);
                results.add(arr.length - upperBound);
            } else {
                results.add(0);
            }
        }

        return results;
    }

    private static int lowerBound(int[] arr, int low, int high, int value) {
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= value) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public static int getSmallestMissingNumberInSortedArray(int[] arr) {
        // Approach 1: Binary search for each integer from 0 to ceil in arr. O(mlogn)
        // Approach 2: Linerar traversal of array, checking a[i] with a[i+1]. If difference is not 1, it is the missing number
        // Approach 3: Modified binary search, with comparison of index & values. First location where arr[i] != i gives the missing number
        return getSmallestMissingNumberIndexInSortedArray(arr, 0, arr.length - 1);
    }

    private static int getSmallestMissingNumberIndexInSortedArray(int[] arr, int start, int end) {
        if (start > end) {
            return end + 1;
        }

        if (arr[start] != start) {
            return start;
        }

        int mid = (start + end) / 2;
        if (arr[mid] == mid) {
            return getSmallestMissingNumberIndexInSortedArray(arr, mid + 1, end);
        } else {
            return getSmallestMissingNumberIndexInSortedArray(arr, start, mid);
        }
    }

    public static Triplet<Integer, Integer, Integer> getIncreasingSubsequenceOfLengthThreeWithMaxProduct(int[] arr) {
        if (arr == null
                || arr.length < 3) {
            return null;
        }

        int n = arr.length;
        Integer[] largestElementToLeftSmallerThanB = new Integer[n];
        Integer[] largestElementToRightGreaterThanB = new Integer[n];

        Arrays.fill(largestElementToLeftSmallerThanB, null);
        AvlTree tree = new AvlTree();
        for (int i = 1; i < n; i++) {
            int floor = tree.getFloor(arr[i]);
            if(floor != Integer.MAX_VALUE) {
                largestElementToLeftSmallerThanB[i] = floor;
            }

            tree.insert(arr[i]);
        }

        Arrays.fill(largestElementToRightGreaterThanB, null);
        int largest = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < largest) {
                largestElementToRightGreaterThanB[i] = largest;
            } else {
                largest = arr[i];
            }
        }

        boolean subSequenceFound = false;
        int maxProduct = Integer.MIN_VALUE;
        int maxA = 0, maxB = 0, maxC = 0;

        for (int i = 0; i < n; i++) {
            var a = largestElementToLeftSmallerThanB[i];
            var b = arr[i];
            var c = largestElementToRightGreaterThanB[i];

            if (a == null || c == null) {
                continue;
            }

            int product = a * b * c;
            if (product > maxProduct) {
                subSequenceFound = true;
                maxProduct = product;
                maxA = a;
                maxB = b;
                maxC = c;
            }
        }


        if (!subSequenceFound) {
            return null;
        }

        return new Triplet<>(maxA, maxB, maxC);
    }

    public static Triplet<Integer, Integer, Integer> getIncreasingSubsequenceOfLengthThreeWithMaxProductApproach2(int[] arr) {
        if (arr == null
                || arr.length < 3) {
            return null;
        }

        boolean subSequenceFound = false;
        int maxProduct = Integer.MIN_VALUE;
        int maxA = 0, maxB = 0, maxC = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            int b = arr[i];

            int largestElementToLeftSmallerThanB = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                if (arr[j] < b
                        && arr[j] > largestElementToLeftSmallerThanB) {
                    largestElementToLeftSmallerThanB = arr[j];
                }
            }

            if (largestElementToLeftSmallerThanB == Integer.MIN_VALUE) {
                continue;
            }

            int largestElementToRightGreaterThanB = Integer.MIN_VALUE;
            for (int j = i + 1; j < arr.length; j++) {
                if (b < arr[j]
                        && arr[j] > largestElementToRightGreaterThanB) {
                    largestElementToRightGreaterThanB = arr[j];
                }
            }

            if (largestElementToRightGreaterThanB == Integer.MIN_VALUE) {
                continue;
            }

            int product = largestElementToLeftSmallerThanB * b * largestElementToRightGreaterThanB;
            if (product > maxProduct) {
                subSequenceFound = true;
                maxProduct = product;
                maxA = largestElementToLeftSmallerThanB;
                maxB = b;
                maxC = largestElementToRightGreaterThanB;
            }
        }

        if (!subSequenceFound) {
            return null;
        }

        return new Triplet<>(maxA, maxB, maxC);
    }

    public static Triplet<Integer, Integer, Integer> getIncreasingSubsequenceOfLengthThreeWithMaxProductNaive(int[] arr) {
        if (arr == null
                || arr.length < 3) {
            return null;
        }

        boolean subSequenceFound = false;
        int maxProduct = Integer.MIN_VALUE;
        int maxA = 0, maxB = 0, maxC = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            int a = arr[i];
            for (int j = i + 1; j < arr.length - 1; j++) {
                int b = arr[j];
                if (b <= a) {
                    continue;
                }

                for (int k = j + 1; k < arr.length; k++) {
                    int c = arr[k];
                    if (c <= b) {
                        continue;
                    }

                    int product = a * b * c;
                    if (product > maxProduct) {
                        subSequenceFound = true;
                        maxProduct = product;
                        maxA = a;
                        maxB = b;
                        maxC = c;
                    }
                }
            }
        }

        if (!subSequenceFound) {
            return null;
        }

        return new Triplet<>(maxA, maxB, maxC);
    }

    public static int minimumIncrementByKOperationsToMakeArrayElementsEqual(int[] arr, int k) {
        if(arr == null) {
            return -1;
        }

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        int operationCount = 0;
        for (int i = 0; i < arr.length; i++) {
            int diff = max - arr[i];
            if(diff % k != 0) {
                return -1;
            }

            operationCount += diff / k;
        }

        return operationCount;
    }

    public static int[] subArrayOfPositiveIntegersWithGivenSum(int[] arr, int sum) {
        if (arr == null) {
            return null;
        }

        int runningSum = arr[0];
        int start = 0;

        for (int i = 1; i < arr.length; i++) {
            while (runningSum > sum
                    && start < i - 1) {
                runningSum -= arr[start++];
            }

            if (runningSum == sum) {
                int[] result = new int[i - start];
                for (int j = start; j < i; j++) {
                    result[j - start] = arr[j];
                }

                return result;
            }

            if (i < arr.length) {
                runningSum += arr[i];
            }
        }

        return null;
    }
}
