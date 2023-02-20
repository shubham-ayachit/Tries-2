// Time Complexity : O(k(m+n)) where k is number of queries, m is avg length of query and n is avg length of pattern
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        if(queries == null || queries.length == 0) return new ArrayList<>();

        List<Boolean> result = new ArrayList<>();

        for(String query : queries) {
            int i = 0;
            boolean isMatching = false;

            for(int j = 0; j<query.length(); j++) {
                if(i<pattern.length() && (query.charAt(j) == pattern.charAt(i))) {
                    i++;
                    if(i == pattern.length()) {
                        isMatching = true;
                    }
                }
                else if(query.charAt(j) >= 'A' && query.charAt(j) <= 'Z') {
                    isMatching = false;
                    break;
                }
            }
            result.add(isMatching);
        }

        return result;
    }
}