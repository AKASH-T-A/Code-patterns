import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            // Process all strings at current removal level
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();

                if (isValid(curr)) {
                    result.add(curr);
                    found = true;
                }

                // If we found valid strings at this level, don't go deeper
                if (found) continue;

                // Try removing one parenthesis at each position
                for (int j = 0; j < curr.length(); j++) {
                    char c = curr.charAt(j);
                    if (c != '(' && c != ')') continue;

                    String next = curr.substring(0, j) + curr.substring(j + 1);
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
            if (found) break; // Stop after processing the entire level where valid strings were found
        }

        return result;
    }

    // Helper: check if parentheses are balanced
    private boolean isValid(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                if (count == 0) return false;
                count--;
            }
        }
        return count == 0;
    }
}