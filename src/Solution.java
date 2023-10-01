import java.util.*;

class Solution {

    /**
     * 정렬된 정수 list 에서 중간값을 찾아서 반환해준다
     * 중간값이 존재하지 않으면 두 중간값의 평균을 반환한다.
     */
    class MedianFinder {

       PriorityQueue<Integer> lessMedian;
       PriorityQueue<Integer> moreMedian;
       int size = 0;

        public MedianFinder() {
            this.lessMedian = new PriorityQueue<Integer>(Comparator.reverseOrder());
            this.moreMedian = new PriorityQueue<Integer>();
        }

        public void addNum(int num) {
            // 첫번째인 경우에는 작은쪽에 넣는다.
            if (size == 0) {
                lessMedian.add(num);
                size++;
                return;
            }
            // 현재 개수가 홀수개인 경우에 큰 쪽에 수를 추가해줘야 한다.
            else if (size % 2 != 0) {
                if (lessMedian.peek() <= num) {
                    moreMedian.add(num);
                }
                else {
                    moreMedian.add(lessMedian.poll());
                    lessMedian.add(num);
                }
            }
            // 현재 개수가 짝수개면 작은 쪽에 수를 추가해줘야 한다.
            else {
                if (num <= moreMedian.peek()) {
                    lessMedian.add(num);
                }
                else {
                    lessMedian.add(moreMedian.poll());
                    moreMedian.add(num);
                }
            }
        }

        public double findMedian() {
            if (size % 2 != 0) {
                return lessMedian.peek();
            }
            return (lessMedian.peek() + moreMedian.peek()) / 2f;
        }
    }

}