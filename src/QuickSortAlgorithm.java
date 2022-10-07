import java.util.Arrays;

public class QuickSortAlgorithm {

    public static void main(String[] args) {
        int[] a = new int[] {38, 27, 43, 3, 9, 82, 10};
        leftPivotSort(a, 0, a.length-1);
        System.out.println(Arrays.toString(a));
    }

    // 왼쪽 피벗 선택 정렬 발식
    private static void leftPivotSort(int[] a, int low, int high) {

        // low 가 high 보다 크거나 같다면 정렬할 원소가 1개 이하므로 그대로 return
        if (low >= high) {
            return;
        }

        // pivot 을 기준으로 배열을 왼쪽, 오른쪽 정렬을 해준다.
        // pivot 보다 작은 수는 대략 앞, 큰 수는 대략 뒤
        int pivot = partition(a, low, high);

        // 이후 pivot 을 기준으로 분할 정복
        leftPivotSort(a, low, pivot-1);
        leftPivotSort(a, pivot+1, high);
    }

    // pivot 을 기준으로 파티션을 나눈다.
    private static int partition(int[] a, int left, int right) {
        int low = left;
        int high =right;

        // 피봇은 시작 지점이다.
        int pivot = a[left];

        while (low < high) {

            // 오른쪽 수 중에 pivot 보다 큰 수를 찾는다.
            while (a[high] > pivot && low < high) {
                high--;
            }

            // 왼쪽 수 중에 pivot 보다 작거나 같은 수를 찾는다.
            while (a[low] <= pivot && low < high) {
                low++;
            }

            swap(a, low, high);
        }

        // 마지막으로 pivot 과 low 가 가리키는 원소를 바꾼다.
        swap(a, left, low);

        // low 가 피벗 위치이므로 반환한다.
        return low;
    }

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
