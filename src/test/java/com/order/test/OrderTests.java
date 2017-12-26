package com.order.test;

import org.junit.Test;

import java.util.Random;

/**
 * Created by HP on 2017/3/21.
 */
public class OrderTests {

    public static boolean insert(int[] array, int end, int index, int num) {
        for (int i = end; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = num;
        return true;
    }

    public static boolean dichotomyInsert(int[] array, int start, int end, int num) {
        boolean ordered = false;
        int small = array[start];
        int big = array[end];
        if (num >= big) {
            return insert(array, endIndex, end + 1, num);
        } else if (num <= small) {
            return insert(array, endIndex, start, num);
        } else if (num > small && num < big) {
            if (end - start == 1) {
                return insert(array, endIndex, end, num);
            } else {
                int index = (start + end) / 2;
                int current = array[index];
                int before = array[index - 1];
                int after = array[index + 1];
                if (num >= current && num <= after) {
                    return insert(array, endIndex, index + 1, num);
                } else if (num >= before && num <= current) {
                    return insert(array, endIndex, index, num);
                } else if (num > after) {
                    return dichotomyInsert(array, index + 1, end - 1, num);
                } else if (num < before) {
                    return dichotomyInsert(array, start + 1, index - 1, num);
                }
            }
        }
        return ordered;
    }

    private static int endIndex = 0;

    public static int[] myDichotomySort(int[] data) {
        int[] array = new int[data.length];
        if (data.length > 1) {
            array[0] = data[0];
            for (endIndex = 1; endIndex < data.length; endIndex++) {
                int num = data[endIndex];
                dichotomyInsert(array, 0, endIndex - 1, num);
            }
        }
        return array;
    }

    public static int[] insertSortData(int[] data) {
        int[] array = new int[data.length];
        if (data.length > 1) {
            array[0] = data[0];
            for (int i = 1; i < data.length; i++) {
                int num = data[i];
                for (int j = i; j >= 0; j--) {
                    if (j == 0) {
                        array[0] = num;
                    } else if (num < array[j - 1]) {
                        array[j] = array[j - 1];
                    } else {
                        array[j] = num;
                        break;
                    }
                }
            }
        }
        return array;
    }

    public static void println(int[] array) {
        if (null != array && array.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(array[i]);
                sb.append(" , ");
            }
            System.out.println(sb);
        }
    }

    public static int[] dichotomySort(int[] data) {
        int[] array = new int[data.length];
        for (int i = 0; i < array.length; i++) {
            int num = data[i];
            if (i == 0) {
                array[i] = num;
            } else {
                for (int j = 0, k = i - 1; j < i && k >= 0; ) {
                    if (array[(j + k) / 2] >= num) {
                        if ((j + k) / 2 == 0) {
                            insert(array, i, (j + k) / 2, num);
                            break;
                        } else if (array[(j + k) / 2 - 1] <= num) {
                            insert(array, i, (j + k) / 2, num);
                            break;
                        } else {
                            k = (k + j) / 2 - 1;
                        }
                    } else if (array[(j + k) / 2] < num) {
                        if ((j + k) / 2 == i - 1) {
                            array[i] = num;
                            break;
                        } else {
                            j = (k + j) / 2 + 1;
                        }
                    }
                }
            }
        }
        return array;
    }

    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static int partition(int[] data, int low, int high) {
        while (low < high) {
            int refer = data[low];
            while (low < high && data[high] >= refer) high--;
            swap(data, low, high);
            while (low < high && data[low] <= refer) low++;
            swap(data, low, high);
        }
        return low;
    }

    public static void quickSort(int[] data, int low, int high) {
        if (high > low) {
            int index = partition(data, low, high);
            if (index > low && index < high) {
                quickSort(data, low, index - 1);
                quickSort(data, index + 1, high);
            } else if (index == low) {
                quickSort(data, low + 1, high);
            } else if (index == high) {
                quickSort(data, low, high - 1);
            }
        }
    }

