/**
 * 이진 트리의 깊이를 구하는 재귀함수입니다.
 */
public class BinaryTreeDepthAlgorithm {

    public static void main(String[] args) {
        BinaryTree.insertNode(3);
        BinaryTree.insertNode(4);
        BinaryTree.insertNode(5);
        BinaryTree.insertNode(1);
        BinaryTree.insertNode(2);
        BinaryTree.insertNode(7);

        System.out.println(BinaryTree.findNode(2, BinaryTree.root));
        System.out.println(BinaryTree.findNode(10, BinaryTree.root));
        System.out.println(BinaryTree.getDepth(0, BinaryTree.root));
        System.out.println(BinaryTree.getSize(BinaryTree.root));
    }

    public static class BinaryTree {
        static Node root = null;

        public static void insertNode(int element) {
            if (root == null) {
                root = new Node(element);
            } else {
                Node head = root;
                Node currentNode;

                while (true) {
                    currentNode = head;

                    // 헤드가 자신보다 크므로 왼쪽 탐색
                    if (head.value > element) {
                        head = head.leftNode;

                        //자식 노드가 비어있으면 자신이 그 자리에 들어가면 된다.
                        if (head == null) {
                            currentNode.setLeftNode(new Node(element));
                            break;
                        }
                    // 자신이 헤드보다 크므로 오른쪽 탐색
                    } else {
                        head = head.rightNode;

                        if (head == null) {
                            currentNode.setRightNode(new Node(element));
                            break;
                        }
                    }
                }
            }
        }

        // 찾는 노드가 존재하는지 탐색
        public static boolean findNode(int value, Node nowNode) {
            if (nowNode == null) {
                return false;
            }

            if (nowNode.value == value) {
                return true;
            }

            // 둘 중 하나라도 true 면 된다.
            return findNode(value, nowNode.leftNode) || findNode(value, nowNode.rightNode);
        }

        // 전체 tree depth 구하기
        public static int getDepth(int nowDepth, Node nowNode) {
            if (nowNode == null) {
                return nowDepth;
            }
            nowDepth += 1;
            return Math.max(getDepth(nowDepth, nowNode.leftNode), getDepth(nowDepth, nowNode.rightNode));
        }

        // 전체 tree 내부 Node 개수 구하기
        public static int getSize(Node nowNode) {
            if (nowNode == null) {
                return 0;
            }
            return getSize(nowNode.leftNode) + getSize(nowNode.rightNode) + 1;
        }

    }

    public static class Node {
        int value;
        Node leftNode;
        Node rightNode;

        public Node(int value) {
            this.value = value;
            leftNode = null;
            rightNode = null;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }
    }

}
