import java.util.*;

class Solution {

    static int maxSheepNumber = 0;
    static Node[] nodeList;

    public static int solution(int[] info, int[][] edges) {

        nodeList = new Node[info.length];
        for (int i = 0; i < info.length; i++) {
            nodeList[i] = new Node(i);
            if(info[i] == 0) {
                nodeList[i].isSheep = true;
            }
        }

        for (int[] edge : edges) {
            nodeList[edge[0]].downNodeId.add(edge[1]);
        }

        ArrayList<Integer> tempArr = new ArrayList<>();
        tempArr.add(0);
        BFS(0, 0, 0, tempArr);
        return maxSheepNumber;
    }

    //노드 방문 리스트에 있는 노드를 탐색해 나가며 sheep 의 개수를 새는 함수
    public static void BFS(int nowSheep, int nowWolf, int visitNode, ArrayList<Integer> nodeArr) {
        Node now_Node = nodeList[nodeArr.get(visitNode)];
        ArrayList<Integer> tempArr = new ArrayList<>();
        if(now_Node.isSheep) {
            nowSheep++;
            nodeArr.remove(visitNode);
            tempArr.addAll(now_Node.downNodeId);
            tempArr.addAll(nodeArr);
        }
        else if(nowSheep > nowWolf + 1) {
            nowWolf++;
            nodeArr.remove(visitNode);
            tempArr.addAll(now_Node.downNodeId);
            tempArr.addAll(nodeArr);
        }
        else {
            maxSheepNumber = Math.max(maxSheepNumber, nowSheep);
            return;
        }

        if(tempArr.isEmpty()) {
            maxSheepNumber = Math.max(maxSheepNumber, nowSheep);
            return;
        }

        int i = 0;
        int tempSize = tempArr.size();
        while (!tempArr.isEmpty()) {
            int tempI = Math.min(i, tempArr.size()-1);
            BFS(nowSheep, nowWolf, tempI, tempArr);
            i++;
            if(i >= tempSize) {
                break;
            }
        }

//        for (int i = 0; i < tempArr.size(); i++) {
//            BFS(nowSheep, nowWolf, i, tempArr);
//        }
    }
    
    public static class Node {
        int id;
        boolean isSheep;
        boolean isVisited;
        List<Integer> downNodeId;

        public Node(int id) {
            this.id = id;
            isSheep = false;
            isVisited = false;
            downNodeId = new ArrayList<>();
        }

    }
}