    public static void bubbleSort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = data.length - 1; j > i; j--) {
                if (data[j] < data[j - 1]) {
                    swap(data, j, j - 1);
                }
            }
        }
    }

    public static void selectionSort(int[] data) {
        if (data.length > 0) {
            for (int i = 0; i < data.length - 1; i++) {
                int min = i;
                int max = data.length - i - 1;
                for (int j = i + 1; j < data.length - i - 1; j++) {
                    if (data[j] < data[min]) {
                        min = j;
                        continue;
                    }
                    if (data[max] < data[j]) {
                        max = j;
                    }
                }
                swap(data, i, min);
                swap(data, max, data.length - i - 1);
            }
        }
    }

    public static void minHeapFixUp(int[] data, int index) {
        int j = (index - 1) / 2;
        int temp = data[index];
        while (j >= 0 && index != 0) {
            if (data[j] > temp) {
                data[index] = data[j];
                index = j;
                j = (index - 1) / 2;
            } else {
                break;
            }
        }
        data[index] = temp;
    }

    public static void minHeapFixUpSort(int[] data) {
        for (int index = 0; index < data.length; index++) {
            int j = (index - 1) / 2;
            int temp = data[index];
            while (j >= 0 && index != 0) {
                if (data[j] > temp) {
                    data[index] = data[j];
                    index = j;
                    j = (index - 1) / 2;
                } else {
                    break;
                }
            }
            data[index] = temp;
        }
    }

    public static void minHeapFixDown(int[] data, int index, int num) {
        int childIndex = index * 2 + 1;
        int temp = data[index];
        while (childIndex < num) {
            if (childIndex + 1 < num && data[childIndex] > data[childIndex + 1]) {
                childIndex++;
            }
            if (data[childIndex] < temp) {
                data[index] = data[childIndex];
                index = childIndex;
                childIndex = index * 2 + 1;
            } else {
                break;
            }
        }
        data[index] = temp;
    }

    public static void minHeapDeleteNumber(int[] data, int num) {
        swap(data, 0, num - 1);
        minHeapFixDown(data, 0, num - 1);
    }

    public static void buildMinHeap(int[] data) {
        for (int i = 0; i < data.length; i++) {
            minHeapFixUp(data, i);
        }
    }

    public static void makeMinHeap(int[] data) {
        for (int i = data.length / 2 - 1; i >= 0; i--) {
            minHeapFixDown(data, i, data.length);
        }
    }

    public static void minHeapSort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            swap(data, 0, data.length - i - 1);
            minHeapFixDown(data, 0, data.length - i - 1);
        }
    }

    public static void shellInsertSort(int a[], int n, int dk) {
        for (int i = dk; i < n; ++i) {
            if (a[i] < a[i - dk]) {          //若第i个元素大于i-1元素，直接插入。小于的话，移动有序表后插入
                int j = i - dk;
                int x = a[i];           //复制为哨兵，即存储待排序元素
                a[i] = a[i - dk];         //首先后移一个元素
                while (j >= 0 && x < a[j]) {     //查找在有序表的插入位置
                    a[j + dk] = a[j];
                    j -= dk;             //元素后移
                }
                a[j + dk] = x;            //插入到正确位置
            }
        }
    }

    /**
     * 先按增量d（n/2,n为要排序数的个数进行希尔排序
     */
    public static void shellSort(int a[], int n) {
        int dk = n / 2;
        while (dk >= 1) {
            shellInsertSort(a, n, dk);
            dk = dk / 2;
        }
    }

    public static void insertSort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i] < data[i - 1]) {
                int j = i - 1;
                int x = data[i];
                data[i] = data[j];
                while (j >= 0 && x < data[j]) {
                    data[j + 1] = data[j];
                    j--;
                }
                data[j + 1] = x;
            }
        }
    }

    /**
     * <pre>
     * 二路归并
     * 原理：将两个有序表合并和一个有序表
     * </pre>
     * @param a
     * @param s 第一个有序表的起始下标
     * @param m 第二个有序表的起始下标
     * @param t 第二个有序表的结束小标
     */
    private static void merge(int[] a, int s, int m, int t) {
        int[] tmp = new int[t - s + 1];
        int i = s, j = m, k = 0;
        while (i < m && j <= t) {
            if (a[i] <= a[j]) {
                tmp[k] = a[i];
                k++;
                i++;
            } else {
                tmp[k] = a[j];
                j++;
                k++;
            }
        }
        while (i < m) {
            tmp[k] = a[i];
            i++;
            k++;
        }
        while (j <= t) {
            tmp[k] = a[j];
            j++;
            k++;
        }
        System.arraycopy(tmp, 0, a, s, tmp.length);
    }

    /**
     * @param a
     * @param s
     * @param len 每次归并的有序集合的长度
     */
    public static void mergeSort(int[] a, int s, int len) {
        int size = a.length;
        int mid = size / (len << 1);
        int c = size & ((len << 1) - 1);
        // -------归并到只剩一个有序集合的时候结束算法-------//
        if (mid == 0)
            return;
        // ------进行一趟归并排序-------//
        for (int i = 0; i < mid; ++i) {
            s = i * 2 * len;
            merge(a, s, s + len, (len << 1) + s - 1);
        }
        // -------将剩下的数和倒数一个有序集合归并-------//
        if (c != 0)
            merge(a, size - c - 2 * len, size - c, size - 1);
        // -------递归执行下一趟归并排序------//
        mergeSort(a, 0, 2 * len);
    }

    private int dataLength = 50000;

    @Test
    public void shellSortTest() {
        long startMils = System.currentTimeMillis();
        int[] shellSortData = new int[dataLength];
        int[] insertSortData = new int[dataLength];
        Random random = new Random();
        for (int i = 0; i < shellSortData.length; i++) {
            shellSortData[i] = random.nextInt(dataLength * 2);
            insertSortData[i] = shellSortData[i];
        }
        long initMils = System.currentTimeMillis();
        shellSort(shellSortData, shellSortData.length);
        long shellSortMils = System.currentTimeMillis();
        insertSort(insertSortData);
        long insertSortMils = System.currentTimeMillis();
        println(insertSortData);
        println(shellSortData);
        System.out.println("init use time mils : " + (initMils - startMils));
        System.out.println("shell sort use time mils : " + (shellSortMils - initMils));
        System.out.println("insert sort use time mils : " + (insertSortMils - shellSortMils));
    }

    @Test
    public void minHeapSortTest() {
        long startMils = System.currentTimeMillis();
        int[] data = new int[dataLength];
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(dataLength * 2);
        }
        long initMils = System.currentTimeMillis();
        makeMinHeap(data);
        println(data);
