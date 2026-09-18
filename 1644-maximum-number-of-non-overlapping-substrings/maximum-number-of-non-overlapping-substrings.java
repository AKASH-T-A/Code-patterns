import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);

        // Find first and last occurrence
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> list = new ArrayList<>();

        // Find valid substring for each character
        for (int c = 0; c < 26; c++) {

            if (first[c] == n)
                continue;

            int left = first[c];
            int right = last[c];

            boolean valid = true;

            for (int i = left; i <= right; i++) {
                int x = s.charAt(i) - 'a';

                // Character appeared before left
                if (first[x] < left) {
                    valid = false;
                    break;
                }

                // Include all occurrences of this character
                right = Math.max(right, last[x]);
            }

            if (valid) {
                list.add(new int[]{left, right});
            }
        }

        // Sort by ending position
        list.sort((a, b) -> a[1] - b[1]);

        List<String> answer = new ArrayList<>();

        int previousEnd = -1;

        for (int[] interval : list) {
            int left = interval[0];
            int right = interval[1];

            if (left > previousEnd) {
                answer.add(s.substring(left, right + 1));
                previousEnd = right;
            }
        }

        return answer;
    }
}