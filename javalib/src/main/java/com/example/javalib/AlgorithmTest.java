package com.example.javalib;

import java.util.Arrays;

public class AlgorithmTest {

    public static void main(String[] args) {
        int[] nums = {2, 15, 6, 19, 7, 9, 11, 23, 29};
        Arrays.sort(nums); // 对数组进行排序
        System.out.println("排序后的数组：" + Arrays.toString(nums));

        int target = 6;

        int index = binarySearch(nums, target);
        if (index != -1) {
            System.out.println("目标值 " + target + " 对应的索引是 " + index);
        } else {
            System.out.println("未找到目标值 " + target);
        }

    }

    /**
     * 二分查找
     * 1、数组必须有序, 因为判断目标值在左/右半部分的前提是需要有序
     * 2、计算中间索引int防止溢出
     * 3、O(logn) 的时间复杂度在大量有序数据中查找时表现出色
     * @param nums 数据源
     * @param target 目标值
     * @return 索引 -1 表示未找到
     */
    public static int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1; // 数组为空或长度为0，直接返回-1
        }

        int left = 0;
        int right = nums.length - 1; // 注意这里是 nums.length - 1

        // 循环条件是 left <= right
        // 意味着查找范围是 [left, right]，当 left > right 时，范围为空，查找结束
        while (left <= right) {
            // 计算中间索引，防止 left + right 溢出 (两个接近int最大范围的数相加，int最大 2,147,483,647)
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // 找到目标值，返回索引
            } else if (nums[mid] < target) {
                // 目标值在右半部分，更新 left 边界
                left = mid + 1;
            } else {
                // 目标值在左半部分，更新 right 边界
                right = mid - 1;
            }
        }

        // 循环结束仍未找到，说明目标值不存在
        return -1;
    }
}
