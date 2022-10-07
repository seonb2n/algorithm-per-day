import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class HeapSortAlgorithm {

    static List<Integer> binaryTreeList = new LinkedList<Integer>();

    public static void main(String[] args) {
        PerfectBinaryTree.insertNode(11);
        PerfectBinaryTree.insertNode(5);
        PerfectBinaryTree.insertNode(8);
        PerfectBinaryTree.insertNode(3);
        PerfectBinaryTree.insertNode(4);
        PerfectBinaryTree.insertNode(1);
        PerfectBinaryTree.insertNode(10);
        System.out.println(binaryTreeList.toString());
    }

    public static class PerfectBinaryTree {

        // 각 자식 노드는 부모 노드보다 작아야만 함.
        static void insertNode(int value) {
            if (binaryTreeList.size() == 0) {
                binaryTreeList.add(value);
            } else {
                Queue<Integer> indexQueue = new LinkedList<Integer>();
                indexQueue.add(0);
                while (true) {
                    int nowIndex = indexQueue.poll();
                    int leftIndex = nowIndex * 2 + 1;
                    int rightIndex = nowIndex * 2 + 2;

                    if ((binaryTreeList.size() - 1) < leftIndex) {
                        binaryTreeList.add(value);
                        break;
                    }
                    else if ((binaryTreeList.size() - 1) < rightIndex) {
                        binaryTreeList.add(value);
                        break;
                    }
                    else {
                        indexQueue.add(leftIndex);
                        indexQueue.add(rightIndex);
                    }
                }
            }
            heapify(binaryTreeList.size()-1);
        }

        static void heapify(int nowIndex) {
            if (nowIndex == 0) {
                return;
            }

            int parentIndex = (nowIndex - 1) / 2;
            if (binaryTreeList.get(parentIndex) < binaryTreeList.get(nowIndex)) {
                swap(parentIndex, nowIndex);
                heapify(parentIndex);
            }
        }

        public static void swap(int a, int b) {
            int tmp = binaryTreeList.get(a);
            binaryTreeList.set(a, binaryTreeList.get(b));
            binaryTreeList.set(b, tmp);
        }
    }
}