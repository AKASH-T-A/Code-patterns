import java.util.*;

class Solution {

    static class State {
        long score;
        long code;

        State(long score, long code) {
            this.score = score;
            this.code = code;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        // Store: [left, right, weight, originalIndex]
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by starting point
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // Find the next interval whose left > current right
        int[] next = new int[n];

        int[] starts = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = arr[i][0];
        }

        for (int i = 0; i < n; i++) {
            next[i] = upperBound(starts, arr[i][1]);
        }

        /*
         * dp[i][k] = best answer using intervals from i onward
         * when we can still select at most k intervals.
         */
        State[][] dp = new State[n + 1][5];

        // Empty answer
        long EMPTY = 0xFFFFFFFFFFFFFFFFL;

        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, EMPTY);
        }

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                // Option 1: Skip current interval
                State skip = dp[i + 1][k];

                // Option 2: Take current interval
                State takeNext = dp[next[i]][k - 1];

                long takeScore = arr[i][2] + takeNext.score;

                long takeCode = insert(
                        takeNext.code,
                        arr[i][3]
                );

                State take = new State(takeScore, takeCode);

                // Choose maximum score.
                // If scores are equal, choose lexicographically smaller indices.
                if (take.score > skip.score ||
                        (take.score == skip.score &&
                                Long.compareUnsigned(take.code, skip.code) < 0)) {

                    dp[i][k] = take;

                } else {
                    dp[i][k] = skip;
                }
            }

            dp[i][0] = new State(0, EMPTY);
        }

        return decode(dp[0][4].code);
    }

    // First index whose value > target
    private int upperBound(int[] arr, int target) {

        int left = 0;
        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /*
     * Store up to 4 indices in a long.
     * Each index uses 16 bits.
     *
     * Example:
     * [1, 5, 8, 20]
     *
     * is stored as:
     * 0001 | 0005 | 0008 | 0020
     *
     * Indices are inserted in sorted order.
     */
    private long insert(long code, int index) {

        int[] a = new int[4];

        for (int i = 0; i < 4; i++) {
            a[i] = (int) ((code >>> (48 - i * 16)) & 0xFFFF);
        }

        // Find position for the new index
        int pos = 0;

        while (pos < 4 && a[pos] < index) {
            pos++;
        }

        // Shift elements right
        for (int i = 3; i > pos; i--) {
            a[i] = a[i - 1];
        }

        a[pos] = index;

        long result = 0;

        for (int i = 0; i < 4; i++) {
            result |= ((long) a[i] & 0xFFFFL)
                    << (48 - i * 16);
        }

        return result;
    }

    private int[] decode(long code) {

        int count = 0;

        for (int i = 0; i < 4; i++) {
            int value = (int) ((code >>> (48 - i * 16)) & 0xFFFF);

            if (value != 0xFFFF) {
                count++;
            }
        }

        int[] ans = new int[count];

        for (int i = 0; i < count; i++) {
            ans[i] = (int) ((code >>> (48 - i * 16)) & 0xFFFF);
        }

        return ans;
    }
}