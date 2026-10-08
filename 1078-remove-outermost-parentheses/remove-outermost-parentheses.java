class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int depth = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Add '(' only if it is not the outermost one
                if (depth > 0) {
                    result.append(c);
                }
                depth++;
            } else {
                depth--;

                // Add ')' only if it is not the outermost one
                if (depth > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}