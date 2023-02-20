// Time Complexity : O(L^n) where L is average length of String and n is number of elements in array
// Space Complexity : O(nL) for Trie structure and backtrack will use n stacks
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

class Solution {
    //constructor for Trie
    class TrieNode {
        TrieNode[] children; //26 children for 26 lower case characters
        List<String> startsWith; //list of strings starting with prefix until that character
        public TrieNode() {
            children = new TrieNode[26];
            startsWith = new ArrayList<>();
        }
    }

    TrieNode root; //root of trie node

    //inserting words in the trie
    private void insert(String word) {
        TrieNode curr = root; //set the current

        for(int i = 0; i< word.length(); i++) {
            char c = word.charAt(i);
            if(curr.children[c - 'a'] == null) { // no node exist among children
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c-'a']; //move curr to child
            curr.startsWith.add(word); //add the word to list
        }
    }

    //implement prefix search
    private List<String> search(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i<prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(curr.children[c-'a'] == null){ //no node is present that start with prefix
                return new ArrayList<>();
            }
            curr = curr.children[c-'a'];
        }

        return curr.startsWith; //return entire startsWith list
    }

    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        root = new TrieNode(); //initialize the Trie
        result = new ArrayList<>();
        //build the Trie
        for(String word : words) {
            insert(word);
        }

        //chhose the word to start the wordSqaure
        List<String> list = new ArrayList<>();
        for (String word : words) {
            //action
            list.add(word);
            //recurse which responsible to build word square
            backtrack(list, word.length());
            //backtrack
            list.remove(list.size() - 1);
        }
    }

    private void backtrack(List<String> list, int k) {
        //base
        if(list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        //logic
        StringBuilder prefix = new StringBuilder();
        //get charachter for individual string residing at list.size()
        for(String word : list) {
            prefix.append(word.charAt(list.size()));
        }
        //get the list of string which have prefix equal to above formed prefix
        List<String> startsWith = search(prefix.toString());
        for(String word : startsWith) {
            //action
            list.add(word);
            //recurese
            backtrack(list, k);
            //backtrack
            list.remove(list.size()-1);
        }
    }
}