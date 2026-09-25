class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression, 0, expression.length() - 1);
        
        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }

    private Set<String> parse(String s, int l, int r) {
        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        int i = l;

        while (i <= r) {
            if (s.charAt(i) == ',') {
                result.addAll(current);
                current = new HashSet<>();
                current.add("");
                i++;
            } 
            else if (s.charAt(i) == '{') {
                int j = findClosing(s, i);

                Set<String> inside = parse(s, i + 1, j - 1);
                current = multiply(current, inside);

                i = j + 1;
            } 
            else {
                Set<String> letter = new HashSet<>();
                letter.add(String.valueOf(s.charAt(i)));

                current = multiply(current, letter);
                i++;
            }
        }

        result.addAll(current);
        return result;
    }

    private int findClosing(String s, int start) {
        int count = 0;

        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                count++;
            } else if (s.charAt(i) == '}') {
                count--;

                if (count == 0) {
                    return i;
                }
            }
        }

        return -1;
    }

    private Set<String> multiply(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}