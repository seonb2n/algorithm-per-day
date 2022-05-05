import java.util.*;

class Solution {
    static int lockSize;
    static int keySize;
    static List<int[]> zeroPosition;

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

        solution(key, lock);
    }

    public static boolean solution(int[][] key, int[][] lock) {
        lockSize = lock.length;
        keySize = key.length;
        zeroPosition = new ArrayList<>();

        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                if(lock[i][j] == 0) {
                    int[] zero = {i+keySize-1, j+keySize-1};
                    zeroPosition.add(zero);
                }
            }
        }

        int zeroNumber = zeroPosition.size();

        //lock 의 크기를 확장
        int newLockSize = 2 * keySize - 2 + lockSize;
        int[][] newLock = new int[newLockSize][newLockSize];

        for (int i = 0; i < newLockSize; i++) {
            Arrays.fill(newLock[i], -1);
        }

        int m = 0;
        int n = 0;
        for (int i = keySize-1; i <= keySize-1+lockSize-1; i++) {
            n = 0;
            for (int j = keySize-1; j <= keySize-1+lockSize-1; j++) {
                newLock[i][j] = lock[m][n];
                n++;
            }
            m++;
        }

        for (int i = 0; i < 4; i++) {
            if(fullMatch(newLock, key)) {
                return true;
            }
            key = rotate(key);
        }

        return false;
    }

    static int[][] rotate(int[][] arr) {
        int[][] temp = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                temp[i][j] = arr[arr.length-j-1][i];
            }
        }
        return temp;
    }

    static boolean fullMatch(int[][] lock, int[][] key) {
        for (int i = 0; i <= lock.length - key.length; i++) {
            for (int j = 0; j <= lock.length - key.length; j++) {
                if(isMatch(lock, key, j, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean isMatch(int[][] lock, int[][] key, int startX, int startY) {
        //모든 0 은 채워지되, 1끼리는 겹치면 안된다.
        int zeroCount = 0;
        int[][] temp = new int[lock.length][lock.length];
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                if(lock[i + startY][j + startX] == key[i][j]) {
                    return false;
                }
                //내부에 있는 모든 홈을 채웠다면
                if(lock[i+startY][j+startX] == 0 ) {
                    zeroCount++;
                }
            }
        }
        if(zeroCount == zeroPosition.size()) {
            return true;
        }
        return false;
    }
}