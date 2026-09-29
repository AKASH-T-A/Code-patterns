class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Number of characters in every possible path
        int len = m + n - 1;

        // A valid parentheses string must have even length
        if (len % 2 != 0) {
            return false;
        }

        // First character must be '('
        if (grid[0][0] == ')') {
            return false;
        }

        // dp[i][j][balance]
        boolean[][][] dp = new boolean[m][n][len + 1];

        // Starting cell is '('
        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Skip starting cell
                if (i == 0 && j == 0) {
                    continue;
                }

                // Try every possible balance
                for (int balance = 0; balance <= len; balance++) {

                    int previousBalance;

                    if (grid[i][j] == '(') {
                        previousBalance = balance - 1;
                    } else {
                        previousBalance = balance + 1;
                    }

                    // Previous balance must be valid
                    if (previousBalance < 0 || previousBalance > len) {
                        continue;
                    }

                    // Come from the cell above
                    if (i > 0 && dp[i - 1][j][previousBalance]) {
                        dp[i][j][balance] = true;
                    }

                    // Come from the cell on the left
                    if (j > 0 && dp[i][j - 1][previousBalance]) {
                        dp[i][j][balance] = true;
                    }
                }
            }
        }

        // At the end, balance must be 0
        return dp[m - 1][n - 1][0];
    }
}