class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                minOpen++;
                maxOpen++;
            } 
            else if (c == ')') {
                minOpen--;
                maxOpen--;
            } 
            else { // '*'
                minOpen--; // '*' acts as ')'
                maxOpen++; // '*' acts as '('
            }

            // Too many ')'
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot be negative
            minOpen = Math.max(minOpen, 0);
        }

        // Valid only if we can have exactly 0 open brackets
        return minOpen == 0;
    }
}