class Solution {
    public long countCommas(long n) {
        long answer = 0;
        
        for (long threshold = 1_000; threshold <= n; threshold *= 1_000) {
            answer += n - threshold + 1;
        }
        
        return answer;
    }
}