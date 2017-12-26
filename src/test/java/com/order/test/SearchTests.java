package com.order.test;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Random;

/**
 * Created by HP on 2017/3/28.
 */
public class SearchTests {

    public void println(int[] array) {
        if (null != array && array.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(array[i]);
                sb.append(" , ");
            }
            System.out.println(sb);
        }
    }

    public int sequenceSearch(int[] data, int value) {
        for (int index = 0; index < data.length; index++) {
            if (data[index] == value) {
                return index;
            }
        }
        return -1;
    }

    public int dichotomySearch(int[] data, int value) {
        int index = -1;
        int low = 0;
        int high = data.length - 1;
        while (low <= high) {
            index = (low + high) / 2;
            if (data[index] > value) {
                high = index - 1;
            } else if (data[index] < value) {
                low = index + 1;
            } else {
                return index;
            }
        }
        return -1;
    }

    public int insertionSearch(int[] data, int value, int low, int high) {
        int index = low + (value - data[low]) / (data[high] - data[low]) * (high - low);
        if (data[index] == value) {
            return index;
        } else if (data[index] > value) {
            return insertionSearch(data, value, low, index - 1);
        } else if (data[index] < value) {
            return insertionSearch(data, value, index + 1, high);
        }
        return -1;
    }

    public void fibonacci(int[] data) {
        data[0] = 0;
        data[1] = 1;
        for (int i = 2; i < data.length; i++) {
            data[i] = data[i - 1] + data[i - 2];
        }
    }

    public int fibonacciSearch(int[] a, int num, int value) {
        int low = 0;
        int high = num - 1;
        int[] fibonacci = new int[max_size];
        fibonacci(fibonacci);
        int k = 0;
        while (num > fibonacci[k] - 1) {
            k++;
        }
        int[] temp = new int[fibonacci[k] - 1];
        for (int i = 0; i < fibonacci[k] - 1; i++) {
            if (i >= num) {
                temp[i] = data[num - 1];
            } else {
                temp[i] = data[i];
            }
        }
        while (low <= high) {
            int index = low + fibonacci[k - 1] - 1;
            if (value < temp[index]) {
                high = index - 1;
                k -= 1;
            } else if (value > temp[index]) {
                low = index + 1;
                k -= 1;
            } else {
                if (index < num) {
                    return index;
                } else {
                    return -1;
                }
            }
        }
        return -1;
    }

    private static int dataLength = 500;
    private static int max_size = dataLength * 2;
    ;
    private int[] data = new int[]{106, 593, 482, 200, 444, 506, 284, 276, 709, 598, 267, 299, 218, 680, 617, 347, 235, 83, 874, 339, 971, 886, 504, 806, 669, 101, 946, 846, 847, 716, 369, 356, 878, 191, 247, 252, 118, 468, 820, 485, 707, 618, 246, 957, 505, 299, 836, 156, 204, 197, 383, 327, 37, 414, 310, 749, 562, 485, 228, 382, 185, 374, 952, 63, 520, 437, 985, 889, 20, 44, 220, 906, 557, 239, 148, 707, 770, 13, 692, 656, 718, 569, 811, 695, 806, 166, 333, 232, 677, 305, 714, 515, 783, 440, 862, 278, 230, 849, 845, 525, 636, 869, 944, 908, 550, 731, 191, 209, 769, 512, 181, 652, 589, 860, 843, 144, 946, 713, 274, 563, 634, 357, 137, 761, 557, 390, 110, 159, 667, 509, 613, 235, 146, 595, 539, 233, 308, 896, 341, 281, 587, 762, 98, 428, 84, 714, 953, 76, 582, 611, 577, 923, 573, 372, 835, 124, 864, 797, 621, 998, 795, 310, 878, 767, 185, 762, 874, 269, 233, 506, 873, 792, 716, 687, 712, 923, 193, 839, 619, 905, 61, 353, 429, 444, 674, 203, 118, 0, 395, 176, 911, 566, 145, 32, 957, 357, 309, 175, 720, 91, 419, 31, 343, 682, 47, 521, 546, 829, 206, 303, 780, 1, 620, 85, 507, 609, 853, 662, 60, 425, 730, 707, 368, 772, 891, 718, 133, 513, 211, 421, 866, 328, 836, 761, 612, 299, 376, 822, 622, 967, 337, 981, 304, 66, 614, 333, 214, 94, 10, 337, 739, 251, 242, 707, 435, 508, 395, 837, 554, 15, 46, 544, 4, 80, 329, 646, 98, 759, 524, 913, 269, 525, 892, 167, 651, 918, 575, 781, 19, 928, 525, 600, 142, 259, 839, 490, 284, 736, 350, 201, 720, 123, 51, 804, 146, 125, 951, 111, 151, 970, 150, 455, 646, 986, 596, 623, 593, 523, 132, 759, 526, 795, 473, 758, 514, 843, 355, 878, 449, 719, 209, 318, 52, 762, 29, 702, 927, 430, 392, 411, 589, 89, 594, 450, 115, 811, 582, 209, 783, 140, 936, 913, 51, 490, 148, 528, 545, 992, 660, 683, 348, 715, 198, 392, 104, 150, 832, 625, 88, 173, 353, 29, 418, 934, 173, 968, 793, 267, 892, 659, 431, 596, 975, 342, 821, 154, 776, 114, 832, 246, 182, 891, 665, 528, 578, 866, 922, 820, 601, 113, 192, 598, 117, 184, 769, 782, 978, 5, 852, 959, 235, 47, 727, 97, 78, 91, 496, 726, 603, 959, 532, 594, 908, 596, 974, 643, 185, 562, 925, 968, 403, 859, 4, 278, 714, 697, 847, 878, 302, 411, 983, 98, 542, 642, 530, 742, 617, 171, 314, 883, 687, 405, 336, 182, 218, 505, 21, 384, 566, 114, 101, 62, 339, 266, 680, 249, 523, 76, 918, 912, 156, 24, 383, 937, 963, 744, 228, 342, 939, 298, 917, 68, 545, 958, 352, 543, 965, 903, 282, 18, 999, 782, 326, 563, 875, 986, 559, 858, 339, 596, 604, 577, 712, 983, 839, 479, 589, 85, 449, 655};

    @Ignore
    @Test
    public void getDataTest() {
        int[] data = new int[dataLength];
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(dataLength * 2);
            data[i] = data[i];
        }
        println(data);
    }

    @Test
    public void sequenceSearchTest() {
        int value = 617;
        int index = sequenceSearch(data, value);
        System.out.println("value is " + value);
        System.out.println("index is " + index);
        if (index >= 0) {
            System.out.println("data[" + index + "] is " + data[index]);
        }
    }

    @Test
    public void binarySearchTest() {
        int value = 617;
        data = OrderTests.dichotomySort(data);
        int index = dichotomySearch(data, value);
        System.out.println("value is " + value);
        System.out.println("index is " + index);
        if (index >= 0) {
            System.out.println("data[" + index + "] is " + data[index]);
        }
    }

    @Test
    public void insertSearchTest() {
        int value = 617;
        data = OrderTests.dichotomySort(data);
        int index = insertionSearch(data, value, 0, data.length - 1);
        System.out.println("value is " + value);
        System.out.println("index is " + index);
        if (index >= 0) {
            System.out.println("data[" + index + "] is " + data[index]);
        }
    }

    @Test
    public void fibonacciSearchTest() {
        int value = 617;
        data = OrderTests.dichotomySort(data);
        int index = fibonacciSearch(data, data.length, value);
        System.out.println("value is " + value);
        System.out.println("index is " + index);
        if (index >= 0) {
            System.out.println("data[" + index + "] is " + data[index]);
        }
    }

}
