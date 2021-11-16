package class067;

// 测试链接 : https://leetcode.com/problems/subarrays-with-k-different-integers/
public class Code05_SubarraysWithKDifferentIntegers {

	public static int subarraysWithKDistinct1(int[] arr, int k) {
		return numsMostK(arr, k) - numsMostK(arr, k - 1);
	}

	public static int numsMostK(int[] arr, int k) {
		int i = 0, res = 0;
		int[] counts = new int[arr.length + 1];
		for (int j = 0; j < arr.length; ++j) {
			if (counts[arr[j]] == 0) {
				k--;
			}
			counts[arr[j]]++;
			while (k < 0) {
				counts[arr[i]]--;
				if (counts[arr[i]] == 0) {
					k++;
				}
				i++;
			}
			res += j - i + 1;
		}
		return res;
	}

	// nums 数组，题目规定，nums中的数字，不会超过nums的长度
	// [ ]长度为5，0~5
	public static int subarraysWithKDistinct2(int[] nums, int k) {
		int n = nums.length;
		// <= k-1种数的窗口词频统计
		int[] minusOneWindow = new int[n + 1];
		int minusOneWindowLeft = 0;
		int minusOneWindowKinds = 0;
		// <= k种数的窗口词频统计
		int[] kWindow = new int[n + 1];
		int kWindowLeft = 0;
		int kWindowKinds = 0;
		int ans = 0;
		for (int r = 0; r < n; r++) {
			// 当前刚来到r位置！
			if (minusOneWindow[nums[r]] == 0) {
				minusOneWindowKinds++;
			}
			if (kWindow[nums[r]] == 0) {
				kWindowKinds++;
			}
			minusOneWindow[nums[r]]++;
			kWindow[nums[r]]++;
			while (minusOneWindowKinds == k) {
				if (minusOneWindow[nums[minusOneWindowLeft]] == 1) {
					minusOneWindowKinds--;
				}
				minusOneWindow[nums[minusOneWindowLeft++]]--;
			}
			while (kWindowKinds > k) {
				if (kWindow[nums[kWindowLeft]] == 1) {
					kWindowKinds--;
				}
				kWindow[nums[kWindowLeft++]]--;
			}
			ans += minusOneWindowLeft - kWindowLeft;
		}
		return ans;
	}

}