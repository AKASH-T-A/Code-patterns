/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int count = 0;
    
    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return count;
    }
    
    // Returns int[]{sum of subtree, number of nodes in subtree}
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        
        int sum = node.val + left[0] + right[0];
        int cnt = 1 + left[1] + right[1];
        
        if (sum / cnt == node.val) {
            count++;
        }
        
        return new int[]{sum, cnt};
    }
}