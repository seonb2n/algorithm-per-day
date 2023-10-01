import java.util.*;

class Solution {

    static int[] x_moves = {-1, 0, 1, 0};
    static int[] y_moves = {0, 1, 0, -1};

    public List<String> findWords(char[][] board, String[] words) {
        // 단어로 이루어진 Trie 를 만든다.
        // board 를 탐색하면서 일치하는 Trie 의 경우가 있는지 탐색한다.
        TrieNode rootNode = buildTrie(words);
        List<String> result = new ArrayList<String>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                int nodeIndex = c - 'a';
                if (rootNode.next[nodeIndex] != null) {
                    board[i][j] = '#';
                    dfs(i, j, board, rootNode, result);
                    board[i][j] = c;
                }
            }
        }

        return result;
    }

    void dfs(int nowX, int nowY, char[][] board, TrieNode nowNode, List<String> result) {
        if (nowNode.word != null) {
            result.add(nowNode.word);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextX = nowX + x_moves[i];
            int nextY = nowY + y_moves[i];
            // 탐색이 가능하다면,
            if (inArea(nextX, nextY, board.length, board[0].length) && board[nextX][nextY] != '#') {
                // trie 에 다음 노드가 존재하는지 확인한다.
                int nextIndex = board[nextX][nextY] - 'a';
                // 다음 노드가 존재한다면
                if (nowNode.next[nextIndex] != null) {
                    char c = board[nextX][nextY];
                    board[nextX][nextY] = '#';
                    dfs(nextX, nextY, board, nowNode.next[nextIndex], result);
                    board[nextX][nextY] = c;
                }
            }
        }

    }

    boolean inArea(int x, int y, int N, int M) {
        return  0 <= x && x < N && 0 <= y && y < M;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        TrieNode nowNode;
        for (String str: words) {
            nowNode = root;
            char[] charArr = str.toCharArray();
            for (int i = 0; i < charArr.length; i++) {
                int nextIndex = charArr[i] - 'a';
                if (nowNode.next[nextIndex] == null) {
                    nowNode.next[nextIndex] = new TrieNode();
                }
                nowNode = nowNode.next[nextIndex];
            }
            nowNode.word = str;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

}