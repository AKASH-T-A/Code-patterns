class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        
        long[] endsWith = new long[26];
        long total = 0;
        
        for (char ch : s.toCharArray()) {
            int index = ch - 'a';
            
            // All prior subsequences + the single-character subsequence ch
            long newCount = (total + 1) % MOD;
            
            // Replace older subsequences ending in ch to avoid duplicates
            total = (total + newCount - endsWith[index] + MOD) % MOD;
            endsWith[index] = newCount;
        }
        
        return (int) total;
    }
}