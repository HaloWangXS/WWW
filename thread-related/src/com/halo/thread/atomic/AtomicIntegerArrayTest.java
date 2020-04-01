package com.halo.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 整形数组原子类使用
 */
public class AtomicIntegerArrayTest {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6};
        AtomicIntegerArray arrays = new AtomicIntegerArray(nums);
        for (int i = 0; i < arrays.length(); i++) {
            /**
             * 获取 index=i 位置元素的值
             */
            System.out.print(arrays.get(i) + " ");
        }
        System.out.println("\n---------------");
        /**
         * 返回 index=i 位置的当前的值，并将其设置为新值：newValue
         */
        int arr0 = arrays.getAndSet(0, 6);
        System.out.println("arr0 -> " + arr0 + " arrays[0] -> " + arrays.get(0));

        /**
         * 返回 index=i 位置的当前的值，并将其add值
         */
        int arr1 = arrays.getAndAdd(1, 6);
        System.out.println("arr1 -> " + arr1 + " arrays[1] -> " + arrays.get(1));

        /**
         * 返回 index=i 位置的当前的值，并将其+1
         */
        int arr2 = arrays.getAndIncrement(2);
        System.out.println("arr2 -> " + arr2 + " arrays[2] -> " + arrays.get(2));

        /**
         * 返回 index=i 位置的当前的值，并将其-1
         */
        int arr3 = arrays.getAndIncrement(3);
        System.out.println("arr3 -> " + arr3 + " arrays[3] -> " + arrays.get(3));

        boolean success = arrays.compareAndSet(4, 5, 10);
        System.out.println("success -> " + success + " arrays[4] -> " + arrays.get(4));
        for (int i = 0; i < arrays.length(); i++) {
            System.out.print(arrays.get(i) + " ");
        }
    }
}
