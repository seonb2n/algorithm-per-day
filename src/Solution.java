import java.util.*;

class Solution {
    static Stack<Integer> deletedStack;
    static Node[] nodeList;
    static int nowPosition;

    public static String solution(int n, int k, String[] cmd) {
        deletedStack = new Stack<>();
        nodeList = new Node[n];
        StringBuilder sb = new StringBuilder();

        nodeList[0] = new Node(-1, 1);
        for (int i = 1; i < n-1; i++) {
            nodeList[i] = new Node(i-1, i+1);
        }
        nodeList[n-1] = new Node(n-2, -1);

        nowPosition = k;

        for (int i = 0; i < cmd.length; i++) {
            String nowCommand = cmd[i];
            int command = getCommand(nowCommand);
            switch (command) {
                case 1: goDown(nowCommand);
                break;
                case 2: goUp(nowCommand);
                break;
                case 3: delete();
                break;
                case 4: rollBack();
                break;
            }
        }

        char[] answers = new char[n];
        for (int i = 0; i < n; i++) {
            answers[i] = 'O';
        }

        while (!deletedStack.empty()) {
            answers[deletedStack.pop()] = 'X';
        }

        for (int i = 0; i < n; i++) {
            sb.append(answers[i]);
        }

        return sb.toString().trim();
    }

    //D 1
    //U 2
    //C 3
    //Z 4
    public static int getCommand(String cmd) {
        if(cmd.contains("D")) {
            return 1;
        }
        if(cmd.contains("U")) {
            return 2;
        }
        if(cmd.contains("C")) {
            return 3;
        }
        if(cmd.contains("Z")) {
            return 4;
        }
        return 0;
    }

    public static void goDown(String cmd) {
        String[] s = cmd.split(" ");
        for (int i = 0; i < Integer.parseInt(s[1]); i++) {
            nowPosition = nodeList[nowPosition].nextNode;
        }

    }

    public static void goUp(String cmd) {
        String[] s = cmd.split(" ");
        for (int i = 0; i < Integer.parseInt(s[1]); i++) {
            nowPosition = nodeList[nowPosition].prevNode;
        }
    }

    public static void delete() {
        //지금 위치가 제일 마지막인 경우
        if(nodeList[nowPosition].nextNode == -1) {
            deletedStack.push(nowPosition);
            Node nowNode = nodeList[nowPosition];
            nodeList[nowNode.prevNode].nextNode = -1;
            nowPosition = nowNode.prevNode;
        }
        //지금 위치가 제일 앞인 경우
        else if(nodeList[nowPosition].prevNode == -1) {
            deletedStack.push(nowPosition);
            Node nowNode = nodeList[nowPosition];
            nodeList[nowNode.nextNode].prevNode = -1;
            nowPosition = nowNode.nextNode;
        }
        else {
            deletedStack.push(nowPosition);
            Node nowNode = nodeList[nowPosition];
            nodeList[nowNode.prevNode].nextNode = nowNode.nextNode;
            nodeList[nowNode.nextNode].prevNode = nowNode.prevNode;
            nowPosition = nowNode.nextNode;
        }

    }

    public static void rollBack() {
        int backedNode = deletedStack.pop();
        Node back = nodeList[backedNode];
        if(back.prevNode != -1) {
            nodeList[back.prevNode].nextNode = backedNode;
        }
        if(back.nextNode != -1) {
            nodeList[back.nextNode].prevNode = backedNode;
        }
    }

    public static class Node {
        int prevNode;
        int nextNode;

        public Node(int prevNode, int nextNode) {
            this.prevNode = prevNode;
            this.nextNode = nextNode;
        }
    }
}