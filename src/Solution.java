import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    static int[] x_moves_line = {0, -1, 1, 0};
    static int[] y_moves_line = {-1, 0, 0, 1};

    static int[] x_moves_cross = {-1, 1, -1, 1};
    static int[] y_moves_cross = {-1, -1, 1, 1};

    static List<Node> personPosition;
    static char[][] map;


    public static void main(String[] args) {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        solution(places);
    }

    public static int[] solution(String[][] places) {
        int[] answer = new int[5];

        Arrays.fill(answer, 1);

        map = new char[5][5];

        for (int i = 0; i < 5; i++) {
            personPosition = new ArrayList<>();
            //맵 할당
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    map[j][k] = places[i][j].charAt(k);
                    if(map[j][k] == 'P') {
                        personPosition.add(new Node(j, k));
                    }
                }
            }

            //맵 내부의 p 를 기준으로 주변 8칸 확인
            for (Node nowNode : personPosition) {
                if (!checkNear(nowNode.y, nowNode.x)) {
                    answer[i] = 0;
                    break;
                }
            }
        }

        return answer;
    }

    public static boolean checkNear(int nowY, int nowX) {
        for (int k = 0; k < 4; k++) {
            int newY = nowY+y_moves_line[k];
            int newX = nowX+x_moves_line[k];
            if(inArea(newY, newX) && map[newY][newX] == 'P') {
                return false;
            }
        }

        for (int i = 0; i < 4; i++) {
            int newY = nowY+y_moves_line[i] * 2;
            int newX = nowX+x_moves_line[i] * 2;
            if(inArea(newY, newX) && map[newY][newX] == 'P') {
                if(map[nowY+y_moves_line[i]][nowX+x_moves_line[i]] != 'X') {
                    return false;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            int newY = nowY+y_moves_cross[i];
            int newX = nowX+x_moves_cross[i];
            if(inArea(newY, newX) && map[newY][newX] == 'P') {
                if(map[newY][nowX] != 'X' || map[nowY][newX] != 'X') {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean inArea(int y, int x) {
        return 0 <= y && y < 5 && 0 <= x && x < 5;
    }

    static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}