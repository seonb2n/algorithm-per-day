import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static int N;
    static Map<Integer, Integer> numberList;
    static int nodeNumber;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        numberList = new HashMap<>();

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int temp =  Integer.parseInt(br.readLine());
            if (temp == 0) {
                if(nodeNumber == 0) {
                    sb.append(0);
                    sb.append("\n");
                } else {
                    sb.append(popNumber());
                    sb.append("\n");
                }
            }

            else {
                addNumber(temp);
            }
        }

        System.out.println(sb);

    }

    //루트 노드를 제거한 후에 배열을 재정렬하는 메서드
    private static int popNumber() {
        int result = numberList.get(1);
        numberList.replace(1, 0);
        swap(1, nodeNumber);
        nodeNumber--;
        if(nodeNumber > 1) {
            int cursor = 1;
            //자신의 자식 노드와 값을 비교해가며, 자식이 자신보다 크면 위치를 바꾼다.
            while (true) {
                //왼쪽 자식과 오른쪽 자식을 비교해서 더 큰 녀석과 자리를 바꿔야 한다.
                if(numberList.containsKey(cursor * 2) && numberList.get(cursor * 2) > numberList.get(cursor)) {
                    if(numberList.containsKey(cursor * 2 +1) && numberList.get(cursor * 2 + 1) > numberList.get(cursor * 2)) {
                        swap(cursor * 2 + 1, cursor);
                        cursor = cursor * 2 + 1;
                    } else {
                        swap(cursor * 2, cursor);
                        cursor = cursor * 2;
                    }
                }
                else if(numberList.containsKey(cursor * 2 + 1) && numberList.get(cursor * 2 + 1) > numberList.get(cursor)) {
                    swap(cursor * 2 + 1, cursor);
                    cursor = cursor * 2 + 1;
                }
                else {
                    break;
                }
            }
        }

        return result;
    }

    //주어진 숫자를 numberList 를 최대 힙의 형태로 정렬해서 추가하는 메서드
    public static void addNumber(int number) {
        if(nodeNumber == 0) {
            nodeNumber = 1;
        } else {
            nodeNumber++;
        }
        numberList.put(nodeNumber, number);

        //부모 노드와 비교해서 자신이 부모보다 크면 부모 노드와 swap
        //이 과정을 자신이 루트 노드에 다다를 때까지 반복
        int cursor = nodeNumber;
        while (cursor != 1) {
            if(numberList.get(cursor / 2) < numberList.get(cursor)) {
                swap(cursor / 2, cursor);
                cursor = cursor / 2;
            } else {
                break;
            }
        }
    }

    public static void swap(int a, int b) {
        int temp = numberList.get(a);
        numberList.replace(a, numberList.get(b));
        numberList.replace(b, temp);
    }

}
