import java.util.*;

class Solution {
    static int INF = Integer.MAX_VALUE - 1;
    //다익스트라에 사용할 배열
    static int[][] dist;
    static List<Vertex>[] vertexArr;
    static List<Vertex>[] rVertexArr;
    static Map<Integer, Integer> trapMap;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        vertexArr = new ArrayList[n+1];
        rVertexArr = new ArrayList[n+1];
        for (int i = 0; i < n + 1; i++) {
            vertexArr[i] = new ArrayList<Vertex>();
            rVertexArr[i] = new ArrayList<Vertex>();
        }

        //trap 의 각각의 좌표를 비트화해준다.
        trapMap = new HashMap<>();
        for (int i = 0; i < traps.length; i++) {
            trapMap.put(traps[i], 1<<(i+1));
        }

        for (int i = 0; i < roads.length; i++) {
            int startPoint = roads[i][0];
            int endPoint = roads[i][1];
            int cost = roads[i][2];

            vertexArr[startPoint].add(new Vertex(endPoint, cost, 0));
            rVertexArr[endPoint].add(new Vertex(startPoint, cost, 0));
        }
        //시작지점에서 n 개의 지점으로 가는 최소 비용을 구한다.
        //각각 트랩의 상태가 j 와 같을 때
        dist = new int[n+1][1<<trapMap.size()+1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dist[i], INF);
        }

        dijkstra(start, end);

        int answer = INF;
        for (int a : dist[end]) {
            answer = Math.min(answer, a);
        }
        return answer;
    }

    //start 에서 end 까지 도달할 때, 필요한 cost 의 최솟값을 구하는 다익스트라 알고리즘
    private static void dijkstra(int start, int end) {
        //cost 가 작은 간선부터 살펴볼 것이므로 pq 를 사용한다.
        Queue<Vertex> pq = new PriorityQueue<>();
        dist[start][0] = 0;

        //현재  출발 상태를 넣어준다.
        pq.add(new Vertex(start, 0, 0));

        while(!pq.isEmpty()) {
            Vertex vertex = pq.poll();
            int nextNode = vertex.endPoint;
            //지금까지 소모된 비용
            int cost = vertex.cost;
            //지금까지 각 트랩의 활성화 상태
            int status = vertex.status;

            if(nextNode == end) return;

            //트랩의 상태에 따라 현재 위치에서 정방향을 탈지 역방향을 탈지가 달라진다.
            int f1 = 0;
            //다음 지점이 트랩일 때,
            if(trapMap.containsKey(nextNode)) {
                //지금 트랩의 상태와, 가고자 하는 트랩의 상태를 체크한다.
                if((status & trapMap.get(nextNode)) != 0) {
                    //0이 아니라는 뜻은 하나만 1이므로
                    //역방향으로 처리해야 한다.
                    f1 = 1;
                }
            }

            int canForward = f1;
            //정방향 중에 활성화된 간선을 처리한다.
            for(Vertex next : vertexArr[nextNode]) {
                canForward = f1;
                int nextStatus = status;
                //다음으로 가고자 하는 지점이 트랩일 때, 상태를 업데이트해준다.
                if(trapMap.containsKey(next.endPoint)) {
                    //가고자 하는 지점을 방문하면 트랩이 활성화된다.
                    if((status & trapMap.get(next.endPoint)) != 0) {
                        canForward ^= 1;
                    }
                    nextStatus ^= trapMap.get(next.endPoint);
                }
                //정방향으로 처리해준다.
                if(canForward == 0) {
                    if(dist[next.endPoint][status] > cost + next.cost) {
                        dist[next.endPoint][status] = cost + next.cost;
                        pq.add(new Vertex(next.endPoint, dist[next.endPoint][status], nextStatus));
                    }
                }
            }
            //역방향 중에 활성화된 간선을 처리한다.
            for(Vertex next : rVertexArr[nextNode]) {
                canForward = f1;
                int nextStatus = status;
                if(trapMap.containsKey(next.endPoint)) {
                    if((status & trapMap.get(next.endPoint)) != 0) {
                        canForward ^= 1;
                    }
                    nextStatus ^= trapMap.get(next.endPoint);
                }
                if (canForward == 1) {
                    if(dist[next.endPoint][status] > cost + next.cost) {
                        dist[next.endPoint][status] = cost + next.cost;
                        pq.add(new Vertex(next.endPoint, dist[next.endPoint][status], nextStatus));
                    }
                }
            }
        }
    }

    public static class Vertex implements Comparable<Vertex>{
        int endPoint;
        int cost;
        int status;

        public Vertex(int endPoint, int cost, int status) {
            this.endPoint = endPoint;
            this.cost = cost;
            this.status = status;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.cost - o.cost;
        }
    }

}