import java.util.*;

class Solution {

    public static void main(String[] args) {
    }

    public static class Trie {
        private Node rootNode;

        public Trie() {
            rootNode = new Node();
        }

        public void insert(String word) {
            Node thisNode = this.rootNode;
            for (int i = 0; i < word.length(); i++) {
                //해당 단어를 가진 child 가 있으면 가져오고 없으면 새로운 node 를 생성(computeIfAbsent) 사용
                thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new Node());
            }
            thisNode.setEndOfWord(true);
        }

        public boolean search(String word) {
            Node thisNode = this.rootNode;
            for (int i = 0; i < word.length(); i++) {
                char nowCharacter = word.charAt(i);
                Node node = thisNode.getChildNodes().get(nowCharacter);

                if(node == null) {
                    return false;
                }

                thisNode = node;
            }
            return thisNode.isEndOfWord();
        }
    }

    public static class Node {
        private Map<Character, Node> childNodes = new HashMap<>();
        boolean endOfWord;

        public Map<Character, Node> getChildNodes() {
            return childNodes;
        }

        public boolean isEndOfWord() {
            return endOfWord;
        }

        public void setEndOfWord(boolean endOfWord) {
            this.endOfWord = endOfWord;
        }
    }
}