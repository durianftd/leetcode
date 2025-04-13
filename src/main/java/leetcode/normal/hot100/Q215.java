package leetcode.normal.hot100;

import java.util.Arrays;

public class Q215 {

    int k = 0;

    public int findKthLargest(int[] nums, int k) {
        this.k = nums.length - k;
        quickSort(0, nums.length - 1, nums);
        return nums[this.k];
    }

    public void quickSort(int first, int end, int[] nums) {
        if (first >= end) {
            return;
        } else {
            int pivot = partition(first, end, nums);
            if (pivot == k) {
                return;
            } else if (pivot < k) {
                quickSort(pivot + 1, end, nums);
            } else {
                quickSort(first, pivot - 1, nums);
            }
        }
    }

    public int partition(int first, int end, int[] nums) {
        int i = first, j = end;
        while (i < j) {
            while (i < j && nums[i] <= nums[j]) {
                j--;
            }
            if (i < j) {
                swap(nums, i, j);
                i++;
            }
            while (i < j && nums[i] <= nums[j]) {
                i++;
            }
            if (i < j) {
                swap(nums, i, j);
                j--;
            }
        }
        return i;
    }

    public void swap(int[] nums, int a, int b) {
        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0};
        int kthLargest = new Q215().findKthLargest(nums, 2);
        System.out.println(kthLargest);
        System.out.println(Arrays.toString(nums));
    }
}
