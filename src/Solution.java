import java.util.*;

class Solution {

    static HashMap<Integer, Node> numberMap;
    static boolean isLeftHand;

    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();

        if(hand.equals("left")) {
            isLeftHand = true;
        }

        numberMap = new HashMap<>();

        numberMap.put(1, new Node(0, 0));
        numberMap.put(2, new Node(0, 1));
        numberMap.put(3, new Node(0, 2));
        numberMap.put(4, new Node(1, 0));
        numberMap.put(5, new Node(1, 1));
        numberMap.put(6, new Node(1, 2));
        numberMap.put(7, new Node(2, 0));
        numberMap.put(8, new Node(2, 1));
        numberMap.put(9, new Node(2, 2));
        numberMap.put(0, new Node(3, 1));
        numberMap.put(11, new Node(3, 0));
        numberMap.put(12, new Node(3, 2));

        Node leftFinger = numberMap.get(11);
        Node rightFinger = numberMap.get(12);

        for (int i = 0; i < numbers.length; i++) {
            int nowNumber = numbers[i];
            if(isLeftFingerGo(rightFinger, leftFinger, nowNumber)) {
                leftFinger = numberMap.get(nowNumber);
                answer.append("L");
            }
            else {
                rightFinger = numberMap.get(nowNumber);
                answer.append("R");
            }
        }

        return answer.toString();
    }

    public boolean isLeftFingerGo(Node rightPosition, Node leftPosition, int nextNumber) {
        if(nextNumber == 1 || nextNumber == 4 || nextNumber == 7) {
            return true;
        }
        if(nextNumber == 3 || nextNumber == 6 || nextNumber == 9) {
            return false;
        }
        // 숫자가 2,5,8,0 인 경우 가까운 쪽이 간다.
        Node numberPosition = numberMap.get(nextNumber);
        if(numberPosition.getAbs(rightPosition) > numberPosition.getAbs(leftPosition)) {
            return true;
        }
        if(numberPosition.getAbs(rightPosition) < numberPosition.getAbs(leftPosition)) {
            return false;
        }
        //둘 사이의 거리가 같을 때는 주로 쓰는 손 쪽으로 간다.
        return isLeftHand;
    }


    public static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getAbs(Node n) {
            return Math.abs(this.y - n.y) + Math.abs(this.x - n.x);
        }
    }
}