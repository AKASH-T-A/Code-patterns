class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];

        long[] dp = new long[k];

        for (int num : nums) {
            long[] next = new long[k];

            int value = num % k;

            // Start a new subarray
            next[value]++;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                int newRemainder = (r * value) % k;
                next[newRemainder] += dp[r];
            }

            dp = next;

            // Add subarrays ending at current index
            for (int r = 0; r < k; r++) {
                result[r] += dp[r];
            }
        }

        return result;
    }
}