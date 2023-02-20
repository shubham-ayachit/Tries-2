// Time Complexity : O(n log k) 
// Space Complexity : O(n) for map
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length == 0) return new int[]{};

        int[] result = new int[k];
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for(int key : map.keySet()) {
            pq.add(new int[] {key, map.get(key)});
            if(pq.size() > k) {
                pq.poll();
            }
        }

        while(!pq.isEmpty()) {
            result[k-1] = pq.poll()[0];
            k--;
        }

        return result;
    }
}