//        minHeapDeleteNumber(data, data.length);
        long buildMils = System.currentTimeMillis();
        minHeapSort(data);
        long sortEndMils = System.currentTimeMillis();
        println(data);
        System.out.println("init use time mils : " + (initMils - startMils));
        System.out.println("build min heap use time mils : " + (buildMils - initMils));
        System.out.println("min heap sort use time mils : " + (sortEndMils - buildMils));
    }

    @Test
    public void partitionTest() {
        int[] data = new int[]{3, 2, 4};
        partition(data, 0, 2);
        println(data);
    }

    @Test
    public void quickSortTest() {
        int[] data = new int[]{3, 2, 4};
        partition(data, 0, 2);
        println(data);
    }

    @Test
    public void insertortTest() {
        long startTimes = System.currentTimeMillis();
        int[] selectionData = new int[dataLength];
        int[] bubbleData = new int[dataLength];
        Random random = new Random();
        for (int i = 0; i < selectionData.length; i++) {
            selectionData[i] = random.nextInt(dataLength * 2);
            bubbleData[i] = selectionData[i];
        }
        long initTimes = System.currentTimeMillis();
        selectionSort(selectionData);
        long dichotomyTimes = System.currentTimeMillis();
        insertSort(bubbleData);
        long insertTimes = System.currentTimeMillis();
        println(selectionData);
        println(bubbleData);
        System.out.println("init use time mils : " + (initTimes - startTimes));
        System.out.println("selection sort use time mils : " + (dichotomyTimes - initTimes));
        System.out.println("insert sort use time mils : " + (insertTimes - dichotomyTimes));
    }

    @Test
    public void selectionHeapSortTest() {
        long startTimes = System.currentTimeMillis();
        int[] selectionData = new int[dataLength];
        int[] bubbleData = new int[dataLength];
        Random random = new Random();
        for (int i = 0; i < selectionData.length; i++) {
            selectionData[i] = random.nextInt(dataLength * 2);
            bubbleData[i] = selectionData[i];
        }
        long initTimes = System.currentTimeMillis();
        selectionSort(selectionData);
        long dichotomyTimes = System.currentTimeMillis();
        bubbleSort(bubbleData);
        long insertTimes = System.currentTimeMillis();
        println(bubbleData);
        println(selectionData);
        System.out.println("init use time mils : " + (initTimes - startTimes));
        System.out.println("selection sort use time mils : " + (dichotomyTimes - initTimes));
        System.out.println("bubble sort use time mils : " + (insertTimes - dichotomyTimes));
    }

    @Test
    public void selectionBubbleSortTest() {
        long startTimes = System.currentTimeMillis();
        int[] selectionData = new int[dataLength];
        int[] bubbleData = new int[dataLength];
        Random random = new Random();
        for (int i = 0; i < selectionData.length; i++) {
            selectionData[i] = random.nextInt(dataLength * 2);
            bubbleData[i] = selectionData[i];
        }
        long initTimes = System.currentTimeMillis();
        selectionSort(selectionData);
        long dichotomyTimes = System.currentTimeMillis();
        bubbleSort(bubbleData);
        long insertTimes = System.currentTimeMillis();
        println(bubbleData);
        println(selectionData);
        System.out.println("init use time mils : " + (initTimes - startTimes));
        System.out.println("selection sort use time mils : " + (dichotomyTimes - initTimes));
        System.out.println("bubble sort use time mils : " + (insertTimes - dichotomyTimes));
    }

    @Test
    public void dichotomyInsertSortTest() {
        long startTimes = System.currentTimeMillis();
        int[] data = new int[dataLength];
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(dataLength * 2);
        }
        long initTimes = System.currentTimeMillis();
        int[] array = dichotomySort(data);
        long dichotomyTimes = System.currentTimeMillis();
        int[] sortArray = insertSortData(data);
        long insertTimes = System.currentTimeMillis();
        println(array);
        println(sortArray);
        System.out.println("init use time mils : " + (initTimes - startTimes));
        System.out.println("dichotomy sort use time mils : " + (dichotomyTimes - initTimes));
        System.out.println("insert sort use time mils : " + (insertTimes - dichotomyTimes));
    }

    @Test
    public void dichotomySelectionSortTest() {
        long startTimes = System.currentTimeMillis();
        int[] data = new int[dataLength];
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(dataLength * 2);
        }
        long initTimes = System.currentTimeMillis();
        int[] array = myDichotomySort(data);
        long dichotomySortMils = System.currentTimeMillis();
        selectionSort(data);
        long endTimes = System.currentTimeMillis();
        println(array);
        println(data);
        System.out.println("init use time mils : " + (initTimes - startTimes));
        System.out.println("dichotomy sort use time mils : " + (dichotomySortMils - initTimes));
        System.out.println("selection sort use time mils : " + (endTimes - dichotomySortMils));
    }

    @Test
    public void dichotomyBubbleSortTest() {
        long startTimes = System.currentTimeMillis();
        int[] data = new int[dataLength];
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(dataLength * 2);
        }
        long initTimes = System.currentTimeMillis();
        int[] array = dichotomySort(data);
        long dichotomyTimes = System.currentTimeMillis();
        bubbleSort(data);
        long bubbleSortTimes = System.currentTimeMillis();
        println(array);
        println(data);
        System.out.println("init use time mils : " + (initTimes - startTimes));
        System.out.println("dichotomy sort use time mils : " + (dichotomyTimes - initTimes));
        System.out.println("bubble sort use time mils : " + (bubbleSortTimes - dichotomyTimes));
    }

    @Test
    public void dichotomyQuickSortTest() {
        long startTimes = System.currentTimeMillis();
        int[] data = new int[dataLength];
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(dataLength * 2);
        }
        long initTimes = System.currentTimeMillis();
        int[] array = dichotomySort(data);
        long dichotomyTimes = System.currentTimeMillis();
        quickSort(data, 0, data.length - 1);
        long quickSortTimes = System.currentTimeMillis();
        println(array);
        println(data);
        System.out.println("init use time mils : " + (initTimes - startTimes));
        System.out.println("dichotomy sort use time mils : " + (dichotomyTimes - initTimes));
        System.out.println("quick sort use time mils : " + (quickSortTimes - dichotomyTimes));
    }

}
