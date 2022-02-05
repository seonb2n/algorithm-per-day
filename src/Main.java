import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static long max;
    static long[] squareHeight;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        while (true) {
            String temp = br.readLine();
            st = new StringTokenizer(temp, " ");
            int squareNumber = Integer.parseInt(st.nextToken());
            if(squareNumber == 0) {
                break;
            }
            squareHeight = new long[squareNumber];
            for (int i = 0; i < squareNumber; i++) {
                squareHeight[i] = Long.parseLong(st.nextToken());
            }

            sb.append(getMaxArea(0, squareNumber-1));
            sb.append("\n");
        }

        System.out.println(sb);
    }

    //startPoint 부터 endPoint 에 있는 사각형 중에서 최대의 넓이를 반환하는 함수
    private static long getMaxArea(int startPoint, int endPoint) {
        // 1. 만약 사각형의 넓이가 1이면 자기 자신에 대한 높이를 반환한다.
        if(startPoint == endPoint) {
            return squareHeight[startPoint];
        } else {
            // 2. 만약 사각형의 넓이가 2 이상이면, 가운데를 기준으로 왼쪽과 오른쪽을 나눈다.
            int  width = (endPoint - startPoint) / 2;
            long leftSize = getMaxArea(startPoint, startPoint + width);
            long rightSize = getMaxArea(startPoint + width+1, endPoint);

            // 3. 왼쪽의 크기와 오른쪽의 크기를 비교해서 최댓값을 찾는다.
            max = Math.max(leftSize, rightSize);
            // 4. 비교해서 찾은 최댓값과 가운데에 존재하는 사각형의 최댓값을 비교한다.
            long middleSize = getMiddleMaxArea(startPoint + width, startPoint, endPoint);
            max = Math.max(max, middleSize);

            return max;
        }
    }

    private static long getMiddleMaxArea(int middlePoint, int startPoint, int endPoint) {
        //가운데 사각형을 포함하는 최대 넓이를 구하는 함수
        long maxSize = squareHeight[middlePoint];
        long nowHeight = squareHeight[middlePoint];
        int leftCursor = middlePoint;
        int rightCursor = middlePoint;

        // 이 과정을 양족이 startPont 와 endPoint 에 닿을 때까지 반복
        while(startPoint < leftCursor && rightCursor < endPoint) {

            // 1. 왼쪽과 오른쪽을 확인하고, 아직 startPoint 와 endPoint 에 도달하지 않았으면 한 칸씩 이동해서 탐색해본다.
            int left = leftCursor - 1;
            int right = rightCursor + 1;

            // 2. 왼쪽이 오른쪽 보다 더 높은 경우
            if(squareHeight[left] > squareHeight[right]) {
                //최대 넓이는 왼쪽으로 한칸 확장한 것과 지금의 최대 넓이를 비교한 것이다.
                nowHeight = Math.min(squareHeight[left], nowHeight);
                leftCursor--;
            }

            // 3.오른쪽이 왼쪽보다 더 높은 경우
            else  {
                nowHeight = Math.min(squareHeight[right], nowHeight);
                rightCursor++;
            }
            maxSize = Math.max(maxSize, nowHeight * (rightCursor - leftCursor + 1));
        }

        //왼쪽이나 오른쪽에 이미 도달해서 종료됐다면 다른 쪽을 마저 탐색한다
        while (rightCursor < endPoint) {
            rightCursor++;
            nowHeight = Math.min(nowHeight, squareHeight[rightCursor]);
            maxSize = Math.max(maxSize, nowHeight * (rightCursor - leftCursor + 1));
        }

        while (startPoint < leftCursor) {
            leftCursor--;
            nowHeight = Math.min(nowHeight, squareHeight[leftCursor]);
            maxSize = Math.max(maxSize, nowHeight * (rightCursor - leftCursor + 1));
        }

        return maxSize;
    }
}
