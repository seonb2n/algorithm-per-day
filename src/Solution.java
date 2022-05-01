import java.util.*;

class Solution {

    static List<Character> nowPossibleAlphabet;
    static HashMap<Character, ArrayList<Node>> alphabetPosition;
    static char[][] tiles;

    public String solution(int m, int n, String[] board) {
        nowPossibleAlphabet = new ArrayList<>();
        alphabetPosition = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        tiles = new char[m + 1][n + 1];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                tiles[i][j] = board[i].charAt(j);
                char temp = tiles[i][j];
                if (Character.isUpperCase(temp)) {
                    if (!alphabetPosition.containsKey(temp)) {
                        alphabetPosition.put(temp, new ArrayList<>());
                    }
                    alphabetPosition.get(temp).add(new Node(i, j));
                }

            }
        }

        while (!alphabetPosition.isEmpty()) {
            for (char al : alphabetPosition.keySet()) {
                ArrayList<Node> temp = alphabetPosition.get(al);
                findPossibleAlphabet(al, temp);
            }

            if (nowPossibleAlphabet.size() == 0) {
                return "IMPOSSIBLE";
            }

            Collections.sort(nowPossibleAlphabet);
            //제일 앞에 해를 추가
            Character character = nowPossibleAlphabet.get(0);
            sb.append(character);

            //더해진 alpha 를 tiles 에서 삭제
            tiles[alphabetPosition.get(character).get(0).y][alphabetPosition.get(character).get(0).x] = '.';
            tiles[alphabetPosition.get(character).get(1).y][alphabetPosition.get(character).get(1).x] = '.';
            alphabetPosition.remove(character);

            //리스트 초기화
            nowPossibleAlphabet = new ArrayList<>();
        }
        return sb.toString().trim();
    }

    public static void findPossibleAlphabet(char nowAlphabet, List<Node> alphaPosition) {
        int nowY = alphaPosition.get(0).y;
        int nowX = alphaPosition.get(0).x;
        int targetY = alphaPosition.get(1).y;
        int targetX = alphaPosition.get(1).x;

        if (isWay(nowX, nowY, targetX, targetY, nowAlphabet)) {
            nowPossibleAlphabet.add(nowAlphabet);
        }

    }

    public static boolean isWay(int x1, int y1, int x2, int y2, char target) {
        if (x1 < x2) {
            //ㄱ 또는 ㄴ 경로를 탐색
            if (horizontalCheck(x1, x2, y1, target) && verticalCheck(y1, y2, x2, target)) {
                return true;
            }
            if (verticalCheck(y1, y2, x1, target) && horizontalCheck(x1, x2, y2, target)) {
                return true;
            }
        } else {
            if (horizontalCheck(x2, x1, y1, target) && verticalCheck(y1, y2, x2, target)) {
                return true;
            }
            if (verticalCheck(y1, y2, x1, target) && horizontalCheck(x2, x1, y2, target)) {
                return true;
            }
        }
        return false;
    }

    //수평상의 경로를 탐색한다. 항상 x1 보다 x2 가 더 크다.
    public static boolean horizontalCheck(int x1, int x2, int y, char target) {
        for (int i = x1; i < x2 + 1; i++) {
            if (tiles[y][i] != '.' && tiles[y][i] != target) {
                return false;
            }
        }
        return true;
    }

    // 수직상의 경로를 탐색한다. 항상 y1 보다 y2 가 더 크다.
    public static boolean verticalCheck(int y1, int y2, int x, char target) {
        for (int i = y1; i < y2 + 1; i++) {
            if (tiles[i][x] != '.' && tiles[i][x] != target) {
                return false;
            }
        }
        return true;
    }

    public static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}