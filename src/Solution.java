import java.util.*;
import java.util.stream.Collectors;

class Solution {
    static int w;
    static int h;

    static ArrayDeque<Integer> leftList;
    static ArrayDeque<Integer> rightList;
    static LinkedList<ArrayDeque<Integer>> bodyList;

    public static void main(String[] args) {
        int[][] rc= {{1,2,3},{4,5,6}, {7,8,9}};
        String[] ops = {"Rotate", "ShiftRow"};
        solution(rc, ops);
    }

    public static int[][] solution(int[][] rc, String[] operations) {
        h = rc.length;
        w = rc[0].length;

        leftList = new ArrayDeque<>();
        rightList = new ArrayDeque<>();
        bodyList = new LinkedList<>();

        //w 가 2인 경우에는 bodyList 가 없으므로 다르게 처리해야 한다.
        if (w == 2) {
            for (int i = 0; i < h; i++) {
                leftList.add(rc[i][0]);
                rightList.add(rc[i][w-1]);
            }
        } else {
            for (int i = 0; i < h; i++) {
                leftList.add(rc[i][0]);
                rightList.add(rc[i][w-1]);
                ArrayDeque<Integer> body = new ArrayDeque<>();
                for (int j = 1; j < w-1; j++) {
                    body.add(rc[i][j]);
                }
                bodyList.add(body);
            }
        }

        for (String op : operations) {
            if (op.equals("Rotate")) {
                rotate();
            } else {
                shiftRow();
            }
        }

        int[][] answer = new int[h][w];
        for (int i = 0; i < bodyList.size(); i++) {
            ArrayDeque<Integer> body = bodyList.get(i);
            int j = 1;
            while (!body.isEmpty()) {
                answer[i][j] = body.pollFirst();
                j++;
            }
        }
        int i = 0;
        while (!leftList.isEmpty()) {
            answer[i][0] = leftList.pollFirst();
            answer[i][w-1] = rightList.pollFirst();
            i++;
        }

        return answer;
    }

    public static void shiftRow() {
        // leftList, rightList 를 하나씩 밑으로 내린다.
        leftList.addFirst(leftList.pollLast());
        rightList.addFirst(rightList.pollLast());

        if (!bodyList.isEmpty()) {
            bodyList.addFirst(bodyList.pollLast());
        }
    }

    public static void rotate() {
        if (!bodyList.isEmpty()) {
            bodyList.getFirst().addFirst(leftList.pollFirst());
            rightList.addFirst(bodyList.getFirst().pollLast());
            bodyList.getLast().addLast(rightList.pollLast());
            leftList.addLast(bodyList.getLast().pollFirst());
        } else {
            rightList.addFirst(leftList.pollFirst());
            leftList.addLast(rightList.pollLast());
        }
    }
}

